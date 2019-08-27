package com.job4kh.indexmap;

import com.job4kh.tokenizer.SegmentResult;

import java.util.Set;

public class BidirectionalSegmentWord  implements WordSegmentation{
    private ForwardSegmentWord forwardSegmentWord;
    private BackwardSegmentWord backwardSegmentWord;

    public BidirectionalSegmentWord( IndexCollection collection, NumericComputedIndexCollection numericComputedIndexCollection, Set<String> wordSet) {
        this.forwardSegmentWord = new ForwardSegmentWord(collection,numericComputedIndexCollection, wordSet);
        this.backwardSegmentWord  =new BackwardSegmentWord( collection , numericComputedIndexCollection , wordSet);
    }
    @Override
    public SegmentResult doSegment(String phrase) {
        SegmentResult forwardResult  = forwardSegmentWord.doSegment( phrase);
        SegmentResult backwardResult = backwardSegmentWord.doSegment( phrase );
        if(forwardResult.getTotalError() == backwardResult.getTotalError())  {
            if(   forwardResult.getTotalSegment() > backwardResult.getTotalSegment()) {
                return backwardResult;
            }
            else {
                return forwardResult;
            }

        }

        else if( forwardResult.getTotalError() < backwardResult.getTotalError()) {
                return forwardResult;
        }
        else {
            return backwardResult;
        }
    }
}
