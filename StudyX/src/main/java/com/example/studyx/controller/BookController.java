package com.example.studyx.controller;

import com.example.studyx.dao.BookDAO;
import com.example.studyx.dao.BorrowDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Book;
import com.example.studyx.pojo.Borrow;
import com.example.studyx.utils.GetNowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookDAO bookDAO;

    @CrossOrigin
    @PostMapping(value = "/api/updatebookstatus")
    public boolean updatebookstatus(@RequestBody Map<String, String> datas) {
        String type = datas.get("type");//得到修改的类型，可借或者不可借
        Integer bookid = Integer.valueOf(datas.get("bookid").toString()); //得到修改的书的id
        Book book=bookDAO.findByBookid(bookid);
        book.setBorrowstatus(type);
        bookDAO.save(book);
        return true;
    }
}
