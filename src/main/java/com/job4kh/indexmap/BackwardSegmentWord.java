package com.job4kh.indexmap;

import com.job4kh.tokenizer.SegmentResult;

import java.util.Arrays;
import java.util.Set;

public class BackwardSegmentWord implements WordSegmentation{
    private IndexCollection indexCollection;
    private Set<String> wordSet;
    private NumericComputedIndexCollection numericComputedIndexCollection;
    public BackwardSegmentWord(IndexCollection collection, NumericComputedIndexCollection numericComputedIndexCollection, Set<String>wordSet) {
        this.indexCollection = collection;
        this.wordSet = wordSet;
        this.numericComputedIndexCollection = numericComputedIndexCollection;
    }

    int binSearch( int target, WordMapIndex []indices)
    {

        return Arrays.binarySearch( indices, new WordMapIndex(target,'\0'), MapIndex.REVERSE_COMPARATOR);

    }

    @Override
    public SegmentResult doSegment(String phrase)
    {

        SegmentResult segmentResult = new SegmentResult();
        StringBuffer buffer = new StringBuffer(phrase);
        StringBuffer unknownWord = new StringBuffer();
        for(int i=buffer.length()-1;i>=0;i--) {
            System.out.println("index i :"+i);
            char ch = buffer.charAt(i);
            if( indexCollection.getBackwardSet().containsKey( ch)) {
                IndexHolder indexHolder = indexCollection.getBackwardSet().get(ch);//get index holder
                WordMapIndex[] indices =   indexHolder.getIndices().toArray( new WordMapIndex[0]);//get indices of the list to array
                int endPosition;
                int length = i +1;
                //loop until find the binary search based on length until it become 0
                do {
                    long startBinSearch  = System.currentTimeMillis();
                    endPosition = binSearch( length, indices);
                    System.out.println("bin search take :"+(System.currentTimeMillis()-startBinSearch));
                    System.out.println("end position :"+endPosition);
                    length--;
                    if( endPosition > -1) {
                        break;
                    }
                }while (length > 0);
                System.out.println("end position :"+endPosition);

                if( endPosition > -1)
                {
                    boolean found = false;
                    for(int position = endPosition; position < indices.length; position++)
                    {
                        WordMapIndex wordMapIndex = indices[ position ];
                        int firstIndex = i- wordMapIndex.getLength() + 1;//get the first index of the buffer string
                        char firstCh = buffer.charAt( firstIndex);
                        if( wordMapIndex.getLinkCharactersSet().contains( firstCh))
                        {
                            String possibleWord =  buffer.substring( firstIndex, i+1);
                            if( wordSet.contains( possibleWord))
                            {
                                if( unknownWord.length() > 0)
                                {
                                    segmentResult.insertUnknownAtFirst( unknownWord.toString());
                                    unknownWord = new StringBuffer();
                                }
                                segmentResult.insertAtFirst( possibleWord);
                                i = i - possibleWord.length() +1;
                                found = true;
                                break;


                            }
                            else {
                                NumericComputedIndexer indexer = numericComputedIndexCollection.getEligibleIndexer( possibleWord );
                                if( indexer !=null ) {
                                    segmentResult.insertUnknownAtFirst( indexer.getWord());
                                    i = i - indexer.getWord().length() +1;
                                    found = true;
                                    break;
                                }
                            }


                        }
                        else {
                            String possibleWord = buffer.substring( firstIndex , i +1);
                            NumericComputedIndexer indexer = numericComputedIndexCollection.getEligibleIndexer( possibleWord );
                            if( indexer !=null ) {
                                segmentResult.insertAtFirst( indexer.getWord());
                                i = i - indexer.getWord().length() +1;
                                found = true;
                                break;
                            }

                        }

                    }

                    if(!found)
                    {
                        unknownWord.insert(0, ch);
                    }

                }
                else{
                    //not found
                    //System.out.println("not found "+ch);
                    unknownWord.insert(0, ch);///todo check for this logic
                }


            }
            else {
                unknownWord.insert(0, ch);
            }


        }

        if(unknownWord.length() > 0)
        {
            segmentResult.insertUnknownAtFirst( unknownWord.toString());
        }
        return segmentResult;
    }



}
