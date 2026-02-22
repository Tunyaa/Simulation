package simulation.Controller;

import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simulation.App;
import simulation.App;
import simulation.World.WorldMap;

public class PrimaryController {

    private WorldMap worldMap;

    @FXML
    private Canvas canvas;
    @FXML
    private TextField width;
    @FXML
    private TextField height;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void toPanel() throws IOException {
        App.setRoot("panel");
    }

    @FXML
    private void toStart() {
        System.out.println("toStart");
        
        System.out.println(" w - " + width.getText());
        System.out.println(" h - " + height);
        int w = Integer.parseInt(width.getText());
        int h = Integer.parseInt(height.getText());
        
        System.out.println(w);
        System.out.println(h);
        worldMap = new WorldMap(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);     // цвет заливки
        gc.setFont(Font.font("Arial", 14));  // шрифт и размер
        // Очистка (важно!)
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        gc.setFill(Color.BLUE);
        int x = 0;
        for (int i = 1; i <= worldMap.getHeight(); i++) {
            for (int j = 1; j <= worldMap.getWidth(); j++) {
//                System.out.println("i - " + i);
//                System.out.println("j - " + j);
                gc.fillOval(3 + j* 6, 3 + i* 6, 5, 5);
// Настройки текста
// Вывод цифры "42" в координатах (x=100, y=150)
//                Integer get = worldMap.getPositions().get(x++);
//                gc.fillText(String.valueOf(get), j * 25, i * 25);  // x=100, y=150 (от левого верхнего угла)
//
//// Flush для немедленного отображения
//                gc.stroke();  // если нужны контуры
            }
        }
    }

    public void initialize() {
        
    }
}
