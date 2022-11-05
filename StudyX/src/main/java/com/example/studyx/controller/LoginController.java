package com.example.studyx.controller;

import com.example.studyx.pojo.User;
import com.example.studyx.result.Result;
import com.example.studyx.result.ResultFactory;
import com.example.studyx.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.openqa.selenium.WebElement;
import javax.servlet.http.HttpSession;

//有几点需要注意，postmapping对应前端 .post
//requestBody 参数后面跟着相关的类

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        //先得到salt加密的值
        User user=userService.getByName(username);
        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        }
        String salt=user.getSalt();

        //加密密码，和原来的做对比
        String password= new SimpleHash("md5",requestUser.getPassword() , salt, 2).toString();;
        user = userService.get(username, password);
        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        } else {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessResult(username);
        }
    }

    @CrossOrigin
    @PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
}
