package cn.edu.bjtu.perception.service.Impl;

import cn.edu.bjtu.perception.entity.Password;
import cn.edu.bjtu.perception.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Boolean login(String username, String password) {
        boolean flag = false;
        Password passwd = mongoTemplate.findById(username, Password.class,"password");
        assert passwd != null;
        if (passwd.getPassword().equals(password)){
            flag = true;
        }
        return flag;
    }

    @Override
    public Password find(String username) {
        return mongoTemplate.findById(username, Password.class,"password");
    }
}
