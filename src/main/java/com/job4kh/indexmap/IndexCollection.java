package com.job4kh.indexmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IndexCollection {

    private final Map<Character,IndexHolder> forwardSet;
    private final Map<Character,IndexHolder> backwardSet;
    public IndexCollection()
    {
        forwardSet = new ConcurrentHashMap<>();
        backwardSet = new ConcurrentHashMap<>();

    }

    public Map<Character, IndexHolder> getForwardSet() {
        return forwardSet;
    }

    public void index(String word)
    {
        final char firstChar = word.charAt(0);
        final char endChar = word.charAt( word.length() -1);
        if( forwardSet.containsKey( firstChar))
        {
            forwardSet.get(firstChar).index( word);
        }
        else {
            IndexHolder indexHolder = new ForwardIndexHolder( word);
            forwardSet.put( firstChar, indexHolder);
        }
        if( backwardSet.containsKey( endChar)) {
            backwardSet.get( endChar).index( word);
        }
        else {
            IndexHolder indexHolder = new BackwardIndexHolder( word);
            backwardSet.put( endChar,indexHolder);
        }
    }

    public Map<Character, IndexHolder> getBackwardSet() {
        return backwardSet;
    }

    public boolean exist(char ch)
    {
        return forwardSet.containsKey(ch);
    }

}
