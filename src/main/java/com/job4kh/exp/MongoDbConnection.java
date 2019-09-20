package com.job4kh.exp;

import com.job4kh.crawler.BasicWebCrawler;
import com.job4kh.crawler.WebItem;
import com.job4kh.crawler.WebPage;
import com.job4kh.tokenizer.task.SegmentTask;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class MongoDbConnection
{
    public static final String DB_NAME = "db_khmer_word";
    public static final String WORD_COLLECTION = "words";
    public static final String WEB_ITEM_COLLECTION = "web_items";
    public static final String WEB_PAGE_COLLECTION = "web_pages";
    public static final String SEGMENT_COLLECTION ="segment_collection";
    private MongoClient mongoClient;

    private MongoDbConnection()
    {
            this.mongoClient = MongoClients.create();//todo get from config file
    }

    public static MongoDbConnection getInstance()
    {
        return InnerMongoDb.connection;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase()
    {
       return mongoClient.getDatabase( DB_NAME);
    }

    public MongoCollection<Document> getTextIndexCollection()
    {
        return getDatabase().getCollection( WORD_COLLECTION);
    }

    public void addWord(List<String> words)
    {
        List<Document>items = new ArrayList<>();
        words.forEach(it->{
            items.add(new Document("word",it) );

        });
        getTextIndexCollection().insertMany( items );
    }

    public long getTotalWord()
    {
        return getTextIndexCollection().countDocuments();
    }

    public void addWord(String word)
    {

        if( find( word) == 0)
        {
            getTextIndexCollection().insertOne( new Document("word", word));
        }

    }

    public long find(String word)
    {

        return getTextIndexCollection().countDocuments( Filters.eq("word",word));
    }


    private static class InnerMongoDb
    {
        private static MongoDbConnection connection = new MongoDbConnection();
    }


    public void putWebItems(List<WebItem>items)
    {
        for(WebItem item:items)
        {
            Bson filter = Filters.eq("_id", item.getUrl());
            Bson doc = new Document("$set",new Document( "_id",item.getUrl())
                    .append("title",item.getTitle())
                    .append("content", item.getContent())
                    .append("updated",new Date())
                    .append("description",item.getDescription())
                    .append("text", item.getRawText())
            );
            UpdateOptions options = new UpdateOptions().upsert(true);
            getDatabase().getCollection( WEB_ITEM_COLLECTION).updateOne( filter, doc, options);
        }
    }

    public void putWebPage(WebPage webPage)
    {
        Bson filter = Filters.eq("_id", webPage.getUrl());
        Bson doc = new Document("$set",new Document("_id", webPage.getUrl())
                .append("content" , webPage.getContent())
                .append("feature_image",webPage.getFeatureImage())
                .append("text", webPage.getText())
                .append("updated", new Date())
        );
        UpdateOptions options = new UpdateOptions().upsert(true);
        getDatabase().getCollection( WEB_PAGE_COLLECTION).updateOne( filter,doc,options);
    }


    public  List<WebPage>ListAllWebPage()
    {
        List<WebPage> items = new ArrayList<>();

         getDatabase().getCollection( WEB_PAGE_COLLECTION).find().forEach((Consumer<Document>) document ->{
             WebPage webPage   =  new WebPage( document.get("_id").toString(),document.getString("content"),
                     document.getString("text"),
                     document.getString("feature_image"));
             items.add( webPage);
         });

        return items;
    }
    public List<WebItem> ListAllWebItems(){
        List<WebItem> items = new ArrayList<>();
        getDatabase().getCollection( WEB_PAGE_COLLECTION).find().forEach((Consumer<Document>) document ->{
            WebItem webItem   =  new WebItem( document.get("_id").toString()
                    ,document.getString("title"),
                    document.getString("description"),
                    document.getString("content"),
                    document.getString("text")
                    );
            items.add( webItem);
        });
        return items;
    }


    public void  putSegmentResult(List<SegmentTask>items,String type)
    {
        List<Document>documents = new ArrayList<>();
        for(SegmentTask item : items)
        {



            Document document = new Document("url",item.getUrl())
                    .append("words",  item.getSegmentResult().getWords())
                    .append("errors",item.getSegmentResult().getErrors())
                    .append("take",item.getSegmentResult().getTakeTime())
                    .append("totalError",item.getSegmentResult().getTotalError())
                    .append("type",type)
                    .append("crated",new Date())

                    ;
            documents.add( document);
        }

        getDatabase().getCollection( SEGMENT_COLLECTION ).insertMany( documents);
    }

    public void putUrl(String siteName, List<BasicWebCrawler.WebUrl>urls) {
        List<Document> documents = new ArrayList<>();
        for(BasicWebCrawler.WebUrl url:urls) {
            Document document = new Document("url",url.getUrl()).append("title",url.getTitle());
            documents.add( document);
        }
        getDatabase().getCollection("web_url").insertMany( documents);
    }




}
