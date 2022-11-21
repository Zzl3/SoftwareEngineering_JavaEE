package com.example.studyx.pojo;

import com.example.studyx.pojo.primarykey.CollectdirPrimaryKey;
import com.example.studyx.pojo.primarykey.CollectionPrimaryKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "collection")
@IdClass(CollectionPrimaryKey.class)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Collection implements Serializable {

    @Id
    @Column(name = "isbn", nullable = false)
    String isbn;

    @Id
    @Column(name = "userid", nullable = false)
    Integer userid;
    String collectiontime;
    String dirname;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }
}