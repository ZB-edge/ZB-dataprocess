package cn.edu.bjtu.perception.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "institution")
public class Institution {
    String name;
    String category;
    String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
