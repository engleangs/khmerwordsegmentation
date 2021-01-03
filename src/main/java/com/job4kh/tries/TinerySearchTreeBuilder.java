package com.job4kh.tries;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TinerySearchTreeBuilder {
    public static TinerySerachTree<Integer>fromFile(String path) throws IOException {
        TinerySerachTree<Integer>tinerySerachTree = new TinerySerachTree<>();
        List<String> items =  Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        items.forEach(it->{
            if(!it.startsWith("#"))
            {
                String[] arr = it.split("\t");
                String word = arr[ 0];
                tinerySerachTree.put(word, word.length());
            }
        });
        return tinerySerachTree;
    }
    public static void main(String[]args) throws IOException {
        System.out.println("start loading...");
        TinerySerachTree<Integer>serachTree =fromFile("data/wordset.txt");

    }
}
