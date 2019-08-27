package com.job4kh.tokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpaceTokenizer implements WordTokenizer
{
    private Set<Character>eliminateCharacters;
    private Set<Character> spaceAndEndSet;

    public SpaceTokenizer(Set<Character> eliminateCharacters, Set<Character> spaceAndEndSet) {
        this.eliminateCharacters = eliminateCharacters;
        this.spaceAndEndSet = spaceAndEndSet;
    }

    @Override
    public SegmentResult tokenize(String text)
    {
        StringBuffer stringBuffer = new StringBuffer();
        List<String>words = new ArrayList<>();
        for(int i =0;i<text.length();i++)
        {
            char ch = text.charAt( i);
            if(  ( eliminateCharacters.contains( ch) || spaceAndEndSet.contains(ch) ) && stringBuffer.length() > 0)
            {
                  words.add( stringBuffer.toString());
            }
            stringBuffer.append( ch );
        }

        if( stringBuffer.length() > 0)
        {
            words.add( stringBuffer.toString());
        }
        return new SegmentResult(0,words.size(),words);
    }
}
