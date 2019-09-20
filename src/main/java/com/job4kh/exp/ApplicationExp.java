package com.job4kh.exp;

import com.job4kh.crawler.WebPage;
import com.job4kh.tokenizer.*;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;
import com.job4kh.tokenizer.task.SegmentTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationExp
{

    static final int SEGMENT_TYPE_FORWARD = 1;
    static final int SGMENT_TYPE_BACKWARD =2;
    static KhmerWordTrainData khmerWordTrainData;
    static  SegmentWebCollection segmentWebCollection = new SegmentWebCollection();
    static WordTokenizer wordTokenizer;
    private static KhmerWordTrainData getWordTrainData() throws Exception
    {
        //String path = System.getProperty("user.dir") + "/data/wordList.plc";
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        return new KhmerWordTrainData(dataLoader);
    }

    private static int getType()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your algorithm");
        System.out.println("1. Forward Segmentation ");
        System.out.println("2. Backward Segmentation ");
        int type = scanner.nextInt();
        return type;
    }

    private static void segmentDb() throws InterruptedException
    {
        List<WebPage> items = MongoDbConnection.getInstance().ListAllWebPage();
        MongoDbConnection.getInstance().getDatabase().getCollection( MongoDbConnection.SEGMENT_COLLECTION ).drop();
        List<SegmentTask>tasks  = new ArrayList<>();

        String typeName = "backward";

        for (WebPage item: items)
        {
            tasks.add( new SegmentTask( wordTokenizer, item.getText(), item.getUrl()));
        }
        segmentWebCollection.execute( tasks);
        MongoDbConnection.getInstance().putSegmentResult(tasks,typeName);



    }

    public static void main(String[] args) throws Exception {
        System.out.println("Initialize configuration");
        khmerWordTrainData = getWordTrainData();
        Scanner scanner = new Scanner(System.in);
        wordTokenizer = new LongestMatchingTokenizer(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
        while (true)
        {
            System.out.println("************************************");
            System.out.println("Select your option :");
            System.out.println("1. Segment all data in mogodb");
            System.out.println("2. Crawl and segment");
            System.out.println("0. Exit ");
            System.out.println("************************************");
            int choice = scanner.nextInt();
            switch (choice) {
                case  0 :
                    segmentWebCollection.stop();
                    return;
                case 1:
                    segmentDb();
                    break;
                case 2:
                    break;
            }
        }


    }
}
