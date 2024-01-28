package Networking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket clientSocket;

    public String username;

    private DataOutputStream initDataOutput;

    private DataInputStream chatDataInput;
    private DataOutputStream chatDataOutput;

    private DataInputStream serverChatDataInput;

    public boolean connect(String ipAddress, int serverPort, String chessUsername) {
        // sets the client's username
        username = chessUsername;

        try {
            // trys to connect to the server
            clientSocket = new Socket(ipAddress, serverPort);
            System.out.println("Connected to Chess Server at " + ipAddress + ":" + serverPort);

            // sends out the init data (player's username)
            initDataOutput = new DataOutputStream(clientSocket.getOutputStream());
            initDataOutput.writeUTF(username);

            connected();

            return true;
        } catch (UnknownHostException u) {
            System.out.println(u);
            return false;
        } catch (IOException i) {
            System.out.println("Error Connecting to Chess Server");
            System.out.println(i);
            return false;
        }
    }

    public void connected() {
        while (clientSocket.isConnected()) {



        }

    }

}
