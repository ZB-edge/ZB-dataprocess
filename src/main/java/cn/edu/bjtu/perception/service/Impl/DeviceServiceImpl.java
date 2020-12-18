package cn.edu.bjtu.perception.service.Impl;

import cn.edu.bjtu.perception.entity.Device;
import cn.edu.bjtu.perception.service.DeviceService;
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
        d.setInstitution(device.getInstitution());
        d.setManufacture(device.getManufacture());
        d.setName(device.getName());
        d.setCategory(device.getCategory());
        mongoTemplate.save(d,"device");
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
    public int findCategory() {
        int i = 0;
        JSONObject js = new JSONObject();
        List<Device> devices = mongoTemplate.findAll(Device.class,"device");
        for (Device device : devices){
            js.put(device.getCategory(),0);
        }
        i = js.size();
        return i;
    }

    @Override
    public int findCategory(String institution) {
        int i = 0;
        JSONObject js = new JSONObject();
        List<Device> devices = findByInstitution(institution);
        for (Device device : devices){
            js.put(device.getCategory(),0);
        }
        i = js.size();
        return i;
    }

    @Override
    public JSONObject num(String institution) {
        List<Device> devices = findByInstitution(institution);
        int i = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        JSONObject js = new JSONObject();
        for (Device device : devices) {
            if (device.getCategory().equals("08式步战车")) {
                i++;
            }
            if (device.getCategory().equals("08式突击车")) {
                j++;
            }
            if (device.getCategory().equals("08式输送车")) {
                k++;
            }
            if (device.getCategory().equals("08式指挥车")) {
                m++;
            }
        }
        js.put("08式步战车",i);
        js.put("08式突击车",j);
        js.put("08式输送车",k);
        js.put("08式指挥车",m);
        return js;
    }
}
