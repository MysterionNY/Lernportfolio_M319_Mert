import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EscapeRoomApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Titel des Fensters
        primaryStage.setTitle("Escape Room Spiel");

        // Inhalt des Fensters
        Label welcomeLabel = new Label("Willkommen im Escape Room!");

        // Layout-Container
        StackPane root = new StackPane();
        root.getChildren().add(welcomeLabel);

        // Szene erstellen
        Scene scene = new Scene(root, 800, 600);

        // Szene zum Fenster hinzufügen und anzeigen
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Startet die JavaFX-Anwendung
    }
}
