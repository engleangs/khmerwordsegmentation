package com.job4kh.bk_tree;

import java.util.LinkedHashMap;
import java.util.Map;

public class LevensteinDistance implements Distance {
    private static final Map<String,Double> CACHE = new LinkedHashMap<>();
    @Override
    public double getDistance(String w1, String w2) {
        StringBuilder sb = new StringBuilder( w1).append(w2);
        if( CACHE.containsKey( sb.toString())) {
            return CACHE.get( sb.toString());
        }
        return  BkTreeBuilder.editDistance( w1, w2);
    }
}
