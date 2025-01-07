package com.example.shatteredrealm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class EscapeRoomApp extends Application {

    private Room currentRoom;
    private Map<String, Room> rooms = new HashMap<>();
    private GridPane roomGrid;
    private VBox infoBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Escape Room");

        try {
            loadRoomsFromJson("C:\\Users\\mert2\\Documents\\GitHub\\Lernportfolio_M319_Mert\\ShatteredRealm\\src\\main\\resources\\rooms.json");
            if (currentRoom == null) {
                throw new IllegalStateException("Startraum wurde nicht korrekt gesetzt!");
            }
            System.out.println("Startraum endgültig: " + currentRoom.getName());
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Räume aus JSON: " + e.getMessage());
            return;
        }

        initializeCurrentRoom();

        // GUI-Initialisierung
        BorderPane root = new BorderPane();
        roomGrid = new GridPane();
        roomGrid.setAlignment(Pos.CENTER);
        roomGrid.setPadding(new Insets(20));
        roomGrid.setHgap(10);
        roomGrid.setVgap(10);

        infoBox = new VBox(10);
        infoBox.setPadding(new Insets(10));
        infoBox.setAlignment(Pos.TOP_CENTER);

        root.setCenter(roomGrid);
        root.setBottom(infoBox);

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        String[] directions = {"Norden", "Süden", "Osten", "Westen"};
        for (String dir : directions) {
            Button button = new Button(dir);
            button.setOnAction(e -> changeRoom(dir));
            buttonBox.getChildren().add(button);
        }

        root.setTop(buttonBox);

        // Startraum anzeigen
        updateRoomDisplay();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void initializeCurrentRoom() {
        if (currentRoom == null) {
            throw new IllegalStateException("Startraum wurde nicht gesetzt!");
        }
        System.out.println("Startraum endgültig gesetzt: " + currentRoom.getName());
    }

    private void loadRoomsFromJson(String filename) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filename)));
        JSONArray jsonArray = new JSONArray(jsonContent);

        // Startraum direkt aus der JSON-Datei extrahieren
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonRoom = jsonArray.getJSONObject(i);
            boolean isStart = jsonRoom.optBoolean("isStart", false);

            if (isStart) {
                String name = jsonRoom.getString("name");
                String description = jsonRoom.getString("description");
                String exitCode = jsonRoom.optString("exitCode", null);
                boolean isExit = jsonRoom.optBoolean("isExit", false);

                Room startRoom = new Room(name, description, isExit, exitCode);

                // Hinweise hinzufügen
                if (jsonRoom.has("clues")) {
                    JSONArray jsonClues = jsonRoom.getJSONArray("clues");
                    for (int j = 0; j < jsonClues.length(); j++) {
                        startRoom.addClue(jsonClues.getString(j));
                    }
                }

                currentRoom = startRoom; // Startraum direkt setzen
                rooms.put(name, startRoom); // Startraum zur Map hinzufügen
                System.out.println("Startraum gesetzt: " + currentRoom.getName());
                break; // Schleife abbrechen, da der Startraum gefunden wurde
            }
        }

        if (currentRoom == null) {
            throw new IllegalStateException("Kein Startraum mit 'isStart: true' gefunden!");
        }

        // Restliche Räume hinzufügen
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonRoom = jsonArray.getJSONObject(i);
            String name = jsonRoom.getString("name");

            // Überspringe den bereits hinzugefügten Startraum
            if (rooms.containsKey(name)) {
                continue;
            }

            String description = jsonRoom.getString("description");
            boolean isExit = jsonRoom.optBoolean("isExit", false);
            String exitCode = jsonRoom.optString("exitCode", null);

            Room room = new Room(name, description, isExit, exitCode);

            // Hinweise hinzufügen
            if (jsonRoom.has("clues")) {
                JSONArray jsonClues = jsonRoom.getJSONArray("clues");
                for (int j = 0; j < jsonClues.length(); j++) {
                    room.addClue(jsonClues.getString(j));
                }
            }

            rooms.put(name, room);
        }

        // Exits verknüpfen
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonRoom = jsonArray.getJSONObject(i);
            String name = jsonRoom.getString("name");
            Room currentRoom = rooms.get(name);

            if (currentRoom == null) {
                System.err.println("Raum " + name + " wurde nicht gefunden!");
                continue;
            }

            if (jsonRoom.has("exits")) {
                JSONObject jsonExits = jsonRoom.getJSONObject("exits");
                for (String direction : jsonExits.keySet()) {
                    String nextRoomName = jsonExits.getString(direction);
                    Room nextRoom = rooms.get(nextRoomName);

                    if (nextRoom != null) {
                        currentRoom.setExit(direction, nextRoom);
                    } else {
                        System.err.println("Ausgang " + direction + " verweist auf einen nicht existierenden Raum: " + nextRoomName);
                    }
                }
            }
        }
    }

    private void changeRoom(String direction) {
        System.out.println("Aktueller Raum vor Wechsel: " + currentRoom.getName());
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("Raum gewechselt zu: " + currentRoom.getName());
            updateRoomDisplay();
        } else {
            infoBox.getChildren().clear();
            Text errorMessage = new Text("Der Raum in Richtung " + direction + " existiert nicht!");
            errorMessage.setFill(Color.RED);
            infoBox.getChildren().add(errorMessage);
        }
    }


    private void updateRoomDisplay() {
        roomGrid.getChildren().clear();
        infoBox.getChildren().clear();

        // Aktueller Raum anzeigen (grafische Darstellung)
        Rectangle currentRoomRect = new Rectangle(100, 100, Color.LIGHTBLUE);
        Text currentRoomText = new Text(currentRoom.getName());
        StackPane currentRoomPane = new StackPane(currentRoomRect, currentRoomText);

        roomGrid.add(currentRoomPane, 1, 1);

        // Mögliche Ausgänge hinzufügen
        Map<String, Room> exits = currentRoom.getExits();
        for (String direction : exits.keySet()) {
            Room exitRoom = exits.get(direction);
            Rectangle exitRect = new Rectangle(50, 50, Color.LIGHTGREEN);
            Text exitText = new Text(direction);

            StackPane exitPane = new StackPane(exitRect, exitText);

            switch (direction.toLowerCase()) {
                case "norden":
                    roomGrid.add(exitPane, 1, 0);
                    break;
                case "süden":
                    roomGrid.add(exitPane, 1, 2);
                    break;
                case "osten":
                    roomGrid.add(exitPane, 2, 1);
                    break;
                case "westen":
                    roomGrid.add(exitPane, 0, 1);
                    break;
            }
        }

        // Beschreibung des Raums anzeigen
        Text descriptionText = new Text("Beschreibung: " + currentRoom.getDescription() + "\n");
        descriptionText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        infoBox.getChildren().add(descriptionText);

        // Hinweise (Clues) hinzufügen
        if (!currentRoom.getClues().isEmpty()) {
            Text cluesHeader = new Text("Hinweise:\n");
            cluesHeader.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            infoBox.getChildren().add(cluesHeader);

            for (String clue : currentRoom.getClues()) {
                Text clueText = new Text("- " + clue + "\n");
                infoBox.getChildren().add(clueText);
            }
        }

        // Escape-Option, falls der aktuelle Raum ein Ausgang ist
        if (currentRoom.isExit()) {
            TextField codeField = new TextField();
            Button submitButton = new Button("Code eingeben");
            submitButton.setOnAction(e -> {
                if (codeField.getText().equals(currentRoom.getExitCode())) {
                    infoBox.getChildren().clear();
                    Text successMessage = new Text("Herzlichen Glückwunsch! Du bist entkommen!");
                    successMessage.setFill(Color.GREEN);
                    infoBox.getChildren().add(successMessage);
                } else {
                    Text errorMessage = new Text("Falscher Code! Versuche es erneut.");
                    errorMessage.setFill(Color.RED);
                    infoBox.getChildren().add(errorMessage);
                }
            });

            infoBox.getChildren().addAll(new Text("Gib den Code ein, um zu entkommen:"), codeField, submitButton);
        }
    }
}
