package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.openqa.selenium.WebElement;
import javax.persistence.*;

//这里就是规定类的属性，set和get的
//以及建立数据库的映射
//GenericGenerator是自增主键
//@Builder 注解开发，那些set,get,noexits都不需要了

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GenericGenerator(name = "id-generator",strategy = "increment")
    @GeneratedValue(generator = "id-generator")
    @Column(name = "id")
    int id;
    String username;
    String password;
    String salt;
}
