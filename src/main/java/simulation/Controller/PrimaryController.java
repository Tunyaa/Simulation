package simulation.Controller;

import java.io.IOException;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simulation.App;
import simulation.App;
import simulation.Service.PrimaryService;
import simulation.Simulation.Simulation;
import simulation.View.EntityTypePng;
import simulation.View.View;
import simulation.World.World;
import simulation.World.WorldMap;

public class PrimaryController {

    private PrimaryService primaryService;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField widthWorldMapField;
    @FXML
    private TextField heightWorldMapField;

//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
    @FXML
    private void createWorldMap() {
        primaryService.createWorldMap(widthWorldMapField, heightWorldMapField);
        primaryService.render(canvas);
    }

//    @FXML
//    private void toStart() {
//        System.out.println("toStart");
//
    ////        Image predatorImage = new Image(getClass().getResourceAsStream("/images/predator.png"));
//        Image p = new Image(getClass().getResourceAsStream(EntityTypePng.PREDATOR.getDisplayName()));
//        Image pp = new Image(getClass().getResourceAsStream(EntityTypePng.HERBIVORE.getDisplayName()));
//        Image g = new Image(getClass().getResourceAsStream(EntityTypePng.GRASS.getDisplayName()));
//        Image s = new Image(getClass().getResourceAsStream(EntityTypePng.STONE.getDisplayName()));
//        Image t = new Image(getClass().getResourceAsStream(EntityTypePng.THREE.getDisplayName()));
//
//        System.out.println(" w - " + widthWorldMapField.getText());
//        System.out.println(" h - " + heightWorldMapField);
//        int w = Integer.parseInt(widthWorldMapField.getText());
//        int h = Integer.parseInt(heightWorldMapField.getText());
//
//        System.out.println(w);
//        System.out.println(h);
//        worldMap = new WorldMap(w, h);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(Color.BLACK);     // цвет заливки
//        gc.setFont(Font.font("Arial", 14));  // шрифт и размер
//        // Очистка (важно!)
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
////        gc.setFill(Color.BLUE);
//        int x = 0;
//        int r = 20;
//
//        Random random = new Random();
//        for (int i = 1; i <= worldMap.getHeight(); i++) {
//            for (int j = 1; j <= worldMap.getWidth(); j++) {
////                System.out.println("i - " + i);
////                System.out.println("j - " + j);
//                int nextInt = random.nextInt(47);
//                if (nextInt == 0) {
//                    gc.drawImage(s, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 1) {
//                    gc.drawImage(p, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 2) {
//                    gc.drawImage(pp, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 3) {
//                    gc.drawImage(t, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 4) {
//                    gc.drawImage(g, j * (r + 1), i * (r + 1), r, r);
//                }
//
////                gc.fillOval(3 + j * 6, 3 + i * 6, 5, 5);
//// Настройки текста
//// Вывод цифры "42" в координатах (x=100, y=150)
////                Integer get = worldMap.getPositions().get(x++);
////                gc.fillText(String.valueOf(get), j * 25, i * 25);  // x=100, y=150 (от левого верхнего угла)
////
//            
//        
//    
//
//    //// Flush для немедленного отображения
////                gc.stroke();  // если нужны контуры
//            }
//        }
//    }

    public void initialize() {
    }

    public void setPrimaryService(PrimaryService primaryService) {
        this.primaryService = primaryService;
    }

}
