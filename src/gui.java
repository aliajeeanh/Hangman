import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Window;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The gui class implements methods for frames which are used before and in the
 * aftermath of the gameplay. This includes methods for the how the main menu
 * will look, how the frame of the difficulty picking/custom word will look and
 * the frame when you win or lose a game.
 * 
 * @author Sheikh Ali Ajeenah
 * @author Karim Haque
 * @version 2023-05-11
 */
public class gui extends JFrame {
    static JFrame mainMFrame = new JFrame("Main menu");
    static JFrame singlePlayerFrame = new JFrame("Hangman - Single player");
    static JFrame singlePlayerDifficultyFrame = new JFrame("Choose difficulty");
    static JFrame multiPlayerWordFrame = new JFrame("Choose word");
    static JFrame multiPlayerFrame = new JFrame("Hangman- Multiplayer");
    static JFrame msm = new JFrame("Hangman- Multiplayer");
    static JFrame winFrame = new JFrame("Win");
    static JFrame finishFrame = new JFrame("Finish");

    // Strig field for every single letter in a word
    public static String letter;

    // Picture number which will start at 0 and progressively grow
    public int pictureNumber = 0;

    // The list of a word where every element in the ArrayList represents a letter
    // in the word
    public static ArrayList<String> wordList = new ArrayList<>();

    // A GrindBagConstraints() object which will be used to create a default for all
    // frames
    static GridBagConstraints constraint = new GridBagConstraints();

