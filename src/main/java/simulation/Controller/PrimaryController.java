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
import simulation.View.CanvasRenderer;
import simulation.World.World;
import simulation.World.WorldField;

public class PrimaryController {

    private PrimaryService primaryService;

    @FXML// Сцена
    private Canvas canvas;

    // Цикл симуляции
    private Timeline turnTimeline;

    @FXML// Ширина создаваемого поля
    private TextField widthWorldMapField;

    @FXML// Высота создаваемого поля
    private TextField heightWorldMapField;

    @FXML// Слайдер регулировки скорости симуляции
    private Slider speedSimulationSlider;
    // Задержка перед тем как значение со слайдера будет принято
    private PauseTransition pause = new PauseTransition(Duration.millis(300));

    // Флаг. Цикл запущен или нет
    private boolean running;

    @FXML// Прослушивание слайдера. Изменение скорости симуляции.
    public void initialize() {
        // Добавляет слушатель на изменение значения
        speedSimulationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                pause.setOnFinished(e -> {
                    // Если симуляция запущена, она перезапускается после паузы с новым аргументом задержки
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

    @FXML  // Останавливает симуляцию
    private void stopSimulation() {
        running = false;
    }

   
    @FXML // Очищает карту мира
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
