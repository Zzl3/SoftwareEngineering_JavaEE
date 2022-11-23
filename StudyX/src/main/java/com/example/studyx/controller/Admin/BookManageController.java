package com.example.studyx.controller.Admin;

import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookManageController {
    @Autowired
    private CategoryDAO categoryDAO;

    @PostMapping("/admin/managebook/")
    public Map<String,String> modifybookproperty(@RequestBody Map<String,Object> m){
        Map<String,String> ret=new HashMap<>();
        String isbn=(String)m.get("isbn");
        Category category=categoryDAO.findByIsbn(isbn);

        category.setAuthor((String)m.get("author"));
        category.setBookname((String)m.get("bookname"));
        category.setPrice((Double)m.get("price"));
        category.setBookabstract((String) m.get("bookabstract"));
        category.setPublishdate((String)m.get("publishdate"));
        category.setType((String)m.get("type"));
        category.setUrl((String) m.get("url"));

        categoryDAO.save(category);
        ret.put("message","succeed");
        return ret;
    }



    //管理员下架某书籍
    public Map<String,String> deletecategory(@RequestBody String isbn){
        Map<String,String> ret=new HashMap<>();
        Category category=categoryDAO.findByIsbn(isbn);
        categoryDAO.delete(category);
        ret.put("message","delete successfully");
        return ret;
    }
}
