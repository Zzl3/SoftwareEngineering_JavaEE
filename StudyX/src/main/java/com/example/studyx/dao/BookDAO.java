package com.example.studyx.dao;

import com.example.studyx.pojo.Book;
import com.example.studyx.pojo.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookDAO extends JpaRepository<Book,Integer> {
    Book findByBookid(Integer bookid);
}
