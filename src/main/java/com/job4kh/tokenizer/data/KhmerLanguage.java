package com.job4kh.tokenizer.data;

public class KhmerLanguage implements Language {
    public static int MIN_CODE = 0x1780;
    public static int MAX_CODE = 0x17FF;

    @Override
    public boolean is(char ch) {
        int code = (int)ch;
        return ( code >= MIN_CODE) && ( code <= MAX_CODE);
    }
    public static boolean isNumber(String word){
        for(int i =0;i< word.length();i++) {
            if(! KhmerCharacterSet.DIGIT_SET.contains( word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public  boolean isKhmer(String word){
        for(int i =0;i<word.length();i++){
            if( !is(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
