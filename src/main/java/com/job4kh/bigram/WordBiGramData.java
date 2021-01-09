package com.job4kh.bigram;

import com.job4kh.corpus.WordTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordBiGramData {
    private WordTable wordTable;
    public WordBiGramData(WordTable wordTable){
        this.wordTable = wordTable;
    }
    private static final Map<String,List<String>> CACHE = new LinkedHashMap<>();
    public double laplace(String w1,String w2){
            int biCount = wordTable.biGramCount( w1,w2) * 10;
            int c1 = wordTable.uniGramCount( w1) * 5;
            if( w1.equals("_")) {
                c1 = 1;
            }

        double p =   ( (double)( biCount + 100) *  (  c1 + 1)) / (double)( c1 + wordTable.size());
    //    System.out.println("p = : "+p + " of "+w1 +" -> "+w2);
//        if( w1.equals("សួស្តី" || w2 =="សួស្តី" || w1=="សូម" || w2=="សួម") {
//            System.out.println("to check");
//        }
        return p;
    }

    public List<String>segment(String text,String exStart , String exEnd){
        if( text == null || text.length() ==0){
            return new ArrayList<>();
        }
        if( CACHE.containsKey( text)) {
            return CACHE.get(text);
        }
        List<List<String>>candidates = new ArrayList<>();
        List<Pair>splitText = split(text);
        for(Pair p : splitText){
            List<String>remainingCandidate = segment( p.second, p.first, exEnd);
            List<String>current  = new ArrayList<>();
            current.add(p.first);
            current.addAll( remainingCandidate);
            candidates.add( current);
        }

        List<String>selection  = selectCandidate(exStart , exEnd,candidates);
        CACHE.put(text, selection);
        return selection;

    }

    public List<String>selectCandidate(String start, String end ,List<List<String>>candidates){
        int inx = 0;
        double max = Double.MIN_VALUE;
        int i = 0;
        for(List<String> c : candidates){
            double p = prob( start , end , c);
            System.out.println("prob of ");
            System.out.print("\t"+ start );
            for(String s : c){
                System.out.print("\t"+s);
            }
            System.out.println("\t"+end +" ---> "+p);
            if( p > max) {
                max = p;
                inx = i;
            }
            i++;
        }
        return candidates.get(inx);

    }
    public double prob(String start, String end ,List<String> words){
        if( words.size() ==0){
            return 0;
        }
        if( start == null){
            start = "";
        }
        if( end ==  null){
            end = "";
        }
        String w1 = start;
        String w2;
        double prob  = 0;
        for(int i =0;i<words.size();i++){
            w2 = words.get(i);
            prob += Math.log( laplace( w1, w2));
            w1 = w2;
        }
        w2 = end;
        prob += Math.log( laplace( w1, w2) );
      return Math.exp(prob);
        //return prob;
    }

    public List<Pair>split(String text){
        return split( text, 20);
    }
    public List<Pair>split(String text, int max){
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


}
