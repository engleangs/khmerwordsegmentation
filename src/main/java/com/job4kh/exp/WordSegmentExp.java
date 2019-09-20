package com.job4kh.exp;

import com.job4kh.tokenizer.*;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;

import java.util.Scanner;

public class WordSegmentExp {
    private static Segmentation backwardSegmentation;
    private static Segmentation forwardSegmentation;

    private static KhmerWordTrainData getWordTrainData() throws Exception
    {
        //String path = System.getProperty("user.dir") + "/data/wordList.plc";
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        return new KhmerWordTrainData(dataLoader);
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Initialize data ...............");
        long startLoadData  = System.currentTimeMillis();
        final KhmerWordTrainData khmerWordTrainData = getWordTrainData();
        System.out.println("finish initialization "+(((double)System.currentTimeMillis()-startLoadData)/1000));
        backwardSegmentation = new BackwardMatchingSegment(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
        forwardSegmentation = new ForwardMatchingSegment(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
        LongestMatchingTokenizer longestMatchingTokenizer = new LongestMatchingTokenizer(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
        System.out.println("Input your search in khmer : ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        SegmentResult backwardResult = backwardSegmentation.doSegment( text);
        SegmentResult forwardResult = forwardSegmentation.doSegment( text );
//        if( forwardResult.getTotalSegment() == 0 && text.length() > 0)
//        {
//            String newText = text.substring(0,text.length() /2);
//            List<String>items  = new ArrayList<>();
//            items.add( newText);
//            String newText2 = text.substring(text.length()/2);
//            items.add( newText2);
//            for(String item:items)
//            {
//                if( item.length() > 0)
//                {
//                    SegmentResult newResult = forwardSegmentation.doSegment( item);
//                    System.out.println("New result : "+newResult.getTotalError());
//                    newResult.getWords().forEach(it-> System.out.println("\t"+it));
//                    System.out.println("************************************************************");
//                    newResult.getErrors().forEach(it->System.out.println("\t"+it));
//                }
//
//            }
//
//
//        }
        SegmentResult result  = longestMatchingTokenizer.tokenize(text);
        System.out.println("***********************************************************");
        System.out.println("Longest matching result : ");
        result.getWords().forEach( it->System.out.print(it+"\t"));
        System.out.println(" Error : "+result.getTotalError());
        System.out.println("************************************************************");

        System.out.println(" Backward result : \n");
        backwardResult.getWords().forEach(it-> System.out.print(it+"\t"));
        System.out.println(" \t\t Take  :"+((double)backwardResult.getTakeTime()/1000)+" second");
        System.out.println(" \t\t Error :"+backwardResult.getTotalError());
        System.out.println("Unknown words :");
        backwardResult.getErrors().forEach(it-> System.out.print(it+"\t"));

        System.out.println("************************************************************");

        System.out.println("Forward result : \n");
        forwardResult.getWords().forEach(it-> System.out.println( it+"\t"));
        System.out.println("\t\t Take : "+((double)forwardResult.getTakeTime()/1000)+" second");
        System.out.println("\t\t Error: "+ forwardResult.getTotalError());
        System.out.println("\t\t Unknown words : ");
        forwardResult.getErrors().forEach(it-> System.out.print(it+"\t"));
        System.out.println("************************************************************");










    }
}
