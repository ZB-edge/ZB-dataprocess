package cn.edu.bjtu.perception.service;

import cn.edu.bjtu.perception.entity.Device;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {
    void save(Device device);
    int sum();
    List<Device> findByInstitution(String institution);
    int findCategory();
    JSONObject num(String institution);
}
