====================: Citations :====================
- The top-level source code for this project was outlined by the CIS 1200 Course team at the University of Pennsylvania. The rest of the functionality of the game was implemented independently by me.

====================: Running Instructions :====================
- To play the game, simply run the Game.java file.

====================: Core Concepts :====================

  1. Collections - I store all of the possible words for my hangman game in a TreeSet. This feature
  is appropriate because treeSet allows me to add words to the set without having to take into
  account a specific order/size - making it easier for me to add/delete words as I choose to.

  2. 2D Arrays - I display the hangman's body and the stand using a 6x6 2D array. This feature is
  appropriate because it allows me to display the graphics in an orderly way. More specifically, it
  makes updating the graphics in the paint component easier because I only need to account for grid
  indices, and not specific coordinate locations.

  3. File I/O - I implemented a "save game" button that writes the current game state to a file. I
  also implemented a "load game" button that reads the saved file back in to set up the game. This
  feature is appropriate because it allows the user to save progress in one game, start another one,
  and return back to the previous game if they choose to. By saving the game state in a separate file,
  I overcome the obstacle/tediousness of having to create duplicate instances of the same variable
  in the rest of my code's implementation.

  4. JUnit Testable Component - I implemented the JUnit testable component in the tests I wrote for
  my game to ensure that the correctWord, currentGuess, numIncorrectGuesses, and HangBody 2D Array
  were being correctly updated whenever the game is first started - as well as if the game is
  saved/re-loaded.

====================: My Implementation ====================

  - The Body class holds the methods and functionality of the hangman picture that is drawn. Here I
  use a 2D array to draw the hangman stand and body. The full picture is segmented into different
  drawing methods for the head, arms, legs, etc. so that it becomes easier to distinguish which
  parts of the body should be drawn (depending on the number of incorrect guesses).

  - The Hangman class holds the methods and functionality of the non-graphic state of the hangman
  game. Here I control the core game state by keeping track of/manipulating the number of incorrect
  guesses, the correct word, and the player's current guess. I also populate the wordMap of potential
  words to be randomly generated in this class.

  - The HangmanGameBoard class holds the methods and functionality of the hangman display "board"
  This is where I create buttons/define action listeners for the user experience. This is where I
  control the display during the game and create methods to signal what should appear when the game
  starts/ends (depending on the current state). I implemented an instructions banner, save/reload
  buttons, a JTextField for the user to type in their guess, and a submit button for the user to click
  once they want to guess a letter.

  - The RunHangman class is where all of the implementation for the game is packaged together into
  one widget. Here I initialize a new HangmanGameBoard so that I have access to all of the buttons/
  widgets implemented in the previous class. This class is where a Hangman/Board is instantiated and
  the game begins.

  - I had difficulties implementing the save/load button and deciding how the player's current guess
  should be stored.

  - I also had difficulties refactoring my code to display the hangbody using 2d array instead of
  directly plotting lines in gctx (realized previous approach did not satisfy assignment requirements).

  - I think overall I did a good job of separating the game functionality. By creating a separate body class I was able to make my code more organized and decrease the amount of unnecessary repetition. If I were to refactor, I would probably consider moving the implementation of my board buttons from HangmanGameBoard to RunHangman.


