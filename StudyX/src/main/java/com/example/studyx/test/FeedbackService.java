package com.example.studyx.test;

import com.example.studyx.pojo.Feedback;
import com.example.studyx.pojo.User;
import com.example.studyx.test.feedbackDAO;
import com.example.studyx.test.userDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedbackService {
    public static boolean addFeedback(String content,Integer userid){
        if(content==""||content==null||content.length()>200)
            return false;
        if(userDAO.getById(userid)==null){
            return false;
        }
        Feedback feedback=new Feedback();
        feedback.setUserid(userid);
        feedback.setContent(content);
        feedback.setType("未回复");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        feedback.setFeedbacktime(formatter.format(date));
        try{
            feedbackDAO.save(feedback);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static boolean addReply(String reply,Integer feedbackid){
        if(reply==""||reply==null||reply.length()>200)
            return false;
        try{
            Feedback feedback=feedbackDAO.getById(feedbackid);
            if(feedback==null){
                return false;
            }
            feedback.setReplycontent(reply);
            try{
                User user=userDAO.getById(feedback.getUserid());
                if(user==null){
                    return false;
                }
                String mail=user.getMail();
                boolean result= smsCodeService.send_reply(reply,mail);
                if(result==false)
                    return false;
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                feedback.setFeedbacktime(formatter.format(date));
                feedback.setReplytime(formatter.format(date));
                feedbackDAO.save(feedback);
                return true;
            }
            catch (Exception e){
                System.out.println(e);
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
