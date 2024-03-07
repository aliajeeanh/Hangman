import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Logic class for the Hangman game. The unknown word is generated through this
 * class as well as other useful method
 * which helps this game work.
 * 
 * @author Sheikh Ali Ajeenah
 * @author Karim Haque
 * @version 2023-05-11
 */
public class Hangman {
    public static String randomWord;
    public static String fixedWord;
    public static int wordLength;

    // All the guessed letters will be put into an ArrayList
    public static ArrayList<String> guessedLettersList = new ArrayList<String>();

    /**
     * Generates a random word from a CSV file containing a list of words and
     * returns the lowercased string.
     * 
     * @throws Exception if the file "words.csv" cannot be found
     * @return a randomly selected word from the CSV file in lowercase string format
     */
    public static ArrayList<String> randomWordParase(String fileName) throws Exception {
        Random random = new Random();
        int randomWordIndex = random.nextInt(172);

        // parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File(fileName));

        for (int i = 0; i < randomWordIndex; i++) {
            sc.next();
        }
        randomWord = sc.next();
        randomWord.toLowerCase();
        sc.close(); // closes the scanner

        ArrayList<String> lettersList = new ArrayList<String>(Arrays.asList(randomWord.split("")));
        // return lettersList;
        return lettersList;
    }

    /**
     * @return
     */
    public static ArrayList<String> fixedWordParase() {
        ArrayList<String> lettersList = new ArrayList<String>(Arrays.asList(fixedWord.toLowerCase().split("")));
        // return lettersList;
        return lettersList;
    }

    /**
     * Returns the length of the input word.
     * 
     * @param word the input word to determine length of
     * @return the length of the input word
     */
    public static int wordLength(String word) {
        int wordLength = word.length();
        return wordLength;
    }

    /**
     * Determines if the input ArrayList of characters contains the input letter.
     * 
     * @param lettersList an ArrayList of individual characters
     * @return true if the input ArrayList contains the input letter, false
     *         otherwise
     */
    public static boolean correctLetterChecker(ArrayList<String> lettersList, String letter) {

        for (int j = 0; j < lettersList.size(); j++) {
            if (lettersList.get(j).equals(letter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines which letter to return based on whether the input word contains
     * the input letter.
     * If the word contains the letter, it returns the input letter. Otherwise, it
     * returns a dash.
     * 
     * @param word the input word to be checked for the input letter
     * @return the input letter if the word contains it, a dash otherwise
     */
    public static String letterReturn(ArrayList<String> lettersList, String letter) {
        if (correctLetterChecker(lettersList, letter) == true) {
            return gui.letter;
        } else {
            return "-";
        }
    }

    /**
     * Returns an ArrayList of indices where the input letter is found in the input
     * word.
     * 
     * @param s the input word to be searched
     * @return an ArrayList of indices where the input letter is found in the input
     *         word
     */
    public static ArrayList<Integer> wordIndex(ArrayList<String> lettersList, String letter) {
        ArrayList<Integer> indexList = new ArrayList<>();
        if (correctLetterChecker(lettersList, letter) == true) {
            for (int i = 0; i < lettersList.size(); i++) {
                if (lettersList.get(i).equals(letter)) {
                    indexList.add(i);
                }
            }
        }
        return indexList;
    }

    /**
     * Adds the input letter to an ArrayList of guessed letters and returns the
     * updated ArrayList.
     * 
     * @param letter the input letter to be added to the ArrayList of guessed
     *               letters
     * @return the updated ArrayList of guessed letters
     */
    public static ArrayList<String> guessedLetters(String letter) {
        guessedLettersList.add(letter);
        Set<String> guessedLettersSet = new LinkedHashSet<>(guessedLettersList);
        guessedLettersList.clear();
        guessedLettersList.addAll(guessedLettersSet);
        return guessedLettersList;
    }

    /**
     * Method which checks whether user has completed the word and won the game or
     * not.
     * 
     * @return true if user has won, false otherwise
     */
    public static boolean wonGame() {
        if (guiHelper.letterDisplayArray.equals(gui.wordList)) {
            return true;
        }
        return false;
    }

}
