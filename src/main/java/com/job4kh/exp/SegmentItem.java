package com.job4kh.exp;

import java.util.Date;

public class SegmentItem
{
    private String webUrl;
    private Date created;
    private String[] words;
    private String[] errors;
    private String strategy;



    public SegmentItem(String webUrl, Date created, String[] words, String[] errors, String strategy) {
        this.webUrl = webUrl;
        this.created = created;
        this.words = words;
        this.errors = errors;
        this.strategy = strategy;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String[] getErrors()
    {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
