package com.job4kh.indexmap;

import com.job4kh.exp.MongoDbConnection;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.MongoDbLoader;

public class TestUnknownWord {
   static String[] words = {
            //"វិបសៃត៍សម្រាប់ទូរស័ព្ទដៃ",
           //"សិទិ្ធ",
            //"អង្គការការពារសិទិ្ធមនុស្ស",
//            "ដែលនេះបណ្តាល",
//            "បើតាមរបាយការណ៍រួមរបស់អង្គការការពារសិទិ្ធមនុស្ស",
//           "សារព័ត៌មាន "
           "ក្រុម\u200Bហ៊ុន\u200Bសុំ\u200Bពន្យារ\u200Bពេល\u200Bបញ្ជួន\u200Bសំរាម\u200B៦៩\u200Bកុង\u200Bតឺន័រទៅ\u200Bកាន់\u200Bប្រទេស\u200Bដើម\u200Bវិញ\u200B - កម្ពុជា - RFI ព័ត៌មាន\u200Bថ្មីៗ\u200Bពី\u200Bប្រទេស\u200Bកម្ពុជា និង\u200Bជុំវិញ\u200Bពិភពលោក ជាមួយ\u200Bវិទ្យុបារាំង\u200Bអន្តរជាតិ RFI ខេមរភាសា ទំព័រ\u200Bដើម កម្ពុជា អាស៊ី អឺរ៉ុប អាមេរិក ដើមបូព៌ា អាហ្វ្រិក សង្គម-វប្បធម៌ សេដ្ឋកិច្ច នយោបាយ ច្បាប់-តុលាការ បទវិភាគ ការផ្សាយប្រចាំថ្ងៃ ពាក្យ ចាប់ផ្តើម\u200Bស្វែងរក Filters រៀនភាសាបារាំង RFI បទភ្លេង ស្វែង"
    };
    public static void main(String[] args) throws Exception {
        DataLoader dataLoader = new MongoDbLoader(MongoDbConnection.WORD_COLLECTION,MongoDbConnection.getInstance().getDatabase());
        KhmerWordTrainData khmerWordTrainData =  new KhmerWordTrainData(dataLoader);
        AllIndexCollection allIndexCollection = IndexUtil.allIndexCollection( khmerWordTrainData.getWordSet());
        final ForwardSegmentWord forwardSegmentWord = new ForwardSegmentWord( allIndexCollection.getIndexCollection(), allIndexCollection.getNumericComputedIndexCollection(),  khmerWordTrainData.getWordSet());
        final BackwardSegmentWord backwardSegmentWord = new BackwardSegmentWord( allIndexCollection.getIndexCollection(), allIndexCollection.getNumericComputedIndexCollection(),  khmerWordTrainData.getWordSet());

        for (String word:words) {
            SegmentResult result =  forwardSegmentWord.doSegment( word);

            System.out.println("Total known word : "+result.getWords().size());
            System.out.println("Total error :"+result.getTotalError());
            StringBuilder builder = new StringBuilder();
            result.getWords().forEach(it->{
                builder.append("[ ").append( it).append(" ] ");
            });
            System.out.println( builder.toString());
            SegmentResult backResult = backwardSegmentWord.doSegment( word);
            StringBuilder backwardBuilder = new StringBuilder();
            backResult.getWords().forEach( it->{
                backwardBuilder.append("[ ").append( it).append(" ] ");
            });
            System.out.println("============= back ward");
            System.out.println("Total known word :"+backResult.getWords().size());
            System.out.println("Total error :"+backResult.getTotalError());



        }

    }
}
