package com.example.studyx.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.studyx.dao.UserMapper;
import com.example.studyx.pojo.User;
import com.example.studyx.service.Admin.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> getUserStatus(String username) {
        Map<String,String> m=new HashMap<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=userMapper.selectOne(queryWrapper);
        if(user.getStatus().equals("abnormal")){
            m.put("message","该用户已经处于封禁状态");
            return m;
        }
        user.setStatus("abnormal");
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",username);
        int update=userMapper.update(user,updateWrapper);
        m.put("message","ban user successfully");
        return m;
    }
}
