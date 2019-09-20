package com.job4kh.indexmap.test;

import com.job4kh.exp.SiteDaoHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportTestData {

    static String[] testNames = {
            "forward_segment",
            "backward_segment",
            "bidirectional_segment",
            "khmer_line_breaker"
    };

    public static List<TestData> fetchData(String testName) {
        List<TestData> items = SiteDaoHelper.fetch( testName);
        List<TestData>result = new ArrayList<>();
        int inx = 1;
        TestData data  =new TestData( 0, 0, 0,0,0);
        data.setTestName( testName);
        for(TestData item :items) {
            data.add( item);
            if( inx % 1000 ==0) {
                data.setCount( inx );
                result.add( data );
                data = TestData.copy( data);
            }
            inx++;
        }

        return result;

    }


    public static void main(String[] args) throws IOException {
        List<List<TestData>>result = new ArrayList<>();
        for(String testName : testNames) {
            result.add( fetchData( testName));
        }
        String fileName = System.getProperty("user.dir")+"/data/report.csv";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
        StringBuilder reportBuilder = new StringBuilder("No webpage,Total segment,Total error , Total word, No character,Duration,Method Name");

        for(int i =0;i<result.get(0).size();i++) {
            for(List<TestData>testDataList : result) {
                TestData testData = testDataList.get( i);
                reportBuilder.append("\n");
                reportBuilder.append( testData.getCount()).append(",")
                        .append( testData.getTotalSegment())
                        .append(",")
                        .append(testData.getTotalError())
                        .append(",")
                        .append(testData.getTotal())
                        .append(",")
                        .append(testData.getTotalLength())
                        .append(",")
                        .append(testData.getDuration())
                        .append( ",")
                        .append( testData.getTestName())
                ;
            }
        }

        bufferedWriter.write(  reportBuilder.toString() );
        bufferedWriter.flush();
    }
}
