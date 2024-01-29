package Essentials;

import Networking.Client;
import Networking.Server;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

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

    private final String version = "0.0.0"; // braden did this joseph was here braden is now back and changed it to 0 bc shat isnt even close to being done

    // fram height and width - changed to 720p bc it is a nice 16:9 ratio :)
    // why change the ratio? - what about moble users? lol 
    // moblie users arent even a real thing lets be honest
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
    // braden here, scaling?
    ImageIcon loading = new ImageIcon("assets/gif/loading.gif");
    
    public Menu() {
        makeMenu(); 
    }

    public void makeMenu() {

        // version use top to figure out how to change the version 
        // JFrame frame        = new JFrame("Chess " + version);
        // JMenuBar menuBar    = new JMenuBar();

        JMenu play = new JMenu("Play");
        JMenu system = new JMenu("System"); // Corrected this line

        system.add(new JMenuItem("Back to main menu"));
        system.add(new JMenuItem("Credits"));
        system.add(new JMenuItem("Exit"));

        play.add(new JMenuItem("Host Game"));
        play.add(new JMenuItem("Join Game"));

        // braden here - why does this open a new window for the chess board?
        // cant we just clear the frame or swap panels?
        // listen for click on start game button
        //system.getItem(1).addActionListener(e -> {
        //    frame.getContentPane().removeAll();
        //    frame.revalidate();
        //    frame.repaint();
        //    new Board();
        //});

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

        // listen for click on exit button
        system.getItem(2).addActionListener(e -> System.exit(0));

        play.getItem(0).addActionListener(e -> hostGame());
        play.getItem(1).addActionListener(e -> joinGame());

        // add menu item to menuBar
        menuBar.add(play);
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
     * this method will be used to display a loading animation of a circle like object
     * braden here - this might not be needed bc there isnt like anything to load
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

    // put these in nice little functions :)
    private void hostGame() {
        final String username = JOptionPane.showInputDialog("Input your username");

        // using threads to have the host be able to also play the game
        Thread serverThread = new Thread() {
            public void run() {
                Server chessServer = new Server(6683);
            }
        };
        Thread clientTread = new Thread() {
            public void run() {
                Client chessServerClient = new Client();
                if (!chessServerClient.connect("127.0.0.1", 6683, username)) {
                    chessServerClient = null;
                }
            }
        };

        // starts both the server and the client
        serverThread.start();

        // this is a very bad way of doing it but it's fine
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException i) {
            System.out.println("Error waiting for client tread");
            System.out.println(i);
            System.exit(1);
        }

        clientTread.start();

        loading();

    }

    private void joinGame() {
        final String username = JOptionPane.showInputDialog("Input your username");
        final String ipAddress = JOptionPane.showInputDialog("Input the IP Address of the Server");

        // creates the client
        Client chessClient = new Client();
        // connects and checks if you actually join the server
        if (!chessClient.connect(ipAddress, 6683, username)) {
            chessClient = null;
        }
        loading();
    }

}
