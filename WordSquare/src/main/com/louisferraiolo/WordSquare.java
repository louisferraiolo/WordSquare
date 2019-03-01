package com.louisferraiolo;

import java.util.ArrayList;

public class WordSquare {

    private int squareNum;
    private String letters;
    private int[] charsLeft; // 26 as there are 26 letters in the alphabet each letter will have how many times it is used assigned to it.
    private ArrayList<String> dictionary = new ArrayList<>();
    private ArrayList<String> usedWords = new ArrayList<>();
    private ArrayList<String> wordSquareWords = new ArrayList<>();

    public WordSquare( int squareNum, String letters, int[] charsLeft )
    {
        this.squareNum = squareNum;
        this.letters = letters;
        this.charsLeft = charsLeft;
    }

    public String getLetters()
    {
        return this.letters;
    }

    public int getSquareNum()
    {
        return this.squareNum;
    }

    public void setLetters( String letters )
    {
        this.letters = letters;
    }

    public void setSquareNum( int squareNum )
    {
        this.squareNum = squareNum;
    }

    public void setCharsLeft( int[] array )
    {
        this.charsLeft = array;
    }

    public int[] getCharsLeft()
    {
        return this.charsLeft;
    }

    public void setDictionary( ArrayList<String> dictionary )
    {
        this.dictionary = dictionary;
    }

    public ArrayList<String> getDictionary()
    {
        return this.dictionary;
    }

    public void setWordSquareWords( ArrayList<String> wordSquareWords )
    {
        this.wordSquareWords = wordSquareWords;
    }

    public ArrayList<String> getWordSquareWords()
    {
        return this.wordSquareWords;
    }

    public void addWordSquareWord( String word )
    {
        this.wordSquareWords.add(word);
    }

    public void removeWordSquareWord( String word )
    {
        this.wordSquareWords.remove(word);
    }

    public void setUsedWords( ArrayList<String> usedWords )
    {
        this.usedWords = usedWords;
    }

    public ArrayList<String> getUsedWords()
    {
        return this.usedWords;
    }

    public void addUsedWord( String word )
    {
        this.usedWords.add(word);
    }

    public void removeUsedWord( String word )
    {
        this.usedWords.remove(word);
    }
}
