package cn.edu.bjtu.perception.service;

import cn.edu.bjtu.perception.entity.Equipment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {
    void save(Equipment equipment);
    int sum();
    List<Equipment> findByInstitution(String institution);
    int findCategory();
    int findCategory(String institution);
    JSONObject num(String institution);
    JSONObject number(String institution,String category);
}
