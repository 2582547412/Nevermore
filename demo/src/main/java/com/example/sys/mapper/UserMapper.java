package com.example.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUsername(String username);
}
