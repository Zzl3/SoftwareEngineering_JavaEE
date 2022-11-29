package com.example.studyx.service;


import com.example.studyx.dao.RemarkDAO;
import com.example.studyx.pojo.Remark;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class RemarkService {
    @Autowired
    RemarkDAO remarkDAO;

    public List<Remark> list(String isbn) {
        //Sort sort = new Sort(Sort.Direction.DESC, "isbn");
        return remarkDAO.findByIsbn(isbn);
    }

    public void addOrUpdate(Remark book) {
        remarkDAO.save(book);
    }
    public void deleteById(int remarkid) {
        remarkDAO.deleteById(remarkid);
    }

    public Remark findById(int remarkid){
        return remarkDAO.findByRemarkid(remarkid);
    }

}
