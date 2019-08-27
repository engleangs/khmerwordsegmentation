package com.job4kh.indexmap;

import java.util.Arrays;
import java.util.TreeSet;

public class NumericComputedIndexHolder {
    private TreeSet<NumericComputedIndexMap> sortedSet = new TreeSet<>(MapIndex.REVERSE_COMPARATOR);

    public static int binarySearch(int length, NumericComputedIndexMap[] indexMaps) {
        MapIndex target = () -> length;
        return Arrays.binarySearch(indexMaps, target, MapIndex.REVERSE_COMPARATOR);
    }
    public int binarySearch(int length) {
        return binarySearch( length,sortedSet.toArray(new NumericComputedIndexMap[0]));
    }

    public void index(String word) {
        NumericComputedIndexMap[] indices = sortedSet.toArray(new NumericComputedIndexMap[0]);
        int lookupExisting = binarySearch(word.length(), indices);
        if (lookupExisting > 0 && lookupExisting < sortedSet.size()) {
            NumericComputedIndexMap found = indices[lookupExisting];
            found.index(word);
        } else {
            NumericComputedIndexMap numericComputedIndexMap = new NumericComputedIndexMap();
            numericComputedIndexMap.index(word);
            sortedSet.add(numericComputedIndexMap);
        }

    }

    public TreeSet<NumericComputedIndexMap> getSortedSet() {
        return sortedSet;
    }

    public NumericComputedIndexMap[] getIndices() {
        return sortedSet.toArray(new NumericComputedIndexMap[0]);
    }


}
