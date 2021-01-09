package com.job4kh.corpus;

public class WordBiGram {
    private String w1;
    private String w2;
    private int count;

    public WordBiGram(String w1, String w2, int count) {
        this.w1 = w1;
        this.w2 = w2;
        this.count = count;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
