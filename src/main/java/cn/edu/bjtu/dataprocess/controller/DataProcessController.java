package cn.edu.bjtu.dataprocess.controller;

import cn.edu.bjtu.dataprocess.entity.Institution;
import cn.edu.bjtu.dataprocess.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/datas")
@RestController
public class DataProcessController {

    @Autowired
    InstitutionService institutionService;

    @CrossOrigin
    @PostMapping("/test")
    public void save(String name, String category, String info){
        institutionService.save(name,category,info);
    }

    @CrossOrigin
    @GetMapping("/insList")
    public List<Institution> insList(){
        return institutionService.findAll();
    }

    @CrossOrigin
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
