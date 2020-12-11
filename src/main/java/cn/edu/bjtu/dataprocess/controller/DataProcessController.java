package cn.edu.bjtu.dataprocess.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/datas")
@RestController
public class DataProcessController {

    @CrossOrigin
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
