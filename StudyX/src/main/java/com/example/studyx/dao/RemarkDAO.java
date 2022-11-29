package com.example.studyx.dao;

import com.example.studyx.pojo.Remark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemarkDAO extends JpaRepository<Remark,Integer>{
    List<Remark> findByIsbn(String isbn);
    Remark findByRemarkid(int remarkid);
}
