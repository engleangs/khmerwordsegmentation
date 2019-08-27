package com.job4kh.indexmap;

public class AllIndexCollection {
    private IndexCollection indexCollection;
    private NumericComputedIndexCollection numericComputedIndexCollection;

    public AllIndexCollection(IndexCollection indexCollection, NumericComputedIndexCollection numericComputedIndexCollection) {
        this.indexCollection = indexCollection;
        this.numericComputedIndexCollection = numericComputedIndexCollection;
    }

    public IndexCollection getIndexCollection() {
        return indexCollection;
    }

    public NumericComputedIndexCollection getNumericComputedIndexCollection() {
        return numericComputedIndexCollection;
    }
}
