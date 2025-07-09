package org.cis1200.hangman;

import java.util.Random;
import java.util.TreeSet;

public class Hangman {

    private int numIncorrectGuesses;
    private String correctWord;
    private char[] currentGuess;
    private TreeSet<String> wordMap = new TreeSet<>();

    //constructor sets up game state
    public Hangman() {
        reset();
    }

    // populate the wordMap
    private void populateWordMap(TreeSet<String> wordMap) {
        wordMap.add("church");
        wordMap.add("coffee");
        wordMap.add("eagle");
        wordMap.add("pizza");
        wordMap.add("demi");
        wordMap.add("tree");
        wordMap.add("boat");
        wordMap.add("eggs");
        wordMap.add("clown");
        wordMap.add("bagel");
        wordMap.add("bronze");
        wordMap.add("mixer");
        wordMap.add("jelly");
        wordMap.add("baby");
        wordMap.add("armor");
        wordMap.add("flyer");
        wordMap.add("chores");
        wordMap.add("crate");
        wordMap.add("storm");
        wordMap.add("cloud");
        wordMap.add("elbow");
        wordMap.add("zoo");
        wordMap.add("eight");
        wordMap.add("debit");
        wordMap.add("fairy");
        wordMap.add("koala");
        wordMap.add("apple");
        wordMap.add("heart");
        wordMap.add("water");
        wordMap.add("teeth");
        wordMap.add("honey");
        wordMap.add("fruit");
        wordMap.add("sunhee");
        wordMap.add("aiden");
        wordMap.add("drew");
        wordMap.add("megan");
    }

    public void reset() {
        populateWordMap(wordMap);
        correctWord = generateNewWord();
        numIncorrectGuesses = 0;
        currentGuess = new char[correctWord.length()];
        for (int i = 0; i < correctWord.length(); i++) {
            currentGuess[i] = '_';
        }
    }

    public String generateNewWord() {
        String[] array = wordMap.toArray(new String[0]);
        Random randomWord = new Random();
        int randomNum = randomWord.nextInt(array.length);
        return array[randomNum];
    }

    public int getNumIncorrectGuesses() {
        return this.numIncorrectGuesses;
    }

    public void setNumIncorrectGuesses(int oldNum) {
        this.numIncorrectGuesses = oldNum;
    }

    public String getCorrectWord() {
        return this.correctWord;
    }

    public void setCorrectWord(String oldWord) {
        this.correctWord = oldWord;
    }

    public char[] getCurrentGuess() {
        return this.currentGuess;
    }

    public void setCurrentGuess(char[] oldGuess) {
        this.currentGuess = oldGuess;
    }

    public void guessLetter(char letter) {
        boolean exists = false;
        for (int i = 0; i < correctWord.length(); i++) {
            if (correctWord.charAt(i) == letter) {
                currentGuess[i] = letter;
                exists = true;
            }
        }
        if (!exists) {
            numIncorrectGuesses++;
        }
    }

    public static void main(String[] args) {
        Hangman h = new Hangman();

    }

}
