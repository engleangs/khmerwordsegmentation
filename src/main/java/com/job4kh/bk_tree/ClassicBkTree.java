package com.job4kh.bk_tree;

import java.util.*;

import static com.job4kh.bk_tree.BkTreeBuilder.editDistance;

/**
 * BK Tree implementation
 * using this link https://www.geeksforgeeks.org/bk-tree-introduction-implementation/
 */
public class ClassicBkTree {
    public static final int MAX_WORD_LENGTH = 45;
    public static final int MAX_WORD_NUMBER = 40000;
    public static final int TOLORRENCE_VALUE = 2;
    private int iterate;
    private static class SearchWord{
        int distance;
        String word;
    }

    private static   class Node{
        String word;
        int next[]  = new int[ MAX_WORD_LENGTH * 2];
        public Node(String word){
            this.word = word;
        }
    }
    Node RT = new Node(null);
    Map<Integer,Node> tree = new HashMap<>();
    int ptr;
    public void add(String word){
        Node node = new Node( word);
        RT = add( RT , node);
    }
    private Node add(Node root, Node current){
        if( root.word==null){
            root = current;
            return root;
        }
        int dist = editDistance( current.word, root.word);
        if( tree.get(root.next[ dist]) ==null){
            ptr++;
            tree.put(ptr, current);
            root.next[dist] = ptr;
        }
        else {
            int inx = root.next[dist];
            Node node = add( tree.get( inx) , current);
            tree.put(inx, node);
        }
        return root;
    }

    public List<String> similarWords(String s){
        iterate =0;
        List<SearchWord> searchWords = getSimilar(RT , s);
        List<String> result = new ArrayList<>();

        searchWords.sort((o1, o2) -> {
            if( o1.distance == o2.distance){
                return 0;
            }
            if( o1.distance > o2.distance){
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
    private List<SearchWord> getSimilar(Node node, String word){
        iterate++;
        List<SearchWord>result = new ArrayList<>();
       if( node == null){
           return result;
       }
       int dist = editDistance( node.word , word);
        System.out.println(" c "+node.word +" - >"+dist);
       if( dist <=  TOLORRENCE_VALUE) {
         SearchWord s = new SearchWord();
         s.distance = dist;
         s.word = node.word;
         result.add(s);
       }
       int start = dist - TOLORRENCE_VALUE;
       if( start < 0) {
           start = 1;
       }
       while (start < dist + TOLORRENCE_VALUE) {
           int inx = node.next[ start];
           List<SearchWord>tmp = getSimilar( tree.get( inx) , word);
           if( tmp.size() > 0){
               result.addAll( tmp);
           }
           start++;
       }
       return result;
    }
    public static void main(String[] args){
        ClassicBkTree classicBkTree = new ClassicBkTree();
        // dictionary words
        String dictionary[] = {"hell","help","shel","smell",
                "fell","felt","oops","pop","oouch","halt"
        };
        for(String st:dictionary){
            classicBkTree.add( st);
        }
        String w1 = "ops";
        System.out.println("similar of : "+w1);
        List<String>similarities = classicBkTree.similarWords( w1);
        for(String w: similarities){
            System.out.println(" "+w);
        }
        String w2 = "helt";
        System.out.println("similar of : "+w2);
        similarities = classicBkTree.similarWords( w2);
        for(String w: similarities){
            System.out.println(" "+w);
        }

    }

}
