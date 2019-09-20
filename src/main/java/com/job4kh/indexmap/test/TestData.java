package com.job4kh.indexmap.test;

public class TestData {
    private int totalSegment;
    private int totalError;
    private  int total;
    private int totalLength;
    private long duration;
    private String url;
    private String testName;
    private int count;
    public static TestData copy(TestData testData){
        TestData instance = new TestData( testData.totalSegment, testData.totalError,testData.total,testData.totalLength,testData.duration);
        instance.setTestName( testData.getTestName());
        return instance;
    }
    public TestData(int totalSegment, int totalError, int total, int totalLength, long duration,String url,String testName){
        this( totalSegment, totalError , total, totalLength , duration);
        this.url = url;
        this.testName = testName;
    }

    public TestData(int totalSegment, int totalError, int total, int totalLength, long duration,String url){
        this( totalSegment, totalError , total, totalLength , duration);
        this.url = url;
    }

    public TestData(int totalSegment, int totalError, int total, int totalLength, long duration) {
        this.totalSegment = totalSegment;
        this.totalError = totalError;
        this.total = total;
        this.totalLength = totalLength;
        this.duration = duration;
    }
    public void add(TestData testData) {
        this.total += testData.getTotal();
        this.totalError += testData.getTotalError();
        this.totalSegment += testData.getTotalSegment();
        this.totalLength += testData.getTotalLength();
        this.duration += testData.getDuration();
    }

    public int getTotalSegment() {
        return totalSegment;
    }

    public void setTotalSegment(int totalSegment) {
        this.totalSegment = totalSegment;
    }

    public int getTotalError() {
        return totalError;
    }

    public void setTotalError(int totalError) {
        this.totalError = totalError;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
