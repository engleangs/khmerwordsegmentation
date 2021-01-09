package com.job4kh.corpus;

public class WordUniGram {
    private String w;
    private int count;

    public int getCount() {
        return count;
    }

    public String getW() {
        return w;
    }

    public WordUniGram(String w, int count) {
        this.w = w;
        this.count = count;
    }
}
