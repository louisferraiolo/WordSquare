package com.louisferraiolo;

public class Helper {

    static long startTime, endTime;
    public static void profile( String message )
    {
        if(message == null) {
            startTime = System.currentTimeMillis();
        }else if( startTime == 0 ) {
            startTime = System.currentTimeMillis();
        }else{
            endTime = System.currentTimeMillis();
            System.out.println(message + " took " + (endTime - startTime) + " ms");
        }
    }



}
