package com.example.studyx.controller;

import com.example.studyx.pojo.Donationmoney;
import com.example.studyx.pojo.User;
import com.example.studyx.service.DonationmoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class DonationmoneyController {
    @Autowired
    DonationmoneyService donationmoneyService;

    @CrossOrigin
    @RequestMapping(value = "/api/donationmoney")
    public String donationmoney(@RequestBody String money, HttpSession session){
        //double v = Double.parseDouble(money);/
        if(money==null||"".equals(money)){
            return "no:捐赠金额不能为空";
        }
        else{
            boolean result= money.matches("^[\\+]?[.\\d]*$");
            if(result) {
                User user = (User) session.getAttribute("user");
                Donationmoney donationmoney = donationmoneyService.addDonationmoney(Double.parseDouble(money), user.getId());
                return "yes:" + donationmoney;
            }else {
                return "no:捐赠金额不符合实际情况";
            }
        }

    }
}

