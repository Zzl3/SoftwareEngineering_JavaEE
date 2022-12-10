package com.example.studyx.utils;

import static java.sql.Types.NULL;

public class MyGlobal{
    public static int getUserid() {
        return userid;
    }

    public static void setUserid(int userid) {
        MyGlobal.userid = userid;
    }

    static int userid;
}