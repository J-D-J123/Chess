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

    private final String version = "1.3.3"; // braden did this joseph was here 

    // fram height and width - changed to 720p bc it is a nice 16:9 ratio :)
    // why change the ratio? - what about moble users? lol 
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private JPanel mainMenuPanel;
    private JPanel gamePanel;

    // buttons for the main menu
    private JButton joinServerButton;
    private JButton hostServerButton;
    private JButton playOfflineButton;
    private JButton creditsButton;
    private JButton exitButton;


    JFrame frame        = new JFrame("Chess " + version);
    JMenuBar menuBar    = new JMenuBar();

    // might need a smaller loading gif icon 
    ImageIcon loading = new ImageIcon("assets/gif/loading.gif");
    
    public Menu() {
        makeMenu(); 
    }

    public void makeMenu() {

        // version use top to figure out how to change the version 
        // JFrame frame        = new JFrame("Chess " + version);
        // JMenuBar menuBar    = new JMenuBar();

        // changed the menu bar to just have exit buttons becuaes of my main menu thing
        JMenu system        = new JMenu("System"); // Corrected this line

        system.add(new JMenuItem("Back to main menu"));
        system.add(new JMenuItem("start game")); 
        system.add(new JMenuItem("Credits"));
        system.add(new JMenuItem("Exit"));

        // listen for click on start game button
        system.getItem(1).addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            new Board(); 
        });

        // listen for click on exit button
        system.getItem(2).addActionListener(e -> System.exit(0));

        // listen for click on back to main menu button
        system.getItem(0).addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            makeMenu();
        });

        // listen for click on credits button 
        system.getItem(1).addActionListener(e -> {
            credits(); 
        });


        // add menu item to menuBar
        menuBar.add(system);

        // add the menu to the frame
        frame.setJMenuBar(menuBar);

        // figure out picutre in background 
        // set the picture of the home screen 
        java.net.URL bgURL = getClass().getResource("home.png");
        ImageIcon chess = new ImageIcon(bgURL);

        JLabel backgorund = new JLabel(chess, JLabel.CENTER);


        // add buttons to the main menu
        // joinServerButton    = new JButton("Join Server");
        // hostServerButton    = new JButton("Host Server");
        // playOfflineButton   = new JButton("Play Offline");
        // creditsButton       = new JButton("Credits");
        // exitButton          = new JButton("Exit");

        // // center buttons to the left side with spacing by 10px
        // joinServerButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        // hostServerButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        // playOfflineButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        // creditsButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        // exitButton.setAlignmentX(JButton.LEFT_ALIGNMENT);

        // frame.add(joinServerButton); 
        // frame.add(hostServerButton);
        // frame.add(playOfflineButton);
        // frame.add(creditsButton);
        // frame.add(exitButton);

        // add bg to frame 
        frame.add(backgorund);
        //frame.setContentPane(backgorund);

        // size and visibility
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * this method will be used to display a loading anamation of a circle like object 
     */
    public void loading() {
        frame.add(new JLabel("loading... ", loading, JLabel.CENTER));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }

    public void stopLoading() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }

    private void credits() {
        loading(); 
        stopLoading(); 
        JOptionPane.showMessageDialog(frame, "Chess " + version + "\n\nCreated by:\nJoseph J\nBraden S\n\n© 2024", "Credits", JOptionPane.INFORMATION_MESSAGE);
    }
}
