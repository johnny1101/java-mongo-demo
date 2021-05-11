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
        dao.CreateCars(cars);
        System.out.println("Inserted cars.");
        printCursor(dao.GetAll());

        System.out.println("Insert a car...");
        dao.Create(new Car("abc-127", "Opel", "Astra", "Yellow", 2018));
        System.out.println("Inserted cars.");
        printCursor(dao.GetAll());

        System.out.println("Update old cars before the millennium.");
        dao.updateOldCarsColor();
        printCursor(dao.GetAll());
        System.out.println("Updated cars.");
        printCursor(dao.GetAll());

        System.out.println("Get car which has abc-123 plate.");
        printCursor(dao.GetByPlateNumber("abc-123"));
        dao.Delete("abc-123");
        System.out.println("After delete.");
        printCursor(dao.GetByPlateNumber("abc-123"));

        System.out.println("Cleanup");
        dao.Clean();
        printCursor(dao.GetAll());
    }

    private static void printCursor(MongoCursor<Document> mongoCursor) {
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }
}
