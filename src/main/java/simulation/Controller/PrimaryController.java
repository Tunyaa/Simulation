package simulation.Controller;

import java.io.IOException;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
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

    @FXML
    private Slider speedSimulationSlider;
    private boolean running;

    @FXML
    public void initialize() {
        // Добавляем слушатель на изменение значения
        speedSimulationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                double newSpeed = newValue.doubleValue();
                // Передаём новое значение в ваш класс
//                myService.setSpeed(newSpeed);
                if (running == true) {
                    running = false;
                    startSimulation();
                }

            }
        });
    }

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

//    public void initialize() {
//    }
    public void setPrimaryService(PrimaryService primaryService) {
        this.primaryService = primaryService;
    }

    public void sim() {

        double speed = speedSimulationSlider.getValue();

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(speed), event -> {
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
