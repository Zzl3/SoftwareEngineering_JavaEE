package com.example.studyx.test.addFeedback;

public class addFeedback_Drive {
    private static boolean UT_TC_001_001_001_001=false;
    private static boolean UT_TC_001_001_002_001=false;
    private static boolean UT_TC_001_001_003_001=false;
    private static boolean UT_TC_001_001_004_001=false;

    public static void main(String[] args) {
        if(addFeedbackTest.UT_TC_001_001_001_001()){
            UT_TC_001_001_001_001=true;
        }
        if(addFeedbackTest.UT_TC_001_001_002_001()){
            UT_TC_001_001_002_001=true;
        }
        if(addFeedbackTest.UT_TC_001_001_003_001()){
            UT_TC_001_001_003_001=true;
        }
        if(addFeedbackTest.UT_TC_001_001_004_001()){
            UT_TC_001_001_004_001=true;
        }

        System.out.println(UT_TC_001_001_001_001);
        System.out.println(UT_TC_001_001_002_001);
        System.out.println(UT_TC_001_001_003_001);
        System.out.println(UT_TC_001_001_004_001);

    }
}
