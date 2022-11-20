package com.example.studyx.controller.Admin;

import com.example.studyx.service.Admin.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserManageController {
    @Autowired
    private UserManageService userManageService;

    @PostMapping("/admin/manageuser/")
    public Map<String,String> getStatus(@RequestParam Map<String,String> m){
        System.out.println("testhere?");
        String username=m.get("username");
        return userManageService.getUserStatus(username);
    }

}
