package com.job4kh.corpus;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordAttribute {
    private int uniCount;
    private int totalWord;
    private Map<String,Integer> bigramCount;
    public WordAttribute(){
        bigramCount = new LinkedHashMap<>();
    }

    public void setTotalWord(int totalWord) {
        this.totalWord = totalWord;
    }

    public int getTotalWord() {
        return totalWord;
    }

    public int getUniCount() {
        return uniCount;
    }

    public Map<String, Integer> getBigramCount() {
        return bigramCount;
    }
    public void incrementUnicord(){
        this.uniCount++;
    }
    public void incrementUnicord(int c){
        this.uniCount += c;
    }

    public void bigram(String w, int count){
        if( bigramCount.containsKey(w)) {
            bigramCount.put( w, bigramCount.get(w) + count);
        }
        else {
            bigramCount.put(w, count);
        }
    }
}
