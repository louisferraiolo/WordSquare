package com.louisferraiolo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class MainTest {

    private static Main main;

    @BeforeAll
    public static void testMain() {
        String[] args = null;
        main = new Main(); // TODO: FIX CODE
    }

    @DisplayName("Download Dictionary from URL")
    @Test
    /*
    Reasons for a fail:
    - No internet (cannot test inside here)
    - Wrong URL
    - Not enough space
     */
    public void testDownloadDictionaryFromURL() {
        try {
            main.dicList = main.initialDictionary();
        }catch(IOException exception) {
            exception.printStackTrace();
        }
        Assertions.assertNotNull( main.dicList, "dictionary list is null");
        //Assertions.assertEquals("aa", main.dicList.get(0), "dictionary list first index value isn't what expected." );
    }

    @DisplayName("Test user inputs")
    @Test
    /*
    Reasons for a fail:
    - Anything other than 2 args
    - If first arg is anything other than a number.
    - If the second arg len of characters isn't the first arg 2'd.
     */
    public void testUserInputs() {
        // Check declining all horrible inputs
        Assertions.assertFalse(main.checkUserInput("1"), "Method allowed '1' as arg");
        Assertions.assertFalse(main.checkUserInput("a"), "Method allowed 'a' as arg");
        Assertions.assertFalse(main.checkUserInput("a 1"), "Method allowed 'a 1' as arg");
        Assertions.assertFalse(main.checkUserInput("1 1"), "Method allowed '1 1' as arg");
        Assertions.assertFalse(main.checkUserInput("2 acb"), "Method allowed '2 acb' as arg");
        Assertions.assertFalse(main.checkUserInput("3 acb"), "Method allowed '3 acb' as arg");
        Assertions.assertFalse(main.checkUserInput("2 acab lol"), "Method allowed '2 acab lol' as arg");
    }

    @DisplayName("Test contain digit input")
    @Test
    /*
    Reasons for a fail:
    - Anything other than a digit
     */
    public void testContainsDigit()
    {
        // Check declining all horrible inputs
        Assertions.assertTrue(main.containsNum("1"), "returned as false for 1");
        Assertions.assertFalse(main.containsNum("a"), "Method allowed 'a' as digit");
        Assertions.assertFalse(main.containsNum("lo lol"), "Method allowed 'lo lol' as digit");
    }
}