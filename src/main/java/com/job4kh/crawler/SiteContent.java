package com.job4kh.crawler;

public class SiteContent {
    private String content;
    private String html;
    private String url;

    public SiteContent(String content, String html, String url) {
        this.content = content;
        this.html = html;
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
