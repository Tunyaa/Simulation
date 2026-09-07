module simulation {
    requires javafx.controls;
    requires javafx.fxml;

    opens simulation.controller to javafx.fxml;
    opens simulation.render to javafx.fxml;
    exports simulation;
}
