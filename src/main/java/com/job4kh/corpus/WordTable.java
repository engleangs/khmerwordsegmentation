package com.job4kh.corpus;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordTable {
    private Map<String, WordAttribute> map = new LinkedHashMap<>();
    public int size(){
        return map.size();
    }
    public void add(String word) {
        WordAttribute wordAttribute = new WordAttribute();
        wordAttribute.incrementUnicord();
        map.put(word, wordAttribute);
    }
    public void uniGram(String word,int count){
        if( map.containsKey(word)) {
            map.get( word).incrementUnicord( count);
        }
        else {
            add( word);
            map.get(word).incrementUnicord(count);
        }
    }

    public void addBigram(String w1, String w2,int count){
        if( !map.containsKey( w1)) {
            add( w1);
        }
        map.get(w1).bigram( w2, count);
    }
    public void count(String word, int count){
        if( !map.containsKey(word)) {
            add( word);
        }
        map.get(word).setTotalWord( count);

    }
    public int totalWord(String w){
        if( map.containsKey(w)){
            return map.get(w).getTotalWord();
        }
        return 0;
    }
    public int uniGramCount(String w){
        if( map.containsKey(w)) {
            return map.get(w).getUniCount();
        }
        return 0;
    }
    public int biGramCount(String w1, String w2){
        if( map.containsKey(w1)) {
            WordAttribute wa = map.get(w1);
            if( wa.getBigramCount().containsKey( w2)) {
                return wa.getBigramCount().get(w2);
            }
        }
        return 0;
    }

}
