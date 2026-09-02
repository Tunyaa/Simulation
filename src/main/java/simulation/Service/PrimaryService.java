package simulation.Service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import simulation.Simulation.Simulation;
import simulation.Render.CanvasRenderer;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class PrimaryService {

    private final World world;
    private final Simulation simulation;
    private final CanvasRenderer renderer;

    // Цикл анимации
    private Timeline turnTimeline;

    // Флаг. Цикл запущен или нет
    private boolean running;

    public PrimaryService(World world, Simulation simulation, CanvasRenderer render) {
        this.world = world;
        this.simulation = simulation;
        this.renderer = render;
    }

    // Cоздание мира
    public void createWorldMap(int w, int h) {
        if (w > 0 && h > 0) {// Проверка на чистоту сетки
            if (world.getWorldGrid().getWorldLen() == 0) {
                world.initWorld(w, h);//Инициализация сетки и списка существ. Спавн существ на сетку
                world.spawnEntitiesOnWorldGrid();
                renderer.setPngSize();// Настройка размера png
                renderer.render();// Отображение сетки
            }
        }

    }

    public void clearWorldMap() {
        if (world.getWorldGrid().getWorldLen() != 0) {
            stopSimulation();
            world.clearWorld();
            renderer.render();
        }

    }

    public void startSimulation(Slider speedSimulationSlider) {
        if (world.getHerbivores().size() > 0 && world.getPredators().size() > 0) {
            if (running == false) {
                running = true;
                runTimelineCycle(speedSimulationSlider);
            }
        }

    }

    // Запускает анимацию
    private void runTimelineCycle(Slider speedSimulationSlider) {

        turnTimeline = new Timeline(
                new KeyFrame(Duration.seconds(speedSimulationSlider.getValue()), event -> {
                    // Цикл завершается, если сущности погибли
                    if (world.getHerbivores().size() == 0 || world.getPredators().size() == 0) {
                        turnTimeline.stop();
                        return;
                    }
                    simulation.runCycle(world);
                    renderer.render();
                })
        );

        turnTimeline.setCycleCount(Timeline.INDEFINITE);
        turnTimeline.play();

    }

    // Перезапуск цикла
    public void restartSimulation(Slider speedSimulationSlider) {
        if (isRunning()) {
            turnTimeline.stop();
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
