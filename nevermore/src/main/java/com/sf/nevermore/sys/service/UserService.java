package com.sf.nevermore.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.sf.nevermore.sys.entity.User;

public interface UserService extends IService<User> {
    public User findByUsername(String username);
}
