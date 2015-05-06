import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.*;

public class Main {
    public static void main(String[] args) {

        try {

            /**** Connect to MongoDB ****/
            // Since 2.10.0, uses MongoClient
            MongoClient mongo = new MongoClient("localhost", 27017);

            /**** Get database ****/
            // if database doesn't exists, MongoDB will create it for you
            DB db = mongo.getDB("testdb");

            /**** Get collection / table from 'testdb' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection table = db.getCollection("user");

            /**** Insert ****/
            // create a document to store key and value
//            BasicDBObject document = new BasicDBObject();
//            document.put("name", "mkyong");
//            document.put("age", 30);
//            document.put("createdDate", new Date());
//            table.insert(document);
//
//            /**** Find and display ****/
//            BasicDBObject searchQuery = new BasicDBObject();
//            searchQuery.put("name", "mkyong");
//
//            DBCursor cursor = table.find(searchQuery);
//
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next());
//            }
//
//            /**** Update ****/
//            // search document where name="mkyong" and update it with new values
//            BasicDBObject query = new BasicDBObject();
//            query.put("name", "mkyong");
//
//            BasicDBObject newDocument = new BasicDBObject();
//            newDocument.put("name", "mkyong-updated");
//
//            BasicDBObject updateObj = new BasicDBObject();
//            updateObj.put("$set", newDocument);
//
//            table.update(query, updateObj);
//
//            /**** Find and display ****/
//            BasicDBObject searchQuery2
//                    = new BasicDBObject().append("name", "mkyong-updated");
//
//            DBCursor cursor2 = table.find(searchQuery2);
//
//            while (cursor2.hasNext()) {
//                System.out.println(cursor2.next());
//            }

            DBObject findQuery = new BasicDBObject().append("name", "mkyong");
            DBObject listItem = new BasicDBObject("scores", new BasicDBObject("id",123).append("code","LHA"));
            DBObject updateQuery = new BasicDBObject("$addToSet", listItem);
            table.update(findQuery, updateQuery);

            DBObject listItem2 = new BasicDBObject("scores", new BasicDBObject("id",345).append("code","ACC"));
            DBObject updateQuery2 = new BasicDBObject("$addToSet", listItem2);
            table.update(findQuery, updateQuery2);

            /**** Find and display ****/
            BasicDBObject searchQuery3
                    = new BasicDBObject().append("name", "mkyong");

            DBCursor cursor3 = table.find(searchQuery3);

            while (cursor3.hasNext()) {
                System.out.println(cursor3.next());
            }

            /**** Done ****/
            System.out.println("Done");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}