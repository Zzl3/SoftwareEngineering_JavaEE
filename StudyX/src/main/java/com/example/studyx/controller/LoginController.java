package com.example.studyx.controller;

import com.example.studyx.dao.AdminDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Admin;
import com.example.studyx.pojo.User;
import com.example.studyx.result.Result;
import com.example.studyx.result.ResultFactory;
import com.example.studyx.service.AdminService;
import com.example.studyx.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

//有几点需要注意，postmapping对应前端 .post
//requestBody 参数后面跟着相关的类

@RestController
public class

LoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;

    @Autowired
    AdminDAO adminDAO;

    @CrossOrigin
    @PostMapping("/api/getuserid")
    public Integer getUserid(@RequestBody String a) throws Exception {
        User user = userDAO.findByMail(a);
        Integer id = user.getId();
        return id;
    }

    @CrossOrigin
    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String mail = requestUser.getMail();
        mail = HtmlUtils.htmlEscape(mail);
        //先得到salt加密的值
        User user = userDAO.findByMail(mail);
        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        }
        String salt = user.getSalt();

        //加密密码，和原来的做对比
        String password = new SimpleHash("md5", requestUser.getPassword(), salt, 2).toString();
        ;
        user = userDAO.getByMailAndPassword(mail, password);
        if (null == user) {
            return ResultFactory.buildFailResult("账号或密码错误");
        } else {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessResult(mail);
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/register")
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("电话号码或密码或用户名不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("该手机号已注册");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @PostMapping(value = "/api/findpassword")
    public Result findpassword(@RequestBody User user) {
        int status = userService.findpassword(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("邮箱或密码或用户名不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("找回密码成功");
            case 2:
                return ResultFactory.buildFailResult("该手机号未注册，请先注册");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
    //注销登录
    @CrossOrigin
    @RequestMapping("/api/logout")
    public void logout(HttpSession session) {
        System.out.println("logout");
        //session失效
        if (session.getAttribute("user") != null)
            session.removeAttribute("user");
        if (session.getAttribute("admin") != null)
            session.removeAttribute("admin");
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
        Admin admin = adminDAO.findByAdminname(adminname);
        if (null == admin) {
            return ResultFactory.buildFailResult("账号不存在");
        }
        String salt = admin.getSalt();
        //加密密码，和原来的做对比
        String password = new SimpleHash("md5", requestAdmin.getPassword(), salt, 2).toString();
        ;
        admin = adminDAO.getByAdminnameAndPassword(adminname, password);
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
