package com.job4kh.tokenizer.data;

public class EnglishLanguage implements Language {
    public static final int MIN_CODE = 0;
    public static final int MAX_CODE = 127;

    @Override
    public boolean is(char ch) {
        int code = (int)ch;
        return (code >= MIN_CODE) && (code <=MAX_CODE);
    }
}
