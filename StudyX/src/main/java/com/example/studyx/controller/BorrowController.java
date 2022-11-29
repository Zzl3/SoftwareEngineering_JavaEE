package com.example.studyx.controller;


import com.example.studyx.dao.BookDAO;
import com.example.studyx.dao.BorrowDAO;
import com.example.studyx.dao.CollectionDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.*;
import com.example.studyx.utils.GetNowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class BorrowController {
    @Autowired
    BorrowDAO borrowDAO;

    @Autowired
    BookDAO bookDAO;

    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @PostMapping(value = "/api/user/findallborrow")
    public List<Borrow> findallborrow(@RequestBody String id) {
        Integer userid = Integer.valueOf(id);
        User user = userDAO.getById(userid);
        List<Borrow> borrows = borrowDAO.findByUserid(userid);
        LocalDate nowTime = LocalDate.now();
        for (Borrow borrow : borrows) {
            if (borrow.getStatus().equals("借阅中")) {
                LocalDate tmptime = LocalDate.parse(borrow.getStarttime());
                int dayy = (int) (nowTime.toEpochDay() - tmptime.toEpochDay());
                int a = 30 - dayy;
                if (a < 0 && user.getStatus().equals("normal")) {
                    user.setIntegration(String.valueOf(Integer.valueOf(user.getIntegration()) - 20));
                    if (Integer.valueOf(user.getIntegration()) < 0) {
                        user.setStatus("banned");
                    }
                    userDAO.save(user);
                }
                borrow.setDuring(String.valueOf(a) + "天");//更新during日期
            }
        }
        return borrows;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/setborrow")
    public Borrow setborrow(@RequestBody Borrow borrow) {
        try {
            String createTime = GetNowTime.gettime().toString();//得到当前时间
            if (borrow.getStatus().equals("申请中")) {
                Integer userid = borrow.getUserid();
                User user = userDAO.getById(userid);
                if (user.getStatus().equals("banned")||(Integer.valueOf(user.getIntegration())<20))//被封禁或者积分小于20不可借
                    return null;
                borrow.setStarttime("---");
                borrow.setReturntime("---");
                borrow.setDuring("---");
            }
            borrowDAO.save(borrow);
            return borrow;
        } catch (Exception e) {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/changeborrow")
    public void changeborrow(@RequestBody Map<String, String> datas) {
        String createTime = GetNowTime.gettime().toString();//得到当前时间
        Integer userid = Integer.valueOf(datas.get("userid").toString());
        Integer bookid = Integer.valueOf(datas.get("bookid").toString());
        String status = datas.get("status").toString();
        Borrow borrow = borrowDAO.findByUseridAndBookid(userid, bookid);
        Book book=bookDAO.findByBookid(bookid);
        if (status.equals("借阅中")) {
            borrow.setStarttime(createTime);
            borrow.setReturntime("---");//设置归还日期
            borrow.setDuring("30天");//设置为一个月
            book.setBorrowstatus("不可借");
            bookDAO.save(book);
        } else if (status.equals("已结束")) {
            borrow.setReturntime(createTime);//设置归还日期
            borrow.setDuring("---");//设置为一个月
            book.setBorrowstatus("可借");
            bookDAO.save(book);
        } else if (status.equals("未借阅")) {
            borrow.setReturntime("---");//设置归还日期
            borrow.setStarttime("---");//设置归还日期
            borrow.setDuring("---");//设置为一个月
        }
        borrow.setStatus(status);
        borrowDAO.save(borrow);
    }
}
