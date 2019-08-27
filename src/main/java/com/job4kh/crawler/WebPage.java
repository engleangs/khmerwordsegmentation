package com.job4kh.crawler;

public class WebPage {
    private String url;
    private String content;
    private String text;
    private String featureImage;

    public WebPage(String url, String content, String text, String featureImage) {
        this.url = url;
        this.content = content;
        this.text = text;
        this.featureImage = featureImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }
}
