package com.example.studyx.controller;

import com.example.studyx.pojo.Admin;
import com.example.studyx.pojo.User;
import com.example.studyx.result.Result;
import com.example.studyx.result.ResultFactory;
import com.example.studyx.service.AdminService;
import com.example.studyx.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

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
        User user = userService.getByName(username);
        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        }
        String salt = user.getSalt();

        //加密密码，和原来的做对比
        String password = new SimpleHash("md5", requestUser.getPassword(), salt, 2).toString();
        ;
        user = userService.get(username, password);
        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        } else {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessResult(username);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/register")
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

    @Autowired
    AdminService AdminService;
    //管理员登录
    @CrossOrigin
    @PostMapping(value = "/api/login/admin")
    public Result adminlogin(@RequestBody Admin requestAdmin, HttpSession session) {
        String adminname = requestAdmin.getAdminname();
        adminname = HtmlUtils.htmlEscape(adminname);
        //先得到salt加密的值
        Admin admin = AdminService.getByName(adminname);
        if (null == admin) {
            return ResultFactory.buildFailResult("账号不存在");
        }
        String salt = admin.getSalt();

        //加密密码，和原来的做对比
        String password = new SimpleHash("md5", requestAdmin.getPassword(), salt, 2).toString();
        ;
        admin = AdminService.get(adminname, password);
        if (null == admin) {
            return ResultFactory.buildFailResult("账号不存在");
        } else {
            session.setAttribute("admin", admin);
            return ResultFactory.buildSuccessResult(adminname);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/register/admin")
    public Result adminregister(@RequestBody Admin admin) {
        int status = AdminService.register(admin);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("管理员名字和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("管理员已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
}
