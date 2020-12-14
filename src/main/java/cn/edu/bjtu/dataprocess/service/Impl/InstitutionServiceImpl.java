package cn.edu.bjtu.dataprocess.service.Impl;

import cn.edu.bjtu.dataprocess.entity.Institution;
import cn.edu.bjtu.dataprocess.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int num() {
        List<Institution> institutions = mongoTemplate.findAll(Institution.class,"institution");
        return institutions.size();
    }

    @Override
    public void save(String name, String category, String info) {
        Institution institution = new Institution();
        institution.setName(name);
        institution.setCategory(category);
        institution.setInfo(info);
        mongoTemplate.save(institution,"institution");

    }

    @Override
    public List<Institution> findAll() {
        return mongoTemplate.findAll(Institution.class,"institution");
    }
}
