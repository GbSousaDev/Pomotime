module com.example.cursojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cursojavafx to javafx.fxml;
    opens com.example.cursojavafx.Controllers to javafx.fxml;
    exports com.example.cursojavafx;
}