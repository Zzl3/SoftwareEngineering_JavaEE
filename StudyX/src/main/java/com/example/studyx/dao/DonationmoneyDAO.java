package com.example.studyx.dao;

import com.example.studyx.pojo.Donationmoney;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DonationmoneyDAO extends JpaRepository<Donationmoney,Integer> {
    //Feedback findById(Integer feedbackid);
}
