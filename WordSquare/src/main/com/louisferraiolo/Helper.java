package com.louisferraiolo;

public class Helper {

    // This function is used for profiling function times.
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

    // This function returns either a true or false value depending on whether the argument specified contains a digit.
    public static boolean containsNum( String s )
    {
        boolean containsNum = false;
        if( s != null ) {
            for( char c : s.toCharArray() ) {
                if( containsNum = Character.isDigit(c) ) {
                    break;
                }
            }
        }
        return containsNum;
    }

    // This function returns a string with the letters left from an integer array which holds the frequency of each char in the alphabet.
    public static String getLettersLeft( int[]array )
    {
        String buildingString = "";
        for( int i = 0; i < array.length; i++ )
        {
            char c = (char)(i+97);
            if( array[i] > 0 )
            {
                for( int j = 0; j < array[i]; j++ )
                    buildingString += c;
            }
        }
        return buildingString;
    }

    // This function returns an int array of char frequencies from a char array which is usually a string.toCharArray.
    public static int[] getCharCount( char[] array )
    {
        int[] countedChars = new int[26];
        for( char currentChar : array )
        {
            int index = currentChar - 97; // 97 as it is the ASCII code for 'a'.
            countedChars[index]++; // Increment the amount of times that specific charcter in the alphabet is used.
            //System.out.println(currentChar + " is used: " + countedChars[index] + " times.");
        }
        return countedChars; // Not straight assigning it to charsLeft as the jUnit testing can use the method and return something.
    }


}
