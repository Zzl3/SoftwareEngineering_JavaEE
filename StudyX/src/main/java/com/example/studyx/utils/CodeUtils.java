package com.example.studyx.utils;

import org.springframework.stereotype.Component;

@Component
public class CodeUtils {

    private String[] patch ={"00000","0000","000","00","0",""};
    public String generator(String tel){
        int hash=tel.hashCode();
        int encryption=20206666;
        long result = hash ^ encryption;
        long nowTime=System.currentTimeMillis();
        result=result^nowTime;
        long code=result%1000000;
        code=code<0?-code:code;


        String codeStr=code+"";
        int len=codeStr.length();

        return patch[len-1]+code;
    }

    public static void main(String[] args){
        System.out.println(new CodeUtils().generator("18174298953"));
    }
}
