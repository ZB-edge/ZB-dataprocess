package cn.edu.bjtu.perception.service.Impl;

import cn.edu.bjtu.perception.entity.LogisticMap;
import cn.edu.bjtu.perception.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(LogisticMap map) {
        LogisticMap logisticMap = new LogisticMap();
        logisticMap.setInstitution(map.getInstitution());
        logisticMap.setSupply(map.getSupply());
        logisticMap.setHeadquarters(map.getHeadquarters());
        logisticMap.setGuarantee(map.getGuarantee());
        logisticMap.setTransport(map.getTransport());
        mongoTemplate.save(logisticMap,"map");
    }

    @Override
    public LogisticMap findByInstitution(String institution) {
        return mongoTemplate.findById(institution, LogisticMap.class,"map");
    }
}
