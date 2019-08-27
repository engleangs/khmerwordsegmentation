package com.job4kh.indexmap.test;

import com.job4kh.indexmap.AllIndexCollection;
import com.job4kh.indexmap.BackwardSegmentWord;
import com.job4kh.indexmap.IndexUtil;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.FileLoader;

public class TestBackward {
    static String words[] =  {
          //  "សួស្តីពិភពលោក",
          //  "សូមស្វាគមន៍មកកាន់ការចូលរួមសសសនេះគឺជាការសាកល្បង",
            "លោកដូណាល់ត្រាំ"
    };
    public static void main(String[] args) throws Exception {
        DataLoader dataLoader = new FileLoader("data/wordset.txt");
        KhmerWordTrainData khmerWordTrainData =  new KhmerWordTrainData(dataLoader);
        AllIndexCollection allIndexCollection = IndexUtil.allIndexCollection( khmerWordTrainData.getWordSet());
        final BackwardSegmentWord backwardSegmentWord = new BackwardSegmentWord( allIndexCollection.getIndexCollection(), allIndexCollection.getNumericComputedIndexCollection(),  khmerWordTrainData.getWordSet());
        for(String str:words) {
            SegmentResult result = backwardSegmentWord.doSegment( str);
            System.out.println("======== for :"+str+"============");
            System.out.println(" total segment : "+result.getTotalSegment());
            System.out.println(" total error : "+result.getTotalError());
            System.out.println("  words : ");
            result.getWords().forEach(it->{
                System.out.println("["+it+"] \t");
            });
        }

    }
}
