package com.example.studyx.pojo.primarykey;


import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BorrowPrimaryKey  implements Serializable {
    private Integer userid;
    private Integer bookid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowPrimaryKey that = (BorrowPrimaryKey) o;
        return Objects.equals(userid, that.userid) && Objects.equals(bookid, that.bookid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, bookid);
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }
}
