package com.job4kh.tries.segment;

import com.job4kh.exp.MongoDbConnection;
import com.job4kh.tokenizer.LongestMatchingTokenizer;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerLanguage;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.FileLoader;
import com.job4kh.tries.TinerySearchTreeBuilder;

import java.util.ArrayList;
import java.util.List;

public class SegmentExp {
    public static void main(String[] args) throws Exception {
        System.out.println("hello");
        String text = "ការ\u200Bធ្វើ\u200Bសេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនេះ ជា\u200Bផ្នែក\u200Bមួយ\u200Bដ៏\u200Bសំខាន់\u200Bដើម្បី\u200Bឲ្យ\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bនៅ\u200Bឆ្នាំ\u200B២០១៣ កាន់\u200Bតែ\u200Bសុក្រឹត និង\u200Bមាន\u200Bតម្លាភាព\u200Bសម្រាប់\u200Bគ្រប់\u200Bគណបក្ស\u200Bនយោបាយ\u200Bដែល\u200Bចូល\u200Bរួម\u200Bប្រកួត\u200Bប្រជែង\u200Bទាំង\u200Bអស់។ អង្គការ\u200Bសង្គម\u200Bស៊ីវិល\u200Bចំនួន ៤ គ្រោង\u200Bនឹង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bនៅ\u200Bចុង\u200Bខែ\u200Bកុម្ភៈ និង\u200Bនៅ\u200Bដើម\u200Bខែ\u200Bមីនា ខាង\u200Bមុខ ស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត មាន\u200Bម្ចាស់\u200Bឆ្នោត និង\u200Bអ្នក\u200Bតំណាងរាស្ត្រ\u200Bចូល\u200Bរួម ដែល\u200Bគេ\u200Bធ្វើ\u200Bឡើង\u200Bនៅ\u200Bខែត្រ\u200Bព្រះសីហនុ និង\u200Bនៅ\u200Bខែត្រ\u200Bតាកែវ។ ប្រធាន\u200Bគណៈកម្មាធិការ\u200Bអព្យាក្រឹត្យ និង\u200Bយុត្តិធម៌ ដើម្បី\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bដោយ\u200Bសេរី\u200Bនៅ\u200Bកម្ពុជា ហៅ\u200Bថា និចហ្វិក (NICFEC) លោក\u200Bបណ្ឌិត ហង្ស ពុទ្ធា បាន\u200Bឲ្យ\u200Bដឹង\u200Bនៅ\u200Bថ្ងៃ\u200Bទី\u200B១៧ កុម្ភៈ ថា ការ\u200Bរៀបចំ\u200Bសិក្ខាសាលា\u200Bស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនេះ ដើម្បី\u200Bស្វែង\u200Bរក\u200Bការ\u200Bគាំទ្រ\u200Bពី\u200Bប្រជាពលរដ្ឋ\u200Bដែល\u200Bជា\u200Bម្ចាស់\u200Bឆ្នោត និង\u200Bអ្នក\u200Bនយោបាយ ពីព្រោះ\u200Bថា សេចក្ដី\u200Bពា្រង\u200Bច្បាប់\u200Bនេះ\u200Bមាន\u200Bសារសំខាន់\u200Bណាស់\u200Bក្នុង\u200Bការ\u200Bកាត់\u200Bបន្ថយ\u200Bអំពើ\u200Bពុករលួយ និង\u200Bការ\u200Bប្រើប្រាស់\u200Bថវិកា\u200Bរបស់\u200Bគណបក្ស\u200Bនយោបាយ\u200Bដែល\u200Bគ្មាន\u200Bប្រភព\u200Bចំណូល\u200Bច្បាស់\u200Bលាស់។ អង្គការ\u200Bចំនួន ៤ សហការ\u200Bគ្នា គ្រោង\u200Bនឹង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត រួម\u200Bមាន គណៈកម្មាធិការ\u200Bដើម្បី\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bដោយ\u200Bសេរី និង\u200Bយុត្តិធម៌\u200Bនៅ\u200Bកម្ពុជា គណៈកម្មាធិការ\u200Bអព្យាក្រឹត និង\u200Bយុត្តិធម៌ ដើម្បី\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bដោយ\u200Bសេរី\u200Bនៅ\u200Bកម្ពុជា អង្គការ ដ្រាក និង\u200Bសម្ព័ន្ធ\u200Bដើម្បី\u200Bសុចរិត\u200Bភាព និង\u200Bគណនេយ្យ\u200Bសង្គម។ សិក្ខាសាលា\u200Bនេះ\u200Bនឹង\u200Bធ្វើ\u200Bឡើង\u200Bនៅ\u200Bខែត្រ\u200Bតាកែវ នៅ\u200Bថ្ងៃ\u200Bទី\u200B២៦ កុម្ភៈ និង\u200Bនៅ\u200Bខែត្រ\u200Bព្រះសីហនុ នៅ\u200Bថ្ងៃ\u200Bទី\u200B៤ មីនា ឆ្នាំ\u200B២០១៣។ លោក\u200Bបណ្ឌិត ហង្ស ពុទ្ធា បាន\u200Bសង្កត់\u200Bធ្ងន់\u200Bថា សេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ នឹង\u200Bបញ្ជូន\u200Bទៅ\u200Bរដ្ឋាភិបាល\u200Bដើម្បី\u200Bសុំ\u200Bការ\u200Bគាំទ្រ\u200Bពី\u200Bរដ្ឋាភិបាល ដើម្បី\u200Bផ្ញើ\u200Bទៅ\u200Bសភា\u200Bអនុម័ត។ មួយ\u200Bវិញ\u200Bទៀត អង្គការ\u200Bសង្គម\u200Bស៊ីវិល\u200Bក៏\u200Bស្វែង\u200Bរក\u200Bការ\u200Bគាំទ្រ\u200Bពី\u200Bសភា\u200Bដោយ\u200Bផ្ទាល់\u200Bដែរ ដើម្បី\u200Bធ្វើ\u200Bយ៉ាង\u200Bណា\u200Bឲ្យ\u200Bសភា\u200Bទទួល\u200Bយក និង\u200Bអនុម័ត\u200Bច្បាប់\u200Bនេះ\u200Bនៅ\u200Bមុន\u200Bពេល\u200Bបោះ\u200Bឆ្នោត\u200Bឆ្នំា\u200B២០១៣។ តំណាងរាស្ត្រ\u200Bគណបក្ស សម រង្ស៊ី លោក សុន ឆ័យ បាន\u200Bគាំទ្រ\u200Bចំពោះ\u200Bសេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ។ ដោយ\u200Bលោក\u200Bបាន\u200Bលើក\u200Bហេតុផល\u200Bថា ច្បាប់\u200Bស្ដី\u200Bពី\u200Bហិរញ្ញវត្ថុ\u200Bគណបក្ស\u200Bនយោបាយ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនេះ មាន\u200Bសារសំខាន់\u200Bណាស់\u200Bក្នុង\u200Bការ\u200Bធានា\u200Bដល់\u200Bលទ្ធផល\u200Bបោះ\u200Bឆ្នោត។ មជ្ឈមណ្ឌល\u200Bសិទ្ធិ\u200Bមនុស្ស\u200Bកម្ពុជា បាន\u200Bចេញ\u200Bផ្សាយ\u200Bកំណត់\u200Bត្រា\u200Bសង្ខេប\u200Bរបស់\u200Bខ្លួន\u200Bកាល\u200Bពី\u200Bសប្ដាហ៍\u200Bមុន ស្ដីពី\u200Bកំណែ\u200Bទម្រង់\u200Bនយោបាយ និង\u200Bប្រព័ន្ធ\u200Bបោះ\u200Bឆ្នោត\u200Bសម្រាប់\u200Bកម្ពុជា។ នៅ\u200Bក្នុង\u200Bកំណត់\u200Bត្រា\u200Bនេះ ក៏\u200Bបាន\u200Bសរសេរ\u200Bអំពី\u200Bគម្លាត\u200Bខុស\u200Bគ្នា\u200Bរវាង\u200Bគណបក្ស\u200Bកាន់\u200Bអំណាច និង\u200Bគណបក្ស\u200Bដទៃ\u200Bទៀត\u200Bក្នុង\u200Bការ\u200Bប្រើប្រាស់\u200Bថវិកា\u200Bនៅ\u200Bពេល\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត គឺ\u200Bគណបក្ស\u200Bប្រជាជន\u200Bកម្ពុជា បាន\u200Bចំណាយ\u200Bលុយ\u200Bច្រើន\u200Bលើស\u200Bលុប\u200Bនៅ\u200Bក្នុង\u200Bពេល\u200Bយុទ្ធនា\u200Bការ\u200Bបោះ\u200Bឆ្នោត។ អង្គការ\u200Bខុមហ្វ្រែល បាន\u200Bរក\u200Bឃើញ\u200Bថា នៅ\u200Bក្នុង\u200Bយុទ្ធនាការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bជ្រើស\u200Bរើស\u200Bក្រុមប្រឹក្សា\u200Bឃុំ សង្កាត់ កាល\u200Bពី\u200Bឆ្នាំ\u200B២០១២ នោះ គណបក្ស\u200Bប្រជាជន\u200Bកម្ពុជា បាន\u200Bប្រើ\u200Bថវិកា\u200Bច្រើន\u200Bជាង\u200Bគេ គឺ\u200Bការ\u200Bចំណាយ\u200Bក្នុង\u200Bការ\u200Bឃោសនា\u200Bនៅ\u200Bមូលដ្ឋាន\u200Bម្តង បាន\u200Bចំណាយ\u200Bថវិកា\u200Bប្រមាណ ៩\u200Bពាន់\u200Bដុល្លារ។ គណបក្ស សម រង្ស៊ី ចំណាយ ១.៥០០\u200Bដុល្លារ គណបក្ស\u200Bសិទ្ធិ\u200Bមនុស្ស ចំណាយ ៣០០\u200Bដុល្លារ គណបក្ស នរោត្តម រណឫទ្ធិ ចំណាយ ២០០\u200Bដុល្លារ និង\u200Bគណបក្ស\u200Bហ៊្វុនស៊ិនប៉ិច ចំណាយ\u200Bតិច\u200Bជាង ២០០\u200Bដុល្លារ\u200Bក្នុង\u200Bមួយ\u200Bដង\u200Bនៃ\u200Bយុទ្ធនាការ\u200Bឃោសនា\u200Bនៅ\u200Bមូលដ្ឋាន។ ប៉ុន្តែ\u200Bទោះ\u200Bបី\u200Bជា\u200Bយ៉ាង\u200Bនេះ\u200Bក្ដី សេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ\u200Bគេ\u200Bមិន\u200Bទាន់\u200Bដឹង\u200Bនៅ\u200Bឡើយ\u200Bទេ\u200Bថា សភា\u200Bនឹង\u200Bទទួល\u200Bយក និង\u200Bអនុម័ត\u200Bឲ្យ\u200Bបាន\u200Bមុន\u200Bពេល\u200Bបោះ\u200Bឆ្នោត\u200Bអាណត្តិ\u200Bទី\u200B៥ នៅ\u200Bខែ\u200Bកក្កដា ឆ្នាំ\u200B២០១៣ នេះ ឬ\u200Bយ៉ាង\u200Bណា\u200Bនៅ\u200Bឡើយ\u200Bទេ";
        LongestMatchTk longestMatchTk = new LongestMatchTk(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, TinerySearchTreeBuilder.fromFile("data/wordset.txt"));
        SegmentResult segmentResult = longestMatchTk.tokenize(text);
        System.out.println(" result : " + segmentResult);
        DataLoader dataLoader = new FileLoader("data/wordset.txt");
        KhmerWordTrainData khmerWordTrainData = new KhmerWordTrainData(dataLoader);
        String endSign = "។";
        String[]arr = text.split(endSign);

        LongestMatchingTokenizer wordTokenizer = new LongestMatchingTokenizer(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
        for(String s : arr){
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

        //

    }
}
