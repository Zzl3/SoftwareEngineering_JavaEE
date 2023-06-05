package com.example.studyx.service;


import com.example.studyx.utils.CodeUtils;
//import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.Map;

@Service
public class SMSCodeService {
    @Autowired
    private CodeUtils codeUtils;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;

    @CachePut(value="smsCode",key="#tel")
    public String sendCodeToSMS(String tel){
        String code = codeUtils.generator(tel);
        return code;
    }
    public boolean send(String content,String mail,String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2083978036@qq.com");
        message.setTo(mail);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("邮件已发送。");
            return true;

        } catch (Exception e) {
            logger.error("邮件时发生异常了！", e);
            return false;
        }
    }
}
