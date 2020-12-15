package cn.edu.bjtu.perception.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "equipment")
public class Equipment {
    String institution;
    String name;
    String unit;
    String category;
    int num;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date entry;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date manufacture;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Date getManufacture() {
        return manufacture;
    }

    public void setManufacture(Date manufacture) {
        this.manufacture = manufacture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
