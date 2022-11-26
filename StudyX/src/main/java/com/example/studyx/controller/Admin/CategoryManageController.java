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
        Map<String,String> ret=new HashMap<>();
        String isbn=(String)m.get("isbn");
        System.out.println(isbn);
        Category category=categoryDAO.findByIsbn(isbn);

        System.out.println(category.getAuthor());
        System.out.println(m.get("price"));
        category.setAuthor((String)m.get("author"));
        category.setBookname((String)m.get("bookname"));
        category.setPrice(Double.parseDouble((String)m.get("price")));
        category.setBookabstract((String) m.get("bookabstract"));
        category.setPublishdate((String)m.get("publishdate"));
        category.setType((String)m.get("type"));

        categoryDAO.save(category);
        ret.put("message","succeed");
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

    //获取全部category
    @CrossOrigin
    @GetMapping ("/api/admin/getallcategory/")
    public List<Category> getallcategory(){
        return categoryDAO.findAll();
    }


}

