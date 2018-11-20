import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author Ccc
 * @date 2018/11/17 0017 下午 3:14
 */
public class test {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("120.78.128.136");
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        Document document1 = new Document();
        spit.insertOne(document1);

        FindIterable<Document> documents = spit.find();


        for (Document document : documents) {
            String s = document.toJson();
            System.out.println(s);
        }
        mongoClient.close();
    }
}
