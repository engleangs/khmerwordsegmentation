package com.job4kh.bigram;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NGramDataSource {
    private int n;
    private Map<String,Integer> words;
    private static final  Map<String,List<String>> CACHE = new LinkedHashMap<>();
    public NGramDataSource(String path) throws Exception{
        load(path);
    }
    private void load( String path) throws IOException {
        List<String> items = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
        words = new LinkedHashMap<>();
        n=0;
        for(int i =6;i< items.size() ;i++) {
            String item = items.get(i);
            String[]w = item.split("\t");
            Integer counter  = Integer.parseInt( w[2]);
            words.put( w[0] , counter);
            n++;
        }
    }

    /***
     * return probability of the word
     * @param word
     * @return
     */
    public double prob(String word){
        if(words.containsKey(word)) {
            int val = words.get( word);
            double p = ((double)val/n);
            return p;
        }
        else {
            return avoidLongWord( word);
        }
    }
    public double prob(List<String>items){
        double p = 1;
        for(String s : items){
            p = p * prob( s);
        }
        return p;
    }
    /**
     * For the word that is not found in the dictionary
     */
    public double avoidLongWord(String key){
        return 10  / (this.n * Math.pow( 10, key.length()));
    }

    public List<String>segment(String text){
        if( text == null || text.length() ==0){
            return new ArrayList<>();
        }
        if( CACHE.containsKey( text)) {
            return CACHE.get(text);
        }
        List<List<String>>candidates = new ArrayList<>();
        List<Pair>splitText = split(text);
        for(Pair p : splitText){
            List<String>remainingCandidate = segment( p.second);
            List<String>current  = new ArrayList<>();
            current.add(p.first);
            current.addAll( remainingCandidate);
            candidates.add( current);
        }

        List<String>selection  = selectCandidate(candidates);
        CACHE.put(text, selection);
        return selection;


    }

    public List<String>selectCandidate(List<List<String>>candidates){
        int inx = 0;
        double max = Double.MIN_VALUE;
        int i = 0;
        for(List<String> c : candidates){
            double p = prob(c);
            if( p > max) {
                max = p;
                inx = i;
            }
            i++;
        }
        return candidates.get(inx);

    }

    public List<Pair>split(String text){
        return split( text, 20);
    }
    public List< Pair>split(String text,int max){
        int size = Math.min(text.length(), max);
        List<Pair>arr = new ArrayList<>();
        for(int i =0;i< size;i++){
            Pair pair = new Pair();
            pair.first =  text.substring(0,i+1);
            pair.second = text.substring(i+1);
            arr.add( pair);
        }
        return arr;
    }

    static class Pair {
        private String first;
        private String second;
    }
    public static void main(String[] args) throws Exception {
        //System.out.println("hello");
        String input = "ស្វាគមន៍មកកាន់គេហទំព័រថ្មីរបស់យើងយើងសុំទោសចំពោះភាពរអាក់រអួលប្រសិនបើវាមានបញ្ហា";
        NGramDataSource NGramDataSource = new NGramDataSource( "data/index.plc");
        long start = System.currentTimeMillis();
        List<String> segment =    NGramDataSource.segment(input);
        System.out.println("done : "+( System.currentTimeMillis()- start));
        for(String w : segment){
            System.out.println(w);
        }
    }

}
