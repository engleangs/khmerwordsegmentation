package com.job4kh.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

// TODO: 3/1/19 get content and convert website into object
// TODO: 3/1/19 then save it into db

public class BasicWebCrawler {
    private HashSet<String> links;
    public BasicWebCrawler()
    {

        links = new LinkedHashSet<>();
    }
    public void getPageLinks(String URL)
    {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!links.contains(URL))
        {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL))
                {
                    System.out.println(URL);
                }


                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                //5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage)
                {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public String getText(String URL ) throws IOException
    {
        Document document = Jsoup.connect(URL).get();
        return document.text();

    }

    private String getElementItemText(Element element,String key){
        Elements items = element.select( key);
        if( items.size() > 0)
        {
            return items.get(0).text();
        }
        return "";
    }

    public List<WebItem>fetch(CrawlWebProp prop) throws IOException
    {
        Document document= Jsoup.connect(prop.getUrl()).get();
        Elements elements  =document.select( prop.getItemKey());
        List<WebItem> items  = new ArrayList<>();
        for(Element element:elements)
        {
            WebItem webItem = new WebItem( );
            webItem.setTitle( getElementItemText( element, prop.getTitleKey()));
            webItem.setUrl( getElementItemText( element, prop.getUrlKey()));
            webItem.setContent( getElementItemText( element , prop.getContentKey()));
            webItem.setDescription( getElementItemText(element,prop.getDescKey()));
            webItem.setRawText( element.text());

            items.add( webItem);
        }
        return  items;

    }


    public  WebPage getPage(String url, CrawlWebProp prop) throws IOException {
        Document document = Jsoup.connect( url).get();
        String text =  document.text();
        String img = document.select("meta[property=og:image]").attr("content");
        return new WebPage( url, document.html(),text,img);

    }

    public List<WebUrl>getWebUrl(ScraptingUrlProp prop) throws IOException {
        List<WebUrl> urls = new ArrayList<>();
        System.out.println("begin to fetch : "+prop.getUrl());
        Document document = Jsoup.connect(prop.url).get();
        int i =0 ;
        Elements elements;
        Element  queryDoc = document;
        do {
            elements = queryDoc.select( prop.querySelection.get(i) );
            if( elements.size() == 0){
                break;
            }
            queryDoc = elements.get(0);

            i++;
        }while ( i < prop.querySelection.size());
        if( elements.size() > 0) {
            for(Element element : elements) {

                String title =  getValOrAttribute( prop.titleQuery, element);
                String url =  prop.baseUrl + getValOrAttribute( prop.urlQuery,element);
                urls.add( new WebUrl( url ,title));
            }
        }
        return urls;
    }

    public SiteContent getSiteContent(String url, String... queries) throws IOException {
        Document document = Jsoup.connect(url).get();
        int i =0 ;
        Elements elements;
        Element  queryDoc = document;
        do {
            elements = queryDoc.select( queries[i] );
            if( elements.size() == 0){
                break;
            }
            queryDoc = elements.get(0);

            i++;
        }while ( i < queries.length);
        if(queryDoc !=null) {
            return new SiteContent( queryDoc.text() , queryDoc.html(), url);
        }
        return null;
    }
    private String getValOrAttribute(String queries,Element element) {
        String[]arr = queries.split("\\.");
        if( arr.length >1) {
            return element.select( arr[0]).attr( arr[1]);
        }
        else if ( arr.length ==1) {
            return element.select( arr[0]).text();
        }
        else {
            return element.select( queries).text();
        }

    }

    public static class WebUrl {
        private String url;
        private String title;

        public WebUrl(String url, String title) {
            this.url = url;
            this.title = title;
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
    }
    public static class ScraptingUrlProp{
        private String baseUrl;
        private String url;
        private List<String>querySelection;
        private String urlQuery;
        private String titleQuery;

        public ScraptingUrlProp(String baseUrl, String url, List<String> querySelection, String urlQuery, String titleQuery) {
            this.baseUrl = baseUrl;
            this.url = url;
            this.querySelection = querySelection;
            this.urlQuery = urlQuery;
            this.titleQuery = titleQuery;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public String getUrl() {
            return url;
        }

        public List<String> getQuerySelection() {
            return querySelection;
        }

        public String getUrlQuery() {
            return urlQuery;
        }

        public String getTitleQuery() {
            return titleQuery;
        }
    }
}
