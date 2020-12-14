package cn.edu.bjtu.dataprocess.service;

import cn.edu.bjtu.dataprocess.entity.Person;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    void save(Person person);
    int sum();
    List<Person> findByInstitution(String institution);
    JSONObject percentage(String institution);
}
