package Essentials;

import javax.swing.*;

/**
 * @authors:        Joseph J
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

    private final String version = "1.0.3"; 

    // fram height and width 
    private final int WIDTH = 300; 
    private final int HEIGHT = 300;
    
    public Menu() {
        makeMenu(); 
    }

    public void makeMenu() {

        // version use top to figure out how to change the version 
        JFrame frame        = new JFrame("Chess " + version);
        JMenuBar menuBar    = new JMenuBar();

        JMenu signIn        = new JMenu("Sign In"); // Corrected this line
        JMenu signUp        = new JMenu("Sign Up");
        JMenu playRemotely  = new JMenu("Connect");

        // login.add(new JMenuItem("Login"));
        // login.add(new JMenuItem("Sign In"));
        signIn.add(new JMenuItem("signUp"));
        signIn.add(new JMenuItem("signIn"));
        playRemotely.add(new JMenuItem("Server Connection"));

        // add menu item to menuBar
        menuBar.add(signIn);
        //menuBar.add(signIn);
        menuBar.add(playRemotely);

        // add the menu to the frame
        frame.setJMenuBar(menuBar);

        // figure out picutre in background 
        // set the picture of the home screen 
        // ImageIcon icon = new ImageIcon("download.png");

        // if(icon.getImage() == null) {
        //     System.out.println("Icon is null");
        // } else {
        //     System.out.println("Icon is generated");
        // }
        // JLabel backgorund = new JLabel(icon);

        // // size of icon 
        // backgorund.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // frame.setContentPane(backgorund);

        // size and visibility
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
