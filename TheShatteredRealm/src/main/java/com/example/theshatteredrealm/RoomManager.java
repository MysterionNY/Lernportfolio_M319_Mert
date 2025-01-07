package com.example.theshatteredrealm;

import java.util.HashMap;
import java.util.Map;

public class RoomManager {
    private Map<String, Room> rooms;

    public RoomManager() {
        rooms = new HashMap<>();
        initializeRooms();
    }

    private void initializeRooms() {
        // Räume erstellen
        Room room1 = new Room("Raum 1", "Beschreibung des Raumes 1");
        Room room2 = new Room("Raum 2", "Beschreibung des Raumes 2");
        Room room3 = new Room("Raum 3", "Beschreibung des Raumes 3");

        // Räume miteinander verbinden
        room1.setNorthRoom(room2);
        room2.setSouthRoom(room1);
        room2.setEastRoom(room3);
        room3.setWestRoom(room2);

        // Räume in die Map hinzufügen
        rooms.put(room1.getName(), room1);
        rooms.put(room2.getName(), room2);
        rooms.put(room3.getName(), room3);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }
}
