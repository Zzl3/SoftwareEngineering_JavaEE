package com.example.studyx.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class GetNowTime {
    static public String gettime(){
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }
}
