package com.example.studyx.service;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.dao.FeedbackDAO;
import com.example.studyx.pojo.Category;
import com.example.studyx.pojo.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//对数据库操作的进一步封装
@Service
public class FeedbackService {
    @Autowired
    FeedbackDAO feedbackDAO;
    @Autowired
    CategoryDAO categoryDAO;

    public void add(Feedback feedback) {
        feedbackDAO.save(feedback);
    }
    public Feedback addFeedback(String content,String type,Integer userid){
        Feedback feedback=new Feedback();
        //feedback.setId(3);
        feedback.setUserid(userid);
        feedback.setContent(content);
        feedback.setType(type);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        feedback.setFeedbacktime(formatter.format(date));
        add(feedback);
        return feedback;
    }
    public boolean isExist(String content){
        Category category=categoryDAO.findByIsbn(content);
        return null!=category;
    }
    public List<Feedback> getAllFeedback(){
        return feedbackDAO.findAll();
    }

    public Feedback getFeedbackById(Integer feedbackid){
        return feedbackDAO.getById(feedbackid);
    }

    public Feedback addReply(String reply,Integer feedbackid,Integer adminid){
        Feedback feedback=feedbackDAO.getById(feedbackid);
        feedback.setReplycontent(reply);
        feedback.setAdminid(adminid);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        feedback.setFeedbacktime(formatter.format(date));
        feedback.setReplytime(formatter.format(date));
        feedbackDAO.save(feedback);
        return feedback;
    }
}
