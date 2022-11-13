package com.example.studyx.service;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

//对数据库操作的进一步封装
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public int register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        if (username.equals("") || password.equals("")) {
            return 0;
        }
        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }
        // 默认生成 16 位盐，干扰数据
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        user.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        userDAO.save(user);
        return 1;
    }
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
