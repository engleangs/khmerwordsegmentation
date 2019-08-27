package com.job4kh.indexmap.test;

import java.util.ArrayList;
import java.util.List;

public class SegmentModel {
    private int totalSegmentation;
    private int totalError;
    private List<String> words;

    public void setTotalError(int totalError) {
        this.totalError = totalError;
    }

    public void setTotalSegmentation(int totalSegmentation) {
        this.totalSegmentation = totalSegmentation;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public SegmentModel(){
        totalError =0;
        totalSegmentation = 0;
        words = new ArrayList<>();
    }
    public void incrSegment(int num){
        this.totalSegmentation += num;
    }
    public void incrError(int error ){
        this.totalError += error;
    }

    public int getTotalError() {
        return totalError;
    }

    public int getTotalSegmentation() {
        return totalSegmentation;
    }

    public List<String> getWords() {
        return words;
    }
    public void addWord(List<String> words) {
        this.words.addAll( words);
    }
    public void addWord(String word){
        this.words.add( word);
    }

    public SegmentModel(int totalSegmentation, int totalError, List<String> words) {
        this.totalSegmentation = totalSegmentation;
        this.totalError = totalError;
        this.words = words;
    }
}
