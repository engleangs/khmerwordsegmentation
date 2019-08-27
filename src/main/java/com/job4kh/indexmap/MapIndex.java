package com.job4kh.indexmap;

import java.util.Comparator;

public interface MapIndex {
    int getLength();
    Comparator<MapIndex> REVERSE_COMPARATOR = ((o1, o2) -> {
        if( o1.getLength() == o2.getLength()) {
            return 0;
        }
        else if( o1.getLength() > o2.getLength()) {
            return  -1;
        }
        else {
            return 1;
        }
    });
}
