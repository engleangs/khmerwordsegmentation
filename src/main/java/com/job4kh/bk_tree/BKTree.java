package com.job4kh.bk_tree;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://issues.apache.org/jira/secure/attachment/12431059/BKTree.java
 *
 */

public class BKTree {
    private Node root;
    private Distance distance;
    private int time = 0;
    public BKTree( Distance distance){
        this.distance = distance;
    }
    public void  add(String word){
        if( root !=null) {
            root.add( word);
        }
        else {
            root = new Node( word);
        }
    }
    public Map<String,Double> query(String word, int threshold){
        Map<String,Double> matches = new LinkedHashMap<>();
        root.query( word , threshold, matches);
        System.out.println("time : "+time);
        return matches;
    }
    private class  Node {
        String word;
        Map<Double,Node> children;
        public Node(String word){
            this.word = word;
            children = new TreeMap<>();
        }
        public void add(String word){
            double dis = distance.getDistance( word, this.word);
            Node child = children.get( dis);
            if( child !=null) {
                child.add( word);
            }
            else {
                children.put( dis , new Node( word));
            }
        }
        public void query(String word , int threshold , Map<String, Double> collection){
            time++;
            double disAtNode = distance.getDistance( word, this.word);
            if( disAtNode <= threshold) {
                collection.put( this.word, disAtNode);
            }
            for(double score = disAtNode - threshold ; score <= disAtNode + threshold; score++) {
                if( score > 0) {
                    Node child = children.get( score);
                    if( child !=null){
                        child.query( word, threshold, collection);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BKTree bkTree = BkTreeBuilder.bkTree("data/wordset.txt",false);
        long start = System.currentTimeMillis();
        Map<String,Double> result =  bkTree.query("សួស្ត",2);
        long time = System.currentTimeMillis() - start;
        System.out.println("total : "+time+" ms");
        for(String s: result.keySet()) {
            System.out.println(" "+s +" " + result.get(s));
        }
    }

}