    /**
     * Method which generates a frame for the main menu when you first enter the
     * game. You can choose
     * between two buttons, single player (auto-generated word based on difficulty
     * pick) or multiplayer
     * (custom word).
     */
    public static void mainMenu() {
        guiHelper.backgroundPic(mainMFrame, "homescreen.png");

        constraint.insets = new Insets(5, 10, 5, 10);

        // sets layout manager
        mainMFrame.setLayout(new GridBagLayout());
        mainMFrame.setResizable(false);
        Icon sPlayIcon = new ImageIcon(guiHelper.imgPath + "singleplayer.png");
        JButton SplayBtn = new JButton(sPlayIcon);
        SplayBtn.setBackground(Color.BLUE);
        SplayBtn.setOpaque(true);
        SplayBtn.setBorderPainted(false);
        SplayBtn.setPreferredSize(new Dimension(400, 50));

        constraint.gridx = 20;
        constraint.gridy = 7;
        mainMFrame.add(SplayBtn, constraint);

        SplayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mainMFrame.dispose();
                difficulty();

            }
        });

        Icon MplayIcon = new ImageIcon(guiHelper.imgPath + "multiplayer.png");
        JButton MplayBtn = new JButton(MplayIcon);
        MplayBtn.setBackground(Color.BLUE);
        MplayBtn.setOpaque(true);
        MplayBtn.setBorderPainted(false);
        MplayBtn.setPreferredSize(new Dimension(400, 50));

        constraint.gridx = 20;
        constraint.gridy = 15;

        mainMFrame.add(MplayBtn, constraint);

        MplayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mainMFrame.dispose();
                multiPlayerChooseWord();

            }
        });

        guiHelper.screenDefault(mainMFrame);
    }

    /**
     * When pressing the single player button on the main menu, this method is
     * called. The difficulty() method generates a new frane where the user can pick
     * between three buttons, "Easy", "Medium" or "Hard". Based of this pick a word
     * is chosen (based on word length) which will later be used in gameplay.
     */
    public static void difficulty() {
        guiHelper.backgroundPic(singlePlayerDifficultyFrame, "homescreen.png");

        // sets layout manager
        singlePlayerDifficultyFrame.setLayout(new GridBagLayout());
        singlePlayerDifficultyFrame.setResizable(false);
        constraint.insets = new Insets(5, 10, 5, 10);

        JButton easy = guiHelper.difficultyButton(singlePlayerDifficultyFrame, singlePlayerFrame, "Easy",
                "src/wordsCSV/test.csv", constraint);
        constraint.gridx = 20;
        constraint.gridy = 20;
        easy.setPreferredSize(new Dimension(400, 50));

        JButton medium = guiHelper.difficultyButton(singlePlayerDifficultyFrame, singlePlayerFrame, "Medium",
                "src/wordsCSV/medium.csv", constraint);
        constraint.gridx = 20;
        constraint.gridy = 30;
        medium.setPreferredSize(new Dimension(400, 50));

        JButton hard = guiHelper.difficultyButton(singlePlayerDifficultyFrame, singlePlayerFrame, "Hard",
                "src/wordsCSV/hard.csv", constraint);
        constraint.gridx = 20;
        constraint.gridy = 40;
        hard.setPreferredSize(new Dimension(400, 50));

        guiHelper.screenDefault(singlePlayerDifficultyFrame);
    }

    /**
     * When pressing the mutliplayer button on the main menu, this method is called.
     * The multiPlayerChooseWord() method generates a new frame where the user can
     * use a custom word instead. This is suitable when there are two users, one who
     * is guessing and one who is deciding the word. The word will then later be
     * used in gameplay.
     */
    public static void multiPlayerChooseWord() {

        guiHelper.backgroundPic(multiPlayerWordFrame, "background.png");
        // sets layout manager
        multiPlayerWordFrame.setLayout(new GridBagLayout());
        constraint.insets = new Insets(5, 10, 5, 10);

        JButton goButton = new JButton("Select Word!");
        multiPlayerWordFrame.getRootPane().setDefaultButton(goButton);
        constraint.anchor = GridBagConstraints.LAST_LINE_END;
        constraint.ipadx = 40;
        constraint.gridx = 2;
        constraint.gridy = GridBagConstraints.CENTER;
        multiPlayerWordFrame.add(goButton, constraint);

        // Txt field that takes input text from user.
        JTextField wordField = new JTextField(25);
        constraint.gridx = 1;
        constraint.gridy = GridBagConstraints.CENTER;
        multiPlayerWordFrame.add(wordField, constraint);

        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!wordField.getText().equals("")) {
                    Hangman.fixedWord = wordField.getText();
                    wordList = Hangman.fixedWordParase();
                    // GO RUN PROGRAM
                    guiHelper.gameScreen(multiPlayerFrame);
                    multiPlayerWordFrame.dispose();
                }

            }
        });

        multiPlayerWordFrame.setResizable(false);
        guiHelper.screenDefault(multiPlayerWordFrame);
    }

    /**
     * The finish() method is invoked when a user completes a game of hangman,
     * either the user has won or the user has lost.
     * 
     * @param outcome a String with the outcome is sent to the method to check
     *                whether user has won or lost
     * @throws Exception if audio file can not be found
     */
    public static void finish(String outcome) throws Exception {

        guiHelper.backgroundPic(finishFrame, "background.png");
        finishFrame.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        String wordJoined = String.join("", wordList);
        JLabel text = new JLabel(outcome + " Your word was " + wordJoined.toUpperCase() + ".");
        Font font = new Font("SansSerif Bold", Font.PLAIN, 15);
        text.setForeground(Color.WHITE);
        text.setFont(font);
        constraint.gridx = 10;
        constraint.gridy = 5;
        finishFrame.add(text, constraint);
        JPanel panel = new JPanel();

        JTextArea definition = new JTextArea(Definition.definitionOfWord(wordJoined), 25, 35);

        definition.setLineWrap(true); // Wrap the text when it reaches the horizontal bounds
        definition.setEditable(false);

        constraint.insets = new Insets(5, 2, 5, 2);
        constraint.gridx = 10;
        constraint.gridy = 10;
        panel.add(definition);
        finishFrame.add(panel, constraint);

        Icon newGameIcon = new ImageIcon(guiHelper.imgPath + "newgame.png");
        JButton newGame = new JButton(newGameIcon);

        newGame.setBackground(Color.BLUE);
        newGame.setOpaque(true);
        newGame.setBorderPainted(false);
        newGame.setPreferredSize(new Dimension(200, 10));
        newGame.setRolloverEnabled(false);

        constraint.insets = new Insets(4, 2, 4, 2);
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 10;
        constraint.gridy = 15;
        finishFrame.add(newGame, constraint);

        try {
            String path;
            if (outcome == "YOU'VE WON!") {
                path = "src/ronaldo.wav";
            } else {
                path = "src/augh.wav";
            }
            File audio = new File(path);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audio));
            // timer
            clip.start();
            // Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Creating a panel to add buttons and a specific layout
        // Adding buttons and textfield to panel using add() method
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                finishFrame.getContentPane().removeAll();
                mainMenu();
            }
        });

        guiHelper.menuBar(finishFrame);
        finishFrame.setPreferredSize(new Dimension(600, 300));
        finishFrame.pack();
        finishFrame.setLocationRelativeTo(null);
        finishFrame.setVisible(true);
    }

}
