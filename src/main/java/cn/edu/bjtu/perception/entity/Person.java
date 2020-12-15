package cn.edu.bjtu.perception.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "person")
public class Person {
    String name;
    String institution;
    String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date birth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date enlistment;
    int party;
    String rank;
    int major;
    String degree;
    int logistic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getEnlistment() {
        return enlistment;
    }

    public void setEnlistment(Date enlistment) {
        this.enlistment = enlistment;
    }

    public int getParty() {
        return party;
    }

    public void setParty(int party) {
        this.party = party;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getLogistic() {
        return logistic;
    }

    public void setLogistic(int logistic) {
        this.logistic = logistic;
    }
}
