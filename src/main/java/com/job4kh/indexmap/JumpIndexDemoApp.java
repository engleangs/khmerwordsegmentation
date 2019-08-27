package com.job4kh.indexmap;

import com.job4kh.crawler.WebItem;
import com.job4kh.exp.MongoDbConnection;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;

import java.util.Arrays;
import java.util.List;

public class JumpIndexDemoApp {
    //test data
    static String[] arrWords = {
            //"កក" , "កុក","កី","ក្ត","ក្តៅ","ក្តៅសាច់","ក្បាល","ក្រម៉ា","ក្របី","ក្រពើ","ក្រពា","ក្រពះ",
            "សារព័ត៌មាន "};
    List<String> words = Arrays.asList( arrWords);
    //end test data
    private static KhmerWordTrainData getWordTrainData() throws Exception
    {
        //String path = System.getProperty("user.dir") + "/data/wordList.plc";
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        return new KhmerWordTrainData(dataLoader);
    }
    static String[] samples = {
        "សួស្តីពិភពលោក",
        "កម្ពុជាជាប្រទេសអច្ឆរិយ",
        "សាលារៀនជាប្រភពមួយនៃចំណេះដឹង"
    };
    static void testClean(){
        CleanTextUtil cleanTextUtil = new CleanTextUtil(KhmerCharacterSet.SPACE_SET,KhmerCharacterSet.DELIMETER, KhmerCharacterSet.DIGIT_SET);
        for(String word:arrWords) {
            List<CleanTextUtil.CleanText> result = cleanTextUtil.cleanText( word);
            for (CleanTextUtil.CleanText cleanText : result) {
                System.out.println(cleanText.getPhrase());
            }

        }
        System.exit(0);
    }

    public static void main(String[] args)
    {

        try {

            final KhmerWordTrainData khmerWordTrainData = getWordTrainData();
            AllIndexCollection collection = IndexUtil.allIndexCollection( khmerWordTrainData.getWordSet());
            final ForwardSegmentWord forwardSegmentWord = new ForwardSegmentWord( collection.getIndexCollection(), collection.getNumericComputedIndexCollection(), khmerWordTrainData.getWordSet());
            List<WebItem> webItems = MongoDbConnection.getInstance().ListAllWebItems();
            int totalError = 0;
            int totalSegment = 0;
            int totalNonSegment = 0;
            CleanTextUtil cleanTextUtil = new CleanTextUtil(KhmerCharacterSet.SPACE_SET,KhmerCharacterSet.DELIMETER, KhmerCharacterSet.DIGIT_SET);
            for( WebItem item : webItems)
            {

                List<CleanTextUtil.CleanText>cleanTexts = cleanTextUtil.cleanText( item.getRawText());
                System.out.println("Website URI :"+item.getUrl());
                for(CleanTextUtil.CleanText cleanText:cleanTexts)
                {
                    if( cleanText.getType() == CleanTextUtil.TextType.Number
                            || cleanText.getType() == CleanTextUtil.TextType.English
                            || khmerWordTrainData.getWordSet().contains( cleanText.getPhrase()) ) {
                        totalNonSegment++;
                        System.out.println("no need segment : "+ cleanText.getPhrase());
                        continue;
                    }

                    SegmentResult segmentResult  = forwardSegmentWord.doSegment( cleanText.getPhrase());
                    List<String> words = segmentResult.getWords();
                    StringBuilder resultBuilder = new StringBuilder( "To Segment for : ").append( cleanText.getPhrase() );
                    resultBuilder.append("\n================================================");
                    words.forEach(it->{
                        resultBuilder.append( it).append(" ,");
                    });
                    resultBuilder.append("\n=================================================");
                    System.out.println(resultBuilder.toString());
                    totalError+= segmentResult.getTotalError();
                    totalSegment += segmentResult.getTotalSegment();
                    if( segmentResult.getTotalError() > 0) {
                        System.out.println("=========To check ==================== Error : "+segmentResult.getTotalError() +", "+item.getUrl());
                    }

                }

            }
            System.out.println("Total error :"+totalError);
            System.out.println("Total segment : "+totalSegment);
            System.out.println("Total non-segmented :"+totalNonSegment);
            System.out.println("Rate : "+(100*totalError/totalSegment) +" %");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
