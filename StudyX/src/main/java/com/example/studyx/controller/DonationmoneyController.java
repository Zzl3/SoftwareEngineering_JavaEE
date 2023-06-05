package com.example.studyx.controller;

import com.example.studyx.dao.DonationmoneyDAO;
import com.example.studyx.pojo.Donationmoney;
import com.example.studyx.service.DonationmoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DonationmoneyController {
    @Autowired
    DonationmoneyService donationmoneyService;
    @Autowired
    DonationmoneyDAO donationmoneyDAO;

    @CrossOrigin
    @RequestMapping(value = "/api/donationmoney")
    public String donationmoney(@RequestBody String money, HttpSession session) {
        //double v = Double.parseDouble(money);/
        if (money == null || "".equals(money)) {
            return "no:捐赠金额不能为空";
        } else {
            boolean result = money.matches("^[\\+]?[.\\d]*$");
            if (result) {
                //User user = (User) session.getAttribute("user");
                //Donationmoney donationmoney = donationmoneyService.addDonationmoney(Double.parseDouble(money), user.getId());
                //Donationmoney donationmoney = donationmoneyService.addDonationmoney(Double.parseDouble(money), 8);
                return "yes:即将前往支付界面";
            } else {
                return "no:捐赠金额不符合实际情况";
            }
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/api/getdonationmoney")
    public List<Donationmoney> getDonationmoney() {
        return donationmoneyDAO.findAll();
    }
}

