package com.example.studyx.controller.Admin;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryManageController {
    @Autowired
    private CategoryDAO categoryDAO;

    @CrossOrigin
    @PostMapping("/api/admin/managecategory/")
    public Map<String,String> modifybookproperty(@RequestBody Map<String,Object> m){
        System.out.println(m);
        Map<String,String> ret=new HashMap<>();
        String isbn=(String)m.get("isbn");
        System.out.println(isbn);
        System.out.println("\n\n\n\n\n");
        Category category=categoryDAO.findByIsbn(isbn);

        String author=(String)m.get("author");
        String bookname= (String)m.get("bookname");
        Double price=Double.parseDouble((String)m.get("price"));
        System.out.println(price);
        String bookabstract=(String)m.get("bookabstract");
        String publishdate=(String) m.get("publishdate");
        String type=(String) m.get("type");
        String publisher=(String) m.get("publisher");
        String url=(String) m.get("url");

        if(category!=null){
            //修改图书
            category.setAuthor(author);
            category.setBookname(bookname);
            category.setPrice(price);
            category.setBookabstract(bookabstract);
            category.setPublishdate(publishdate);
            category.setType(type);
            categoryDAO.save(category);
            ret.put("message","modify successfully");
        }
        else{
            //新建图书
            Category c=new Category(isbn,bookname,publisher,author,type,publishdate,url,bookabstract,price);
            categoryDAO.saveAndFlush(c);
            ret.put("message","add successfully");
        }

        return ret;
    }


    //管理员下架某书籍
    @CrossOrigin
    @PostMapping("/api/admin/deletecategory/")
    public Map<String,String> deletecategory(@RequestBody Map<String,String> m){
        Map<String,String> ret=new HashMap<>();
        String isbn=m.get("isbn");
        Category category=categoryDAO.findByIsbn(isbn);
        System.out.println(category);
        categoryDAO.delete(category);
        ret.put("message","delete successfully");
        return ret;
    }

    @CrossOrigin
    @PostMapping("/api/admin/addcategory/")
    public Map<String,String> addcategory(@RequestBody Map<String,String> m){
        Map<String,String> ret=new HashMap<>();
        String isbn=m.get("isbn");

        String bookname=m.get("bookname");
        String publisher=m.get("publisher");
        String author=m.get("author");
        String type=m.get("type");
        String publishdate=m.get("publishdate");
        String url=m.get("url");
        String bookabstract=m.get("bookabstract");
        Double price=Double.parseDouble(m.get("price"));

        Category c=new Category(isbn,bookname,publisher,author,type,publishdate,url,bookabstract,price);
        categoryDAO.saveAndFlush(c);
        ret.put("message","add successfully");
        return ret;
    }

    //获取全部category
    @CrossOrigin
    @GetMapping ("/api/admin/getallcategory/")
    public List<Category> getallcategory(){
        return categoryDAO.findAll();
    }


}

