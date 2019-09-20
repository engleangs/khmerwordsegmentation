package com.job4kh.crawler;

public class BaseWebsiteConfig {
    public static final String[] WEBISTES = {
            "https://www.rfa.org/khmer/indepth/RSS",
            "http://km.rfi.fr/general/rss",
            "https://khmer.voanews.com/rssfeeds"
    };

    public static final CrawlWebProp[] CRAWL_WEB_PROPS = {
            new CrawlWebProp("https://www.rfa.org/khmer/indepth/RSS", "item", "title","description","content|encoded", "link"),
            new CrawlWebProp("http://km.rfi.fr/general/rss", "item", "title","description","content", "link"),
            new CrawlWebProp("https://khmer.voanews.com/api/z$ytretqur", "item", "title","description","content", "link"),
    };

}
