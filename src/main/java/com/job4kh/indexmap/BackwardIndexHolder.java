package com.job4kh.indexmap;

public class BackwardIndexHolder extends IndexHolder {
    public BackwardIndexHolder(String word) {
        super(word);
    }

    @Override
    char getStartCharacter(String word) {
        return word.charAt( word.length() -1);
    }

    @Override
    char getFinalCharacter(String word) {
        return word.charAt(0);
    }
}
