package com.example.studyx.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
@Table(name = "donationmoney")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Donationmoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer donationid;
    Integer userid;
    Double donationcost;
    String donationtime;
}
