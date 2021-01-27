package com.sf.nevermore.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sf.nevermore.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUsername(String username);
}
