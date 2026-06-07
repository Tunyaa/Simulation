package simulation.Controller;

import java.io.IOException;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import simulation.App;
import simulation.App;
import simulation.Service.PrimaryService;
import simulation.Simulation.Simulation;
import simulation.View.EntityTypePng;
import simulation.View.View;
import simulation.World.World;
import simulation.World.WorldField;

public class PrimaryController {

    private PrimaryService primaryService;

    @FXML
    private Canvas canvas;
    private Timeline turnTimeline;
    @FXML
    private TextField widthWorldMapField;
    @FXML
    private TextField heightWorldMapField;

//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
    @FXML// Генерация поля. Заполнение сущностями.
    private void createWorldMap() {
        primaryService.createWorldMap(widthWorldMapField, heightWorldMapField);
        primaryService.render(canvas);
    }

    @FXML // Начало симуляции
    private void startSimulation() {
        System.out.println("START");

//        primaryService.startSimulation();
//        primaryService.render(canvas);

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), event -> {

                    primaryService.startSimulation();
                    primaryService.render(canvas);
                })
        );
        turnTimeline.setCycleCount(Timeline.INDEFINITE);
        turnTimeline.play();
    }
//    @FXML // Начало симуляции
//    private void startSimulation() {
//        System.out.println("START");
//        primaryService.startSimulation();
//        primaryService.render(canvas);
//    }

    @FXML
    private void stopSimulation() {
    }

    @FXML
    private void clearWorldMap() {
        primaryService.clearWorldMap();
        primaryService.render(canvas);
    }

    public void initialize() {
    }

    public void setPrimaryService(PrimaryService primaryService) {
        this.primaryService = primaryService;
    }

}
