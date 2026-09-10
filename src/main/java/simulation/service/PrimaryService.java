package simulation.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import simulation.simulation.Simulation;
import simulation.render.CanvasRenderer;
import simulation.world.World;

/**
 *
 * @author tunyaa
 */
public class PrimaryService {

    private final World world;
    private final Simulation simulation;
    private final CanvasRenderer renderer;

    PieChart pieChart;

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
                    updatePieChart();
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

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;

    }

    public void updatePieChart() {
        // Считаем количество каждого типа существ
        int herbivoreCount = this.world.getHerbivores().size();
        int predatorCount = world.getPredators().size();
        int grassCount = world.getGrassCount();

        // Создаём список данных
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Травоядные - " + herbivoreCount, herbivoreCount),
                new PieChart.Data("Хищники - " + predatorCount, predatorCount),
                new PieChart.Data("Трава - " + grassCount, grassCount)
        );

        // Устанавливаем данные в диаграмму
        pieChart.setData(pieChartData);
    }

}
