package com.example.studyx.controller;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.pojo.Category;
import com.example.studyx.pojo.Collection;
import com.example.studyx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    private CategoryDAO categoryDAO;

    @CrossOrigin
    @GetMapping("/api/searchcategory/")
    public List<Category> searchcategory(@RequestParam String standard,String content,boolean ifblur){
        if(standard.equals("isbn")){
            List<Category> ret=new ArrayList<>();
            Category find=categoryDAO.findByIsbn(content);
            System.out.println("abcde");
            if(find!=null){
                ret.add(find);
            }
            return ret;
        }

        if(standard.equals("author")){
            return categoryDAO.findByAuthorContaining(content);
        }

        if(standard.equals("bookname")){
            if(!ifblur)
                //不模糊搜索
                return categoryDAO.findByBookname(content);
            else
                return categoryDAO.findAllByBooknameContains(content);
        }

        if(standard.equals("publisher")){
            return categoryDAO.findByPublisher(content);
        }

        if(standard.equals("publishdate"))
            return categoryDAO.findByPublishdate(content);

        //正常是不会运行到这里到
        return null;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/getcategoryinfo")
    public Category getcategoryinfo(@RequestBody String a) {
        Category category=categoryDAO.findByIsbn(a);
        return category;
    }

    @CrossOrigin
    @GetMapping("/api/user/getcategorynum")
    public Integer[] getcategorynum(){
        Map<String,Integer> ret=new HashMap<>();
        List<Category> categories=categoryDAO.findAll();
        for(Category c:categories){
            if(!ret.containsKey(c.getType())){
                ret.put(c.getType(),1);
            }
            else{
                ret.put(c.getType(),ret.get(c.getType())+1);
            }
        }

        Integer[] returnvalue=new Integer[6];
        int i=0;
        for(int j=0;j<6;j++) returnvalue[j]=0;
        for(Integer c:ret.values()){
            returnvalue[i++]=c;
        }
        return returnvalue;
    }
}
