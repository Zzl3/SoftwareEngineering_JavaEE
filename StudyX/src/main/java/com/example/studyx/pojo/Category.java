package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Category {
    @Id
    @Column(name = "isbn")
    String isbn;

    String bookname;
    String publisher;
    String author;
    String type;
    String publishdate;
    String url;
    String bookabstract;
    String label;
    Double price;
    Double mark;
}