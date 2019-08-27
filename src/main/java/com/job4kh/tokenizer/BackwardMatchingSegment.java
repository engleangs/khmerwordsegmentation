package com.job4kh.tokenizer;


import com.job4kh.tokenizer.data.KhmerWordTrainData;

import java.util.Set;

public class BackwardMatchingSegment implements Segmentation {
    private Set<Character> spaceAndEnd;
     private Set<Character> numbers;
    private Set<Character>eliminateCharacter;
    private KhmerWordTrainData khmerWordTrainData;

    public BackwardMatchingSegment(Set<Character> spaceAndEnd, Set<Character> numbers, Set<Character> eliminateCharacter, KhmerWordTrainData khmerWordTrainData) {
        this.spaceAndEnd = spaceAndEnd;
        this.numbers = numbers;
        this.eliminateCharacter = eliminateCharacter;
        this.khmerWordTrainData = khmerWordTrainData;
    }

    @Override
    public SegmentResult doSegment(String text)
    {
        long startTime = System.currentTimeMillis();
        SegmentResult result = new SegmentResult();
        StringBuffer buffer = new StringBuffer(text);
        if( text.length() == 0) {
            return result;
        }

        int start = 0;
        while (true)
        {

            char ch = text.charAt(start);
            while ( spaceAndEnd.contains(  ch ) && start < text.length()-1 && text.length() >0)
            {

                start++;
                ch = text.charAt(start);
            }
            if(start > text.length())
            {
                break;
            }

            StringBuffer copyBuffer = new StringBuffer( buffer.substring(start) );
            if( numbers.contains( copyBuffer.charAt(0)) )
            {
                //start with number
                String number = number(copyBuffer.toString());
                result.add( number);
                start = start+number.length();
                copyBuffer =  new StringBuffer( buffer.substring(start));
            }


            while (copyBuffer.length() > 0)
            {

                String expectedWord  = copyBuffer.toString();
                if(   ( khmerWordTrainData.getWordSet().contains(expectedWord)))
                {
                    result.add(expectedWord);
                    start =  start + expectedWord.length();
                    break;

                }
                copyBuffer.deleteCharAt( copyBuffer.length() -1 );


            }

            if(copyBuffer.length() == 0 || start== text.length())
            {
                if(buffer.length() - start > 0)
                {
                    String unknown = buffer.substring(start);
                    if( unknown.length() > 0)
                    {
                        result.addUnknown( unknown);
                    }

                }
                break;

            }
        }

        if( result.getTotalSegment() == 0 && buffer.length() > 0)
        {
            result.addUnknown( buffer.toString());
        }
        result.setTakeTime( System.currentTimeMillis()- startTime);
        result.setTypeName("backward");
        if(result.getTotalError() > 0)
        {
            result.getErrors().add("for-text["+text+"]");
        }
        return result;
    }

    @Override
    public String number(String text) {
        StringBuffer stringBuffer  = new StringBuffer();
        for(int i= 0;i<text.length();i++)
        {
            char ch = text.charAt(i);
            if(  numbers.contains( ch ))
            {
                stringBuffer.append(ch);
            }
            else {
                break;
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public String otherLanguage(String text) {
        return null;
    }

}
