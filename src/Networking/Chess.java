package Networking;

/**
 *
 * File: Chess.java
 * Authors: Braden S
 * Last Modified: Jan 28, 2024
 *
 * This file is prolly just going to be used for debuging now so don't really worry ab it
 *
**/

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Chess {

    private boolean gameStarted = false;

    private String slection;

    private String username;

    private void mainMenu() {
        Scanner inputScanner = new Scanner(System.in);
        Scanner usernameScanner = new Scanner(System.in);

        System.out.println("Welcome to Chess!");
        System.out.println("");
        System.out.print("Please input your username: ");
        username = usernameScanner.next();

        System.out.println("");
        System.out.println("1. Host a Game");
        System.out.println("2. Host a Server");
        System.out.println("3. Join a Game");
        System.out.println("4. Credits");
        System.out.println("5. Exit");

        System.out.print("Selection: ");
        slection = inputScanner.next();
    }

    public void createServer(int port) {
        Server chessServer = new Server(6683);
    }

    public void joinServer(int port) {
        // creates the client
        Client chessClient = new Client();
        // connects and checks if you actually join the server
        if (!chessClient.connect("127.0.0.1", port, username)) {
            chessClient = null;
        } else {
            gameStarted = true;
        }
    }

    // boths hosts a game and plays on the server at the same time
    public void hostClient(int port) {
        // using threads to have the host be able to also play the game
        Thread serverThread = new Thread() {
            public void run() {
                // hard coded port for now
                Server chessServer = new Server(6683);
            }
        };
        Thread clientTread = new Thread() {
            public void run() {
                Client chessServerClient = new Client();
                if (!chessServerClient.connect("127.0.0.1", port, username)) {
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

        gameStarted = true;
    }

    public static void main(String[] args) {

        Chess game = new Chess();

        // main menu loop
        while (!game.gameStarted) {
            game.mainMenu();

            switch (game.slection) {
                case "1":
                    game.hostClient(6683);
                    break;
                case "2":
                    game.createServer(6683);
                    break;
                case "3":
                    game.joinServer(6683);
                    break;
                case "4":
                    System.out.println("Created by Braden S and Joey J");
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("ERROR: invalid selection");
                    break;
            }
        }
    }
}