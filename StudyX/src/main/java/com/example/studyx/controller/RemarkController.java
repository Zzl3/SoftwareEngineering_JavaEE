package com.example.studyx.controller;

import com.example.studyx.dao.RemarkDAO;
import com.example.studyx.pojo.Remark;
import com.example.studyx.result.Result;
import com.example.studyx.service.RemarkService;
import com.example.studyx.result.ResultFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RemarkController {
    @Autowired
    private RemarkDAO remarkDAO;

    @Autowired
    private RemarkService remarkService;

    @CrossOrigin
    @GetMapping("/api/remarks")
    public Result listBooks(@RequestParam String isbn) {
        return ResultFactory.buildSuccessResult(remarkService.list(isbn));
    }

    @CrossOrigin
    @PostMapping("/api/addremarks")
    public Result addOrUpdate(@RequestBody Remark remark) throws Exception {
        remarkService.addOrUpdate(remark);
        return new Result(200,"评论显示成功",remark);
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    public Result delete(@RequestBody Remark remark) throws Exception {
        //int id=remarkid;
        remarkService.deleteById(remark.getRemarkid());
        return new Result(200,"评论已删除",remark);

    }
}
