module com.example.theshatteredrealm {
    requires com.google.gson;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.theshatteredrealm to javafx.fxml;
    exports com.example.theshatteredrealm;
}