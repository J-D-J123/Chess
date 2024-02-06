package com.bradenjoey.Networking.Packet;

import java.io.Serializable;

public class Packet implements Serializable {
    public PacketType type;
    public String sender;
    public String color;

    public String data;
}
