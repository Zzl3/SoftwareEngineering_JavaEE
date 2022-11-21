package com.example.studyx.pojo;

import com.example.studyx.pojo.primarykey.CollectdirPrimaryKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

//两个主码
//不能驼峰命名
@Entity
@Table(name = "collectiondir")
@IdClass(CollectdirPrimaryKey.class)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Collectiondir implements Serializable {

    @Id
    @Column(name = "dirname", nullable = false)
    String dirname;

    @Id
    @Column(name = "userid", nullable = false)
    Integer userid;
    String createtime;
    String content;
    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }
}

