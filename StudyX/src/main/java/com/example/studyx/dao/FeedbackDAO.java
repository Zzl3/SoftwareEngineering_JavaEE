package com.example.studyx.dao;

import com.example.studyx.pojo.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeedbackDAO extends JpaRepository<Feedback,Integer> {
    Feedback getById(Integer id);
    List<Feedback> findAll();
}