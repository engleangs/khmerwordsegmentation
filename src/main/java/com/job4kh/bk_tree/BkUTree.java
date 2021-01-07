package com.job4kh.bk_tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.job4kh.bk_tree.BkTreeBuilder.editDistance;

public class BkUTree {
    public static final int MAX_WORD_LENGTH = 100;
    public static final int MAX_WORD_NUMBER = 40000;
    public static final int TOLORRENCE_VALUE = 2;
    public static final int R = 26;
    private static class Node{
        private String word;
        private Map<Integer, List<Node>> children = new LinkedHashMap<>();
        private int[] next = new int[ MAX_WORD_LENGTH];
        public Node(String word){
            this.word = word;
        }
    }
    private Node root = new Node(null);
    public void add( String word){
        Node node = new Node( word);
        root = add( root, node);
    }
    private Node add(Node root, Node node){
        if( root.word ==null){
            return node;
        }
        int dist = editDistance( node.word, root.word);
        if( root.next[dist]==0) {
            root.children.put(dist, new ArrayList<>());
        }
        root.next[dist]++;
        if( root.next[dist] <= R) {
            root.children.get(dist).add( node);
        }
        else {
            int n =  root.next[dist]% R;
            Node next =  root.children.get(dist).get( n );
            next = add( next,  node);
        }
        return root;
    }
    private int iterate;

    public List<String> similarWords(String s){
        iterate =0;
        List<SearchWord> searchWords = similar(root , s);
        List<String> result = new ArrayList<>();
        searchWords.sort((o1, o2) -> {
            if( o1.dis == o2.dis){
                return 0;
            }
            if( o1.dis > o2.dis){
                return 1;
            }
            return -1;
        });
        searchWords.forEach(sw->{
            result.add(sw.word);
        });
        System.out.println("iterate : "+iterate);
        return result;
    }
    private static class SearchWord {
        int dis;
        String word;
    }
    private List<SearchWord> similar(Node node,String word){
        iterate++;
        List<SearchWord> found = new ArrayList<>();
        if( node == null){
            return found;
        }
        int dist = editDistance( node.word , word);
        System.out.println(" c "+node.word +" - >"+dist);
        if( dist <=  TOLORRENCE_VALUE) {
            SearchWord s = new SearchWord();
            s.dis = dist;
            s.word = node.word;
            found.add(s);
        }
        int start = dist - TOLORRENCE_VALUE;
        if( start < 0) {
            start = 1;
        }
        System.out.print(" indices : ");
        for(int k: node.children.keySet()){
            System.out.print(""+k +":"+node.children.get(k).size() +" \t");
        }
        System.out.println();
        while (start < dist + TOLORRENCE_VALUE) {
            if( node.children.containsKey( start)) {
                //System.out.println("found dist :"+start);
                for( Node c : node.children.get( start)) {
                    int tmpDist = editDistance( c.word , word);
                    System.out.println("check on "+c.word + " ->" +tmpDist);
                    if( tmpDist <= TOLORRENCE_VALUE) {
                        SearchWord s = new SearchWord();
                        s.dis = dist;
                        s.word = node.word;
                        found.add(s);//todo check
                    }
                    else {
                        if( c.children.containsKey(tmpDist)) {
                            
                        }
                    }
                }
            }
            else {
               // System.out.println(" no dist of start "+start);
            }

            start++;
        }
        return found;
    }

}
