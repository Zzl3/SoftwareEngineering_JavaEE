package com.example.studyx.dao;

import com.example.studyx.pojo.Admin; //这里就是引入了pojo层的实体类
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<Admin,Integer> {
    Admin findByAdminname(String adminname);
    Admin getByAdminnameAndPassword(String adminname,String password);
}