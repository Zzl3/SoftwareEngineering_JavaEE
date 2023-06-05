package com.example.studyx.test.addReply;

import com.example.studyx.test.FeedbackService;
import com.example.studyx.test.feedbackDAO;
import junit.framework.TestCase;

public class addReplyTest extends TestCase {
    public static String CASE_NUM="";

    public static boolean UT_TC_001_002_001_001(){
        CASE_NUM="UT_TC_001_002_001_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addReply("",1)==false&& feedbackDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }

    public static boolean UT_TC_001_002_002_001(){
        CASE_NUM="UT_TC_001_002_002_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addReply("testReply",0)==false&&feedbackDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }

    public static boolean UT_TC_001_002_003_001(){
        CASE_NUM="UT_TC_001_002_003_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addReply("呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃",1)==false&&feedbackDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }

    public static boolean UT_TC_001_002_004_001(){
        CASE_NUM="UT_TC_001_002_004_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addReply("testReply",1)==true&&feedbackDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }
}
