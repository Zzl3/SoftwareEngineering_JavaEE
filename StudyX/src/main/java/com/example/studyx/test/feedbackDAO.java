package com.example.studyx.test;


import com.example.studyx.pojo.Feedback;
import com.example.studyx.pojo.User;
import com.example.studyx.test.addReply.addReplyTest;

public class feedbackDAO {
    public static boolean STUB_SUC=true;

    //根据userid寻找用户
    public static Feedback getById(Integer id){
        Feedback result=new Feedback();
        result.setId(1);
        if(addReplyTest.CASE_NUM.compareTo("UT_TC_001_002_001_001")==0||addReplyTest.CASE_NUM.compareTo("UT_TC_001_002_003_001")==0||addReplyTest.CASE_NUM.compareTo("UT_TC_001_002_004_001")==0){
            if(id!=1){
                STUB_SUC=false;
                return null;
            }
            return result;
        }
        if(addReplyTest.CASE_NUM.compareTo("UT_TC_001_002_002_001")==0){
            if(id!=0){
                STUB_SUC=false;
            }
            return null;
        }
        STUB_SUC=false;
        return null;

    }
    public static void save(Feedback feedback){

    }
}
