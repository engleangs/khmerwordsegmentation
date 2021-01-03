package com.job4kh.tries.segment;

import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.EnglishLanguage;
import com.job4kh.tries.SymbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LongestMatchTk {
    private Set<Character> spaceAndEnd;
    private Set<Character> eliminateCharacter;
    private Set<Character> numbers;
    private BackwardMatchSeg backwardSegmentation;
    private ForwardMatchSeg forwardSegmentation;
    private SymbolTable triesKh;


    public LongestMatchTk(Set<Character> spaceAndEnd, Set<Character> eliminateCharacter, Set<Character> numbers, SymbolTable triesKh) {
        this.spaceAndEnd = spaceAndEnd;
        this.eliminateCharacter = eliminateCharacter;
        this.numbers = numbers;
        backwardSegmentation = new BackwardMatchSeg(spaceAndEnd, numbers, triesKh);
        forwardSegmentation = new ForwardMatchSeg(spaceAndEnd, numbers, triesKh, eliminateCharacter, new EnglishLanguage());
        this.triesKh = triesKh;
    }


    private List<String> splitWord(String text) {
        StringBuffer stringBuffer = new StringBuffer(text);
        StringBuffer buffer = new StringBuffer();
        List<String> splitText = new ArrayList<>();
        for (int i = 0; i < stringBuffer.length(); i++) {
            char ch = stringBuffer.charAt(i);
            if ((spaceAndEnd.contains(ch) || eliminateCharacter.contains(ch))) {
                if (buffer.length() > 0) {
                    splitText.add(buffer.toString());
                    buffer = new StringBuffer();
                }

            } else {
                buffer.append(ch);
            }

        }
        if (splitText.size() == 0) {
            splitText.add(text);
        } else if (buffer.length() > 0) {
            splitText.add(buffer.toString());
        }

        return splitText;

    }


    public SegmentResult tokenize(String text) {
        long startTime = System.currentTimeMillis();
        List<String> words = splitWord(text);
        //  System.out.println("finish split word :" + words.size());
//        words.forEach(it -> {
//            System.out.println("->" + it);
//        });
        List<String> result = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (String word : words) {
            //System.out.println("begin to segment word :" + word);
            if (triesKh.contains(word)) {
                //System.out.println("word exist : " + word);
                result.add(word);
            } else {
                SegmentResult backwardResult = backwardSegmentation.doSegment(word);
                SegmentResult forwardResult = forwardSegmentation.doSegment(word);
                SegmentResult selected = backwardResult;
                //  System.out.println("backward : " + backwardResult.getTotalError() + " ");
//                backwardResult.getWords().forEach(it -> {
//                   // System.out.println("-> " + it);
//                });
//
//           //     System.out.println("forward : " + forwardResult.getTotalError() + " ");
//                forwardResult.getWords().forEach(it -> {
//                    System.out.println("-> " + it);
//                });


                if (selected.getTotalError() > forwardResult.getTotalError()) {
                    selected = forwardResult;
                } else if (selected.getTotalError() == forwardResult.getTotalError() && selected.getTotalSegment() < forwardResult.getTotalSegment()) {
                    selected = forwardResult;
                }
                result.addAll(selected.getWords());
                errors.addAll(selected.getErrors());

            }
        }
        SegmentResult segmentResult = new SegmentResult(errors.size(), result.size(), result);
        segmentResult.setErrors(errors);
        segmentResult.setTakeTime(System.currentTimeMillis() - startTime);
        return segmentResult;

    }
}
