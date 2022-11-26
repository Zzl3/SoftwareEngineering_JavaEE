package com.example.studyx.service;

import com.example.studyx.dao.DonationmoneyDAO;
import com.example.studyx.pojo.Donationmoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DonationmoneyService {
    @Autowired
    DonationmoneyDAO donationmoneyDAO;
    public void add(Donationmoney donationmoney){
        donationmoneyDAO.save(donationmoney);
    }
    public Donationmoney addDonationmoney(Double money,Integer userid){
        Donationmoney donationmoney=new Donationmoney();
        donationmoney.setDonationcost(money);
        donationmoney.setUserid(userid);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        donationmoney.setDonationtime(formatter.format(date));
        add(donationmoney);
        return donationmoney;
    }
}
