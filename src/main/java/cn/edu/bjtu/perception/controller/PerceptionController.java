package cn.edu.bjtu.perception.controller;

import cn.edu.bjtu.perception.entity.Equipment;
import cn.edu.bjtu.perception.entity.Institution;
import cn.edu.bjtu.perception.entity.LogisticMap;
import cn.edu.bjtu.perception.entity.Password;
import cn.edu.bjtu.perception.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/perception")
@RestController
public class PerceptionController {

    @Autowired
    PersonService personService;
    @Autowired
    InstitutionService institutionService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    MapService mapService;

    @CrossOrigin
    @PostMapping("/test")
    public void save(@RequestBody LogisticMap logisticMap){
        mapService.save(logisticMap);
    }

    @CrossOrigin
    @GetMapping("/num")
    public int[] num(){
        int[] n = new int[4];
        n[0]=institutionService.findAll().size();
        n[1]=personService.sum();
        n[2]=deviceService.findCategory();
        n[3]=equipmentService.findCategory();
        return n;
    }

    @CrossOrigin
    @GetMapping("/insList")
    public List<Institution> insList(){
        return institutionService.findAll();
    }

    @CrossOrigin
    @GetMapping("/person/{institution}")
    public JSONObject percent(@PathVariable String institution){
        return personService.percentage(institution);
    }

    @CrossOrigin
    @GetMapping("/device/{institution}")
    public JSONObject deviceNum(@PathVariable String institution){
        return deviceService.num(institution);
    }

    @CrossOrigin
    @GetMapping("/equipment/{institution}")
    public JSONObject equipmentNum(@PathVariable String institution){
        return equipmentService.num(institution);
    }

    @CrossOrigin
    @GetMapping("/material/{institution}/{category}")
    public JSONObject materialNum(@PathVariable String institution,@PathVariable String category){
        return equipmentService.number(institution,category);
    }

    @CrossOrigin
    @GetMapping("/map/{institution}")
    public JSONObject map(@PathVariable String institution){
        LogisticMap logisticMap = mapService.findByInstitution(institution);
        JSONObject js = new JSONObject();
        js.put("装备保障大队",logisticMap.getHeadquarters());
        js.put("修理连",logisticMap.getGuarantee());
        js.put("运输连",logisticMap.getTransport());
        js.put("供应保障队",logisticMap.getSupply());
        return js;

    }

    @CrossOrigin
    @PostMapping("/adminLogin")
    public JSONObject adminLogin(String username,String password){
        Password data = passwordService.find(username);
        JSONObject js = new JSONObject();

        if (data == null){
            js.put("status",401);
            js.put("message","请输入用户名和密码！");
            return js;
        }else if(!data.getUsername().equals("admin")){
            js.put("status",401);
            js.put("message","此用户不存在！");
            return js;
        }else {
            boolean flag = passwordService.login(username,password);
            if(flag){
                js.put("status",200);
                js.put("message","登录成功！");
                return js;
            }else {
                js.put("status",401);
                js.put("message","密码有误，登录失败！");
                return js;
            }
        }
    }

    @CrossOrigin
    @PostMapping("/edgeLogin")
    public JSONObject edgeLogin(String username,String password){

        Password data = passwordService.find(username);
        JSONObject js = new JSONObject();

        if (data == null){
            js.put("status",401);
            js.put("message","请输入用户名和密码！");
            return js;
        }else if(!data.getUsername().equals("user1")&&!data.getUsername().equals("user2")&&!data.getUsername().equals("user3")&&!data.getUsername().equals("user4")){
            js.put("status",401);
            js.put("message","此用户不存在！");
            return js;
        }else {
            boolean flag = passwordService.login(username,password);
            if(flag){
                js.put("status",200);
                js.put("message","登录成功！");
                return js;
            }else {
                js.put("status",401);
                js.put("message","密码有误，登录失败！");
                return js;
            }
        }

    }

    @CrossOrigin
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
