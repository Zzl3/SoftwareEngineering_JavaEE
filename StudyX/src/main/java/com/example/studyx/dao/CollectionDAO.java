package com.example.studyx.dao;

import com.example.studyx.pojo.Collection;
import com.example.studyx.pojo.Collectiondir;
import com.example.studyx.pojo.primarykey.CollectdirPrimaryKey;
import com.example.studyx.pojo.primarykey.CollectionPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectionDAO extends JpaRepository<Collection, CollectionPrimaryKey> {
    List<Collection> findByUserid(Integer userid);
    List<Collection> findAll(); //查询全部
    List<Collection> findByUseridAndDirname(Integer userid,String dirname);//通过收藏夹名称查找
    @Transactional
    @Modifying
    Long deleteByUseridAndIsbn(Integer userid,String isbn);//删除需要加这个
}