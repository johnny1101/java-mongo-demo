package hu.nagyj.mongodb.model;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;

@Getter
@Setter
public class Car {
    private String plateNumber;
    private String brand;
    private String type;
    private String color;
    private Integer year;

    public Car(String plateNumber, String brand, String type, String color, Integer year) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.year = year;
    }

    public Document getDocument() {
        Document doc = new Document();
        doc.append("plateNumber", this.plateNumber);
        doc.append("brand", this.brand);
        doc.append("type", this.type);
        doc.append("color", this.color);
        doc.append("year", this.year);

        return doc;
    }
}
