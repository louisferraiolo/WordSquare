package com.louisferraiolo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private WordSquare wordSquare;

    public static void main(String[] args)
    {
        Main instance = new Main(); // Had to create an instance of main as you cannot call a non-static method from a static method.
        // Get users input to allow for the larger word squares and custom input.
        instance.getUserInput();

        // Get all the applicable words that could be possible
        Helper.profile(null);
        try {
            instance.wordSquare.setDictionary(instance.initialDictionary());
        }catch( IOException exception ) {
            exception.printStackTrace();
        }
        Helper.profile("Getting relative words");
        Helper.profile(null);
        if( instance.wordSquare.getDictionary().size() > 0 ) {
            // TODO: Try eventually do all of this in the one while loop to save time. - look into the possibility might not be possible due to dictionary not being initialised.
            instance.populateSquare(instance.wordSquare.getDictionary(), 1);
        }else{
            System.out.println("No applicable words for that character set.");
        }
        Helper.profile("Creating word square");
        instance.printArray(instance.wordSquare.getWordSquareWords(), "FINAL wordSquare");
    }

    public ArrayList<String> initialDictionary() throws IOException
    {
        ArrayList<String> dictionaryList  = new ArrayList<>();

        String home = System.getProperty("user.home");
        File f = new File(home + File.separator + "Desktop" + File.separator + "dictionary.txt"); // Using file seperators to anticipate crashes on various os's as windows and mac use diff sep's.
        if(!f.exists())
        {
            System.out.println("File not found at: " + f.getAbsolutePath() );
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new FileReader(f));
        String currentInput;
        // Do all my manipulating inside this loop as creating another for loop will make it O(n^2).
        while( (currentInput = in.readLine() ) != null ) // Time complexity O(n) where n is the amount of lines in the txt file
        {
            if( currentInput.length() == wordSquare.getSquareNum()  ) // Only use the words from dictionary which have same length as specified.
            {
                // Matches the optimal length.
                if( checkApplicable(currentInput, wordSquare.getCharsLeft()) ) { // Getting all words which can be made from the letter set (widens down search aswell - easier to process).
                    //System.out.println(currentInput);
                    dictionaryList.add(currentInput);
                }
            }
        }
        in.close(); // Made sure to close as can lead to leaks.
        return dictionaryList;
    }

    // Function has to be recursive to avoid multiple nested for loops which would be inefficient.
    private void populateSquare( ArrayList<String> array, int what )
    {
        for( int i = 0; i < array.size(); i++ )
        {
            String initialWord = array.get(i);
            System.out.println("word: " + initialWord);
            String letter = "";
            for( int j = 1; j <= what; j++)
            {
                if( initialWord.length() > j )
                    letter += Character.toString(initialWord.charAt(j));
            }
            //System.out.println("LETTER: " + letter);
            ArrayList<String> wordsStarting = getWordStarting(letter);
            if( wordsStarting.size() > 0 )
            {
                printArray(wordsStarting, "wordsStarting");
                addToWordSquare(initialWord);
                if(what == wordSquare.getSquareNum())
                {
                    System.out.println("REACHED ALL WORDS !!");
                    printArray(wordSquare.getWordSquareWords(), "wordSquare");
                    break;
                }
                System.out.println("what: " + what);
                // Recursive call to avoid nested for loops and worse time complexity
                populateSquare(wordsStarting, ++what);
            }else{
                System.out.println("Not possible with: " + initialWord);
            }
        }

    }


    private void addToWordSquare( String initialWord )
    {
        wordSquare.addUsedWord(initialWord);
        wordSquare.addWordSquareWord(initialWord);
        wordSquare.setCharsLeft(removeCharacters(initialWord, wordSquare.getCharsLeft()));
    }


    public int[] removeCharacters( String word, int[] array )
    {
        String lettersLeft = Helper.getLettersLeft(array);
        System.out.println("Word: " + word + " <> lettersLeft PRE: " + lettersLeft );
        ArrayList<Integer> indexes = new ArrayList<>(); // This will hopefully avoid the problem of letters having 2 a's but word having 1 a.
        for( int i = 0; i < word.length(); i++ )
        {
            char currentChar = word.charAt(i);
            int index = currentChar - 97; // 97 as it is the ASCII code for 'a'.
            int previousVal = array[index];
            array[index]--; // Decrement the amount of times that specific charcter in the alphabet is used.
            System.out.println(currentChar + " is now used: " + array[index] + " times from " + previousVal + " times.");
        }
        lettersLeft = Helper.getLettersLeft(array);
        System.out.println("lettersLeft POST: " +  lettersLeft );
        return array;
    }

    public boolean checkApplicable( String input, int[] array )
    {
        for( int i = 0; i < input.length(); i++ )
        {
            char currentChar = input.charAt(i);
            int index = currentChar - 97; // 97 as it is the ASCII code for 'a'.
            if( array[index] == 0 )
            {
                return false;
            }
        }
        return true;
    }

    public void getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Provide a word square integer and letters? 4 eeeeddoonnnsssrv");
            input = scanner.nextLine();
        }while( !checkUserInput(input) );
    }

    public boolean checkUserInput( String input )
    {
        String[] spacesSplit = input.split("\\s+");
        int squareNum;
        String letters;
        if( spacesSplit.length != 2 )
            return false;
        try {
            squareNum = Integer.parseInt(spacesSplit[0]);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return false;
        }
        letters = spacesSplit[1].toLowerCase();
        if( letters.length() != (squareNum * squareNum) )
            return false;
        if( Helper.containsNum(letters) )
            return false;

        //Create wordSquare;
        int[] charsLeft = Helper.getCharCount(letters.toCharArray());
        wordSquare = new WordSquare(squareNum,letters,charsLeft);
        return true;
    }

    private ArrayList<String> getWordStarting( String startsWith )
    {
        ArrayList<String> wordsStarting  = new ArrayList<>();
        for( String word : wordSquare.getDictionary() )
        {
            if( word.startsWith(startsWith) && !wordSquare.getUsedWords().contains(startsWith) && checkApplicable(word,wordSquare.getCharsLeft()) ) {
                wordsStarting.add(word);
            }
        }
        return wordsStarting;
    }

    private void printArray( ArrayList<String> array, String prefix )
    {
        System.out.println(prefix + ": ");
        for( String s : array ) // Explore the use of foreach instead of mu usual (for int i =0;.....)
            System.out.println(s);
        System.out.println("end of " + prefix);
    }

}