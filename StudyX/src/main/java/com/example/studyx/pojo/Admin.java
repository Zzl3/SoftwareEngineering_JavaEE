package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Admin {
    @Id
    @GenericGenerator(name = "id-generator", strategy = "increment")
    @GeneratedValue(generator = "id-generator")
    @Column(name = "id")
    int id;
    String adminname;
    String password;
    String salt;
}
