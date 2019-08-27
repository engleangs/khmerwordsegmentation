package com.job4kh.crawler;

public class CrawlWebProp
{
    private String url;
    private String itemKey;
    private String titleKey;
    private String descKey;
    private String contentKey;
    private String urlKey;


    public CrawlWebProp(String url, String itemKey, String titleKey, String descKey, String contentKey, String urlKey) {
        this.url = url;
        this.itemKey = itemKey;
        this.titleKey = titleKey;
        this.descKey = descKey;
        this.contentKey = contentKey;
        this.urlKey = urlKey;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public String getDescKey() {
        return descKey;
    }

    public void setDescKey(String descKey) {
        this.descKey = descKey;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    @Override
    public String toString() {
        return this.url;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }
}
