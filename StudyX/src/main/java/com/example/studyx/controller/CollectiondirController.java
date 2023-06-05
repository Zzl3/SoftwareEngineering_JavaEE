package com.example.studyx.controller;


import com.example.studyx.dao.CollectiondirDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Collectiondir;
import com.example.studyx.utils.GetNowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CollectiondirController {
    @Autowired
    CollectiondirDAO collectiondirDAO;

    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @PostMapping(value = "/api/user/deletecollectiondir")
    public long deletecollectiondir(@RequestBody Map<String,String> datas) {
        Integer userid=Integer.valueOf(datas.get("userid").toString());
        String title=datas.get("dirname").toString();
        long a=collectiondirDAO.deleteByUseridAndDirname(userid,title);
        return a;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/getcollectiondir")
    public List<Collectiondir> getcollectiondir(@RequestBody String id) {
        List<Collectiondir> collectiondir = collectiondirDAO.findByUserid(Integer.valueOf(id));
        return collectiondir;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/setcollectiondir")
    public int setcollectiondir(@RequestBody Collectiondir collectiondir) {
        try {
            String createTime=GetNowTime.gettime().toString();//得到当前时间
            collectiondir.setCreatetime(createTime);
            collectiondirDAO.save(collectiondir);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/findallcollection")
    @ResponseBody
    public List<Collectiondir> findallcollection() {
        List<Collectiondir> collectiondirs=collectiondirDAO.findAll();
        return collectiondirs;
    }
}
