package cn.edu.bjtu.perception.controller;

import cn.edu.bjtu.perception.entity.*;
import cn.edu.bjtu.perception.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    VehicleService vehicleService;
    @Autowired
    CloudService cloudService;
    @Autowired
    RestTemplate restTemplate;

    public static JSONObject jinstitution = new JSONObject();
    public static JSONObject jcategory = new JSONObject();
    public static JSONObject jname = new JSONObject();
    public static JSONObject jspeed = new JSONObject();
    public static JSONObject jmileage = new JSONObject();
    public static JSONObject jrotation = new JSONObject();
    public static JSONObject jmidOil = new JSONObject();
    public static JSONObject jtemperature = new JSONObject();
    public static JSONObject joilTemperature = new JSONObject();
    public static JSONObject jfirstOil = new JSONObject();

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
    @GetMapping("/{institution}/edgeNum")
    public int[] edgeNum(@PathVariable String institution){
        int[] n = new int[3];
        n[0]=personService.findByInstitution(institution).size();
        n[1]=deviceService.findCategory(institution);
        n[2]=equipmentService.findCategory(institution);
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
    @GetMapping("/list1/{institution}")
    public JSONObject List1(@PathVariable String institution){
        List<Device> devices = deviceService.findByInstitution(institution);
        JSONObject js = new JSONObject();
        for (Device device : devices){
            js.put(device.getCategory(),0);
        }
        return js;
    }

    @CrossOrigin
    @GetMapping("/list2/{institution}/{category}")
    public JSONObject List2(@PathVariable String institution,@PathVariable String category){
        List<Device> devices = deviceService.findByInstitution(institution);
        JSONObject js = new JSONObject();
        for (Device device : devices){
            if (device.getCategory().equals(category)){
                js.put(device.getName(),0);
            }
        }
        return js;
    }

    @CrossOrigin
    @PostMapping("/simulate/{institution}/{category}/{name}")
    public String simulate(@PathVariable String institution,@PathVariable String category,@PathVariable String name,
                              @RequestParam(value = "speed") int speed,@RequestParam(value = "mileage") int mileage,
                              @RequestParam(value = "rotation") int rotation,@RequestParam(value = "midOil") int midOil,
                              @RequestParam(value = "temperature") int temperature,@RequestParam(value = "oilTemperature") int oilTemperature,
                              @RequestParam(value = "firstOil") int firstOil){
        Vehicle v = new Vehicle();
        v.setInstitution(institution);
        v.setCategory(category);
        v.setName(name);
        v.setSpeed(speed);
        v.setMileage(mileage);
        v.setRotation(rotation);
        v.setMidOil(midOil);
        v.setTemperature(temperature);
        v.setOilTemperature(oilTemperature);
        v.setFirstOil(firstOil);
        vehicleService.save(v);
        jinstitution.put("单位",institution);
        jcategory.put("装备类型",category);
        jname.put("装备名称",name);
        jspeed.put("车速",speed);
        jmileage.put("里程统计",mileage);
        jrotation.put("转速",rotation);
        jmidOil.put("中置油箱油量",midOil);
        jtemperature.put("发动机水温",temperature);
        joilTemperature.put("变速箱油温",oilTemperature);
        jfirstOil.put("车首油箱油量",firstOil);
        return "收到！";
    }

    @CrossOrigin
    @PostMapping("/exportSimulate/{institution}/{category}/{name}")
    public String exportSimulate(@PathVariable String institution,@PathVariable String category,@PathVariable String name,
                           @RequestParam(value = "speed") int speed,@RequestParam(value = "mileage") int mileage,
                           @RequestParam(value = "rotation") int rotation,@RequestParam(value = "midOil") int midOil,
                           @RequestParam(value = "temperature") int temperature,@RequestParam(value = "oilTemperature") int oilTemperature,
                           @RequestParam(value = "firstOil") int firstOil){
        Vehicle v = new Vehicle();
        v.setInstitution(institution);
        v.setCategory(category);
        v.setName(name);
        v.setSpeed(speed);
        v.setMileage(mileage);
        v.setRotation(rotation);
        v.setMidOil(midOil);
        v.setTemperature(temperature);
        v.setOilTemperature(oilTemperature);
        v.setFirstOil(firstOil);
        vehicleService.save(v);
        jinstitution.put("单位",institution);
        jcategory.put("装备类型",category);
        jname.put("装备名称",name);
        jspeed.put("车速",speed);
        jmileage.put("里程统计",mileage);
        jrotation.put("转速",rotation);
        jmidOil.put("中置油箱油量",midOil);
        jtemperature.put("发动机水温",temperature);
        joilTemperature.put("变速箱油温",oilTemperature);
        jfirstOil.put("车首油箱油量",firstOil);
        String ip = cloudService.findIp("cloud");
        String url = "http://" + ip + ":8095/api/perception/simulate/" + institution + "/" + category + "/" + name;
        MultiValueMap<String, Integer> js = new LinkedMultiValueMap<>();
        js.add("speed",speed);
        js.add("mileage",mileage);
        js.add("rotation",rotation);
        js.add("midOil",midOil);
        js.add("temperature",temperature);
        js.add("oilTemperature",oilTemperature);
        js.add("firstOil",firstOil);
        String result = restTemplate.postForObject(url,js,String.class);
        System.out.println(result);
        return "收到！";
    }


    @CrossOrigin
    @GetMapping("/simulate/{institution}/{category}/{name}")
    public JSONArray simulateShow(@PathVariable String institution,@PathVariable String category,@PathVariable String name){
        if(jinstitution.equals(new JSONObject())){
            return new JSONArray();
        }else if(jinstitution.get("单位").equals(institution)&&jcategory.get("装备类型").equals(category)&&jname.get("装备名称").equals(name)){
            JSONArray result = new JSONArray();
            result.add(jspeed);
            result.add(jmileage);
            result.add(jrotation);
            result.add(jmidOil);
            result.add(jtemperature);
            result.add(joilTemperature);
            result.add(jfirstOil);
            return result;
        }else {
            return new JSONArray();
        }
    }

    @CrossOrigin
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
