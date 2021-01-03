package com.job4kh.tokenizer;



import com.job4kh.tokenizer.data.KhmerWordTrainData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LongestMatchingTokenizer implements WordTokenizer
{

    private Set<Character>spaceAndEnd;
    private Set<Character>eliminateCharacter;
    private Set<Character>numbers;
    private KhmerWordTrainData khmerWordTrainData;
    private Segmentation backwardSegmentation;
    private Segmentation forwardSegmentation;


    public LongestMatchingTokenizer(Set<Character> spaceAndEnd, Set<Character> eliminateCharacter, Set<Character> numbers, KhmerWordTrainData khmerWordTrainData) {
        this.spaceAndEnd = spaceAndEnd;
        this.eliminateCharacter = eliminateCharacter;
        this.numbers = numbers;
        this.khmerWordTrainData = khmerWordTrainData;
        backwardSegmentation  =new BackwardMatchingSegment(spaceAndEnd, numbers, eliminateCharacter, khmerWordTrainData);
        forwardSegmentation =    new ForwardMatchingSegment(spaceAndEnd,numbers,eliminateCharacter,khmerWordTrainData);
    }


    private List<String>splitWord(String text)
    {
        StringBuffer stringBuffer = new StringBuffer(text);
        StringBuffer buffer = new StringBuffer();
        List<String>splitText = new ArrayList<>();
        for(int i=0;i<stringBuffer.length();i++)
        {
            char ch = stringBuffer.charAt(i);
            if(( spaceAndEnd.contains(ch) || eliminateCharacter.contains(ch) ) )
            {
                if( buffer.length() > 0)
                {
                    splitText.add(buffer.toString());
                    buffer = new StringBuffer();
                }

            }
            else
            {
                buffer.append(ch);
            }

        }
        if(splitText.size() == 0)
        {
            splitText.add( text );
        }
        else if( buffer.length() > 0)
        {
            splitText.add( buffer.toString());
        }

        return splitText;

    }






    @Override
    public SegmentResult tokenize(String text)
    {
        long startTime = System.currentTimeMillis();
        List<String>words = splitWord( text);
      //  System.out.println("finish split word :"+words.size());
//        words.forEach(it->{
//            System.out.println("->"+it );
//        });
        List<String> result = new ArrayList<>();
        List<String>errors = new ArrayList<>();
        for(String word : words )
        {
//            System.out.println("begin to segment word :"+word);
            if( khmerWordTrainData.getWordSet().contains(word))
            {
//                System.out.println("word exist : "+word);
                result.add( word);
            }
            else {
               SegmentResult backwardResult = backwardSegmentation.doSegment( word);
               SegmentResult forwardResult = forwardSegmentation.doSegment( word);
               SegmentResult selected = backwardResult;
//                System.out.println("backward : "+backwardResult.getTotalError() +" ");
//                backwardResult.getWords().forEach(it->{
//                    System.out.println("-> "+it);
//                });

//                System.out.println("forward : "+forwardResult.getTotalError() +" ");
//                forwardResult.getWords().forEach(it->{
//                    System.out.println("-> "+it);
//                });


                if( selected.getTotalError() > forwardResult.getTotalError())
               {
                   selected = forwardResult;
               }
               else if( selected.getTotalError() == forwardResult.getTotalError() && selected.getTotalSegment() < forwardResult.getTotalSegment())
               {
                   selected = forwardResult;
               }
               result.addAll( selected.getWords());
               errors.addAll( selected.getErrors());

            }
        }
        SegmentResult segmentResult =  new SegmentResult(errors.size(),result.size(), result);
        segmentResult.setErrors( errors);
        segmentResult.setTakeTime( System.currentTimeMillis() - startTime);
        return segmentResult;

    }
}
