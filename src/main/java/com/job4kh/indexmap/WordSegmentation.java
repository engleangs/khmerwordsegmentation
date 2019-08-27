package com.job4kh.indexmap;

import com.job4kh.tokenizer.SegmentResult;

public interface WordSegmentation {
    SegmentResult doSegment(String phrase);
}
