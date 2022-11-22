package com.example.studyx.controller.Admin;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserManageController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/admin/manageuser")
    public Map<String,String> changeStatus(@RequestParam Map<String,String> m) {
        String username=m.get("username");
        User user=userDAO.findByUsername(username);
        Map<String,String> ret=new HashMap<>();
        if(user.getStatus().equals("banned")){
            ret.put("message","the user is already banned");
        }
        else{
            user.setStatus("banned");
            userDAO.save(user);
            ret.put("message","ban user successfully");
        }
        return ret;
    }

}
