package hu.nagyj.mongodb.dao;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import hu.nagyj.mongodb.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public CarDao(MongoClient mongoClient) {
        this.database = mongoClient.getDatabase("cars");
        this.collection = this.database.getCollection("cars");
    }

    public void Create(Car car) {
        this.collection.insertOne(car.getDocument());
    }

    public void CreateCars(List<Car> cars) {
        List<Document> carDocuments = cars.stream().map(x -> x.getDocument()).toList();
        this.collection.insertMany(carDocuments);
    }

    public void updateOldCarsColor() {
        Document updateObject = new Document("$set", new Document("color", "Red"));
        this.collection.updateMany(new Document("year", new Document("$lt", 2000)), updateObject);
    }

    public MongoCursor<Document> GetByPlateNumber(String plateNumber) {
        MongoCursor<Document> documents = this.collection.find(new Document("plateNumber", plateNumber)).iterator();
        return documents;
    }

    public MongoCursor<Document> GetAll() {
        MongoCursor<Document> documents = this.collection.find(new Document()).iterator();
        return documents;
    }

    public void Delete(String plateNumber) {
        this.collection.deleteOne(new Document("plateNumber", plateNumber));
    }

    public void Clean() {
        this.collection.deleteMany(new Document());
    }
}
