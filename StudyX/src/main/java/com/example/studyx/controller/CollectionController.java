package com.example.studyx.controller;


import com.example.studyx.dao.CollectionDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Collection;
import com.example.studyx.pojo.Collectiondir;
import com.example.studyx.utils.GetNowTime;
import com.example.studyx.utils.MyGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CollectionController {
    @Autowired
    CollectionDAO collectionDAO;

    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @PostMapping(value = "/api/user/deletecollection")
    public long deletecollection(@RequestBody Map<String,String> datas) {
        Integer userid=Integer.valueOf(datas.get("userid").toString());
        String isbn=datas.get("isbn").toString();
        userid= MyGlobal.getUserid();
        long a=collectionDAO.deleteByUseridAndIsbn(userid,isbn);
        return a;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/getcollection")
    public List<Collection> getcollectionbydirname(@RequestBody Map<String,String> datas) {
        Integer userid=Integer.valueOf(datas.get("userid").toString());
        userid=MyGlobal.getUserid();
        String dirname=datas.get("dirname").toString();
        List<Collection> collections=collectionDAO.findByUseridAndDirname(userid,dirname);
        return collections;
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/setcollection")
    public int setcollection(@RequestBody Collection collection) {
        try {
            String createTime=GetNowTime.gettime().toString();//得到当前时间
            collection.setUserid(MyGlobal.getUserid());
            collection.setCollectiontime(createTime);
            collectionDAO.save(collection);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
