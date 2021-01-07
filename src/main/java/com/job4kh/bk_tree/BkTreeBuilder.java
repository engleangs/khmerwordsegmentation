package com.job4kh.bk_tree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BkTreeBuilder {
    public static int min(int a , int b , int c){
        return Math.min( a, Math.min(b,c));
    }

    public static int editDistance( String a , String b){
        int m = a.length(), n = b.length();
        int dp[][] = new int[m+1][ n + 1];
        for(int i=0;i<=m;i++){
            dp[i][0] = i;
        }
        for(int j=0; j<=n;j++) {
            dp[0][j] = j;
        }
        for(int i =1;i <= m; i++) {
            for(int j =1 ;j <=n;j++){
                if( a.charAt(i-1)!= b.charAt(j-1)) {
                    dp[i][j] = min( 1 + dp[i-1][j] , //deleteion ,
                            1+ dp[ i][j-1],   //insertion
                            1 + dp[i-1][j-1] //replacement
                    );
                }
                else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[ m][n];
    }
    public static BkTree fromFile( String path) throws IOException {
        List<String> items =  Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        BkTree bkTree = new BkTree();
        final int[] max = {0};
        items.forEach(it->{
            if(!it.startsWith("#"))
            {
                String[] arr = it.split("\t");
                String word = arr[ 0];
                if( max[0] < word.length()) {
                    max[0] = word.length();
                }
                bkTree.add( word);
            }

        });
        System.out.println("max length :"+max[0]);
        return bkTree;
    }
    public static BkUTree ufromFile( String path) throws IOException {
        List<String> items =  Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        BkUTree bkTree = new BkUTree();
        final int[] max = {0};
        items.forEach(it->{
            if(!it.startsWith("#"))
            {
                String[] arr = it.split("\t");
                String word = arr[ 0];
                if( max[0] < word.length()) {
                    max[0] = word.length();
                }
                bkTree.add( word);
            }

        });
        return bkTree;
    }
    public static void main(String[] args) throws IOException {
        BkTree bkTree = fromFile("data/wordset.txt");
        List<String>words = bkTree.similarWords("មហាយុ");
        for(String word : words){
            System.out.println(" "+word);
        }
//        BkUTree bkUTree = ufromFile("data/wordset.txt");
//        System.out.println("done load");
//        List<String>words = bkUTree.similarWords("មហាយុ");
//        for(String word : words){
//            System.out.println(" "+word);
//        }
    }
}
