package com.example.shatteredrealm;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<String> clues;
    private boolean isExit;
    private String exitCode;
    private String[] exitDirections;

    public Room(String name, String description, String... exitDirections) {
        this(name, description, false, null, exitDirections);
    }

    public Room(String name, String description, boolean isExit, String exitCode, String... exitDirections) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.clues = new ArrayList<>();
        this.isExit = isExit;
        this.exitCode = exitCode;
        this.exitDirections = exitDirections;
    }

    public void addClue(String clue) {
        clues.add(clue);
    }

    public List<String> getClues() {
        return clues;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getExitCode() {
        return exitCode;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction.toLowerCase(), room);
    }

    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}