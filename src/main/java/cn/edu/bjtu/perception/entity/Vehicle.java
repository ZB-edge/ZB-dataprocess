package cn.edu.bjtu.perception.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicle")
public class Vehicle {
    String institution;
    String category;
    String name;
    int speed;
    int mileage;
    int rotation;
    int midOil;
    int temperature;
    int oilTemperature;
    int firstOil;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getMidOil() {
        return midOil;
    }

    public void setMidOil(int midOil) {
        this.midOil = midOil;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getOilTemperature() {
        return oilTemperature;
    }

    public void setOilTemperature(int oilTemperature) {
        this.oilTemperature = oilTemperature;
    }

    public int getFirstOil() {
        return firstOil;
    }

    public void setFirstOil(int firstOil) {
        this.firstOil = firstOil;
    }
}
