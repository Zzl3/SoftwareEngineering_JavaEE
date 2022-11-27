package com.example.studyx.controller;

import com.example.studyx.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SMSCodeController {
    @Autowired
    private SMSCodeService smsCodeService;
    @CrossOrigin
    @PostMapping(value = "/api/sms")
    public String getCode(@RequestBody String mail){
        String code=smsCodeService.sendCodeToSMS("11111111111");
        //Map map = new HashMap();
        //map.put("code",code);
        System.out.println(mail);
        boolean result= smsCodeService.send(code,mail);
        if(result){
            return code;
        }
        return null;
    }
}
