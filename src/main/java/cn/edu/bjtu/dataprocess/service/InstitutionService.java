package cn.edu.bjtu.dataprocess.service;

import cn.edu.bjtu.dataprocess.entity.Institution;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstitutionService {
    int num();
    void save(String name,String category,String info);
    List<Institution> findAll();
}

