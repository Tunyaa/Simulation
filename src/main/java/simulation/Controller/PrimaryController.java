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

    private float speedSIm;
    private boolean running;

    @FXML// Генерация поля. Заполнение сущностями.
    private void createWorldMap() {
        primaryService.createWorldMap(widthWorldMapField, heightWorldMapField);
        primaryService.render(canvas);
    }

    @FXML // Начало симуляции
    private void startSimulation() {

        running = true;
        sim();
//        System.out.println("START");
//
//        turnTimeline = new Timeline(
//                new KeyFrame(Duration.seconds(0.1), event -> {
//
//                    primaryService.startSimulation();
//                    primaryService.render(canvas);
//                })
//        );
//        turnTimeline.setCycleCount(Timeline.INDEFINITE);
//        turnTimeline.play();
    }

    @FXML
    private void stopSimulation() {
        System.out.println("STOP&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        running = false;
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

    public void sim() {

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    if (running == false) {
                        turnTimeline.stop();
                    }
                    primaryService.startSimulation();
                    primaryService.render(canvas);
                })
        );

        turnTimeline.setCycleCount(Timeline.INDEFINITE);
        turnTimeline.play();

    }

}
