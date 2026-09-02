package simulation.Controller;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import simulation.Service.PrimaryService;

public class PrimaryController {

    private PrimaryService primaryService;

    @FXML// Сцена
    private Canvas canvas;

    @FXML// Ширина создаваемого поля
    private TextField widthWorldMapField;

    @FXML// Высота создаваемого поля
    private TextField heightWorldMapField;

    @FXML// Слайдер регулировки скорости симуляции
    private Slider speedSimulationSlider;
    // Задержка перед тем как значение со слайдера будет принято
    private PauseTransition pause = new PauseTransition(Duration.millis(300));

    @FXML// Прослушивание слайдера. Изменение скорости симуляции.
    public void initialize() {
        System.out.println("CANVAS Controller init - " + canvas);
        // Добавляет слушатель на изменение значения
        speedSimulationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                pause.setOnFinished(e -> {
                    // Если симуляция запущена, она перезапускается после паузы с новым аргументом задержки
                    primaryService.restartSimulation(speedSimulationSlider);
                });
                pause.playFromStart();
            }
        });
    }

    @FXML// Генерация поля. Заполнение сущностями.
    private void createWorldMap() {
        primaryService.createWorldMap(parseIntCheckOrDeafault(widthWorldMapField.getText()), parseIntCheckOrDeafault(heightWorldMapField.getText()));
    }

    @FXML // Начало симуляции
    private void startSimulation() {
        primaryService.startSimulation(speedSimulationSlider);

    }

    @FXML  // Останавливает симуляцию
    private void stopSimulation() {
        primaryService.stopSimulation();
    }

    @FXML // Очищает карту мира
    private void clearWorldMap() {
        primaryService.clearWorldMap();
    }

    public void setPrimaryService(PrimaryService primaryService) {
        this.primaryService = primaryService;
    }

    // Передаёт Canvas в метод start() класса App для создания рендерера
    public Canvas getCanvas() {
        return canvas;
    }

    // Проверка на преобразование в int и на минимальное значение
    private int parseIntCheckOrDeafault(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            // Минимальное значение для создания поля 7
            parseInt = parseInt < 7 ? 7 : parseInt;
            return parseInt;
        } catch (Exception e) {
            return 0;
        }
    }
}
