package cn.edu.bjtu.perception.service.Impl;

import cn.edu.bjtu.perception.entity.Person;
import cn.edu.bjtu.perception.service.PersonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(Person person) {
        Person p = new Person();
        p.setBirth(person.getBirth());
        p.setDegree(person.getDegree());
        p.setEnlistment(person.getEnlistment());
        p.setInstitution(person.getInstitution());
        p.setMajor(person.getMajor());
        p.setName(person.getName());
        p.setSex(person.getSex());
        p.setParty(person.getParty());
        p.setRank(person.getRank());
        p.setLogistic(person.getLogistic());
        mongoTemplate.save(p,"person");
    }

    @Override
    public int sum() {
        return mongoTemplate.findAll(Person.class,"person").size();
    }

    @Override
    public List<Person> findByInstitution(String institution) {
        Query query = Query.query(Criteria.where("institution").is(institution));
        return mongoTemplate.find(query,Person.class,"person");
    }

    @Override
    public JSONObject percentage(String institution) {
        List<Person> people = findByInstitution(institution);
        int num = people.size();
        int i = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        JSONObject js = new JSONObject();
        for (Person p : people){
            if (p.getParty() == 1){
                i++;
            }
            if (p.getMajor() == 1){
                j++;
            }
            if (p.getRank().equals("校官")){
                k++;
            }
            if (p.getDegree().equals("本科")||p.getDegree().equals("硕士")||p.getDegree().equals("博士")){
                m++;
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        js.put("党员", numberFormat.format((float) i / (float) num * 100) + "%");
        js.put("专业人员",numberFormat.format((float) j / (float) num * 100) + "%");
        js.put("校官及以上",numberFormat.format((float) k / (float) num * 100) + "%");
        js.put("本科及以上",numberFormat.format((float) m / (float) num * 100) + "%");
        return js;
    }
}
