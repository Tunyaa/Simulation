package simulation.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import simulation.App;
import simulation.Model.Herbivore;

public class SecondaryController {

    @FXML
    private Canvas canvas3;
    private int[][] p = new int [10000][2];

    {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            p[i][0] = random.nextInt(1000);
            p[i][1] = random.nextInt(1000);

        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void initialize() {
        // Этот метод вызывается автоматически после загрузки FXML
        GraphicsContext gc = canvas3.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillOval(90, 90, 90, 90); // рисуем синий кружок
        gc.fillArc(33, 33, 33, 33, 33, 33, ArcType.OPEN);

    }

    @FXML
    private void handleAdd() {
        // Этот метод будет вызываться при нажатии кнопки
        GraphicsContext gc = canvas3.getGraphicsContext2D();
        gc.setFill(Color.RED);
        for (int i = 0; i <p.length; i++) {
            gc.fillOval(p[i][0], p[i][1], 20, 20);
        }
        gc.fillOval(0, 0, 20, 20); // рисуем ещё один красный кружок
    }

}
