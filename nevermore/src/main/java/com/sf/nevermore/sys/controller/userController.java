package com.sf.nevermore.sys.controller;

import com.sf.nevermore.sys.entity.User;
import com.sf.nevermore.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class userController {
    @Value("${shiro.HashAlgorithmName}")
    private String HashAlgorithmName;
    @Value("${shiro.HashIterations}")
    private Integer HashIterations;
    @Value("${shiro.StoredCredentialsHexEncoded}")
    private boolean StoredCredentialsHexEncoded;

    @Autowired
    private UserService userService;
    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session, HttpServletRequest request) {
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            request.setAttribute("user",username);
        } catch (UnknownAccountException e) {
            request.setAttribute("msg","用户名不存在！");
            return "login";
        } catch (AuthenticationException e) {
            request.setAttribute("msg","账号或密码错误！");
            return "login";
        } catch (AuthorizationException e) {
            request.setAttribute("msg","没有权限！");
            return "login";
        }
        return "index";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

//    //访问login时跳到login.jsp
//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }
    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    //admin角色才能访问
    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin success";
    }

    //有delete权限才能访问
    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        return "edit success";
    }

    @RequestMapping("/test")
    @ResponseBody
    @RequiresRoles("guest")
    public String test(){
        return "test success";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user){
        Object salt = ByteSource.Util.bytes(user.getUsername());
        SimpleHash simpleHash=new SimpleHash("MD5", user.getPassword(), salt, 1024);
        user.setPassword(simpleHash.toString());
        user.setSalt(salt.toString());
        userService.insert(user);
        return "/list";
    }
}
