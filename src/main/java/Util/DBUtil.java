package Util;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.*;


public class DBUtil{

    static MongoClient mongoclient;
    static MongoDatabase database;
    static MongoCollection<Document> dbcollection;

    public static MongoCollection getConnection () {
        mongoclient = new MongoClient("localhost", 27017);
        database =  mongoclient.getDatabase("testDB");
        dbcollection = database.getCollection("sampleCollection");
        return dbcollection;
    }

public void InsertDocument(List<String> data){

    //Insert Array List,  took help from Google
    dbcollection = DBUtil.getConnection();
    Document document = new Document("_id", "ArrayList")
            .append("data", data);

    dbcollection.insertOne(document);
    DBUtil.closeConnection();

}

public static List<String> retrieveDocument(){

    List<String> retrieveData = new ArrayList<String>();
    dbcollection = DBUtil.getConnection();
    FindIterable<Document> docs = dbcollection.find();
     for(Document doc: docs){
        retrieveData.add(doc.getString("CustomerName"));
        retrieveData.add(doc.getString("email"));
        retrieveData.add(doc.getString("FlightFrom"));
        retrieveData.add(doc.getString("FlightTo"));
        retrieveData.add(doc.getString("address"));
        retrieveData.add(doc.getString("Details"));
      }
   DBUtil.closeConnection();
   return retrieveData;
}

public static void closeConnection(){
try {
    mongoclient.close();
}catch (MongoClientException e){

}
    }
}




