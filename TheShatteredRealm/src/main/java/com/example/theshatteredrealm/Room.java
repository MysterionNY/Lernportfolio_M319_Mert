package com.example.theshatteredrealm;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Image image; // Optional: Für bildbasierte Räume
    private Map<String, Room> exits;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
    }
    public Room(String name, String description, Image image) {
        this(name, description);
        this.image = image;
    }


    public void setExit(String direction, Room room) {
        exits.put(direction.toLowerCase(), room);
    }

    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return this.name; // Für Debugging und einfachere Anzeige
    }
}