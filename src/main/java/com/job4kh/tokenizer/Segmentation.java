package com.job4kh.tokenizer;

public interface Segmentation {
    SegmentResult doSegment(String text);
    String number(String text);
    String otherLanguage(String text);
}
