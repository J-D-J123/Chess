package Essentials;

import javax.swing.*;

/**
 * Authors:         Joseph J
 * Last Updated:    1/27/2024
 * 
 * Description:     The Menu has options for the user to choose from.
 *                  This calls the Board class and then the Player class which 
 *                  will start the Game of Chess and ask the user if they want to input 
 *                  a server and a name. 
 */

public class Menu {

    // 4.2.1 (major.minor.patch) 
    // 4 = major update 
    // 2 = minor update
    // 1 = patch update (fixing errors)

    private final double version = 1.0; 

    // fram height and width 
    private final int WIDTH = 300; 
    private final int HEIGHT = 300;
    
    public Menu() {
        makeMenu(); 
    }

    public void makeMenu() {

        // Version v 
        JFrame frame = new JFrame("Chess" + version);
        JMenuBar menuBar = new JMenuBar();
        JMenu login = new JMenu("Login");

        login.add(new JMenuItem("Login"));

        // add the menu to the frame
        frame.setJMenuBar(menuBar);

        // size and visibility
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
