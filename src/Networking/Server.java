package Networking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket chessServerSocket;

    private Socket player1ChessSocket;
    private DataInputStream player1InitDataInput;
    private String player1Username;

    private Socket player2ChessSocket;
    private DataInputStream player2InitDataInput;
    private String player2Username;

    private boolean ready = false;

    public void searchForClients() {
        try {
            // connects the client to the server
            player1ChessSocket = chessServerSocket.accept();

            // gets init data from the client (the player's username)
            player1InitDataInput = new DataInputStream(new BufferedInputStream(player1ChessSocket.getInputStream()));
            player1Username = player1InitDataInput.readUTF();
            // prints the username out to the server terminal
            System.out.println("Player 1's username is " + player1Username);

            // checks if the first player has joined before allowing the second player socket to be filled
            if (player1ChessSocket.isConnected()) {
                // connects the client to the server
                player2ChessSocket = chessServerSocket.accept();

                // gets the init data from the client (the player's username)
                player2InitDataInput = new DataInputStream(new BufferedInputStream(player2ChessSocket.getInputStream()));
                player2Username = player2InitDataInput.readUTF();
                // prints the username out to the server terminal
                System.out.println("Player 2's username is " + player2Username);
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

            while (!ready) {
                searchForClients();
            }

            gameStart();

        } catch (IOException i) {
            System.out.println("Failed to start Server");
            System.out.println(i);
            System.exit(1);
        }

    }

    public void clientChecker() {
        if (!player1ChessSocket.isConnected() || !player2ChessSocket.isConnected()) {
            ready = false;
        }
    }

    public void gameStart() {

        while (true) {
            clientChecker();
        }

    }

}
