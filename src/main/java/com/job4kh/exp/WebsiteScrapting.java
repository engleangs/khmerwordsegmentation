package com.job4kh.exp;

import com.job4kh.crawler.BasicWebCrawler;
import com.job4kh.crawler.SiteContent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WebsiteScrapting {

    static void  fetchUrl() throws InterruptedException, IOException {
        System.out.println("Hello world");

        BasicWebCrawler.ScraptingUrlProp freshNewsProp = new BasicWebCrawler.ScraptingUrlProp("http://www.freshnewsasia.com", "http://www.freshnewsasia.com/index.php/en/",
                Arrays.asList("div[class=content-category]","table","td[class=list-title]"),"a.href","a");
        BasicWebCrawler basicWebCrawler = new BasicWebCrawler();
        int start = 50;
        String url = "http://www.freshnewsasia.com/index.php/en/";

        for(int i =0;i<1000;i++){
            List<BasicWebCrawler.WebUrl> urls  =basicWebCrawler.getWebUrl( freshNewsProp);
            MongoDbConnection.getInstance().putUrl("freshnews" ,urls);
            Thread.sleep(1000);
            freshNewsProp.setUrl( url+"?start="+start);
            System.out.println("urls :"+urls.size());
            start = start+50;
        }
        System.out.println("finished");

    }

    static void fetchContent() throws  InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

        BasicWebCrawler basicWebCrawler = new BasicWebCrawler();
       ConcurrentLinkedQueue<SiteContent>queue = new ConcurrentLinkedQueue<>();
       List<BasicWebCrawler.WebUrl>urls = SiteDaoHelper.getUrls();
        CountDownLatch countDownLatch = new CountDownLatch( urls.size() );
        for(BasicWebCrawler.WebUrl url : urls) {
            threadPoolExecutor.execute(()->{
                try {
                    System.out.println("begin to get url : "+url.getUrl());
                    if( url.getUrl().startsWith("http://www.freshnewsasia.comhttp://plus.freshnewsasia.com"))
                    {
                        url.setUrl( url.getUrl().replace("http://www.freshnewsasia.comhttp://plus.freshnewsasia.com","http://www.freshnewsasia.com"));
                    }
                     SiteContent siteContent =    basicWebCrawler.getSiteContent( url.getUrl(),"div[itemprop=articleBody]");
                     if( siteContent != null) {
                         queue.add( siteContent );
                     }
                     Thread.sleep(100);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("remaining :"+ countDownLatch.getCount());
            });
        }
        countDownLatch.await();
       SiteDaoHelper.putSites( queue,"freshnews");
    }

    public static void main(String[]args) throws IOException, InterruptedException {
        fetchContent();
//        fetchUrl();

    }
}
