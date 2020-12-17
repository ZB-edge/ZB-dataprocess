package cn.edu.bjtu.perception.service;

import cn.edu.bjtu.perception.entity.LogisticMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {
    void save(LogisticMap map);
    LogisticMap findByInstitution(String institution);
}
