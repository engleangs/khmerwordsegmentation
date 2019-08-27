package com.job4kh.exp;

import com.job4kh.crawler.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CrawlerExp
{
    static BasicWebCrawler basicWebCrawler;
    private static void crawl()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your website to crawl.");
        for(int i =0;i< BaseWebsiteConfig.WEBISTES.length;i++)
        {

            System.out.print(i+1);
            System.out.println(" => "+BaseWebsiteConfig.CRAWL_WEB_PROPS[i]);

        }
        int choice = scanner.nextInt();
        if( choice < BaseWebsiteConfig.WEBISTES.length +1 && choice > 0)
        {
            CrawlWebProp prop = BaseWebsiteConfig.CRAWL_WEB_PROPS[ choice - 1];
            System.out.println("Begin to scan => "+prop);
            try {
                List<WebItem> items = basicWebCrawler.fetch( prop );
                System.out.println(" fetched items "+items.size());
                if( items.size() > 0 )
                {
                    for(WebItem item : items)
                    {
                        System.out.println("========= "+ item.getTitle() +" =========================================");
                        System.out.println("********************************************");
                        System.out.println(item.getDescription());
                        System.out.println("********************************************");
                    }

                    MongoDbConnection.getInstance().putWebItems( items);

                    System.out.println("Begin to craw website content ");

                    for (WebItem item : items)
                    {
                        System.out.println(" crawling this URL : "+item);
                        WebPage webPage = basicWebCrawler.getPage( item.getUrl(), prop);
                        System.out.println("******************* Done Fetching ***************************************");
                        System.out.println( webPage.getText());
                        MongoDbConnection.getInstance().putWebPage( webPage );



                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args)
    {
        basicWebCrawler = new BasicWebCrawler();
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Please select your options:");
            System.out.println("1. Crawl websites ");
            System.out.println("2. Build index ");
            System.out.println("3. Search for text ");
            System.out.println("0. Exit");
            int select  = scanner.nextInt();
            switch (select)
            {
                case 1:
                    System.out.println("Crawl website......");
                    crawl();

                    break;
                case 0:
                    return;
            }

        }



    }
}
