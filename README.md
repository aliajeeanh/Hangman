# Hangman
Hangman is a classic word-guessing game where players try to guess a secret word by suggesting letters. If the player guesses a letter that is not in the word, a part of a hangman figure is drawn on the screen. The game continues until the player either guesses the word or the hangman figure is fully drawn. This program was built with Java, with a graphical interface made with the inbuilt java API Swing.

This project was a part of the course [DD1349 Project in Introduction to Computer Science](https://www.kth.se/student/kurser/kurs/DD1349). 

# Features
- Single player 
  - Randomly generate a secret word from a list of words.
  - Choose difficulty, three options easy, medium and hard word. The difficulty is based on the probability of an appearance of a letter as well as the length of the word.   

- Multiplayer 
  - choose an own word.

- General features 
  - Display the number of letters in the secret word and provide spaces for the letters to be guessed.
  - Allow the user to input letters to guess the word.
  - Keep track of incorrect guesses and display the corresponding parts of the hangman figure.
  - End the game when the user either correctly guesses the word or the hangman figure is fully drawn.
  - Provide the option for the user to play again after the game ends.
  - Give the definition of the password using Free Dictionary API. <br> <br> 

# Getting started
## Prerequisites:
In order to run and play this game must any version of JDK be installed. JDK development kit can be found and installed [here.](https://www.oracle.com/se/java/technologies/downloads/)

## Installation
1. Clone the repo to your local machine:
    ```
    git clone git@gits-15.sys.kth.se:karimha/Hangman.git
    ```

2. Open the Hangman file 
    ```
    cd Hangman/
    ```
3. Compile all the classes including the classpath of the jar file
    ```java
    javac -cp src/lib/json-simple-1.1.1.jar  src/*.java
    ```
4. Run the program 
    ```java
    java -cp lib/json-simple-1.1.1.jar:src run
    ```

# Collaborators
- Karim Haque <karimha@kth.se>
- Ali Ajeenah <aajeenah@kth.se>
