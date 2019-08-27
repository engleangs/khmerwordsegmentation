package com.job4kh.indexmap;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
public abstract class IndexHolder {
    private SortedSet<WordMapIndex> sortedSet = new TreeSet<>( (o1, o2) -> {
        if(o1.getLength() == o2.getLength())  {
            return 0;
        }
        else if( o1.getLength() > o2.getLength()) {
            return  -1;
        }
        return 1;
    });
    private char keyCharacter;
    public char getKeyCharacter() {
        return keyCharacter;
    }
    abstract char getStartCharacter(String word);
    abstract char getFinalCharacter(String word);

    public IndexHolder(String word)
    {
        keyCharacter = getStartCharacter( word);
        WordMapIndex wordMapIndex = new WordMapIndex( word.length(), getFinalCharacter(word));
        sortedSet.add(wordMapIndex);
    }

    public void index(String word)
    {
         final char finalCharacter = getFinalCharacter( word);
         final WordMapIndex[] indices =  sortedSet.toArray(new WordMapIndex[0]);
         WordMapIndex wordMapIndex =  binarySearch( word.length() , indices );
         if( wordMapIndex == null)
         {
             wordMapIndex = new WordMapIndex( word.length(),  finalCharacter);
             sortedSet.add(wordMapIndex);
         }
         else {
             wordMapIndex.add( finalCharacter);
         }

    }

    public WordMapIndex binarySearch(int length, WordMapIndex[] indices) {
//        int start = 0;
//        int end = indices.length-1;
//        while ( start <= end )
//        {
//            int mid = ((start + end) /2);
//            if(indices[mid].getLength() == length)
//            {
//                return indices[mid];
//            }
//            else if( length > indices[ mid].getLength())
//            {
//                end = mid -1;
//            }
//            else {
//
//                start = mid+1;
//            }
//        }
        int lookupIndex = Arrays.binarySearch( indices ,new WordMapIndex(length,'\0'), MapIndex.REVERSE_COMPARATOR);
        if( lookupIndex <0  || lookupIndex > indices.length -1) {
            return null;
        }
        else {
            return indices[ lookupIndex];
        }
    }

    public SortedSet<WordMapIndex> getIndices()
    {
        return sortedSet;
    }
}
