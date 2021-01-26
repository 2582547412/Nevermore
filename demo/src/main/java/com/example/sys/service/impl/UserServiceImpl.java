package com.example.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.sys.mapper.UserMapper;
import com.example.sys.entity.User;
import com.example.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired(required=false)
    private UserMapper userMapper;
    @Override
    public
    User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
