package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "remark")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Remark {
    @Id
    @Column(name = "remarkid")
    Integer remarkid;

    String isbn;
    Integer userid;
    String remarktime;
    String content;

    public Integer getRemarkid() {
        return remarkid;
    }

    public void setRemarkid(Integer remarkid) {
        this.remarkid = remarkid;
    }

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

    public String getRemarktime() {
        return remarktime;
    }

    public void setRemarktime(String remarktime) {
        this.remarktime = remarktime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
