package Essentials;

import javax.swing.*;

/**
 * @authors:        Joseph J, Braden S
 * Last Updated:    1/28/2024
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

    private final String version = "1.1.3"; // braden did this

    // fram height and width - changed to 720p bc it is a nice 16:9 ratio :)
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private JPanel mainMenuPanel;
    private JPanel gamePanel;
    
    public Menu() {
        makeMenu(); 
    }

    public void makeMenu() {

        // version use top to figure out how to change the version 
        JFrame frame        = new JFrame("Chess " + version);
        JMenuBar menuBar    = new JMenuBar();

        // changed the menu bar to just have exit buttons becuaes of my main menu thing
        JMenu system        = new JMenu("System"); // Corrected this line

        system.add(new JMenuItem("Back to main menu"));
        system.add(new JMenuItem("Credits"));
        system.add(new JMenuItem("Exit"));

        // add menu item to menuBar
        menuBar.add(system);

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
