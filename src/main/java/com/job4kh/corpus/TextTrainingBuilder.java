package com.job4kh.corpus;

import com.job4kh.bk_tree.BKTree;
import com.job4kh.bk_tree.LevensteinDistance;
import com.job4kh.exp.MongoDbConnection;
import com.job4kh.tokenizer.LongestMatchingTokenizer;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerLanguage;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.FileLoader;
import org.bson.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TextTrainingBuilder {
    private  DataLoader dataLoader;
    private KhmerWordTrainData khmerWordTrainData;
    private LongestMatchingTokenizer wordTokenizer;
    private static TextTrainingBuilder instace = null;
    private static final BKTree bkTree = new BKTree( new LevensteinDistance());

    public static BKTree getBkTree() {
        return bkTree;
    }

    public static TextTrainingBuilder getInstace() {
        if( instace == null) {
            instace = new TextTrainingBuilder();
        }
        return instace;
    }
    private TextTrainingBuilder(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
         dataLoader = new FileLoader("data/wordset.txt");
         khmerWordTrainData = new KhmerWordTrainData(dataLoader);
         wordTokenizer = new LongestMatchingTokenizer(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
    }
    public void train(String content){
        String finish = "៕";
        String body =  content.split( finish)[0];
        String endSign = "។";
        String[] text = body.split(endSign);
        List<String> listOfSententence = new ArrayList<>();
        for(String s:text){
            String w = s;
            listOfSententence.add( w);
        }
        train( listOfSententence);
    }
    public void train(List<String> listOfSentence) {
        for(String s : listOfSentence){
            SegmentResult result = wordTokenizer.tokenize(s);
            if( result.getTotalError() > 0) {
                continue;
            }
            System.out.println("result : " + result +" \n\t");
            String w1 = "_";
            String w2 = "";
            List<String> pairWords = new ArrayList<>();
            List<String>uniWords = new ArrayList<>();
            for(int i =0;i< result.getWords().size();i++){
                StringBuilder sb = new StringBuilder(w1);
                w2  = result.getWords().get(i);
                if(KhmerLanguage.isNumber( w2)) {
                    w2 = "_num";
                }
                sb.append("|").append( w2);
                pairWords.add( sb.toString());
                uniWords.add( w2);
                w1 = w2;
            }
            pairWords.add( w2 +"|_");

            MongoDbConnection.getInstance().bigramWord( pairWords);
            MongoDbConnection.getInstance().unigram( uniWords);
        }

    }
    public static List<WordBiGram> wordBiGrams(){
        List<WordBiGram> result = new ArrayList<>();
        MongoDbConnection.getInstance().getDatabase().getCollection("bigram_word").find().forEach((Consumer<Document>) document ->{
            String w = document.getString("w");
            String[] a = w.split("\\|");
            if(a.length > 1) {

                WordBiGram wordB = new WordBiGram( a[0] ,a[1],document.getInteger("count"));
                result.add( wordB);
            }
            else {
                System.out.println("not found splitter" +w);
            }

        });
        return result;
    }
    public static List<WordUniGram> wordUniGrams(){
        List<WordUniGram> result = new ArrayList<>();
        KhmerLanguage khmerLanguage = new KhmerLanguage();
        MongoDbConnection.getInstance().getDatabase().getCollection("unigram_word").find().forEach((Consumer<Document>) document ->{
             WordUniGram wordUniGram = new WordUniGram( document.getString("w"), document.getInteger("count"));
             if( khmerLanguage.isKhmer( wordUniGram.getW())) {
                 result.add( wordUniGram);
             }
        });
        return result;
    }

    public static WordTable buildWordTable(){
        String path = "data/WordList.txt";

        WordTable wordTable = new WordTable();
        List<WordUniGram> wordUniGrams = wordUniGrams();
        for(WordUniGram w: wordUniGrams) {
            wordTable.uniGram( w.getW(), w.getCount());
        }
        List<WordBiGram> wordBiGrams = wordBiGrams();
        for(WordBiGram w: wordBiGrams){
            wordTable.addBigram( w.getW1(), w.getW2(), w.getCount());
        }
        try {
            List<String> items =  Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            for(String item : items){
                String[] arr = item.split("\t");
                wordTable.count( arr[0], Integer.parseInt( arr[1]));
                bkTree.add( arr[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordTable;
    }

}
