package cn.edu.bjtu.perception.service;

import cn.edu.bjtu.perception.entity.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {
    void save(Vehicle vehicle);
}
