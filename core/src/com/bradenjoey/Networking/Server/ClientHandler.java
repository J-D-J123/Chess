package com.bradenjoey.Networking.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.bradenjoey.Networking.Packet.Packet;
import com.bradenjoey.Networking.Packet.PacketType;

public class ClientHandler {

    public boolean isInitalized;
    private boolean isListening;
    
    public Socket socket;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public String username;
    public String color;

    public String move;

    public ClientHandler(ServerSocket serverSocket) {
        acceptClient(serverSocket);
        initStreams();

        isListening = true;
        listenForPackets();
    }

    public void acceptClient(ServerSocket serverSocket) {
        try {
            socket = serverSocket.accept();
        } catch (IOException i) {
            System.out.println("Error Occured While Listening for Client");
        }
    }

    private void initStreams() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException i) {
            System.out.println("Error Occured While Starting I/O Streams");
            System.out.println(i);
        }
    }

    private void listenForPackets() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (socket.isConnected() && isListening) {
                    Packet packet = null;

                    try {
                        packet = (Packet) objectInputStream.readObject();
                    } catch (IOException i) {
                        try {
                            socket.close();
                        } catch (IOException o) {
                            System.out.println("Error Occured Trying to Close Client Socket!");
                            System.out.println(o);
                        }
                    } catch (ClassNotFoundException c) {
                        System.out.println("Error Occured: Packet Class Not Found!");
                        System.out.println(c);
                    }

                    if (packet != null) {
                        switch (packet.type) {
                            case INIT:
                                username = packet.sender;
                                isInitalized = true;
                                break;
                            case MOVE:
                                if (validateMove(packet.data)) {
                                    move = packet.color + " " + packet.data;
                                }
                                break;
                        }
                    }

                }
            }
        }).start();
    }

    public boolean validateMove(String move) {
        // temp becuase we dont have a validate move thing
        return true;
    }

    public void sendMove(String move) {
        Packet movePacket = new Packet();
        movePacket.type = PacketType.MOVE;
        movePacket.sender = "SERVER";
        movePacket.data = move;

        sendPacket(movePacket);
    }

    public void sendPlayerLeft() {
        Packet leftPacket = new Packet();
        leftPacket.type = PacketType.DISCONNECT;
        leftPacket.sender = "SERVER";
        leftPacket.color = color;

        sendPacket(leftPacket);
    }

    public void sendInit() {
        Packet initPacket = new Packet();
        initPacket.type = PacketType.INIT;
        initPacket.sender = "SERVER";
        initPacket.color = color;

        sendPacket(initPacket);
    }

    private void sendPacket(Packet packet) {
        try {
            objectOutputStream.writeObject(packet);
        } catch (IOException i) {
            System.out.println("Error Occured While Trying to Send Packet");
            System.out.println(i);
        }
    }

    public void exit() {
        isListening = false;
    }
}
