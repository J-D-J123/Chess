package com.bradenjoey.Networking.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientHandlers;

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

        clientHandlers = new ArrayList<>();
        moveHistory = new ArrayList<>();

        System.out.println("Listening at Port: " + port);
        listenForClients();

        serverLoop();

    }

    public void listenForClients() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (connectedClients == clientHandlers.size()) {
                    clientHandlers.add(new ClientHandler(serverSocket));

                    while (!clientHandlers.get(connectedClients).isInitalized) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println("Error Occured While Checking if Client is Initalized!");
                            System.exit(1);
                        }
                    }

                    if (clientHandlers.get(connectedClients).socket.isConnected()) {
                        System.out.println(clientHandlers.get(connectedClients).username + " Has Connected!");
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
            for (int i = 0; i < clientHandlers.size(); i++) {
                if (clientHandlers.get(i).socket.isClosed()) {
                    clientHandlers.get(i).exit();
                    String message = clientHandlers.get(i).username + " Has Disconnected";
                    clientHandlers.remove(i);
                    connectedClients--;
                }
            }
        }
    }

    public void newMoveChecker() {
        if (connectedClients > 0) {
            for (int i = 0; i < clientHandlers.size(); i++) {
                if (clientHandlers.get(i).message != null && clientHandlers.get(i).socket.isConnected()) {
                    moveHistory.add(clientHandlers.get(i).message);
                    clientHandlers.get(i).message = null;
                    broadcastMove(moveHistory.get(moveHistory.size() - 1), i);
                }
            }
        }
    }

    public void broadcastMove(String move, int originalSenderIndex) {
        System.out.println(moveHistory.get(moveHistory.size() - 1));
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (clientHandlers.get(i).socket.isConnected() && i != originalSenderIndex) {
                clientHandlers.get(i).sendMove(move);
            }
        }
    }
}
