package com.example.studyx.test;

import com.example.studyx.dao.BookDAO;
import com.example.studyx.dao.BorrowDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.*;
import com.example.studyx.pojo.primarykey.BorrowPrimaryKey;
import com.example.studyx.utils.GetNowTime;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BorrowService {
    public Borrow setborrow(@RequestBody Borrow borrow) {
        BorrowDAO borrowDAO= new BorrowDAO() {
            @Override
            public List<Borrow> findAll() {
                return null;
            }

            @Override
            public List<Borrow> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Borrow> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public List<Borrow> findAllById(Iterable<BorrowPrimaryKey> borrowPrimaryKeys) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(BorrowPrimaryKey borrowPrimaryKey) {

            }

            @Override
            public void delete(Borrow entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends BorrowPrimaryKey> borrowPrimaryKeys) {

            }

            @Override
            public void deleteAll(Iterable<? extends Borrow> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Borrow> S save(S entity) {
                return null;
            }

            @Override
            public <S extends Borrow> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<Borrow> findById(BorrowPrimaryKey borrowPrimaryKey) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(BorrowPrimaryKey borrowPrimaryKey) {
                return false;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Borrow> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Borrow> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Borrow> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<BorrowPrimaryKey> borrowPrimaryKeys) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Borrow getOne(BorrowPrimaryKey borrowPrimaryKey) {
                return null;
            }

            @Override
            public Borrow getById(BorrowPrimaryKey borrowPrimaryKey) {
                return null;
            }

            @Override
            public Borrow getReferenceById(BorrowPrimaryKey borrowPrimaryKey) {
                return null;
            }

            @Override
            public <S extends Borrow> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Borrow> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Borrow> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends Borrow> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Borrow> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Borrow> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Borrow, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public List<Borrow> findByUserid(Integer userid) {
                return null;
            }

            @Override
            public Borrow findByUseridAndBookid(Integer userid, Integer bookid) {
                return null;
            }
        };
        try {
            String createTime = GetNowTime.gettime().toString();//得到当前时间
             if (borrow.getStatus().equals("申请中")) {
                Integer userid = borrow.getUserid();
                User user = userDAO.getById(userid);
                if (user.getStatus().equals("banned")||(Integer.valueOf(user.getIntegration())<20))//被封禁或者积分小于20不可借
                    return null;
                borrow.setStarttime("---");
                borrow.setReturntime("---");
                borrow.setDuring("---");
            }
            borrowDAO.save(borrow);
            return borrow;
        } catch (Exception e) {
            return null;
        }
    }
}
