module com.example.empire_of_rot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.empire_of_rot to javafx.fxml;
    exports com.example.empire_of_rot;
}