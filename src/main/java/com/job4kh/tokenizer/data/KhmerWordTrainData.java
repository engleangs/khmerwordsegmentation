package com.job4kh.tokenizer.data;



import com.job4kh.tokenizer.loader.DataLoader;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class KhmerWordTrainData
{
    private final Set<String> wordSet;
    private final int maxLength;
    public KhmerWordTrainData(DataLoader dataLoader) throws Exception
    {
        Set<String>words = new HashSet<>();
        dataLoader.load( words);
        wordSet = Collections.unmodifiableSet( words);
        maxLength = dataLoader.getMaxLength();

    }


    public Set<String> getWordSet()
    {
        return wordSet;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
