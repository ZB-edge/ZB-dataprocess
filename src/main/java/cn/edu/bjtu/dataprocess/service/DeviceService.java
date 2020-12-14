package cn.edu.bjtu.dataprocess.service;

import cn.edu.bjtu.dataprocess.entity.Device;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {
    void save(Device device);
    int sum();
    List<Device> findByInstitution(String institution);
    JSONObject num(String institution);
}
