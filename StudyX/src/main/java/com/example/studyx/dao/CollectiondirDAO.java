package com.example.studyx.dao;

import com.example.studyx.pojo.primarykey.CollectdirPrimaryKey;
import com.example.studyx.pojo.Collectiondir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;


public interface CollectiondirDAO extends JpaRepository<Collectiondir,CollectdirPrimaryKey> {
    List<Collectiondir> findByUserid(Integer userid);
    List<Collectiondir> findAll(); //查询全部

    @Transactional
    @Modifying
    Long deleteByUseridAndDirname(Integer userid,String dirname);//删除需要加这个
}
