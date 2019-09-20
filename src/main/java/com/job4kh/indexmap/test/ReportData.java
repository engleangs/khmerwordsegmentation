package com.job4kh.indexmap.test;

import com.google.gson.Gson;
import com.job4kh.crawler.SiteContent;
import com.job4kh.exp.MongoDbConnection;
import com.job4kh.exp.SiteDaoHelper;
import com.job4kh.indexmap.*;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ReportData {
    static  ForwardSegmentWord forwardSegmentWord;
    static CleanTextUtil cleanTextUtil;
    static BackwardSegmentWord backwardSegmentWord ;
    static KhmerWordTrainData khmerWordTrainData;
    static HashMap<Integer,TestData> result = new HashMap<>();
    static Gson gson = new Gson();
    private static KhmerWordTrainData getWordTrainData() throws Exception
    {
        //String path = System.getProperty("user.dir") + "/data/wordList.plc";
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        return new KhmerWordTrainData(dataLoader);
    }

    static TestData check(SiteContent siteContent, WordSegmentation wordSegmentation,String testName)
    {
        long startTime = System.currentTimeMillis();
        List<CleanTextUtil.CleanText>cleanTexts = cleanTextUtil.cleanText( siteContent.getContent());
        int totalSegment = 0;
        int totalError = 0;
        int total = 0;
        for(CleanTextUtil.CleanText cleanText:cleanTexts) {
            if( cleanText.getType() == CleanTextUtil.TextType.Number
                    || cleanText.getType() == CleanTextUtil.TextType.English
                    || cleanText.getType() == CleanTextUtil.TextType.Delimiter
                    || cleanText.getType() == CleanTextUtil.TextType.SpaceOrEnd
                    || khmerWordTrainData.getWordSet().contains( cleanText.getPhrase())
            ) {
                totalSegment++;
                total++;
                //System.out.println("no need segment : "+ cleanText.getPhrase());
                continue;
            }
            SegmentResult segmentResult  = wordSegmentation.doSegment( cleanText.getPhrase() );
            totalSegment += segmentResult.getTotalSegment();
            totalError += segmentResult.getTotalError();
            total  += segmentResult.getWords().size();
        }
        long duration = System.currentTimeMillis() - startTime;
        return new TestData(totalSegment,totalError,total, siteContent.getContent().length(), duration, siteContent.getUrl(), testName);
    }


    void reportTocsv(){
//        result.put( count , new TestData( totalSegment , totalError,total, totalLength, totalDuration));
//        String fileName = System.getProperty("user.dir")+"/data/report.csv";
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
//        StringBuilder reportBuilder = new StringBuilder("No webpage,Total segment,Total error , Total word, No character,Duration");
//        for(int key : result.keySet()) {
//            TestData testData = result.get( key );
//            reportBuilder.append("\n");
//            reportBuilder.append(key).append(",")
//                    .append( testData.getTotalSegment())
//                    .append(",")
//                    .append(testData.getTotalError())
//                    .append(",")
//                    .append(testData.getTotal())
//                    .append(",")
//                    .append(testData.getTotalLength())
//                    .append(",")
//                    .append(testData.getDuration())
//            ;
//
//        }
//        bufferedWriter.write(  reportBuilder.toString() );
//        bufferedWriter.flush();
    }

    static  boolean running = true;
    static ConcurrentLinkedQueue<TestData>queue = new ConcurrentLinkedQueue<>();
    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    public static void addDb() {
        List<TestData> items = new ArrayList<>();
        TestData testData;
        while ((testData = queue.poll())!= null) {
            items.add( testData);
        }
        if( items.size() > 0) {
            SiteDaoHelper.putTestData( items);
            System.out.println("finish for db : "+items.size());
        }

    }
    public  static void checkForQueue() throws InterruptedException {
        while (running) {
            if( queue.size() > 0) {
                addDb();

            }
            Thread.sleep(100);
        }
    }

    public static void writeData(List<TestData>result,String file) throws IOException {
                String fileName = System.getProperty("user.dir")+"/data/"+file;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
        StringBuilder reportBuilder = new StringBuilder();
        for(TestData test: result) {
            String json = gson.toJson( test);
            reportBuilder.append(json).append("\n");
        }
        bufferedWriter.write(  reportBuilder.toString() );
        bufferedWriter.flush();
    }
    public static void simpleTest(String testName, WordSegmentation wordSegmentation , String basePath,int start,int end,String outputFile) throws IOException {

        List<SiteContent>allData  = new ArrayList<>();
        for(int i=start;i<end;i++) {
            String folderName = "site_" + i;
            List<SiteContent> siteContents = getFromFolder(basePath, folderName);
            allData.addAll( siteContents);
        }
        List<TestData> result = new ArrayList<>();
        for(SiteContent siteContent:allData) {
            TestData testData =  check( siteContent, wordSegmentation , testName);
            result.add( testData);
        }
        writeData( result, outputFile);




    }

    public static void testByFolderName(String testName, WordSegmentation wordSegmentation , String basePath,int start,int end) throws IOException {

        System.out.println("begin to test for "+testName +" start : "+start +" , "+end);
        for(int i=start;i<end;i++) {
            String folderName = "site_"+i;
            List<SiteContent> siteContents = getFromFolder(basePath, folderName);
            for(SiteContent siteContent :siteContents) {
                threadPoolExecutor.execute( ()->{
                    TestData testData =  check( siteContent, wordSegmentation , testName);
                    queue.add( testData);
                });

            }
        }
    }
    static void singleTest() throws Exception {
        khmerWordTrainData = getWordTrainData();
        AllIndexCollection collection = IndexUtil.allIndexCollection( khmerWordTrainData.getWordSet());
        forwardSegmentWord= new ForwardSegmentWord( collection.getIndexCollection(), collection.getNumericComputedIndexCollection(), khmerWordTrainData.getWordSet());
        cleanTextUtil = new CleanTextUtil(KhmerCharacterSet.SPACE_SET,KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET);
        simpleTest("forward_segment", forwardSegmentWord, "/Resource/RD/Java8/research/data",0,1,"output.txt");
    }

    static void testAll() throws Exception {
        khmerWordTrainData = getWordTrainData();
        AllIndexCollection collection = IndexUtil.allIndexCollection( khmerWordTrainData.getWordSet());
        forwardSegmentWord= new ForwardSegmentWord( collection.getIndexCollection(), collection.getNumericComputedIndexCollection(), khmerWordTrainData.getWordSet());
        backwardSegmentWord = new BackwardSegmentWord( collection.getIndexCollection(), collection.getNumericComputedIndexCollection(), khmerWordTrainData.getWordSet());
        BidirectionalSegmentWord bidirectionalSegmentWord = new BidirectionalSegmentWord(  collection.getIndexCollection(), collection.getNumericComputedIndexCollection(), khmerWordTrainData.getWordSet());
        cleanTextUtil = new CleanTextUtil(KhmerCharacterSet.SPACE_SET,KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET);
        WordSegmentation[] wordSegmentations = {
                forwardSegmentWord,
//                backwardSegmentWord,
//                bidirectionalSegmentWord
        };
        String[] testNames = {
                "forward_segment",
//          "backward_segment",
//          "bidirectional_segment"
        };
        int inx = 0;
        int start = 0;
        int end = 10;
        Thread thread = new Thread(()->{
            try {
                checkForQueue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        for(WordSegmentation wordSegmentation : wordSegmentations) {
            String testName = testNames[ inx++];
            testByFolderName( testName, wordSegmentation ,"/Resource/RD/Java8/research/data", start, end);

        }
        threadPoolExecutor.shutdown();
        while ( !threadPoolExecutor.isTerminated()) {
            Thread.sleep(200);
            System.out.println("wait for thread pool to finished : "+threadPoolExecutor.getCompletedTaskCount());
        }
        addDb();
        System.out.println("finish add to db and exit");
    }



    public static void main(String[]args) throws Exception {

        singleTest();

    }

    static List<SiteContent>getFromFolder(String basePath ,String folderName) throws IOException {
        String path = basePath+"/"+folderName;
        File folder = new File( path);
        List<SiteContent>result  = new ArrayList<>();
        for(File file : folder.listFiles()) {
            String content = new String ( Files.readAllBytes( Paths.get( file.getPath()) ) );
            SiteContent siteContent = gson.fromJson( content,SiteContent.class);
            result.add( siteContent);
        }
        return result;

    }
}
