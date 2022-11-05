package com.example.studyx.dao;

import com.example.studyx.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

//操作数据库对象，相当于写sql语句的地方
//这里函数名就是对数据库的操作，有save,exists,find,count,delete
//里面的By,And,Between等都有对应意思
//继承<类,ID>

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User getByUsernameAndPassword(String username,String password);
}
