package com.job4kh.tries.segment;

import com.job4kh.tokenizer.LongestMatchingTokenizer;
import com.job4kh.tokenizer.SegmentResult;
import com.job4kh.tokenizer.data.KhmerCharacterSet;
import com.job4kh.tokenizer.data.KhmerWordTrainData;
import com.job4kh.tokenizer.loader.DataLoader;
import com.job4kh.tokenizer.loader.FileLoader;
import com.job4kh.tries.TinerySearchTreeBuilder;

public class SegmentExp {
    public static void main(String[] args) throws Exception {
        System.out.println("hello");
        String text = "ថ្ងៃទី២៩ ខែធ្នូ ឆ្នាំ២០២០ ត្រូវបានគណបក្សកាន់អំណាចចាត់ទុកជាថ្ងៃខួប២២ឆ្នាំនៃនយោបាយឈ្នះឈ្នះ ដែលផ្តើមឲ្យមានការបញ្ចប់សង្រ្គាមស៊ីវិលនៅកម្ពុជា។ លោក ហ៊ុន សែន ឆ្លៀតឱកាសនេះ បានលើកឡើងពីស្នាដៃ ដែលនាំដល់ការបង្កើតនយោបាយឈ្នះឈ្នះរបស់លោកនាពេលនោះ។ ក៏ប៉ុន្តែនៅក្នុងស្ថានភាពបច្ចុប្បន្ននេះ តើហេតុអ្វីលោក ហ៊ុន សែន មិនប្រើនយោបាយឈ្នះឈ្នះ ជាមួយលោកសម រង្ស៊ី?";
        LongestMatchTk longestMatchTk = new LongestMatchTk(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, TinerySearchTreeBuilder.fromFile("data/wordset.txt"));
        SegmentResult segmentResult = longestMatchTk.tokenize(text);
        System.out.println(" result : " + segmentResult);
        DataLoader dataLoader = new FileLoader("data/wordset.txt");
        KhmerWordTrainData khmerWordTrainData = new KhmerWordTrainData(dataLoader);
        LongestMatchingTokenizer wordTokenizer = new LongestMatchingTokenizer(KhmerCharacterSet.SPACE_SET, KhmerCharacterSet.DELIMITER, KhmerCharacterSet.DIGIT_SET, khmerWordTrainData);
        SegmentResult result = wordTokenizer.tokenize(text);
        System.out.println("result : " + result);

    }
}
