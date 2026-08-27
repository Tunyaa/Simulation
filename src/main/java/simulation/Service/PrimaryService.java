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
    private CanvasRenderer render;

    // Цикл анимации
    private Timeline turnTimeline;

    // Флаг. Цикл запущен или нет
    private boolean running;

    public PrimaryService(World world, Simulation simulation, CanvasRenderer render) {
        this.world = world;
        this.simulation = simulation;
        this.render = render;
    }

    // Cоздание мира
    public void createWorldMap(int w, int h) {
        if (w > 0 && h > 0) {// Проверка на чистоту сетки
            if (world.getWorldGrid().getWorldLen() == 0) {
                world.initWorld(w, h);//Инициализация сетки и списка существ. Спавн существ на сетку
                world.spawnEntitiesOnWorldGrid();
                render.setPngSize();// Настройка размера png
                render();// Отображение сетки
            }
        }

    }

    // TODO Убрать
    public void render() {
        // TODO перенести
        render.render();
    }

    public void clearWorldMap() {
        if (world.getWorldGrid().getWorldLen() != 0) {
            stopSimulation();
            world.clearWorld();
            render();
        }

    }

    // ПРОВЕРКУ ЕСЛИ МИР ОЧИЩЕН/ Включается симуляция с пустым миром
    public void startSimulation(Slider speedSimulationSlider) {
        if (world.getHerbivores().size() > 0 && world.getPredators().size() > 0) {
            if (running == false) {// TODO Перенести runTimelineCycle  в сервис> simul
                running = true;
                runTimelineCycle(speedSimulationSlider);
            }
        }

    }

    private void runSimulationCycle() {
        simulation.runCycle(world);
    }

    // Запускает анимацию
    private void runTimelineCycle(Slider speedSimulationSlider) {

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(speedSimulationSlider.getValue()), event -> {
                    // Цикл завершается, если все сущности погибли
                    if (world.getHerbivores().size() + world.getPredators().size() == 0) {
                        turnTimeline.stop();
                    }
                    runSimulationCycle();
                    render();
                })
        );

        turnTimeline.setCycleCount(Timeline.INDEFINITE);
        turnTimeline.play();

    }

    public void restartSimulation(Slider speedSimulationSlider) {
        if (isRunning()) {// TODO
            turnTimeline.stop();
//            stopSimulation(); УДАЛИТЬ
//            startSimulation(speedSimulationSlider);
            runTimelineCycle(speedSimulationSlider);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void stopSimulation() {
        if (isRunning()) {
            setRunning(false);
            turnTimeline.stop();
        }

    }

}
