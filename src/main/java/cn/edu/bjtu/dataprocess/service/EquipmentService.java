package cn.edu.bjtu.dataprocess.service;

import cn.edu.bjtu.dataprocess.entity.Equipment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {
    void save(Equipment equipment);
    int sum();
    List<Equipment> findByInstitution(String institution);
    JSONObject num(String institution);
}
