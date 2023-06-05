package com.example.studyx.interfact;

import com.example.studyx.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;

public class SMS {
    @Autowired
    private SMSCodeService smsCodeService;


    public String sendCodeToSMS(String mail){
        return smsCodeService.sendCodeToSMS(mail);
    }
    public boolean send(String content,String mail,String subject){
        return smsCodeService.send(content,mail,subject);
    }

}
