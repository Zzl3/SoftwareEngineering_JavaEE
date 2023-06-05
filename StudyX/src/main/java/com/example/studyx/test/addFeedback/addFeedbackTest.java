package com.example.studyx.test.addFeedback;
import com.example.studyx.test.FeedbackService;
import com.example.studyx.test.userDAO;
import junit.framework.TestCase;

public class addFeedbackTest extends TestCase{
    public static String CASE_NUM="";

    public static boolean UT_TC_001_001_001_001(){
        CASE_NUM="UT_TC_001_001_001_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addFeedback("",1)==false&& userDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }

    public static boolean UT_TC_001_001_002_001(){
        CASE_NUM="UT_TC_001_001_002_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addFeedback("testContent",0)==false&&userDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }

    public static boolean UT_TC_001_001_003_001(){
        CASE_NUM="UT_TC_001_001_003_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addFeedback("呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃",1)==false&&userDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }

    public static boolean UT_TC_001_001_004_001(){
        CASE_NUM="UT_TC_001_001_004_001";
        boolean CASE_SUC =false;
        if(FeedbackService.addFeedback("testContent",1)==true&&userDAO.STUB_SUC==true){
            CASE_SUC=true;
        }
        return CASE_SUC;
    }
}
