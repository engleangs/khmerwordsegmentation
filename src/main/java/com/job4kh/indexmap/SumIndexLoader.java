package com.job4kh.indexmap;

import com.job4kh.exp.MongoDbConnection;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;

public class SumIndexLoader {
    static NumericComputedIndexCollection loadAndTest() throws Exception {
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        KhmerWordTrainData khmerWordTrainData =  new KhmerWordTrainData(dataLoader);
        NumericComputedIndexCollection collection = new NumericComputedIndexCollection();
        khmerWordTrainData.getWordSet().forEach(it->{
            if( NumericComputedIndexChecker.isEligible( it)) {
                collection.index( it);
            }
        });
//        System.out.println("after collected : ");
//        System.out.println( collection);
//        System.out.println("duplicate list : ");
//        System.out.println(collection.duplicateBuilder.toString());
        return collection;
    }
    static String[] wordsForTest  = {
//            "ស្ត្រី", "កញ្ច្រែង",
//            "សិទ្ធិ",

//            "សន្សឹម", "សម្រាល", "សម្បួរ", "ស្គាក់", "សុញ្ញា","ការសុំច្បាប់",	"កាន់តែច្បាស់","ក្បាច់ក្បូរ","ក្រាក់ខ្នុរ"
//            ,"ឱ្យតម្រុយ" , "ឱ្យតម្រួយ", "ឱ្យសម្រួល",
            //"ស្រោមដាវ" , "ស្រែកសួរ",
//            "ខ្មាន់","ខ្វាត់","ផ្លូន","ផ្នូល","ស្នាប់","ស្បាន់","ស្កុល",
//            "ស្លុក","សុ្លក",
//            "រាមស្ទឹង","រោងល្ខោន",
//            "មុខស្មើ",
            "មើលគ្រូ","មើលគូ្រ",
//            "អផ្ទាញ" , "អនត្តា",
//            "អណ្ដើក", "អញ្ញាត",
//            "អារច្រៀក","អបស័ក្ដិ",
//            "អចេស្ដា","អញ្ឆើលៗ",
//            "អង្សុ", "អស្សុ",
//            "ស្នានស្ថាន",	"ស្វស្តិភាព",	"ស្កន្ធាពារ",
//            "ស្លែត","ស្វែត","ស្ទែត","ស្មោញ","ស្ពែត",
            "ស្ញាញ","សញ្ញា","សមញ្ញា","សម្ញាញ"
    };

    static void experiment(){
        for(String word : wordsForTest) {
            System.out.println( word +" => "+ NumericComputedIndexChecker.isEligible( word)  +" , length : "+ word.length() );
            NumericComputedIndexer indexer = new NumericComputedIndexer( word);
            System.out.println(indexer.getSum());
        }
    }

    public static void main(String[] args) throws Exception {

        //experiment();
        loadAndTest();
        System.out.println("finish");
        //
    }
}
