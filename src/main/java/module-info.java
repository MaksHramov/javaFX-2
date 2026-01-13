module com.example.slider {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.slider to javafx.fxml;
    exports com.example.slider;
}