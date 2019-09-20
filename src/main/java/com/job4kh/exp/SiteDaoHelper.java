package com.job4kh.exp;

import com.job4kh.crawler.BasicWebCrawler;
import com.job4kh.crawler.SiteContent;
import com.job4kh.indexmap.test.TestData;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class SiteDaoHelper {
    public static void putSites(Collection<SiteContent> contentList, String siteName) {
        List<Document> documents  = new ArrayList<>();
        for(SiteContent siteContent: contentList ){
            Document document = new Document("content", siteContent.getContent())
                    .append("html", siteContent.getHtml())
                    .append("url",siteContent.getUrl())
                    .append("site_name",siteName)
                    ;
            documents.add( document);
        }
        MongoDbConnection.getInstance(). getDatabase().getCollection("site_content").insertMany( documents);

    }
    public static List<BasicWebCrawler.WebUrl>getUrls(){
        List<BasicWebCrawler.WebUrl>result = new ArrayList<>();
        MongoDbConnection.getInstance().getDatabase().getCollection("web_url").find().forEach((Consumer<Document>) document-> {
            result.add( new BasicWebCrawler.WebUrl( document.getString("url"), document.getString("title")));
        });
        return result;
    }

    public static List<SiteContent> getSiteContents(){
        List<SiteContent>result = new ArrayList<>();
        MongoDbConnection.getInstance().getDatabase().getCollection("site_content").find().forEach((Consumer<Document>)document->{
            result.add( new SiteContent( document.getString("content"),document.getString("html"),document.getString("url")));
        });
        return result;

    }
    public static void putTestData(Collection<TestData>testData) {
        List<Document>documents = new ArrayList<Document>();
        for(TestData item : testData) {
            Document document = new Document().append("total",item.getTotal())
                    .append("duration",item.getDuration())
                    .append("totalError",item.getTotalError())
                    .append("totalSegment",item.getTotalSegment())
                    .append("totalLength",item.getTotalLength())
                    .append("testName", item.getTestName())
                    ;
            documents.add( document);

        }
        MongoDbConnection.getInstance().getDatabase().getCollection("test_data").insertMany(documents);
    }

    public static List<TestData>fetch(String testName) {
        List<TestData> testData = new ArrayList<>();
         MongoDbConnection.getInstance().getDatabase().getCollection("test_data")
                .find(Filters.eq("testName",testName))
                .sort(Sorts.ascending("url")).forEach((Consumer<Document>)document->{
                    testData.add(
                            new TestData( document.getInteger("totalSegment"),
                                    document.getInteger("totalError") ,
                                    document.getInteger("total"),
                                    document.getInteger("totalLength"),
                                    document.getLong("duration")
                            )
                    );
         });
         return testData;
    }
}
