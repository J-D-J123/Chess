package Networking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * File: Server.java
 * Authors: Braden S
 * Last Modified: Jan 28, 2024
 * Work Needed: All Chess moves will be calculated here and then sent out to the clients
 * this makes stuff server side and means people cant cheat kinda
 *  - There is a lot wrong with the use of heartbeats here but i will work on it later, backend shit is not fun
**/

public class Server {

    private ServerSocket chessServerSocket;

    private Socket player1ChessSocket;
    private DataInputStream player1DataInput;
    private String player1Username;

    private boolean player1Connected = false;

    private Socket player2ChessSocket;
    private DataInputStream player2DataInput;
    private String player2Username;

    private boolean player2Connected = false;

    private boolean ready = false;

    public void searchForClients() {
        try {
            // connects the client to the server
            player1ChessSocket = chessServerSocket.accept();

            // gets init data from the client (the player's username)
            player1DataInput = new DataInputStream(new BufferedInputStream(player1ChessSocket.getInputStream()));
            player1Username = player1DataInput.readUTF();

            if (player1Username.substring(0,4).equals("INIT")) {
                player1Username = player1Username.substring(5);

                player1Connected = true;

                // prints the username out to the server terminal
                System.out.println(player1Username + " has joined");
            }

            // checks if the first player has joined before allowing the second player socket to be filled
            if (player1ChessSocket.isConnected()) {
                // connects the client to the server
                player2ChessSocket = chessServerSocket.accept();

                // gets the init data from the client (the player's username)
                player2DataInput = new DataInputStream(new BufferedInputStream(player2ChessSocket.getInputStream()));
                player2Username = player2DataInput.readUTF();

                if (player2Username.substring(0,4).equals("INIT")) {
                    player2Username = player2Username.substring(5);

                    player2Connected = true;

                    // prints the username out to the server terminal
                    System.out.println(player2Username + " has joined");
                }
            }
            ready = true;
        } catch (IOException i) {
            System.out.println("Failed while searching for clients");
            System.out.println(i);
            System.exit(1);
        }
    }

    public Server(int serverPort) {
        try {
            // creates the server
            chessServerSocket = new ServerSocket(serverPort);
            System.out.println("Server started");

            searchForClients();

            gameLoop();

        } catch (IOException i) {
            System.out.println("Failed to start Server");
            System.out.println(i);
            System.exit(1);
        }

    }

    public void heartbeat() {
        if (player1Connected) {
            try {
                if (player1DataInput.readUTF().substring(0, 7).equals("HEARTBEAT")) {
                    player1Connected = true;
                }
            } catch (IOException i) {
                if (player1Connected) {
                    player1Connected = false;
                    player1ChessSocket = null;
                    System.out.println(player1Username + " has disconnected");
                }
            }
        }

        if (player2Connected) {
            try {
                if (player2DataInput.readUTF().substring(0, 7).equals("HEARTBEAT")) {
                    player2Connected = true;
                }
            } catch (IOException i) {
                if (player2Connected) {
                    player2Connected = false;
                    player2ChessSocket = null;
                    System.out.println(player2Username + " has disconnected");
                }
            }
        }
    }

    public void gameLoop() {
        while (true) {
            heartbeat();

            if (!player1Connected || !player2Connected) {
                searchForClients();
            }

        }
    }

}
