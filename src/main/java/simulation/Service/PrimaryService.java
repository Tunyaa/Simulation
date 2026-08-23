package simulation.Service;

import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import simulation.Simulation.Simulation;
import simulation.Render.CanvasRenderer;
import simulation.World.World;
import simulation.World.WorldGrid;

/**
 *
 * @author tunyaa
 */
public class PrimaryService {

    private World world;
    private Simulation simulation;
    private CanvasRenderer view;// TODO

    // Цикл симуляции
    private Timeline turnTimeline;

    // Флаг. Цикл запущен или нет
    private boolean running;

    public PrimaryService(World world, Simulation simulation, CanvasRenderer view) {
        this.world = world;
        this.simulation = simulation;
        this.view = view;
    }

    // Cоздание мира
    public void createWorldMap(int w, int h) {
        if (w > 0 && h > 0) {// Проверка на чистоту сетки
            if (world.getWorldGrid().getWorldLen() == 0) {
                world.initWorld(w, h);//Инициализация сетки и списка существ. Спавн существ на сетку
                world.spawnEntitiesOnWorldGrid();
                view.setPngSize();// Настройка размера png
                render();// Отображение сетки
            }
        }

    }

    // TODO Убрать
    public void render() {
        // TODO перенести
        view.render();
    }

    public void clearWorldMap() {
        world.clearWorld();
    }

    public void startSimulation(Slider speedSimulationSlider) {
        if (running == false) {// TODO Перенести sim  в сервис> simul
            running = true;
            sim(speedSimulationSlider);
        }

    }

    public void simulation() {
        simulation.startSimulation(world);
    }

    // Запускает цикл симуляции.
    public void sim(Slider speedSimulationSlider) {

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(speedSimulationSlider.getValue()), event -> {
                    if (running == false) {
                        turnTimeline.stop();
                    }

                    simulation();// TODO rename
                    render();
                })
        );

        turnTimeline.setCycleCount(Timeline.INDEFINITE);
        turnTimeline.play();

    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
