package com.example.studyx.controller;

import com.example.studyx.pojo.Feedback;
import com.example.studyx.pojo.User;
import com.example.studyx.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @CrossOrigin
    @RequestMapping(value = "/api/addFeedback")
    public String addFeedback(@RequestBody String content,HttpSession session){
        System.out.println("content"+content);
        if(content==null||"".equals(content)){
            return "no";
        }else{
            User user=(User)session.getAttribute("user");
            //Feedback feedback= feedbackService.addFeedback(content,user.getId());
            Feedback feedback= feedbackService.addFeedback(content,8);
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
    public Feedback getFeedbackById(@RequestBody String Id){
        int feedbackid=Integer.parseInt(Id);
        return  feedbackService.getFeedbackById(feedbackid);
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
            return "no:答复内容不能为空";
        }else {
            Feedback feedback = feedbackService.addReply(reply, feedbackid, adminid);
            return "yes:" + feedback;
        }
    }
}
