package Util;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Iterator;
import java.util.List;


public class DBUtil{

    static MongoClient mongoclient;
    static MongoCollection<Document> collection;
    public static MongoCollection getConnection () {

        mongoclient = new MongoClient("localhost", 27017);
        MongoDatabase dbs = mongoclient.getDatabase("testdb");
        collection = dbs.getCollection("sampleCollection");
        return collection;
    }

public void InsertDocument(List<String> data){

//Insert Array List,  took help from Google
    collection = DBUtil.getConnection();
    Document document = new Document("_id", "ArrayList")
            .append("address", data);

    collection.insertOne(document);
    DBUtil.closeConnection();


}

public  void retrieveDocument(){

    collection = DBUtil.getConnection();
    FindIterable<Document> itr= collection.find();
    int i = 1;

    // Getting the iterator
    Iterator it = itr.iterator();

    while (it.hasNext()) {
        it.next();
        i++;
    }

DBUtil.closeConnection();
}

public static void closeConnection(){
try {
    mongoclient.close();
}catch (MongoClientException e){

}
    }
}




