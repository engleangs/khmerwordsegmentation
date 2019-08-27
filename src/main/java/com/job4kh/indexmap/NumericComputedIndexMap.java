package com.job4kh.indexmap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class NumericComputedIndexMap implements  MapIndex{
    private Character start;
    private Map<BigInteger, List<NumericComputedIndexer>> indexers;
    private int length;

    @Override
    public int getLength() {
        return length;
    }

    public Character getStart() {
        return start;
    }

    public NumericComputedIndexMap(){
        indexers = new HashMap<>();
    }

    public void index(String word) {
        start = word.charAt(0);
        length = word.length();
        NumericComputedIndexer numericComputedIndexer = new NumericComputedIndexer( word);
        if( indexers.containsKey( numericComputedIndexer.getSum())) {

            List<NumericComputedIndexer> list = indexers.get( numericComputedIndexer.getSum());
            boolean duplicate = false;
            for(NumericComputedIndexer item :list) {
                if( item.isSimilar(numericComputedIndexer)) {
                    duplicate = true;
                    System.out.println("duplicate found : " + word + " vs "+item.getWord()) ;
                    break;
                }
            }
            if( !duplicate) {
                list.add(numericComputedIndexer);//add if not duplicate
            }
        }
        else {
            List<NumericComputedIndexer> items = new ArrayList<NumericComputedIndexer>( );
            items.add(numericComputedIndexer);
            this.indexers.put( numericComputedIndexer.getSum(), items);
        }
    }

    public Map<BigInteger, List<NumericComputedIndexer>> getIndexers() {
        return indexers;
    }
}
