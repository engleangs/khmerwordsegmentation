package com.job4kh.exp;

import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataSourceExp {
    static String[] data  = {"ថ្នមគោក្របី","ព្រើសចិត្ត"};
    public static void main(String[] args) throws Exception {
        System.out.println("begin to fetch data from mognodb");
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        KhmerWordTrainData khmerWordTrainData =  new KhmerWordTrainData(dataLoader);
        System.out.println("start : "+startCh);
        System.out.println("end : "+endCh);
        String fileName = System.getProperty("user.dir")+"/data/wordset.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
        khmerWordTrainData.getWordSet().forEach(it->{
            if( checkIfValidUnicode( it)) {
                try {
                    bufferedWriter.write(it+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("no word "+it);
            }
        });
        for(String word : data) {
            System.out.println("word :"+word +":"+checkIfValidUnicode( word));
        }

    }
    static int startCh = 0x1780;
    static int endCh =0x17D3;
    static boolean isValueKhmerValidCharacter(int code) {
        if( code >= startCh && code <= endCh ) {
            return true;
        }
        else if ( code == 6103) {
            return true;
        }

        return false;
    }
    static boolean checkIfValidUnicode(String str){


        for(int i=0;i<=str.length()/2;i++) {
            int chCode =(int)str.charAt(i);
            int endCode = (int)str.charAt( str.length() - i-1);
            if(! isValueKhmerValidCharacter( chCode) || !isValueKhmerValidCharacter( endCode)) {
                System.out.println( str.charAt(i) +" : "+str.charAt( str.length() -i-1)) ;
                System.out.println( chCode +" : "+  endCode) ;
                return false;
            }

        }
        return true;
    }
}
