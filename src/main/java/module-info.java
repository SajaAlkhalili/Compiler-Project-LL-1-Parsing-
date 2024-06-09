module com.example.finalprojectcompiler {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalprojectcompiler to javafx.fxml;
    exports com.example.finalprojectcompiler;
}