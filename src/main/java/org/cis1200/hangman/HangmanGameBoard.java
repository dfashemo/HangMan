package org.cis1200.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class HangmanGameBoard extends JPanel {
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;
    private JLabel guessLabel = new JLabel("");
    private JLabel incorrectGuessesLabel = new JLabel("");
    private JLabel status;
    private Hangman hman;
    private Body hangBody;
    private JButton resetter = new JButton("Reset");
    private JButton saveButton = new JButton("Save Game");
    private JButton loadButton = new JButton("Load Game");
    private JLabel userInstructions = new JLabel("Type Letter Here: ");
    private JTextField userInput = new JTextField(3);
    private JButton submitter = new JButton("Submit");

    // Constructs Board
    public HangmanGameBoard(JLabel statusInit) {

        setFocusable(true);
        hman = new Hangman();
        hangBody = new Body();
        status = statusInit;

        resetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });

        submitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitLetter();
            }
        });

        add(resetter);
        add(guessLabel);
        add(saveButton);
        add(loadButton);
        add(incorrectGuessesLabel);
        add(userInstructions);
        add(userInput);
        add(submitter);
    }

    // show game instructions
    private void showInstructions() {
        JOptionPane.showMessageDialog(
                this,
                "Welcome to hangman! " +
                        "You have 6 tries to guess each letter in the word.\n" +
                        "The word will never be more than 6 letters.\n" +
                        "When you're ready, type one letter into the textbox and hit submit. " +
                        "\nGood luck!"
        );
    }

    // resets game to initial state
    public void reset() {
        hman.reset();
        status.setText("Game in Progress");
        showInstructions();
        if (!userInput.getText().isEmpty()) {
            updateGuessLabel();
        }
        updateIncorrectGuessLabel();
        repaint();

        requestFocusInWindow();
    }

    // update Status
    private void updateIncorrectGuessLabel() {
        incorrectGuessesLabel.setText("Incorrect Guesses: " + hman.getNumIncorrectGuesses());
        guessLabel.setText("Current Guess: " + Arrays.toString(hman.getCurrentGuess()));
    }

    // update guess label
    private void updateGuessLabel() {
        StringBuilder display = new StringBuilder();
        char in = userInput.getText().toLowerCase().charAt(0);
        display.append(in).append(" ");
        guessLabel.setText(display.toString());
    }

    // save game
    public void saveGame() {
        String fileName = "/Users/dfashemo/Downloads/hw09_local_temp/src/" +
                "main/java/org/cis1200/hangman/SavedGameState.txt";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(hman.getCorrectWord() + "\n");
            fileWriter.write(hman.getNumIncorrectGuesses() + "\n");
            char[] copy = hman.getCurrentGuess();
            for (int i = 0; i < copy.length; i++) {
                fileWriter.write(copy[i]);
            }
            JOptionPane.showMessageDialog(
                    this, "Game saved successfully!"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "save unsuccessful");
            e.printStackTrace();
        }
    }

    // load game
    public void loadGame() {
        String fileName = "/Users/dfashemo/Downloads/hw09_local_temp/src/" +
                "main/java/org/cis1200/hangman/SavedGameState.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String correctWord = reader.readLine();
            int numIncorrectGuesses = Integer.parseInt(reader.readLine());
            String currentGuess = reader.readLine();

            hman.setCorrectWord(correctWord);
            hman.setCurrentGuess(currentGuess.toCharArray());
            hman.setNumIncorrectGuesses(numIncorrectGuesses);

            if (!userInput.getText().isEmpty()) {
                updateGuessLabel();
            }
            updateIncorrectGuessLabel();
            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "load unsuccessful");
            e.printStackTrace();
        }
    }

    // end game
    private void endGame() {
        if (wordComplete()) {
            JOptionPane.showMessageDialog(
                    this,
                    "You guessed the word! You win!"
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "You ran out of attempts. You lose! The word was: "
                            + hman.getCorrectWord()
            );
        }
    }

    // check if word has been guessed
    private boolean wordComplete() {
        for (char c : hman.getCurrentGuess()) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    // submit letter
    public void submitLetter() {
        String in = userInput.getText().toLowerCase();
        char input = in.charAt(0);
        hman.guessLetter(input);
        updateGuessLabel();
        updateIncorrectGuessLabel();

        if (hman.getNumIncorrectGuesses() == 6 || wordComplete()) {
            repaint();
            endGame();
            reset();
        }
        userInput.setText("");
        repaint();
    }

    // draw hangman game board
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        hangBody.drawStand(g);
        if (hman.getNumIncorrectGuesses() == 1) {
            hangBody.drawHead(g);
        }
        if (hman.getNumIncorrectGuesses() == 2) {
            hangBody.drawBody(g);
        }
        if (hman.getNumIncorrectGuesses() == 3) {
            hangBody.drawLeftLeg(g);
        }
        if (hman.getNumIncorrectGuesses() == 4) {
            hangBody.drawRightLeg(g);
        }
        if (hman.getNumIncorrectGuesses() == 5) {
            hangBody.drawLeftArm(g);
        }
        if (hman.getNumIncorrectGuesses() == 6) {
            hangBody.drawRightArm(g);
            status.setText("No more guesses! You lose.");
        }
    }

    //returns size of the game board
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

}
