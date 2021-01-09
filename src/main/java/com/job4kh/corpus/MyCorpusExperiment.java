package com.job4kh.corpus;

import com.job4kh.bigram.WordBiGramData;

import java.util.List;

public class MyCorpusExperiment {
    public static void main(String [] args){
        System.out.println("starting...");
        WordTable wordTable = TextTrainingBuilder.buildWordTable();
        System.out.println("done loading");
        WordBiGramData wordBiGramData = new WordBiGramData( wordTable);
        List<String> result = wordBiGramData.segment("សួស្តីសូមស្វាគមន៍មកកាន់គេហទំព័ររបស់ខ្ញុំ", "_", "_");
        for(String r:result){
            System.out.print("\t"+r);
        }
        System.out.println("\ndone");

    }
}
