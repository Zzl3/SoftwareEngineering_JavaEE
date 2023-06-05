package com.example.studyx.service;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.dao.FeedbackDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.dao.BookDAO;
import com.example.studyx.pojo.Category;
import com.example.studyx.pojo.Feedback;
import com.example.studyx.pojo.Book;
import com.example.studyx.pojo.User;
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
    @Autowired
    SMSCodeService smsCodeService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BookDAO bookDAO;
    public boolean addFeedback(String content,Integer userid){
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
    public boolean isExist(String content){
        Category category=categoryDAO.findByIsbn(content);
        return null!=category;
    }
    public List<Feedback> getAllFeedback(){
        return feedbackDAO.findAll();
    }
    public boolean adminf_book(Feedback feedback){
        Book book=new Book();
        book.setBorrowstatus("可借");
        book.setIsbn(feedback.getContent());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        book.setDonatetime(formatter.format(date));
        book.setUserid(feedback.getUserid());
        User user=userDAO.getById(feedback.getUserid());
        int integration=Integer.parseInt(user.getIntegration());
        integration=integration+50;
        user.setIntegration(integration+"");
        feedback.setReplytime(formatter.format(date));
        feedbackDAO.save(feedback);
        userDAO.save(user);
        bookDAO.save(book);
        return true;
    }
    public Feedback getFeedbackById(Integer feedbackid){
        return feedbackDAO.getById(feedbackid);
    }

    public boolean addReply(String reply,Integer feedbackid){
        if(reply==""||reply==null||reply.length()>200)
            return false;
        try{
            Feedback feedback= com.example.studyx.test.feedbackDAO.getById(feedbackid);
            if(feedback==null){
                return false;
            }
            feedback.setReplycontent(reply);
            try{
                User user= com.example.studyx.test.userDAO.getById(feedback.getUserid());
                if(user==null){
                    return false;
                }
                String mail=user.getMail();
                boolean result= com.example.studyx.test.smsCodeService.send_reply(reply,mail);
                if(result==false)
                    return false;
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                feedback.setFeedbacktime(formatter.format(date));
                feedback.setReplytime(formatter.format(date));
                com.example.studyx.test.feedbackDAO.save(feedback);
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

    public boolean refuse(Integer feedbackid){
        Feedback feedback=feedbackDAO.getById(feedbackid);
        if(feedback==null)
            return false;
        feedback.setReplycontent("不好意思，您要捐赠的书籍不符合要求，已被拒绝");
        String mail=userDAO.getById(feedback.getUserid()).getMail();
        boolean result= smsCodeService.send("不好意思，您要捐赠的书籍不符合要求，已被拒绝",mail,"反馈");
        if(result==false)
            return false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        feedback.setFeedbacktime(formatter.format(date));
        feedback.setReplytime(formatter.format(date));
        feedbackDAO.save(feedback);
        return true;
    }
}
