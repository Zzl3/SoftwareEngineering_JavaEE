package com.example.studyx.controller;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Feedback;
import com.example.studyx.pojo.User;
import com.example.studyx.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @RequestMapping(value = "/api/addFeedback")
    public String addFeedback(@RequestBody Feedback feedback1,HttpSession session){
        String content=feedback1.getContent();
        String type=feedback1.getType();
        System.out.println("content"+content);
        if(content==null||"".equals(content)){
            return "no";
        }else{
            //User user=(User)session.getAttribute("user");
            //Feedback feedback= feedbackService.addFeedback(content,user.getId());
            Feedback feedback= feedbackService.addFeedback(content,type,8);
            return "yes";
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/api/addDonationbook")
    public String addDonationbook(@RequestBody Feedback feedback1,HttpSession session){
        String content=feedback1.getContent();
        String type=feedback1.getType();
        System.out.println("content"+content);
        if(!(feedbackService.isExist(content))){
            return "no";
        }else{
            //User user=(User)session.getAttribute("user");
            //Feedback feedback= feedbackService.addFeedback(content,user.getId());
            feedbackService.addFeedback(content,type,8);
            return "yes";
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/api/getAllFeedback")
    public List<Feedback> getAllFeedback(){
        return feedbackService.getAllFeedback();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/getFeedbackById")
    public Feedback getFeedbackById(@RequestBody Feedback feedback){
        return  feedbackService.getFeedbackById(feedback.getId());
    }

    @CrossOrigin
    @RequestMapping(value = "/api/addReply")
    public String addReply(@RequestBody Feedback Reply,HttpSession session){
        int feedbackid=Reply.getId();
        String reply=Reply.getReplycontent();
        /*Admin admin=(Admin) session.getAttribute("admin");
        Integer adminid=admin.getId();*/
        Integer adminid=1;
        if("".equals(reply)||reply==null){
            return "no";
        }else {
            boolean result = feedbackService.addReply(reply, feedbackid);
            return result==true?"yes":"no";
        }
    }
    @CrossOrigin
    @RequestMapping("/api/getuserinfo")
    public User getUserinfo(@RequestBody User user){
        return userDAO.getById(user.getId());
    }
}
