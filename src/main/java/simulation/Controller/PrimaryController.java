package simulation.Controller;

import java.io.IOException;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
    private PauseTransition pause = new PauseTransition(Duration.millis(300));

    // Прослушивание слайдера. Изменение скорости симуляции.
    @FXML
    public void initialize() {
        // Добавляем слушатель на изменение значения
        speedSimulationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                pause.setOnFinished(e -> {
                    // Если симуляция запущена, она перезапускается с новым аргументом задержки
                    if (running == true) {
                        turnTimeline.stop();
                        running = false;
                        startSimulation();
                    }
                });
                pause.playFromStart();
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
        if (running == false) {
            running = true;
            sim();
        }
    }

    // Останавливает симуляцию
    @FXML
    private void stopSimulation() {
        running = false;
    }

    // Очищает карту мира
    @FXML
    private void clearWorldMap() {
        stopSimulation();
        primaryService.clearWorldMap();
        primaryService.render(canvas);
    }

    public void setPrimaryService(PrimaryService primaryService) {
        this.primaryService = primaryService;
    }

    // Запускает цикл симуляции.
    public void sim() {

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(speedSimulationSlider.getValue()), event -> {
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
