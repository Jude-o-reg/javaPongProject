module org.example.projectpart1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projectpart1 to javafx.fxml;
    exports org.example.projectpart1;
}