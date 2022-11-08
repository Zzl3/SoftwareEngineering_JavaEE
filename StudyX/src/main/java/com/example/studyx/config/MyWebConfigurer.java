package com.example.studyx.config;

import com.example.studyx.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    //这里就是拦截器，为了postman调试方面，都注释掉了，最后再加上
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
//        registration.addPathPatterns("/**"); //所有路径都被拦截
//        registration.excludePathPatterns(    //添加不拦截路径,这里的拦截和不拦截指的是后端的接口
//                "/api/register",
//                "/api/login/admin",
//                "/api/register/admin",
//                "/api/logout",
//                "/api/login",                    //登录路径
//                "/**/*.html",                //html静态资源
//                "/**/*.js",                  //js静态资源
//                "/**/*.css"                  //css静态资源
//        );
    }
}
