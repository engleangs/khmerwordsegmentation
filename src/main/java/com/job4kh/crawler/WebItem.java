package com.job4kh.crawler;

public class WebItem {
    private String url;
    private String title;
    private String description;
    private String content;
    private String rawText;

    public WebItem()
    {

    }

    public WebItem(String url, String title, String description, String content, String rawText) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.content = content;
        this.rawText = rawText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    @Override
    public String toString() {
        return title+":"+url;
    }
}
