module com.chat_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.chat_app to javafx.fxml;
    exports com.chat_app;
    exports com.chat_app.Controller;
    opens com.chat_app.Controller to javafx.fxml;
    exports com.chat_app.Process;
    opens com.chat_app.Model to javafx.fxml;
}