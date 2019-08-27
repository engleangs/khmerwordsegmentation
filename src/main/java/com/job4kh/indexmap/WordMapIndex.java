package com.job4kh.indexmap;

import java.util.HashSet;
import java.util.Set;

public class WordMapIndex implements MapIndex{
    private int length;
    private Set<Character> linkCharactersSet;
    private int count;
    public WordMapIndex(int length, char end)
    {
        this.length = length;
        linkCharactersSet = new HashSet<>();
        linkCharactersSet.add( end);
        count =1;
    }

    @Override
    public int getLength() {
        return length;
    }

    public Set<Character> getLinkCharactersSet() {
        return linkCharactersSet;
    }

    public void add(char end)
    {
        linkCharactersSet.add( end);
        count++;
    }

    public int getCount() {
        return count;
    }
}
