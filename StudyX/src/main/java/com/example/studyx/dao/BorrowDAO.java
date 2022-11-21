package com.example.studyx.dao;

import com.example.studyx.pojo.Admin;
import com.example.studyx.pojo.Borrow;
import com.example.studyx.pojo.Collection;
import com.example.studyx.pojo.primarykey.BorrowPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowDAO extends JpaRepository<Borrow, BorrowPrimaryKey> {
    List<Borrow> findByUserid(Integer userid);
    Borrow findByUseridAndBookid(Integer userid,Integer bookid);
}