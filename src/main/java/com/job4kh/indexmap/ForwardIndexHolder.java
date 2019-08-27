package com.job4kh.indexmap;

public class ForwardIndexHolder extends IndexHolder {
    public ForwardIndexHolder(String word) {
        super(word);
    }

    @Override
    char getStartCharacter(String word) {
        return word.charAt(0);
    }

    @Override
    char getFinalCharacter(String word) {
        return word.charAt(word.length()-1);
    }
}
