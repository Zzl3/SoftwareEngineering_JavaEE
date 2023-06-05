package com.example.studyx.test;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

import com.example.studyx.controller.BorrowController;
import com.example.studyx.controller.CategoryController;
import com.example.studyx.dao.CategoryDAO;
import com.example.studyx.dao.BorrowDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.*;
import com.example.studyx.pojo.primarykey.BorrowPrimaryKey;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class SearchServiceTest {
    CategoryController categoryController=new CategoryController();
    CategoryDAO categoryDAO=new CategoryDAO() {
        @Override
        public Category findByIsbn(String isbn) {
            return null;
        }

        @Override
        public List<Category> findByAuthorContaining(String author) {
            return null;
        }

        @Override
        public List<Category> findByBookname(String bookname) {
            return null;
        }

        @Override
        public List<Category> findAllByBooknameContains(String bookname) {
            return null;
        }

        @Override
        public List<Category> findByPublisher(String publisher) {
            return null;
        }

        @Override
        public List<Category> findByPublishdate(String publishdate) {
            return null;
        }

        @Override
        public List<Category> findByAuthor(String author) {
            return null;
        }

        @Override
        public List<Category> findAllByBooknameLikeOrAuthorLike(String keyword1, String keyword2) {
            return null;
        }

        @Override
        public List<Category> findByType(String type) {
            return null;
        }

        @Override
        public List<Category> findAll() {
            return null;
        }

        @Override
        public List<Category> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Category> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public <S extends Category> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Category> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Category> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Category getOne(Integer integer) {
            return null;
        }

        @Override
        public Category getById(Integer integer) {
            return null;
        }

        @Override
        public Category getReferenceById(Integer integer) {
            return null;
        }

        @Override
        public <S extends Category> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Category> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Category> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Category> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Category entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Category> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Category> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Category> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Category> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithIsbnAndValidContentAndNotBlur() {
        // Arrange
        String standard = "isbn";
        String content = "1234567890";
        boolean ifblur = false;
        Category category = new Category();

        //Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(category, result.get(0));
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithIsbnAndValidContentAndBlur() {
        // Arrange
        String standard = "isbn";
        String content = "1234567890";
        boolean ifblur = true;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
//        when(categoryDAO.findAllByBooknameContains(content)).thenReturn(categories);
//
//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categories, result);
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithAuthorAndValidContentAndNotBlur() {
        // Arrange
        String standard = "author";
        String content = "J.K. Rowling";
        boolean ifblur = false;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
//        when(categoryDAO.findByAuthorContaining(content)).thenReturn(categories);

//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categories, result);
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithBooknameAndValidContentAndNotBlur() {
        // Arrange
        String standard = "bookname";
        String content = "Harry Potter";
        boolean ifblur = false;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
//        when(categoryDAO.findByBookname(content)).thenReturn(categories);
//
//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categories, result);
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithBooknameAndValidContentAndBlur() {
        // Arrange
        String standard = "bookname";
        String content = "Harry Potter";
        boolean ifblur = true;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
//        when(categoryDAO.findAllByBooknameContains(content)).thenReturn(categories);
//
//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categories, result);
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithPublisherAndValidContentAndNotBlur() {
        // Arrange
        String standard = "publisher";
        String content = "Scholastic";
        boolean ifblur = false;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
//        when(categoryDAO.findByPublisher(content)).thenReturn(categories);
//
//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categories, result);
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithPublishdateAndValidContentAndNotBlur() {
        // Arrange
        String standard = "publishdate";
        String content = "2022-01-01";
        boolean ifblur = false;
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
//        when(categoryDAO.findByPublishdate(content)).thenReturn(categories);
//
//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categories, result);
    }

    @org.junit.jupiter.api.Test
    void testSearchcategoryWithInvalidStandard() {
        // Arrange
        String standard = "invalidStandard";
        String content = "invalidContent";
        boolean ifblur = false;

//        // Act
        List<Category> result = categoryController.searchcategory(standard, content, ifblur);
//
//        // Assert
        assertNull(result);
    }
}