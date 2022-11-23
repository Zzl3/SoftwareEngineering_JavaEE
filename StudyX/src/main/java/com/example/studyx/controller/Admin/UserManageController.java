package com.example.studyx.controller.Admin;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserManageController {

    @Autowired
    private UserDAO userDAO;
    @CrossOrigin
    @PostMapping("/api/admin/manageuser")
    public Map<String,String> changeStatus(@RequestBody Map<String,String> m) {
        String username=m.get("username");
        User user=userDAO.findByUsername(username);
        Map<String,String> ret=new HashMap<>();
        if(user.getStatus().equals("banned")){
            user.setStatus("normal");
            userDAO.save(user);
            ret.put("message","unban user successfully");
        }
        else{
            user.setStatus("banned");
            userDAO.save(user);
            ret.put("message","ban user successfully");
        }
        return ret;
    }

    @CrossOrigin
    @GetMapping(value = "/api/admin/getalluser")
    public List<User> getalluser(){
        return userDAO.findAll();
    }

}
