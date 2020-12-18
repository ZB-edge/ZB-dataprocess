package cn.edu.bjtu.perception.service.Impl;

import cn.edu.bjtu.perception.entity.Vehicle;
import cn.edu.bjtu.perception.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(Vehicle vehicle) {
        Vehicle v = new Vehicle();
        v.setInstitution(vehicle.getInstitution());
        v.setCategory(vehicle.getCategory());
        v.setName(vehicle.getName());
        v.setSpeed(vehicle.getSpeed());
        v.setMileage(vehicle.getMileage());
        v.setRotation(vehicle.getRotation());
        v.setMidOil(vehicle.getMidOil());
        v.setTemperature(vehicle.getTemperature());
        v.setOilTemperature(vehicle.getOilTemperature());
        v.setFirstOil(vehicle.getFirstOil());
        mongoTemplate.save(v,"vehicle");
    }
}
