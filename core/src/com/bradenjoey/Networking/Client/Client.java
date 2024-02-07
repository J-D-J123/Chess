package com.bradenjoey.Networking.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import com.bradenjoey.Networking.Packet.Packet;
import com.bradenjoey.Networking.Packet.PacketType;

public class Client {

    public Socket socket;
    private String username;
    public String color;

    private ObjectOutputStream packetOutputStream;
    private ObjectInputStream packetInputStream;

    public Client() {

        // temp
        System.out.println("Welcome to Chess");
        System.out.print("Please Input a Username to Begin: ");

        @SuppressWarnings("resource")
        Scanner usernameScanner = new Scanner(System.in);
        username = usernameScanner.nextLine();
    }

    public void connect(String ipAddress, int port) {
        try {
            socket = new Socket(ipAddress, port);
            System.out.println("Connected to Server!");
        } catch (IOException i) {
            System.out.println("Error Occured When Trying to Connecting to Chat Server");
            System.out.println(i);
        }

        initStreams();
    
        listenForPackets();

        sendInit();
    }

    public void initStreams() {
        try {
            packetOutputStream = new ObjectOutputStream(socket.getOutputStream());
            packetInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException i) {
            System.out.println("Error Occured While Starting I/O Streams");
            System.out.println(i);
        }
    }

    private void listenForPackets() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    Packet packet = null;

                    try {
                        packet = (Packet) packetInputStream.readObject();
                    } catch (IOException i) {
                        System.out.println("Server Has Closed!");
                        System.exit(0);
                    } catch (ClassNotFoundException c) {
                        System.out.println("Error Occured: Packet Class Not Found!");
                        System.out.println(c);
                    }

                    if (packet != null) {
                        switch (packet.type) {
                            case INIT:
                                color = packet.color;
                                System.out.println(color);
                                break;
                            case MOVE:
                                System.out.println(packet.data);
                                break;
                        }
                    }

                }
            }
        }).start();
    }

    private void sendInit() {
        Packet initPacket = new Packet();
        initPacket.type = PacketType.INIT;
        initPacket.sender = username;

        sendPacket(initPacket);
    }

    public void sendMove(String move) {
        Packet messagePacket = new Packet();
        messagePacket.type = PacketType.MOVE;
        messagePacket.sender = username;
        messagePacket.data = move;

        sendPacket(messagePacket);
    }

    private void sendPacket(Packet packet) {
        try {
            packetOutputStream.writeObject(packet);
        } catch (IOException i) {
            System.out.println("Error Occured While Trying to Send Packet");
            System.out.println(i);
        }
    }

}