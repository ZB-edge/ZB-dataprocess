package cn.edu.bjtu.dataprocess.service.Impl;

import cn.edu.bjtu.dataprocess.entity.Device;
import cn.edu.bjtu.dataprocess.entity.Person;
import cn.edu.bjtu.dataprocess.service.DeviceService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(Device device) {
        Device d = new Device();
        d.setEntry(device.getEntry());
        d.setInstituion(device.getInstituion());
        d.setManufacture(device.getManufacture());
        d.setName(device.getName());
        d.setNum(device.getNum());
        d.setUnit(device.getUnit());
        mongoTemplate.save(Device.class,"device");
    }

    @Override
    public int sum() {
        return mongoTemplate.findAll(Device.class,"device").size();
    }

    @Override
    public List<Device> findByInstitution(String institution) {
        Query query = Query.query(Criteria.where("institution").is(institution));
        return mongoTemplate.find(query, Device.class,"device");
    }

    @Override
    public JSONObject num(String institution) {
        List<Device> devices = findByInstitution(institution);
        int i = 0;
        int j = 0;
        int k = 0;
        JSONObject js = new JSONObject();
        for (Device device : devices) {
            if (device.getName().equals("坦克")) {
                i = device.getNum();
            }
            if (device.getName().equals("手枪")) {
                j = device.getNum();
            }
            if (device.getName().equals("装甲车")) {
                k = device.getNum();
            }
        }
        js.put("坦克",i);
        js.put("手枪",j);
        js.put("装甲车",k);
        return js;
    }
}
