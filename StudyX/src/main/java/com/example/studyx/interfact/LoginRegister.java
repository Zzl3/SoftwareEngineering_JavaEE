package com.example.studyx.interfact;

import com.example.studyx.dao.AdminDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Admin;
import com.example.studyx.pojo.User;
import com.example.studyx.service.AdminService;
import com.example.studyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRegister {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    UserDAO userDAO;

    Admin findByAdminname(String adminname){
        return adminDAO.findByAdminname(adminname);
    }

    Admin getByAdminnameAndPassword(String adminname,String password){
        return adminDAO.getByAdminnameAndPassword(adminname,password);
    }

    User findByUsername(String username){
        return userDAO.findByUsername(username);
    }
    User findByPhone(String phone){
        return userDAO.findByPhone(phone);
    }
    User getById(Integer id){
        return userDAO.getById(id);
    }
    User findByMail(String mail){
        return userDAO.findByMail(mail);
    }
    User getByUsernameAndPassword(String username,String password){
        return userDAO.getByUsernameAndPassword(username,password);
    }
    User getByMailAndPassword(String mail,String password){
        return userDAO.getByMailAndPassword(mail,password);
    }

    public boolean isExist_user(String mail) {
        return userService.isExist(mail);
    }


    public int register(User user) {
        return userService.register(user);
    }
    public int findpassword(User user) {
        return userService.findpassword(user);
    }


    public boolean isExist_admin(String adminname) {
        return adminService.isExist(adminname);
    }


    public int register(Admin admin) {
        return adminService.register(admin);
    }
}
