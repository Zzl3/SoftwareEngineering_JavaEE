package com.example.studyx.dao;

import com.example.studyx.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
    Category findByIsbn(String isbn);

    List<Category> findByAuthorContaining(String author);

    List<Category> findByBookname(String bookname);

    //bookname fluzzy search
    List<Category> findAllByBooknameContains(String bookname);

    List<Category> findByPublisher(String publisher);

    List<Category> findByPublishdate(String publishdate);

}
