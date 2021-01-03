package com.job4kh.tries;

import com.job4kh.indexmap.CleanTextUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TriesBuilder {
    public static final int KHMER_UNICODE_COUNT = 144;
    public static final int ASCII_COUNT = 256;
    public static final int R = KHMER_UNICODE_COUNT + ASCII_COUNT;
    public static int charAt(int i,String st){
        if(i < st.length()) {
            char ch = st.charAt(i);
            int c = ( int)ch;
            if( c >= CleanTextUtil.startCh && c <= CleanTextUtil.endCh) {
                return  ASCII_COUNT + ( ch- CleanTextUtil.startCh);
            }
            else if( c <= ASCII_COUNT) {
                return c;
            }
        }
        return -1;
    }
    public static TriesKh fromFile(String path) throws IOException {
        TriesKh triesKh = new TriesKh();
        List<String> items =  Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        items.forEach(it->{
            if(!it.startsWith("#"))
            {
                String[] arr = it.split("\t");
                String word = arr[ 0];
                triesKh.put(word, word.length());
            }

        });
        return triesKh;

    }


}
