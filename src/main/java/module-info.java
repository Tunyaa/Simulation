module simulation {
    requires javafx.controls;
    requires javafx.fxml;

    opens simulation.Controller to javafx.fxml;
    opens simulation.Render to javafx.fxml;
    exports simulation;
}
