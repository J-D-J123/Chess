package Networking;

/**
 * File: Client.java
 * Authors: Braden S
 * Last Modified: Jan 28, 2024
 *
 * Data sending identifers
 *  INIT - sends the first data the server will recive, this will be the username of the player
 *  ALIV - sends a heart beat signal telling the server that the client is still connected
 *
 *  more to come
**/

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    private boolean connected = false;

    private Socket clientSocket;

    public String username;

    private DataOutputStream dataOutput;

    public boolean connect(String ipAddress, int serverPort, String chessUsername) {
        // sets the client's username
        username = chessUsername;

        try {
            // trys to connect to the server
            clientSocket = new Socket(ipAddress, serverPort);
            System.out.println("Connected to Chess Server at " + ipAddress + ":" + serverPort);

            // sends out the init data (player's username)
            dataOutput = new DataOutputStream(clientSocket.getOutputStream());
            dataOutput.writeUTF("INIT " + username);

            startGame();

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

    public void heartbeat() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    dataOutput.writeUTF("ALIV");
                } catch (IOException i) {
                    System.out.println("Failed creating heartbeat");
                    System.out.println(i);
                    System.exit(1);
                }
            }
        }, 0, 5000);
    }

    public void startGame() {
        heartbeat();
    }

}