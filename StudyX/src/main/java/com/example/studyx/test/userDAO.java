package com.example.studyx.test;


import com.example.studyx.pojo.User;
import com.example.studyx.test.addFeedback.addFeedbackTest;

public class userDAO {
    public static boolean STUB_SUC=true;

    //根据userid寻找用户
    public static User getById(Integer id){
        User result=new User();
        result.setId(1);
        if(addFeedbackTest.CASE_NUM.compareTo("UT_TC_001_001_001_001")==0||addFeedbackTest.CASE_NUM.compareTo("UT_TC_001_001_003_001")==0||addFeedbackTest.CASE_NUM.compareTo("UT_TC_001_001_004_001")==0){
            if(id!=1){
                STUB_SUC=false;
                return null;
            }
            return result;
        }
        if(addFeedbackTest.CASE_NUM.compareTo("UT_TC_001_001_002_001")==0){
            if(id!=0){
                STUB_SUC=false;
            }
            return null;
        }
        STUB_SUC=false;
        return result;

    }
}
