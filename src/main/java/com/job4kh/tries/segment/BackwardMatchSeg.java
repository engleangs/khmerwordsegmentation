package com.job4kh.tries.segment;

import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tries.SymbolTable;

import java.util.Set;

public class BackwardMatchSeg {
    private Set<Character> spaceAndEnd;
    private Set<Character> numbers;
    private SymbolTable triesKh;

    public BackwardMatchSeg(Set<Character> spaceAndEnd, Set<Character> numbers, SymbolTable triesKh) {
        this.spaceAndEnd = spaceAndEnd;
        this.numbers = numbers;
        this.triesKh = triesKh;
    }

    public SegmentResult doSegment(String text) {
        long startTime = System.currentTimeMillis();
        SegmentResult result = new SegmentResult();
        if (text.length() == 0) {
            return result;
        }

        int start = 0;
        while (true) {
            char ch = text.charAt(start);
            while (spaceAndEnd.contains(ch) && start < text.length() - 1 && text.length() > 0) {

                start++;
                ch = text.charAt(start);
            }
            if (start > text.length()) {
                break;
            }

            StringBuffer copyBuffer = new StringBuffer(text.substring(start));
            if (numbers.contains(copyBuffer.charAt(0))) {
                //start with number
                String number = number(copyBuffer.toString());
                result.add(number);
                start = start + number.length();
                copyBuffer = new StringBuffer(text.substring(start));
            }

            while (copyBuffer.length() > 0) {

                String expectedWord = copyBuffer.toString();
                if ((triesKh.contains(expectedWord))) {
                    result.add(expectedWord);
                    start = start + expectedWord.length();
                    break;

                }
                copyBuffer.deleteCharAt(copyBuffer.length() - 1);


            }

            if (copyBuffer.length() == 0 || start == text.length()) {
                if (text.length() - start > 0) {
                    String unknown = text.substring(start);
                    if (unknown.length() > 0) {
                        result.addUnknown(unknown);
                    }
                }
                break;

            }
        }

        if (result.getTotalSegment() == 0 && text.length() > 0) {
            result.addUnknown(text);
        }
        result.setTakeTime(System.currentTimeMillis() - startTime);
        result.setTypeName("backward");
        if (result.getTotalError() > 0) {
            result.getErrors().add("for-text[" + text + "]");
        }
        return result;
    }

    public String number(String text) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (numbers.contains(ch)) {
                stringBuffer.append(ch);
            } else {
                break;
            }
        }
        return stringBuffer.toString();
    }

}
