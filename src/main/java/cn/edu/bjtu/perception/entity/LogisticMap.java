package cn.edu.bjtu.perception.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "map")
public class LogisticMap {
    @Id
    String institution;
    double[] headquarters;
    double[] transport;
    double[] guarantee;
    double[] supply;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public double[] getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(double[] headquarters) {
        this.headquarters = headquarters;
    }

    public double[] getTransport() {
        return transport;
    }

    public void setTransport(double[] transport) {
        this.transport = transport;
    }

    public double[] getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(double[] guarantee) {
        this.guarantee = guarantee;
    }

    public double[] getSupply() {
        return supply;
    }

    public void setSupply(double[] supply) {
        this.supply = supply;
    }
}
