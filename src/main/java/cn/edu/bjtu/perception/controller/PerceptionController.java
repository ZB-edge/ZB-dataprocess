package cn.edu.bjtu.perception.controller;

import cn.edu.bjtu.perception.entity.Device;
import cn.edu.bjtu.perception.entity.Equipment;
import cn.edu.bjtu.perception.entity.Institution;
import cn.edu.bjtu.perception.entity.Person;
import cn.edu.bjtu.perception.service.DeviceService;
import cn.edu.bjtu.perception.service.EquipmentService;
import cn.edu.bjtu.perception.service.InstitutionService;
import cn.edu.bjtu.perception.service.PersonService;
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

    @CrossOrigin
    @PostMapping("/test")
    public void save(@RequestBody Equipment equipment){
        equipmentService.save(equipment);
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
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
