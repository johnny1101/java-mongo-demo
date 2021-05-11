package hu.nagyj.mongodb.connection;

import com.mongodb.*;

public class MongoConnectionFactory {

    private MongoClient mongoClient;

    public MongoClient CreateConnection() {
        if (this.mongoClient == null) {
            this.mongoClient = new MongoClient("localhost", 27017);
        }
        return this.mongoClient;
    }
}
