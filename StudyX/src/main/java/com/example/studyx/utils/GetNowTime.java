package com.example.studyx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNowTime {
    static public String gettime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());//得到时间
        return date.toString();
    }
}
