package cn.edu.bjtu.dataprocess.service.Impl;

import cn.edu.bjtu.dataprocess.entity.Equipment;
import cn.edu.bjtu.dataprocess.service.EquipmentService;
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
        e.setInstituion(equipment.getInstituion());
        e.setManufacture(equipment.getManufacture());
        e.setName(equipment.getName());
        e.setUnit(equipment.getUnit());
        e.setNum(equipment.getNum());
        mongoTemplate.save(Equipment.class,"equipment");
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
    public JSONObject num(String institution) {
        List<Equipment> equipments = findByInstitution(institution);
        int i = 0;
        int j = 0;
        int k = 0;
        JSONObject js = new JSONObject();
        for (Equipment equipment : equipments) {
            if (equipment.getName().equals("弹药")) {
                i = equipment.getNum();
            }
            if (equipment.getName().equals("钢盔")) {
                j = equipment.getNum();
            }
            if (equipment.getName().equals("燃油")) {
                k = equipment.getNum();
            }
        }
        js.put("弹药",i);
        js.put("钢盔",j);
        js.put("燃油",k);
        return js;
    }
}
