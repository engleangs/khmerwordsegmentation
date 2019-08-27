package com.job4kh.indexmap;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class NumericComputedIndexCollection {
    private HashMap<Character, NumericComputedIndexHolder>indexMap = new HashMap<>();
    public final StringBuilder duplicateBuilder = new StringBuilder();
    public void index(String word)
    {
        char ch = word.charAt(0);
        if(indexMap.containsKey( ch )) {
            indexMap.get( ch ).index( word);
        }
        else {
            NumericComputedIndexHolder numericComputedIndexHolder = new NumericComputedIndexHolder();
            indexMap.put(ch, numericComputedIndexHolder);
            numericComputedIndexHolder.index( word );
        }
    }

    public HashMap<Character, NumericComputedIndexHolder> getIndexMap() {
        return indexMap;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        indexMap.forEach((key,value)->{
            builder.append(key).append(" :");
            value.getSortedSet().forEach(item->{
                builder.append("\n").append("[").append( item.getLength()).append("]:");
                item.getIndexers().forEach( (indexKey,indexVal)->{
                    builder.append( indexKey ).append("=> hashcode[").append( indexKey.hashCode()).append("]");
                    AtomicLong atomicLong = new AtomicLong(0);
                    if( indexVal.size() > 1 ) {
                        //System.out.println("to check ");
                        duplicateBuilder.append("\n************************************ \n");
                    }
                    for (NumericComputedIndexer numericComputedIndexer : indexVal) {
                        builder.append(numericComputedIndexer.getWord()).append(" | ");
                        if(indexVal.size() > 1) {
                            duplicateBuilder.append( numericComputedIndexer.getWord()).append("\t");

                        }

                        if( atomicLong.incrementAndGet() % 5==0) {
                            builder.append("\n\t\t");
                        }
                    }
                    if( indexVal.size() > 1 ) {
                        //System.out.println("to check ");
                        duplicateBuilder.append("\n************************************ \n");
                    }

                    builder.append("\n");
                });
            });
            builder.append("\n");
        });
        System.out.println("=================================");
        return builder.toString();
    }

    public NumericComputedIndexer getEligibleIndexer(String word) {
        long startTime = System.currentTimeMillis();
        char ch = word.charAt(0);
        if( indexMap.containsKey(ch))
        {
             NumericComputedIndexMap[] indices =  indexMap.get( ch).getIndices();
             int index = NumericComputedIndexHolder.binarySearch( word.length() , indices);
             if( index > -1 && index < indices.length)
             {
                NumericComputedIndexMap found = indices[ index];
                NumericComputedIndexer target = new NumericComputedIndexer( word);
                if( found.getIndexers().containsKey( target.getSum()) ) {
                    List<NumericComputedIndexer> items = found.getIndexers().get( target.getSum() );
                    for(NumericComputedIndexer indexer : items) {
                        if( indexer.isSimilar( target) ) {
                            return indexer;
                        }
                    }
                }
             }
        }
        System.out.println("Eligible indexer take :"+(System.currentTimeMillis() - startTime));
        return null;

    }

}
