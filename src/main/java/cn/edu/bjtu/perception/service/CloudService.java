package cn.edu.bjtu.perception.service;

import org.springframework.stereotype.Service;

@Service
public interface CloudService {
    String findIp(String name);
}
