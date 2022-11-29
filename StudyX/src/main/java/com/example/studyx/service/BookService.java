package com.example.studyx.service;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Category;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class BookService {
    @Autowired
    CategoryDAO categoryDAO;

   /* public List<Category> list() {
        List<Category> books;
        String key = "booklist";
       // Object bookCache = redisService.get(key);

        if (bookCache == null) {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            books = bookDAO.findAll(sort);
            redisService.set(key, books);
        } else {
            books = CastUtils.objectConvertToList(bookCache, Book.class);
        }
        return books;
    }*/

    public List<Category> list() {
        //Sort sort = new Sort(Sort.Direction.DESC, "isbn");
        return categoryDAO.findAll(Sort.by(Sort.Direction.DESC, "isbn"));
    }

    public List<Category> listbytype(String type) {
        //Sort sort = new Sort(Sort.Direction.DESC, "isbn");
        return categoryDAO.findByType(type);
    }

    public List<Category> Search(String keywords) {
        return categoryDAO.findAllByBooknameLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }

}
