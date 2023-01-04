package com.example.studyx.controller;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.dao.FeedbackDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Feedback;
import com.example.studyx.pojo.User;
import com.example.studyx.pojo.Category;
import com.example.studyx.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    FeedbackDAO feedbackDAO;

    @CrossOrigin
    @RequestMapping(value = "/api/addFeedback")
    public String addFeedback(@RequestBody Feedback feedback1){
        String content=feedback1.getContent();
        String type=feedback1.getType();
        System.out.println("content"+content);
        if(content==null||"".equals(content)){
            return "no";
        }else{
            Feedback feedback= feedbackService.addFeedback(content,type,feedback1.getUserid());
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
            feedbackService.addFeedback(content,type,feedback1.getUserid());
            return "yes";
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/api/Adminf_book")
    public String Adminf_book(@RequestBody Feedback feedback1,HttpSession session){
        Feedback feedback= feedbackDAO.getById(feedback1.getId());
        if(feedback==null){
            return "no";
        }else{
            feedbackService.adminf_book(feedback);
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
    @CrossOrigin
    @RequestMapping("/api/getbookinfo")
    public Category getBookinfo(@RequestBody Category category){
        return categoryDAO.findByIsbn(category.getIsbn());
    }
    @CrossOrigin
    @RequestMapping("/api/refuse")
    public String refuse(@RequestBody Feedback feedback){
        boolean result= feedbackService.refuse(feedback.getId());
        if(result){
            return "yes";
        }else{
            return "no";
        }
    }
}
