package com.job4kh.indexmap;

import java.util.Set;

public class IndexUtil {

    public static IndexCollection indexCollection( Set<String> wordSet){
        IndexCollection collection = new IndexCollection();

        wordSet.forEach( it-> collection.index( it));
        collection.getForwardSet().forEach((k, v)->{
            System.out.println(" start with : "+k );
            System.out.println("=========================");
            v.getIndices().forEach( item->{
                final StringBuilder itemBuilder = new StringBuilder();
                itemBuilder.append(" Length : "+item.getLength())
                        .append("    word count : " ).append( item.getCount())
                        .append( " , index size : ").append( item.getLinkCharactersSet().size())
                        .append("  [");
                item.getLinkCharactersSet().forEach(endCh->{
                    itemBuilder.append( endCh).append(" , ");
                });
                itemBuilder.append(" ] ");
                System.out.println( itemBuilder.toString());
            });
            System.out.println("=========================");
        });
        return collection;
    }

    public static AllIndexCollection allIndexCollection(Set<String> wordSet)
    {
        IndexCollection indexCollection = new IndexCollection();
        NumericComputedIndexCollection numericComputedIndexCollection = new NumericComputedIndexCollection();
        wordSet.forEach( it-> {
            indexCollection.index( it);
            numericComputedIndexCollection.index( it);
        });
        return new AllIndexCollection( indexCollection, numericComputedIndexCollection);
    }

}
