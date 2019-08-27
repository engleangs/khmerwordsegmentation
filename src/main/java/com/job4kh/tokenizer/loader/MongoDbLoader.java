package com.job4kh.tokenizer.loader;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Set;
import java.util.function.Consumer;

public class MongoDbLoader implements DataLoader
{
    private String collectionName;
    private MongoDatabase mongoDatabase;

    public MongoDbLoader(String collectionName, MongoDatabase mongoDatabase){
        this.collectionName = collectionName;
        this.mongoDatabase = mongoDatabase;
    }


    @Override
    public void load(Set<String> wordSet) throws Exception {

        FindIterable<Document> documents = mongoDatabase.getCollection(collectionName).find();
        documents.forEach((Consumer<Document>) document -> {
            String word =    document.getString("word");
            wordSet.add( word);

        });
    }

    @Override
    public int getMaxLength() {
        return 0;
    }
}
