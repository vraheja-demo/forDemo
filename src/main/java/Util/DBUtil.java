package Util;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.Iterator;
import java.util.List;


public class DBUtil{
    public  static MongoCollection getConnection(){

        MongoClient mongoclient = new MongoClient("localhost", 27017);
        MongoDatabase dbs = mongoclient.getDatabase("testdb");
        MongoCollection<Document> collection = dbs.getCollection("sampleCollection");
        return collection;
    }

public void InsertDoscument(List<String> data){


    MongoCollection<Document> collection = DBUtil.getConnection();
    Document document = new Document("email", data.get(0))
            .append("address", data.get(1))
            .append("details", data.get(2));
    collection.insertOne(document);


}

public  void retrieveDocument(){

    MongoCollection<Document> collection = DBUtil.getConnection();
    FindIterable<Document> itr= collection.find();
    int i = 1;

    // Getting the iterator
    Iterator it = itr.iterator();

    while (it.hasNext()) {
        String list = (it.next());
        i++;
    }
}


}
}
