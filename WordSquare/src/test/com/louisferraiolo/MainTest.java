package com.louisferraiolo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class MainTest {

    private static Main main;
    private static WordSquare wordSquare;

    @BeforeAll
    public static void testMain() {
        String[] args = null;
        main = new Main();
        wordSquare = new WordSquare(4, "eeeeddoonnnsssrv");
    }

    @DisplayName("Download Dictionary from path")
    @Test
    /*
    Reasons for a fail:
    - Not enough space
    - File not found
     */
    public void testDownloadDictionaryFromDesktop() {
        try {
            wordSquare.setDictionary(main.initialDictionary());
        } catch( IOException exception ) {
            exception.printStackTrace();
        }
        Assertions.assertNotNull( wordSquare.getDictionary(), "dictionary list is null");
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

    @DisplayName("Test deleting characters")
    @Test
    /*
    Reasons for a fail:
    - Digit instead of character (no chance of that happening due to validation previously).
     */
    public void testDeletingCharacters()
    {
        // Check declining all horrible inputs
        String letters = "abbcddef";
        char currentChar = letters.charAt(1);
        int index = currentChar - 97; // 97 as it is the ASCII code for 'a'.
        int[] charsLeft = main.countChars(letters.toCharArray());
        Assertions.assertEquals(charsLeft[index], 2); // Checking there are 2 b's.
        String word = "dad";
        currentChar = word.charAt(0);
        index = currentChar - 97;
        charsLeft = main.removeCharacters(word, charsLeft);
        Assertions.assertEquals(charsLeft[index], 0); // Should return as 0 as used the a.
    }

    @DisplayName("Test applicability")
    @Test
    /*
    Reasons for a fail:
    - Digit instead of character (no chance of that happening due to validation previously).
     */
    public void testApplicability()
    {
        // Check declining all horrible inputs
        String letters = "aabceillpp";
        int[] charsLeft = main.countChars(letters.toCharArray());
        String word = "applicable";
        boolean applicable = main.checkApplicable(word, charsLeft);
        Assertions.assertTrue(applicable); // Should be true as the letterset contains all the characters inside word.
        word = "dog";
        applicable = main.checkApplicable(word, charsLeft);
        Assertions.assertFalse(applicable); // Should be false as the letterset does not contain the nessecary characters which is (d, o and g).
    }
}