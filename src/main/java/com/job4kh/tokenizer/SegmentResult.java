package com.job4kh.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class SegmentResult {
    private int totalError;
    private int totalSegment;
    private List<String>words;
    private List<String>errors;
    private long takeTime;
    private String typeName;

    public List<String> getErrors() {
        return errors;
    }

    public SegmentResult(){
        words = new ArrayList<>();
        errors = new ArrayList<>();
        totalError  =0;
        totalSegment = 0 ;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public SegmentResult(int totalError, int totalSegment, List<String> words) {
        this.totalError = totalError;
        this.totalSegment = totalSegment;
        this.words = words;
        errors = new ArrayList<>();

    }

    public int getTotalError() {
        return totalError;
    }


    public int getTotalSegment() {
        return totalSegment;
    }



    public List<String> getWords() {
        return words;
    }



    public void add(String word)
    {
        totalSegment++;
        words.add( word );
    }
    public void insertAtFirst(String word) {
        totalSegment++;
        words.add(0,word);
    }
    public void insertUnknownAtFirst(String word) {
        totalError++;
        errors.add(0 , word);
        words.add(0,word);
    }

    public void addUnknown(String word)
    {
        totalError++;
        words.add( word);
        errors.add( word);
    }

    public long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(long takeTime) {
        this.takeTime = takeTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
