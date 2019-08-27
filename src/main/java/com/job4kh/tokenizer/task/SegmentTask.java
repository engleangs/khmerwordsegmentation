package com.job4kh.tokenizer.task;

import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.Segmentation;
import com.job4kh.tokenizer.WordTokenizer;

import java.util.concurrent.CountDownLatch;

public class SegmentTask implements Runnable  {


    private WordTokenizer wordTokenizer;
    private String text;
    private SegmentResult segmentResult;
    private CountDownLatch endController;
    private String url;

    public SegmentResult getSegmentResult() {
        return segmentResult;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public void setEndController(CountDownLatch endController) {
        this.endController = endController;
    }

    public WordTokenizer getWordTokenizer() {
        return wordTokenizer;
    }

    public SegmentTask(WordTokenizer tokenizer, String text, String url)
    {
        this.wordTokenizer = tokenizer;
        this.text = text;
        this.url = url;
    }


    @Override
    public void run()
    {
        System.out.println("begin to do segmentation for :"+getUrl());
        segmentResult = wordTokenizer.tokenize( text);
        System.out.println("segment result : "+segmentResult.getWords() +" , error : "+segmentResult.getErrors().size());
        segmentResult.getWords().forEach(it->{
            System.out.print( it+"\t");
        });
        if( segmentResult.getErrors().size() > 0)
        {
            System.out.println("----- Error : --------------");
            segmentResult.getErrors().forEach(it->{
                System.out.print( it+"\t");
            });
        }

        System.out.println("*******************************************************");
        endController.countDown();
    }
}
