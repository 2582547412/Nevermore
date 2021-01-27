package com.sf.nevermore.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sf.nevermore.sys.entity.User;
import com.sf.nevermore.sys.mapper.UserMapper;
import com.sf.nevermore.sys.service.UserService;
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
