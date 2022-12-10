package com.example.studyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication()

public class StudyXApplication implements WebMvcConfigurer {
// 放行所有跨域请求

    public static void main(String[] args) {
        SpringApplication.run(StudyXApplication.class, args);
    }

}
