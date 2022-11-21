package com.example.studyx.controller;


import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.User;
import com.example.studyx.result.ResultFactory;
import com.example.studyx.service.UserService;
import com.example.studyx.utils.StringUtils;
import org.apache.catalina.Store;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.io.IOException;


@RestController
public class MyinfoController {
    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @PostMapping(value = "/api/user/getuserinfo")
    public User getuserinfo(@RequestBody String a) {
        User user = userDAO.getById(Integer.valueOf(a));
        return user;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/edituserinfo")
    public boolean edituserinfo(@RequestBody User user) {
        Integer id = Integer.valueOf(user.getId());
        User usera = userDAO.getById(id);
        if (user.getPassword() !=  null) {
            // 默认生成 16 位盐，干扰数据
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            usera.setSalt(salt);
            String encodedPassword = new SimpleHash("md5", user.getPassword(), salt, times).toString();
            usera.setPassword(encodedPassword);
        }
        if (user.getMail() !=  null) {
            usera.setMail(user.getMail());
        }
        if (user.getAge() != null) {
            usera.setAge(user.getAge());
        }
        if (user.getGender() !=  null) {
            usera.setGender(user.getGender());
        }
        if (user.getDetail() !=  null) {
            usera.setDetail(user.getDetail());
        }
        if (user.getPhone() !=  null) {
            usera.setPhone(user.getPhone());
        }
        if (user.getSchool() !=  null) {
            usera.setSchool(user.getSchool());
        }
        if(user.getUrl()!= null){
            usera.setUrl(user.getUrl());
        }
        userService.add(usera);
        return true;
    }

    @CrossOrigin
    @PostMapping("/api/user/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        //这里也需要更改
        String folder = "/Users/zhouzilei/Documents/GitHub/SoftwareEngineering-JavaEE/StudyX/img/avator";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            //去掉了后缀，因为前端需要
            String imgURL =f.getName().substring(0,f.getName().lastIndexOf("."));
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @CrossOrigin
    @PostMapping("/api/user/getImg")
    public String getImg(@RequestBody String a) throws Exception {
        User user = userDAO.getById(Integer.valueOf(a));
        String url=user.getUrl();
        return url;
    }
}

