package com.example.studyx.pojo;

import com.example.studyx.pojo.primarykey.BorrowPrimaryKey;
import com.example.studyx.pojo.primarykey.CollectdirPrimaryKey;
import com.example.studyx.pojo.primarykey.CollectionPrimaryKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "borrow")
@IdClass(BorrowPrimaryKey.class)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Borrow implements Serializable {

    @Id
    @Column(name = "bookid", nullable = false)
    Integer bookid;

    @Id
    @Column(name = "userid", nullable = false)
    Integer userid;

    String starttime;
    String during;
    String status;
    String returntime;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getDuring() {
        return during;
    }

    public void setDuring(String during) {
        this.during = during;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }
}