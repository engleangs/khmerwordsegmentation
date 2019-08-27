package com.job4kh.tokenizer.loader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class FileLoader implements DataLoader {
    private String path;
    private int max =0;

    public FileLoader(String path)
    {
        this.path  = path;
    }

    @Override
    public void load(Set<String> wordSet) throws IOException {
        List<String> items =  Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        items.forEach(it->{
            if(!it.startsWith("#"))
            {
                String[] arr = it.split("\t");
                String word = arr[0];
                if( max < word.length())
                {
                    max = word.length();
                }
                wordSet.add( word);


            }

        });
    }

    @Override
    public int getMaxLength() {
        return max;
    }
}
