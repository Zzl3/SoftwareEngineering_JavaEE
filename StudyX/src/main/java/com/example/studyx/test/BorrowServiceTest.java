package com.example.studyx.test;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

import com.example.studyx.controller.BorrowController;
import com.example.studyx.dao.BookDAO;
import com.example.studyx.dao.BorrowDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.*;
import com.example.studyx.pojo.primarykey.BorrowPrimaryKey;

class BorrowServiceTest {
    BorrowController borrowController=new BorrowController();
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testSetborrowWithValidRequestAndActiveUser() {
        // Arrange
        Borrow borrow = new Borrow();
        borrow.setStatus("申请中");
        borrow.setUserid(1);
        User user = new User();
        user.setStatus("active");
        user.setIntegration("25");

        // Act
        Borrow result = borrowController.setborrow(borrow);
    }

    @org.junit.jupiter.api.Test
    void testSetborrowWithBannedUser() {
        // Arrange
        Borrow borrow = new Borrow();
        borrow.setStatus("申请中");
        borrow.setUserid(1);
        User user = new User();
        user.setStatus("banned");
        user.setIntegration("25");

        // Act
        Borrow result = borrowController.setborrow(borrow);

    }

    @org.junit.jupiter.api.Test
    void testSetborrowWithLowIntegrationUser() {
        // Arrange
        Borrow borrow = new Borrow();
        borrow.setStatus("申请中");
        borrow.setUserid(1);
        User user = new User();
        user.setStatus("active");
        user.setIntegration("15");

        // Act
        Borrow result = borrowController.setborrow(borrow);
    }

    @org.junit.jupiter.api.Test
    void testSetborrowWithUnrequestedBorrow() {
        // Arrange
        Borrow borrow = new Borrow();
        borrow.setStatus("未申请");
        borrow.setUserid(1);

        // Act
        Borrow result = borrowController.setborrow(borrow);
    }

    @org.junit.jupiter.api.Test
    void testSetborrowWithApprovedBorrow() {
        // Arrange
        Borrow borrow = new Borrow();
        borrow.setStatus("已通过");
        borrow.setUserid(1);

        // Act
        Borrow result = borrowController.setborrow(borrow);
    }
}