module com.example.shatteredrealm {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.shatteredrealm to javafx.fxml;
    exports com.example.shatteredrealm;
}