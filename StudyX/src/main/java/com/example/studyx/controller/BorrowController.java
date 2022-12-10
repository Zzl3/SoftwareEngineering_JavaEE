package com.example.studyx.controller;


import com.example.studyx.dao.BookDAO;
import com.example.studyx.dao.BorrowDAO;
import com.example.studyx.dao.CollectionDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.*;
import com.example.studyx.utils.GetNowTime;
import com.example.studyx.utils.MyGlobal;
import org.apache.ibatis.javassist.LoaderClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
        user=userDAO.getById(MyGlobal.getUserid());
        List<Borrow> borrows = borrowDAO.findByUserid(MyGlobal.getUserid());
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
    @GetMapping ("/api/user/countborrow")
    public Integer[] countborrow(@RequestParam String id){
        Integer userid = Integer.parseInt(id);
//        User user = userDAO.getById(userid);
        //find all books borrowed by a certain person
        List<Borrow> borrows = borrowDAO.findByUserid(MyGlobal.getUserid());
//        List<Integer> l=new ArrayList<>();
        Integer[] ret_value=new Integer[12];
        for(int i=0;i<12;i++) ret_value[i]=0;
        Map<String,Integer> ret=new HashMap<>();
        for(Borrow borrow:borrows){
            System.out.println(borrow.getStarttime());
            String[] date=borrow.getStarttime().split("-");
            System.out.println(date.length);
            System.out.println(date[0]);
            System.out.println(date[1]);
            System.out.println(date[2]);
            ret_value[Integer.parseInt(date[1])]+=1;
            //ret.put(date[1],ret.get(Integer.toString(Integer.parseInt(date[1])+1)));
        }

        return ret_value;

//        List<Integer> returnvalue=new ArrayList<>();
//        int num=0;
//        for(Integer value:ret.values()){
//            returnvalue.set(num++, value);
//        }
//        return returnvalue;
    }



    @CrossOrigin
    @PostMapping(value = "/api/user/setborrow")
    public Borrow setborrow(@RequestBody Borrow borrow) {
        try {
            String createTime = GetNowTime.gettime().toString();//得到当前时间
            if (borrow.getStatus().equals("申请中")) {
                Integer userid = borrow.getUserid();
                User user = userDAO.getById(userid);
                user=userDAO.getById(MyGlobal.getUserid());
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
        Borrow borrow = borrowDAO.findByUseridAndBookid(MyGlobal.getUserid(), bookid);
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
