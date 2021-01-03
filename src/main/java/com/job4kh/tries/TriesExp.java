package com.job4kh.tries;

import java.io.IOException;

public class TriesExp {
    public static void main(String [] args) throws IOException {
        TriesKh triesKh = TriesBuilder.fromFile("data/wordset.txt");
        TinerySerachTree<Integer>tinerySerachTree = TinerySearchTreeBuilder.fromFile("data/wordset.txt");
        System.out.println("done loading");
        String[] test = {"ពាររបង",
                "ច្បាប់សាធារណៈ",
                "រពឹក",
                "ខត្តៈ",
                "អព្រហ្មចារ្យ",
                "យាមៈ"};
        for(String st :test){
            System.out.println(st+" exist : "+triesKh.contains(st));
        }
        System.out.println("===========================================");
        for(String st : test){
            System.out.println(st+" exist : "+tinerySerachTree.contains(st));
        }
    }
}
