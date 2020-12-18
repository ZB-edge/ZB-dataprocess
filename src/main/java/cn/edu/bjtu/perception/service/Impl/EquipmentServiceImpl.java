package cn.edu.bjtu.perception.service.Impl;

import cn.edu.bjtu.perception.entity.Equipment;
import cn.edu.bjtu.perception.service.EquipmentService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(Equipment equipment) {
        Equipment e = new Equipment();
        e.setEntry(equipment.getEntry());
        e.setInstitution(equipment.getInstitution());
        e.setManufacture(equipment.getManufacture());
        e.setName(equipment.getName());
        e.setUnit(equipment.getUnit());
        e.setNum(equipment.getNum());
        e.setCategory(equipment.getCategory());
        mongoTemplate.save(e,"equipment");
    }

    @Override
    public int sum() {
        return mongoTemplate.findAll(Equipment.class,"equipment").size();
    }

    @Override
    public List<Equipment> findByInstitution(String institution) {
        Query query = Query.query(Criteria.where("institution").is(institution));
        return mongoTemplate.find(query, Equipment.class,"equipment");
    }

    @Override
    public int findCategory() {
        int i = 0;
        JSONObject js = new JSONObject();
        List<Equipment> equipments = mongoTemplate.findAll(Equipment.class,"equipment");
        for (Equipment equipment : equipments){
            js.put(equipment.getName(),0);
        }
        i = js.size();
        return i;
    }

    @Override
    public int findCategory(String institution) {
        int i = 0;
        JSONObject js = new JSONObject();
        List<Equipment> equipments = findByInstitution(institution);
        for (Equipment equipment : equipments){
            js.put(equipment.getName(),0);
        }
        i = js.size();
        return i;
    }

    @Override
    public JSONObject num(String institution) {
        List<Equipment> equipments = findByInstitution(institution);
        JSONObject js = new JSONObject();
        for (Equipment equipment : equipments){
            js.put(equipment.getName(),equipment.getNum());
        }
        return js;
    }

    @Override
    public JSONObject number(String institution, String category) {
        List<Equipment> equipments = findByInstitution(institution);
        JSONObject js = new JSONObject();
        for (Equipment equipment : equipments){
            if(equipment.getCategory().equals(category)){
                js.put(equipment.getName(),equipment.getNum());
            }
        }
        return js;
    }
}
