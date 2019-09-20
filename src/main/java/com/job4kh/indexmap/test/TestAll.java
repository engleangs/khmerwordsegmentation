package com.job4kh.indexmap.test;

import com.job4kh.indexmap.*;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.FileLoader;

import java.util.List;

public class TestAll {
    static String[] words = {
        "ក្រុម\u200Bហ៊ុន\u200Bសុំ\u200Bពន្យារ\u200Bពេល\u200Bបញ្ជួន\u200Bសំរាម\u200B៦៩\u200Bកុង\u200Bតឺន័រទៅ\u200Bកាន់\u200Bប្រទេស\u200Bដើម\u200Bវិញ\u200B - កម្ពុជា - RFI ព័ត៌មាន\u200Bថ្មីៗ\u200Bពី\u200Bប្រទេស\u200Bកម្ពុជា និង\u200Bជុំវិញ\u200Bពិភពលោក ជាមួយ\u200Bវិទ្យុបារាំង\u200Bអន្តរជាតិ RFI ខេមរភាសា ទំព័រ\u200Bដើម កម្ពុជា អាស៊ី អឺរ៉ុប អាមេរិក ដើមបូព៌ា អាហ្វ្រិក សង្គម-វប្បធម៌ សេដ្ឋកិច្ច នយោបាយ ច្បាប់-តុលាការ បទវិភាគ ការផ្សាយប្រចាំថ្ងៃ ពាក្យ ចាប់ផ្តើម\u200Bស្វែងរក Filters រៀនភាសាបារាំង RFI បទភ្លេង ស្វែង"
    };

   static SegmentModel updateModel(SegmentResult segmentResult, SegmentModel model) {
        model.incrError( segmentResult.getTotalError());
        model.incrSegment( segmentResult.getTotalSegment());
        model.addWord( segmentResult.getWords());
        return model;
    }
    static String getResult(SegmentModel segmentModel){
       StringBuilder builder = new StringBuilder();
       builder.append("Total segment : ").append( segmentModel.getTotalSegmentation()).append("\n");
       builder.append("Total error :").append( segmentModel.getTotalError()).append("\n");
       segmentModel.getWords().forEach(it->builder .append("[").append(it).append("]\t"));
       return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        DataLoader dataLoader = new FileLoader("data/wordset.txt");
        KhmerWordTrainData khmerWordTrainData =  new KhmerWordTrainData(dataLoader);
        AllIndexCollection allIndexCollection = IndexUtil.allIndexCollection( khmerWordTrainData.getWordSet());
        final ForwardSegmentWord forwardSegmentWord = new ForwardSegmentWord( allIndexCollection.getIndexCollection(), allIndexCollection.getNumericComputedIndexCollection(),  khmerWordTrainData.getWordSet());
        final BackwardSegmentWord backwardSegmentWord = new BackwardSegmentWord( allIndexCollection.getIndexCollection(), allIndexCollection.getNumericComputedIndexCollection(),  khmerWordTrainData.getWordSet());
        final BidirectionalSegmentWord bidirectionalSegmentWord = new BidirectionalSegmentWord( allIndexCollection.getIndexCollection(),allIndexCollection.getNumericComputedIndexCollection(), khmerWordTrainData.getWordSet());
        CleanTextUtil cleanTextUtil = new  CleanTextUtil(KhmerCharacterSet.SPACE_SET,KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET);

        for (String word:words) {
            SegmentModel forwardModel = new SegmentModel();
            SegmentModel backwardModel = new SegmentModel();
            SegmentModel bidirectionalModel  = new SegmentModel();
            List<CleanTextUtil.CleanText> cleanTexts = cleanTextUtil.cleanText( word);
            for (CleanTextUtil.CleanText cleanText : cleanTexts)
            {



                if( cleanText.getType() == CleanTextUtil.TextType.Number
                        || cleanText.getType() == CleanTextUtil.TextType.English
                        || khmerWordTrainData.getWordSet().contains( cleanText.getPhrase()) ) {
                    forwardModel.incrSegment(1);
                    forwardModel.addWord( cleanText.getPhrase());
                    backwardModel.incrSegment(1);
                    backwardModel.addWord( cleanText.getPhrase());
                    System.out.println("No segment : "+cleanText.getPhrase());
                    continue;
                }
                System.out.println("=========================================");
                System.out.println("to segment :"+cleanText.getPhrase());
                System.out.println("=========================================");
                SegmentResult segmentResult  =  forwardSegmentWord.doSegment( cleanText.getPhrase());
                forwardModel = updateModel( segmentResult, forwardModel);
                SegmentResult backResult =  backwardSegmentWord.doSegment( cleanText.getPhrase() );
                backwardModel  = updateModel( backResult , backwardModel);
                SegmentResult biDirectionalResult = bidirectionalSegmentWord.doSegment( cleanText.getPhrase());
                bidirectionalModel = updateModel( biDirectionalResult, bidirectionalModel);

            }
            System.out.println("Forward :");
            System.out.println( getResult( forwardModel) );
            System.out.println( getResult( backwardModel ));
            System.out.println( getResult( bidirectionalModel ));

        }
    }
}
