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

    public boolean isExist(String mail) {
        User user = getByMail(mail);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }
    public User getByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }
    public User getByMail(String mail) {
        return userDAO.findByMail(mail);
    }

    public User get(String mail, String password){
        return userDAO.getByMailAndPassword(mail, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public int register(User user) {
        String mail = user.getMail();
        String username = user.getUsername();
        String password = user.getPassword();

        mail = HtmlUtils.htmlEscape(mail);
        user.setMail(mail);
        user.setUsername(username);

        if (mail.equals("") || password.equals("")||username.equals("")) {
            return 0;
        }
        boolean exist = isExist(mail);

        if (exist) {
            return 2;
        }
        // 默认生成 16 位盐，干扰数据
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        user.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        user.setStatus("normal");
        user.setIntegration("100");
        userDAO.save(user);
        return 1;
    }
    public int findpassword(User user) {
        String mail = user.getMail();
        String password = user.getPassword();

        mail = HtmlUtils.htmlEscape(mail);
        user.setMail(mail);

        if (mail.equals("") || password.equals("")) {
            return 0;
        }
        boolean exist = isExist(mail);

        if (!exist) {
            return 2;
        }
        User user1=getByMail(mail);
        user.setId(user1.getId());
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
