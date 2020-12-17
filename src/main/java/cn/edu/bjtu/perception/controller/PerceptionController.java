package cn.edu.bjtu.perception.controller;

import cn.edu.bjtu.perception.entity.Institution;
import cn.edu.bjtu.perception.entity.LogisticMap;
import cn.edu.bjtu.perception.entity.Password;
import cn.edu.bjtu.perception.service.*;
import com.alibaba.fastjson.JSONArray;
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
    public JSONArray map(@PathVariable String institution){
        LogisticMap logisticMap = mapService.findByInstitution(institution);
        JSONArray result = new JSONArray();
        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();
        JSONObject js3 = new JSONObject();
        JSONObject js4 = new JSONObject();
        js1.put("name","装备保障大队");
        js1.put("lng",logisticMap.getHeadquarters()[0]);
        js1.put("lat",logisticMap.getHeadquarters()[1]);
        result.add(js1);
        js2.put("name","修理连");
        js2.put("lng",logisticMap.getGuarantee()[0]);
        js2.put("lat",logisticMap.getGuarantee()[1]);
        result.add(js2);
        js3.put("name","运输连");
        js3.put("lng",logisticMap.getTransport()[0]);
        js3.put("lat",logisticMap.getTransport()[1]);
        result.add(js3);
        js4.put("name","供应保障队");
        js4.put("lng",logisticMap.getSupply()[0]);
        js4.put("lat",logisticMap.getSupply()[1]);
        result.add(js4);
        return result;
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
                switch (data.getUsername()){
                    case "user1":
                        js.put("status",200);
                        js.put("user","装甲兵1旅");
                        js.put("message","登录成功！");
                        return js;
                    case "user2":
                        js.put("status",200);
                        js.put("user","装甲兵2旅");
                        js.put("message","登录成功！");
                        return js;
                    case "user3":
                        js.put("status",200);
                        js.put("user","装甲兵3旅");
                        js.put("message","登录成功！");
                        return js;
                    default:
                        js.put("status",200);
                        js.put("user","装甲兵4旅");
                        js.put("message","登录成功！");
                        return js;
                }
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
