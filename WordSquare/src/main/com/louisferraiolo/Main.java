package com.louisferraiolo;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<String> dicList;
    int squareNum; // TODO: Make a class for these;
    String letters; // TODO: Make a class for these;
    char[] letterCharSet; // TODO: Make a class for these;
    int[] charsLeft = new int[64];
    ArrayList<String> usedWords  = new ArrayList<>();
    ArrayList<String> wordSquare  = new ArrayList<>();


    public static void main(String[] args) {
        Main instance = new Main(); // Had to create an instance of main as you cannot call a non-static method from a static method.

        // Get users input to allow for the larger word squares and custom input.
        instance.getUserInput();
        // Convert the string to char array so it can be manipulated more easily (each character)
        instance.letterCharSet = instance.letters.toCharArray();
        //
        instance.charsLeft = instance.countChars();
        // Get all the applicable words that could be possible
        try {
            instance.dicList = instance.initialDictionary();
        }catch( IOException exception ) {
            exception.printStackTrace();
        }
        if( instance.dicList.size() > 0 ) {
            // TODO: Try eventually do all of this in the one while loop to save time.
            instance.populateSquare(instance.dicList, 1);
        }else{
            System.out.println("No applicable words for that character set.");
        }
//        main.getAllWordsStartWith('b');
//        main.printDictionary();
    }

    public int[] countChars()
    {
        int[] countedChars = new int[64];
        for( char currentChar : letterCharSet )
        {
            int index = currentChar - 97; // 97 as it is the ASCII code for 'a'.
            countedChars[index]++; // Increment the amount of times that specific charcter in the alphabet is used.
            System.out.println(currentChar + " is used: " + countedChars[index] + " times.");

        }
        return countedChars;
    }

    public ArrayList<String> initialDictionary() throws IOException
    {
        ArrayList<String> dictionaryList  = new ArrayList<>();

        String home = System.getProperty("user.home");
        File f = new File(home + File.separator + "Desktop" + File.separator + "dictionary.txt");

        BufferedReader in = new BufferedReader(new FileReader(f));
        String currentInput;
        // Do all my manipulating inside this loop as creating another for loop will make it O(n^2).
        while( (currentInput = in.readLine() ) != null )
        {
            if( currentInput.length() == squareNum  ) // Only use the words from dictionary which have same length as specified.
            {
                // Matches the optimal length.
                if( checkApplicable( currentInput, letters ) ) {
                    System.out.println(currentInput);
                    dictionaryList.add(currentInput);
                }
            }
        }
        in.close();
        return dictionaryList;
    }



    public void populateSquare( ArrayList<String> array, int what )
    {

        for( int i = 0; i < array.size(); i++ ) {
            System.out.println("what: " + what);
            String initialWord = array.get(i);
            System.out.println("word: " + initialWord);
            String letter = "";
            for( int j = 1; j <= what; j++) {
                letter += Character.toString(initialWord.charAt(j));
            }
            System.out.println("LETTER: " + letter);
            ArrayList<String> wordsStarting = getWordStarting(letter);
            if( wordsStarting.size() > 0 )
            {
                usedWords.add(initialWord);
                wordSquare.add(initialWord);
                printArray(wordsStarting, "wordsStarting");
                if(what == squareNum) {
                    System.out.println("2736126362137 - REACHED ALL WORDS !!");
                    printArray(wordSquare, "wordSquare");
                    return;
                }
                System.out.println("what: " + what);
                populateSquare(wordsStarting, ++what);
            }else{
                System.out.println("Not possible with: " + initialWord);
            }
//        usedWords.add(firstWord);
//        char letter = firstWord[1];
//        String next = getWordStarting(letter);
        }

    }

    public boolean checkApplicable( String input, String letters )
    {
        int inputLen = input.length();
        for( int i = 0; i < inputLen; i++ )
        {
            char currentChar = input.charAt(i);
            if( !letters.contains( Character.toString(currentChar) ) )
            {
                return false;
            }
        }
        return true;
    }

    public void createWordSquare()
    {
        // Make copy of letters incase this all goes to sh*t.
        String characters = letters;
        for( int i = 0; i < squareNum; i++) {
            for (int j = 0; j < dicList.size(); j++) {
                String currentWord = dicList.get(j);
                System.out.println(currentWord);
                characters = removeLetters(currentWord, characters);// This is going to remove the characters which are now used for index J.
                for(int lol =0;lol<characters.length();lol++) {
                    System.out.println(characters.charAt(lol));
                }
            }
        }
    }

    public String removeLetters( String input, String letters )
    {
        char[] removeChars = input.toCharArray();
        char[] currentChars = letters.toCharArray();
        return currentChars.toString();
    }


    public void getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Provide a word square integer and letters? 4 eeeeddoonnnsssrv");
            input = scanner.nextLine();
        } while( !checkUserInput(input) );
    }

    public boolean checkUserInput( String input )
    {
        String[] spacesSplit = input.split("\\s+");
        if ( spacesSplit.length != 2 )
            return false;
        try {
            squareNum = Integer.parseInt(spacesSplit[0]);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return false;
        }
        letters = spacesSplit[1].toLowerCase();
        if ( letters.length() != (squareNum * squareNum) )
            return false;
        if ( containsNum(letters) )
            return false;
        return true;
    }

    public final boolean containsNum(String s) {
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

    public ArrayList<String> getWordStarting( String startsWith )
    {
        ArrayList<String> wordsStarting  = new ArrayList<>();
        for( String word : dicList )
        {
            if(word.startsWith(startsWith) && !usedWords.contains(startsWith)) {
                wordsStarting.add(word);
            }
        }
        return wordsStarting;
    }

    public void printArray( ArrayList<String> array, String prefix )
    {
        System.out.println(prefix + ": ");
        for( String s : array ) { // Explore the use of foreach instead of mu usual (for int i =0;.....)
            System.out.println(s);
        }
        System.out.println(prefix + "end of " + prefix);
    }

}