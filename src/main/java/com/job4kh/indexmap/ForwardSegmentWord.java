package com.job4kh.indexmap;

import com.job4kh.tokenizer.SegmentResult;

import java.util.*;

public class ForwardSegmentWord implements WordSegmentation{
    private IndexCollection indexCollection;
    private Set<String> wordSet;
    private NumericComputedIndexCollection numericComputedIndexCollection;
    public ForwardSegmentWord(IndexCollection collection, NumericComputedIndexCollection numericComputedIndexCollection, Set<String>wordSet) {
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
        for(int i =0;i<buffer.length();i++) {
            char ch = buffer.charAt(i);
             if( indexCollection.exist( ch)) {
                 IndexHolder indexHolder = indexCollection.getForwardSet().get(ch);
                 WordMapIndex[] indices =   indexHolder.getIndices().toArray( new WordMapIndex[0]);
                int startPosition = -1;
                int length = buffer.length() -i;// buffer size minus with current index
                do {
                    long startBinSearch = System.currentTimeMillis();
                    startPosition = binSearch( length, indices);
                    System.out.println("bin search take :"+(System.currentTimeMillis()-startBinSearch));
                    length--;
                    if( startPosition > -1) {
                        break;
                    }
                }while (length > 0);

                  if( startPosition > -1)
                  {
                      boolean found = false;
                      for(int start = startPosition; start < indices.length; start++)
                      {
                          WordMapIndex wordMapIndex = indices[ start ];
                          int lastIndex = i+ wordMapIndex.getLength()-1;
                          char lastCh = buffer.charAt( lastIndex);
                          if( wordMapIndex.getLinkCharactersSet().contains( lastCh))
                          {
                              String possibleWord =  buffer.substring( i , lastIndex +1);
                              if( wordSet.contains( possibleWord))
                              {
                                  if( unknownWord.length() > 0)
                                  {
                                      segmentResult.addUnknown( unknownWord.toString());
                                      unknownWord = new StringBuffer();
                                  }

                                  segmentResult.add( possibleWord);
                                  i = i + possibleWord.length()-1;
                                  found = true;
                                  break;


                              }
                              else {
                                  NumericComputedIndexer indexer = numericComputedIndexCollection.getEligibleIndexer( possibleWord );
                                  if( indexer !=null ) {
                                      segmentResult.add( indexer.getWord());
                                      i = i + indexer.getWord().length() -1;
                                      found = true;
                                      break;
                                  }
                              }


                          }
                          else {
                              String possibleWord = buffer.substring( i, lastIndex +1);
                              NumericComputedIndexer indexer = numericComputedIndexCollection.getEligibleIndexer( possibleWord );
                              if( indexer !=null ) {
                                  segmentResult.add( indexer.getWord());
                                  i = i + indexer.getWord().length() -1;
                                  found = true;
                                  break;
                              }

                          }

                      }

                      if(!found)
                      {
                          unknownWord.append( ch);
                      }

                  }
                  else{
                      //not found
                      unknownWord.append( ch);///todo check for this logic
                  }


            }
            else {
                unknownWord.append( ch);
            }


        }

        if(unknownWord.length() > 0)
        {
            segmentResult.addUnknown( unknownWord.toString());
        }
        return segmentResult;
    }

}
