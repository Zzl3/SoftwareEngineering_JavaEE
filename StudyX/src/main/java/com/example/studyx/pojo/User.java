package com.example.studyx.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Interceptor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

//这里就是规定类的属性，set和get的
//以及建立数据库的映射,所以名称都要和数据库的一样
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
    Integer id;
    String username;
    String password;
    String salt;
    String phone;
    String mail;
    String gender;
    String detail;
    String school;
    Integer age;
    String url;
    String integration;
    String realname;
    String idcard;
    String status;
}
