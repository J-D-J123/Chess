package com.bradenjoey.Networking.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    
    private ServerSocket serverSocket;
    private ClientHandler[] clientHandlers;

    private int connectedClients;

    private ArrayList<String> moveHistory;

    public static void main(String[] args) {
        Server chatServer = new Server();
        chatServer.startServer(6678);
    }

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Started Server!");

        } catch (IOException i) {
            System.out.println("Failed to Start Server Socket!");
            System.out.println(i);
        }

        clientHandlers = new ClientHandler[2];
        moveHistory = new ArrayList<>();

        System.out.println("Listening at Port: " + port);
        listenForClients();

        serverLoop();

    }

    public void listenForClients() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (connectedClients == clientHandlers.length) {
                    clientHandlers[connectedClients] = new ClientHandler(serverSocket);

                    while (!clientHandlers[connectedClients].isInitalized) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println("Error Occured While Checking if Client is Initalized!");
                            System.exit(1);
                        }
                    }

                    if (clientHandlers[connectedClients].socket.isConnected()) {
                        System.out.println(clientHandlers[connectedClients].username + " Has Connected!");
                        connectedClients++;
                    }
                }
            }
        }).start();   
    }

    public void serverLoop() {
        while (!serverSocket.isClosed()) {
            disconnectChecker();
            newMoveChecker();
        }
    }

    public void disconnectChecker() {
        if (connectedClients > 0) {
            for (int i = 0; i < clientHandlers.length; i++) {
                if (clientHandlers[i].socket.isClosed()) {
                    clientHandlers[i].exit();
                    String message = clientHandlers[i].username + " Has Disconnected";
                    clientHandlers[i] = null;
                    connectedClients--;
                    moveHistory.add(message);
                    broadcastMove(message, -1); // -1 because no sender will ever have an index of -1
                }
            }
        }
    }

    public void newMoveChecker() {
        if (connectedClients > 0) {
            for (int i = 0; i < clientHandlers.length; i++) {
                if (clientHandlers[i].message != null && clientHandlers[i].socket.isConnected()) {
                    moveHistory.add(clientHandlers[i].message);
                    clientHandlers[i].message = null;
                    broadcastMove(moveHistory.get(moveHistory.size() - 1), i);
                }
            }
        }
    }

    public void broadcastMove(String move, int originalSenderIndex) {
        System.out.println(moveHistory.get(moveHistory.size() - 1));
        for (int i = 0; i < clientHandlers.length; i++) {
            if (clientHandlers[i].socket.isConnected() && i != originalSenderIndex) {
                clientHandlers[i].sendMove(move);
            }
        }
    }
}
