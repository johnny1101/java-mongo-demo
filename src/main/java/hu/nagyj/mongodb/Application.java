package hu.nagyj.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import hu.nagyj.mongodb.connection.MongoConnectionFactory;
import hu.nagyj.mongodb.dao.CarDao;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import hu.nagyj.mongodb.model.Car;

public class Application {
    public static void main(String[] args) {
        MongoConnectionFactory connectionFactory = new MongoConnectionFactory();
        MongoClient dbConnection = connectionFactory.CreateConnection();
        CarDao dao = new CarDao(dbConnection);

        System.out.println("Insert new cars...");
        List<Car> cars = new ArrayList<Car>();
        cars.add(new Car("abc-123", "Ford", "Escort", "White", 1988));
        cars.add(new Car("abc-124","Opel", "Antara", "Grey", 2012));
        cars.add(new Car("abc-125","Toyota", "Corolla", "Black", 2002));
        cars.add(new Car("abc-126","Renault", "5", "Silver", 1982));
        dao.createCars(cars);
        System.out.println("Inserted cars.");
        printCursor(dao.GetAll());

        // INSERT
        // UPDATE

        System.out.println("Get car which has abc-123 plate.");
        printCursor(dao.GetByPlateNumber("abc-123"));
    }

    private static void printCursor(MongoCursor<Document> mongoCursor) {
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }
}
