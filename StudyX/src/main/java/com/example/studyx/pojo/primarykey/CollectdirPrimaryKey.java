package com.example.studyx.pojo.primarykey;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class CollectdirPrimaryKey implements Serializable {
    private String dirname;
    private Integer userid;

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectdirPrimaryKey that = (CollectdirPrimaryKey) o;
        return Objects.equals(dirname, that.dirname) && Objects.equals(userid, that.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dirname, userid);
    }
}