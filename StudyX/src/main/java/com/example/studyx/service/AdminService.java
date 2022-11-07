package com.example.studyx.service;

import com.example.studyx.dao.AdminDAO;
import com.example.studyx.pojo.Admin;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public boolean isExist(String adminname) {
        Admin admin = getByName(adminname);
        return null != admin;
    }

    public Admin getByName(String adminname) {
        return adminDAO.findByAdminname(adminname);
    }

    public Admin get(String adminname, String password) {
        return adminDAO.getByAdminnameAndPassword(adminname, password);
    }

    public void add(Admin admin) {
        adminDAO.save(admin);
    }

    public int register(Admin admin) {
        String adminname = admin.getAdminname();
        String password = admin.getPassword();

        adminname = HtmlUtils.htmlEscape(adminname);
        admin.setAdminname(adminname);

        if (adminname.equals("") || password.equals("")) {
            return 0;
        }
        boolean exist = isExist(adminname);

        if (exist) {
            return 2;
        }
        // 默认生成 16 位盐，干扰数据
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        admin.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        admin.setPassword(encodedPassword);
        adminDAO.save(admin);
        return 1;
    }

    public Admin findByUsername(String username) {
        return adminDAO.findByAdminname(username);
    }
}