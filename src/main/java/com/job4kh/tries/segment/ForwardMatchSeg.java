package com.job4kh.tries.segment;

import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.EnglishLanguage;
import com.job4kh.tries.SymbolTable;

import java.util.Set;

public class ForwardMatchSeg {
    private Set<Character> spaceAndEnd;
    private Set<Character> numbers;
    private SymbolTable triesKh;
    private EnglishLanguage englishLanguage;
    private Set<Character> eliminateCharacter;

    public ForwardMatchSeg(Set<Character> spaceAndEnd, Set<Character> numbers, SymbolTable triesKh, Set<Character> eliminateCharacter, EnglishLanguage englishLanguage) {
        this.spaceAndEnd = spaceAndEnd;
        this.numbers = numbers;
        this.triesKh = triesKh;
        this.eliminateCharacter = eliminateCharacter;
        this.englishLanguage = englishLanguage;

    }

    public SegmentResult doSegment(String text) {
        long startTime = System.currentTimeMillis();
        SegmentResult result = new SegmentResult();
        int end = text.length() - 1;
        while (end > -1) {
            char ch = text.charAt(end);
            while ((spaceAndEnd.contains(ch) || eliminateCharacter.contains(ch)) && end > 0 && text.length() > 0) {

                end--;
                ch = text.charAt(end);
            }

            StringBuffer copyBuffer = new StringBuffer(text.substring(0, end + 1));
            ch = copyBuffer.charAt(end);
            if (englishLanguage.is(ch)) {
                String otherLangauge = otherLanguage(copyBuffer.toString());
                end = end - otherLangauge.length();
                result.insertAtFirst(otherLangauge);
                continue;
            }
            if (numbers.contains(ch)) {
                String numberText = number(copyBuffer.toString());
                result.insertAtFirst(numberText);
                end = end - numberText.length();
                continue;

            }
            while (copyBuffer.length() > 0) {
                String tmp = copyBuffer.toString();
                if (triesKh.contains(tmp)) {
                    result.insertAtFirst(tmp);
                    end = end - tmp.length();
                    break;
                }
                copyBuffer.deleteCharAt(0);
            }
            if (copyBuffer.length() == 0 || end == 0) {
                if (text.length() > end && end >= 0) {
                    String unknown = text.substring(0, end + 1);
                    if (unknown.length() > 0) {
                        result.insertUnknownAtFirst(unknown);
                    }

                    break;

                }
            }


        }


//        if( result.getTotalSegment() == 0 && buffer.length() >0 )
//        {
//            result.insertUnknownAtFirst( buffer.toString());
//        }

        result.setTakeTime(System.currentTimeMillis() - startTime);
        result.setTypeName("forward");
        if (result.getTotalError() > 0) {
            result.getErrors().add("for-text[" + text + "]");
        }
        return result;
    }

    public String number(String text) {
        StringBuffer numberBuffer = new StringBuffer();
        for (int i = text.length() - 1; i > -1; i--) {
            char digit = text.charAt(i);
            if (numbers.contains(digit)) {
                numberBuffer.insert(0, digit);
            } else {
                break;

            }
        }
        return numberBuffer.toString();
    }

    public String otherLanguage(String text) {
        StringBuffer buffer = new StringBuffer();
        for (int i = text.length() - 1; i > -1; i--) {
            char engChar = text.charAt(i);
            if (englishLanguage.is(engChar) && !this.eliminateCharacter.contains(engChar)) {
                buffer.insert(0, engChar);
            } else {
                break;
            }
        }
        return buffer.toString();
    }
}
