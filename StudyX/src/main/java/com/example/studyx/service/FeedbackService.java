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
        Feedback feedback=feedbackDAO.getById(feedbackid);
        if(feedback==null)
            return false;
        feedback.setReplycontent(reply);
        String mail=userDAO.getById(feedback.getUserid()).getMail();
        boolean result= smsCodeService.send_reply(reply,mail);
        if(result==false)
            return false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        feedback.setFeedbacktime(formatter.format(date));
        feedback.setReplytime(formatter.format(date));
        feedbackDAO.save(feedback);
        return true;
    }

    public boolean refuse(Integer feedbackid){
        Feedback feedback=feedbackDAO.getById(feedbackid);
        if(feedback==null)
            return false;
        feedback.setReplycontent("不好意思，您要捐赠的书籍不符合要求，已被拒绝");
        String mail=userDAO.getById(feedback.getUserid()).getMail();
        boolean result= smsCodeService.send_reply("不好意思，您要捐赠的书籍不符合要求，已被拒绝",mail);
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
