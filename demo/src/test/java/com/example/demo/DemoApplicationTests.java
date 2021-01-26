package com.example.demo;

import com.example.sys.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {


    public static void main(String[] args) {
        Object salt = ByteSource.Util.bytes("张三");
        SimpleHash simpleHash=new SimpleHash("MD5", "123456", salt, 1024);
        User user=new User();
        //user.setUserName(username);
        System.out.println(simpleHash.toString());
        user.setPassword(simpleHash.toString());

    }

}
