package cn.edu.bjtu.perception.service;

import cn.edu.bjtu.perception.entity.Password;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    Boolean login(String username,String password);
    Password find(String username);
}
