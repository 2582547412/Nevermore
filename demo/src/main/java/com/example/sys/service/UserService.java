package com.example.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.sys.entity.User;


public interface UserService extends IService<User> {
    public User findByUsername(String username);
}
