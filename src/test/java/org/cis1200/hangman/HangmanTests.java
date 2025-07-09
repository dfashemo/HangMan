package org.cis1200.hangman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class HangmanTests {

    private HangmanGameBoard hmanBoard;
    private Hangman hman;
    private Body hangBody;

    @Test
    public void testResetNumIncorrectGuesses() {
        JLabel statusLabel = new JLabel("");
        hmanBoard = new HangmanGameBoard(statusLabel);
        hman = new Hangman();
        hangBody = new Body();
        hman.setNumIncorrectGuesses(3);
        hman.setCorrectWord("apple");
        char[] fake = new char[hman.getCorrectWord().length()];
        hman.setCurrentGuess(fake);
        hman.reset();
        assertEquals(0, hman.getNumIncorrectGuesses());
    }

    @Test
    public void testResetCurrentGuess() {
        JLabel statusLabel = new JLabel("");
        hmanBoard = new HangmanGameBoard(statusLabel);
        hman = new Hangman();
        hangBody = new Body();
        hman.setCorrectWord("zoo");
        char[] fake = new char[hman.getCorrectWord().length()];
        fake[0] = 'z';
        hman.setCurrentGuess(fake);
        hman.setNumIncorrectGuesses(2);
        hman.reset();
        char[] expected = new char[hman.getCorrectWord().length()];
        assertNotEquals(expected[0], 'z');
    }

    @Test
    public void testSaveNumIncorrectGuesses() {
        JLabel statusLabel = new JLabel("");
        hmanBoard = new HangmanGameBoard(statusLabel);
        hman = new Hangman();
        hangBody = new Body();
        hman.setNumIncorrectGuesses(4);
        hman.setCorrectWord("boat");
        char[] guess = new char[hman.getCorrectWord().length()];
        guess[0] = 'b';
        hman.setCurrentGuess(guess);
        hmanBoard.saveGame();
        hmanBoard.reset();
        hmanBoard.loadGame();
        assertEquals(4, hman.getNumIncorrectGuesses());

    }

    @Test
    public void testSaveCurrentGuess() {
        JLabel statusLabel = new JLabel("");
        hmanBoard = new HangmanGameBoard(statusLabel);
        hman = new Hangman();
        hangBody = new Body();
        hman.setNumIncorrectGuesses(4);
        hman.setCorrectWord("boat");
        char[] guess = new char[hman.getCorrectWord().length()];
        guess[0] = 'b';
        hman.setCurrentGuess(guess);
        hmanBoard.saveGame();
        hmanBoard.reset();
        hmanBoard.loadGame();
        char[] expected = new char[hman.getCorrectWord().length()];
        expected[0] = 'b';
        assertEquals(expected[0], hman.getCurrentGuess()[0]);
    }

    @Test
    public void test2DArray() {
        JLabel statusLabel = new JLabel("");
        hmanBoard = new HangmanGameBoard(statusLabel);
        hman = new Hangman();
        hangBody = new Body();
        hman.setCorrectWord("tree");
        hman.setNumIncorrectGuesses(2);
        char[] guess = new char[hman.getCorrectWord().length()];
        hman.setCurrentGuess(guess);
        hmanBoard.saveGame();
        hmanBoard.reset();
        hmanBoard.loadGame();
        hangBody.drawHeadArray();
        hangBody.drawBodyArray();
        assertTrue(hangBody.isArrayNonEmpty(2, 3));
        assertTrue(hangBody.isArrayNonEmpty(3, 3));
        assertFalse(hangBody.isArrayNonEmpty(4, 2));
    }
}
