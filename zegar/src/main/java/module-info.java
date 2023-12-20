module com.example.zegar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zegar to javafx.fxml;
    exports com.example.zegar;
}