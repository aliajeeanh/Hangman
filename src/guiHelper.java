
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The guiHelper class implements methods for frames which are used during the
 * gameplay.
 * 
 * @author Sheikh Ali Ajeenah
 * @author Karim Haque
 * @version 2023-05-11
 */
public class guiHelper extends JFrame {

    // The picture number which starts with 1 because picture 0.png already has been
    // shown using the gui class
    public static int pictureNumber = 1;

    // Path to image folder
    public static String imgPath = "src/img/";

    // ArrayList to display the letters of the word, each letter is an element
    public static ArrayList<String> letterDisplayArray = new ArrayList<>();

    // ArrayList to display the already guessed letter, each letter is an element
    public static ArrayList<String> invalidLetter = new ArrayList<>();

    // GridBagConstraint() object for the defaults of a frame
    public static GridBagConstraints constraint = new GridBagConstraints();

    /**
     * Adds a menu bar to the given JFrame containing two menu items: "Force quit"
     * and "Main menu".
     *
     * @param frame The JFrame to add the menu bar to.
     */
    public static void menuBar(JFrame frame) {
        // adds menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemExit = new JMenuItem("Force quit");
        JMenuItem menuItemBackToMainMenu = new JMenuItem("Main menu");
        menuFile.add(menuItemExit);
        menuFile.add(menuItemBackToMainMenu);

        menuBar.add(menuFile);

        // adds menu bar to the frame
        frame.setJMenuBar(menuBar);

        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0); // Terminate the program
            }
        });

        menuItemBackToMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                gui.mainMenu();
            }
        });
    }

    /**
     * Adds a WindowAdapter to the given JFrame to handle the window closing event.
     * 
     * @param frame The JFrame to add the WindowAdapter to.
     */
    public static void CloseAction(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame,
                        "Do you want to Exit ?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else if (result == JOptionPane.NO_OPTION)
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }

        });

    }

    /**
     * Displays an error screen in a new JFrame with the given error message. The
     * error could be different types of wrongful inputs when guessing a letter.
     * 
     * @param errorMessage The error message to display on the error screen.
     */
    public static void errorScreen(String errorMessage) {
        JFrame error = new JFrame();
        error.setVisible(true);
        // sets layout manager
        error.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5, 10, 5, 10);

        JLabel letterDescription = new JLabel(errorMessage);
        constraint.gridx = 0;
        constraint.gridy = 7;
        error.add(letterDescription, constraint);

        error.setPreferredSize(new Dimension(600, 300));
        error.pack();
        error.setLocationRelativeTo(null);
        error.setVisible(true);
    }

    /**
     * Displays the word as only underscores, when starting a game every letter is
     * of course unknown.
     * 
     * @param frame The JFrame to add the list of empty letters to.
     */
    public static void showEmptyArray(JFrame frame) {
        letterDisplayArray.clear();
        invalidLetter.clear();
        for (int i = 0; i < gui.wordList.size(); i++) {
            letterDisplayArray.add(i, "-");
        }

        StringBuilder line = new StringBuilder();
        for (String letter : letterDisplayArray) {
            line.append(letter + " ");
        }

        JLabel word = new JLabel(line.toString());
        int size;
        if (line.length() < 10) {
            size = 50;
        } else {
            size = 35;
        }
        Font font = new Font("SansSerif Bold", Font.PLAIN, size);
        word.setFont(font);
        word.setForeground(Color.WHITE);
        word.setName("DisplayedArray");

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 2;
        constraint.gridy = 1;
        frame.add(word, constraint);
    }

    /**
     * This method updates and displays an array of letters representing a hidden
     * word on a graphical user interface. It first removes any previous image label
     * and then updates the letterDisplayArray for matching letters. Finally, a new
     * JLabel is created with the updated letterDisplayArray and added to the GUI,
     * and the GUI is revalidated and repainted. The method takes in a JFrame object
     * and a string letter as parameters.
     * 
     * @param frame  the JFrame object representing the graphical user interface
     * @param letter the string letter to be displayed in the updated
     *               letterDisplayArray
     */
    public static void showWordArrayUpdated(JFrame frame, String letter) {

        // remove previous image label, if any
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JLabel && ((JLabel) component).getName() != null
                    && ((JLabel) component).getName().equals("DisplayedArray")) {
                frame.remove(component);
            }
        }

        for (int i = 0; i < gui.wordList.size(); i++) {
            if (gui.wordList.get(i).equals(letter)) {
                letterDisplayArray.set(i, letter);
            }
        }

        StringBuilder line = new StringBuilder();
        for (String eachLetter : letterDisplayArray) {
            line.append(eachLetter + " ");
        }

        JLabel word = new JLabel(line.toString().toUpperCase());
        int size;
        if (line.length() < 10) {
            size = 50;
        } else {
            size = 35;
        }
        Font font = new Font("SansSerif Bold", Font.PLAIN, size);
        word.setFont(font);
        word.setForeground(Color.WHITE);
        word.setName("DisplayedArray");

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 2;
        constraint.gridy = 1;
        frame.add(word, constraint);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * 
     * This method displays a list of invalid letters guessed by the user on a
     * graphical user interface. It removes any previous panel containing the
     * invalid letters from the GUI and creates a new panel to display the invalid
     * letters. The panel is added to the GUI and the GUI is revalidated and
     * repainted to display the updated panel. The method takes in a JFrame object
     * as a parameter.
     * 
     * @param frame the JFrame object to which the panel containing the invalid
     *              letters should be added
     */
    public static void showInvalidLetter(JFrame frame) {
        // remove previous image label, if any
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel && ((JPanel) component).getName() != null
                    && ((JPanel) component).getName().equals("panel")) {
                frame.remove(component);
            }
        }

        StringBuilder line = new StringBuilder();
        for (String eachLetter : invalidLetter) {
            line.append(eachLetter + " ");
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setName("panel");
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Used letters"));

        JLabel invalidLetters = new JLabel(line.toString().toUpperCase());
        Font font = new Font("SansSerif Bold", Font.PLAIN, 35);
        invalidLetters.setFont(font);
        invalidLetters.setForeground(Color.BLACK);
        invalidLetters.setName("invalidArray");

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.insets = new Insets(2, 0, 2, 0);
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(invalidLetters, panelConstraints);

        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 2;
        frameConstraints.gridy = 5;
        frameConstraints.insets = new Insets(2, 2, 2, 2);
        frameConstraints.fill = GridBagConstraints.BOTH;
        frame.add(panel, frameConstraints);

        frame.revalidate();
        frame.repaint();

    }

    /**
     * Displays an image of the hangman at a specified position on the graphical
     * user interface (GUI). The method removes any previously displayed hangman
     * image label from the GUI, if any exists. It then creates a new JLabel object
     * to display the new hangman image on the GUI and adds it using
     * GridBagConstraints layout.
     * 
     * @param frame  The JFrame object representing the GUI.
     * @param picNum An integer representing the position of the hangman image to be
     *               displayed.
     */
    public static void showPic(JFrame frame, int picNum) {
        // remove previous image label, if any
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JLabel && ((JLabel) component).getName() != null
                    && ((JLabel) component).getName().equals("hangmanImage")) {
                frame.remove(component);
            }
        }

        // add new image label
        ImageIcon icon = new ImageIcon(imgPath + picNum + ".png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(400, 600, Image.SCALE_SMOOTH);

        JLabel picLabel = new JLabel(new ImageIcon(imgScale));
        picLabel.setName("hangmanImage");

        constraint.insets = new Insets(10, 0, 10, 0);
        constraint.gridx = 2;
        constraint.gridy = 0;

        frame.add(picLabel, constraint);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Creates a JButton with a difficulty level image icon, adds it to a JFrame
     * using the specified GridBagConstraints, and sets an ActionListener to handle
     * a button click event. When the button is clicked, the method sets the
     * wordList in the gui class to a randomly selected list of words from a
     * specified file, calls the gameScreen method to display the game screen in a
     * new JFrame, and disposes of the current displayFrame.
     * 
     * @param displayFrame      the JFrame to which the button will be added
     * @param singlePlayerFrame the JFrame for the game screen
     * @param difficulty        the difficulty level of the game
     * @param filename          the name of the file from which to select a word
     *                          list
     * @param constraints       the GridBagConstraints to be used when adding the
     *                          button to the displayFrame
     * @return the created JButton
     */
    public static JButton difficultyButton(JFrame displayFrame, JFrame singlePlayerFrame, String difficulty,
            String filename, GridBagConstraints constraints) {

        Icon icon = new ImageIcon(imgPath + difficulty.toLowerCase() + ".png");
        JButton btn = new JButton(icon);
        btn.setBackground(Color.BLUE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);

        displayFrame.add(btn, constraints);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    gui.wordList = Hangman.randomWordParase(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gameScreen(singlePlayerFrame);
                displayFrame.dispose();

            }
        });

        return btn;
    }

    /**
     * Sets the default properties of the given JFrame, including the maximum size,
     * icon image, and visibility. The method also adds a menu bar and a close
     * action to the JFrame.
     * 
     * @param frame frame the JFrame to set the default properties of
     */
    public static void screenDefault(JFrame frame) {
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the maximum size of the JFrame
        int maxWidth = (int) (screenSize.getWidth() * 0.35);
        int maxHeight = (int) (screenSize.getHeight() * 0.9);

        ImageIcon img = new ImageIcon(imgPath + "icon.png");

        menuBar(frame);
        CloseAction(frame);

        // Set the maximum size of the JFrame
        frame.setResizable(false);
        frame.setIconImage(img.getImage());
        frame.setPreferredSize(new Dimension(maxWidth, maxHeight));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * Sets up the game screen with all the necessary components, including the
     * hangman picture, the word array, the letter input field, and the "Go" button.
     * When the button is pressed, the letter input is checked and processed by the
     * game logic class, and the appropriate components are updated to reflect the
     * new state of the game. If the game is won or lost, the game screen is closed
     * and the finish screen is displayed.
     * 
     * @param frame the JFrame on which to display the game screen
     */
    public static void gameScreen(JFrame frame) {

        backgroundPic(frame, "background.png");

        // sets layout manager
        frame.setLayout(new GridBagLayout());
        // GridBagConstraints constraint = new GridBagConstraints();

        constraint.insets = new Insets(8, 8, 8, 8);

        JLabel letterDescription = new JLabel("Letter: ");
        Font font = new Font("SansSerif Bold", Font.PLAIN, 20);
        letterDescription.setForeground(Color.WHITE);
        letterDescription.setFont(font);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 2;
        frame.add(letterDescription, constraint);

        // Txt field that takes input text from user.

        JTextField letterField = new JTextField(25);
        constraint.gridx = 2;
        constraint.gridy = 2;
        frame.add(letterField, constraint);

        /*
         * Executes the HÄR SKA JAG SKRIVA ATT DEN EXECUTE METOD SOM
         * KÖR PROGRAMMET FÖR DEN BOKSTAGVEN
         */
        JButton btn1 = new JButton("Go");
        constraint.anchor = GridBagConstraints.LAST_LINE_END;
        constraint.ipadx = 30;
        constraint.gridx = 3;
        constraint.gridy = 2;
        frame.getRootPane().setDefaultButton(btn1);
        frame.add(btn1, constraint);

        showPic(frame, 0);
        showEmptyArray(frame);
        showInvalidLetter(frame);
        /*
         * Saves the input from letterfield and stores it
         * as a string when the go button is pressed, as well as
         * running the main logical class SKRIVA OM SENARE
         */
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (letterField.getText().length() != 1) {
                    if (letterField.getText().length() == 0) {
                        errorScreen("You must input a letter!");
                    } else {
                        errorScreen("One letter at a time!");
                    }
                } else if (invalidLetter.contains(letterField.getText().toLowerCase()) == true) {
                    errorScreen("You can't guess the same letter multiple times!");

                } else {
                    String inputLetter = letterField.getText().toLowerCase();

                    if (Hangman.correctLetterChecker(gui.wordList, inputLetter) == false) {
                        showPic(frame, pictureNumber);
                        pictureNumber++;
                        invalidLetter.add(inputLetter);
                        showInvalidLetter(frame);
                    } else {
                        showWordArrayUpdated(frame, inputLetter);
                        invalidLetter.add(inputLetter);
                        showInvalidLetter(frame);

                    }

                    // Karim ändrade
                    if (Hangman.wonGame() == true) {
                        try {
                            gui.finish("YOU'VE WON!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pictureNumber = 1;
                    } else if (pictureNumber == 11) {
                        // dispose game plan and put lose frame
                        try {
                            showPic(frame, 10);
                            gui.finish("YOU'VE LOST!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pictureNumber = 1;
                    }
                }
                letterField.setText("");
            }
        });

        screenDefault(frame);
    }

    /**
     * Sets the background image of the given JFrame to the specified image file.
     * 
     * @param frame    the JFrame whose background is to be set
     * @param filename the name of the image file to be used as the background
     */
    public static void backgroundPic(JFrame frame, String filename) {

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Calculate the maximum size of the JFrame
        int maxWidth = (int) (screenSize.getWidth() * 0.35);
        int maxHeight = (int) (screenSize.getHeight() * 0.9);

        ImageIcon icon = new ImageIcon(imgPath + filename);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
        JLabel backgound = new JLabel(new ImageIcon(imgScale));
        frame.setContentPane(backgound);
    }

}