package com.example.studyx.pojo.primarykey;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class CollectionPrimaryKey  implements Serializable {
    Integer userid;
    String isbn;

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
        CollectionPrimaryKey that = (CollectionPrimaryKey) o;
        return Objects.equals(userid, that.userid) && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
