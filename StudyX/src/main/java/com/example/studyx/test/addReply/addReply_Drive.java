package com.example.studyx.test.addReply;

import com.example.studyx.test.addFeedback.addFeedbackTest;

public class addReply_Drive {
    private static boolean UT_TC_001_002_001_001=false;
    private static boolean UT_TC_001_002_002_001=false;
    private static boolean UT_TC_001_002_003_001=false;
    private static boolean UT_TC_001_002_004_001=false;

    public static void main(String[] args) {
        if(addReplyTest.UT_TC_001_002_001_001()){
            UT_TC_001_002_001_001=true;
        }
        if(addReplyTest.UT_TC_001_002_002_001()){
            UT_TC_001_002_002_001=true;
        }
        if(addReplyTest.UT_TC_001_002_003_001()){
            UT_TC_001_002_003_001=true;
        }
        if(addReplyTest.UT_TC_001_002_004_001()){
            UT_TC_001_002_004_001=true;
        }

        System.out.println(UT_TC_001_002_001_001);
        System.out.println(UT_TC_001_002_002_001);
        System.out.println(UT_TC_001_002_003_001);
        System.out.println(UT_TC_001_002_004_001);

    }
}
