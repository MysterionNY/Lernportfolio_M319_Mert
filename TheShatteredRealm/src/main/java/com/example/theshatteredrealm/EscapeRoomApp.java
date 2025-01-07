package com.example.theshatteredrealm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EscapeRoomApp extends Application {

    private Room currentRoom;
    private Label roomDescription;
    private ImageView roomImage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Escape Room");

        // Räume erstellen (wie im vorherigen Beispiel)
        Image startRoomImage = new Image(getClass().getResourceAsStream("start_room.jpg")); // Bildpfad relativ zur Klasse
        Image libraryImage = new Image(getClass().getResourceAsStream("library.jpg"));
        Room startRoom = new Room("Startraum", "Du befindest dich in einem dunklen Raum.", startRoomImage);
        Room library = new Room("Bibliothek", "Regale voller Bücher ragen bis zur Decke.", libraryImage);
        Room secretRoom = new Room("Geheimer Raum", "Ein versteckter Raum mit einem alten Schreibtisch.", null);

        startRoom.setExit("Norden", library);
        library.setExit("Süden", startRoom);
        library.setExit("Osten", secretRoom);
        secretRoom.setExit("Westen", library);

        currentRoom = startRoom; // Startraum setzen

        // JavaFX Layout
        BorderPane root = new BorderPane();

        // Raumbeschreibung
        roomDescription = new Label();
        roomDescription.setWrapText(true); // Textumbruch aktivieren
        root.setCenter(roomDescription);

        //ImageView für die Bilder
        roomImage = new ImageView();
        roomImage.setPreserveRatio(true); // Seitenverhältnis beibehalten
        roomImage.setFitWidth(400); // Maximale Breite des Bildes setzen
        roomImage.setFitHeight(300);
        BorderPane.setAlignment(roomImage, Pos.CENTER); //zentriert das Bild
        root.setTop(roomImage); //Bild nach oben

        // Buttons für Himmelsrichtungen in einer HBox für bessere Anordnung
        HBox buttonBox = new HBox(10); // Abstand zwischen den Buttons
        buttonBox.setPadding(new Insets(10)); // Rand um die Buttons
        buttonBox.setAlignment(Pos.CENTER);
        Button northButton = new Button("Norden");
        Button southButton = new Button("Süden");
        Button eastButton = new Button("Osten");
        Button westButton = new Button("Westen");
        buttonBox.getChildren().addAll(northButton, southButton, eastButton, westButton);
        root.setBottom(buttonBox);

        // Event-Handler (Norden als Beispiel - die anderen analog)
        northButton.setOnAction(e -> changeRoom("Norden"));
        southButton.setOnAction(e -> changeRoom("Süden"));
        eastButton.setOnAction(e -> changeRoom("Osten"));
        westButton.setOnAction(e -> changeRoom("Westen"));

        updateRoomDisplay(); // Erste Anzeige aktualisieren

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            updateRoomDisplay();
        } else {
            roomDescription.setText("Du kannst nicht in diese Richtung gehen!");
        }
    }

    private void updateRoomDisplay() {
        roomDescription.setText(currentRoom.getDescription());
        if (currentRoom.getImage() != null) {
            roomImage.setImage(currentRoom.getImage());
        } else {
            roomImage.setImage(null); // Bild entfernen, falls kein Bild vorhanden ist
        }
    }
}