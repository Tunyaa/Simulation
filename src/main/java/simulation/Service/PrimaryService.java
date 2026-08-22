package simulation.Service;

import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
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

    public PrimaryService(World world, Simulation simulation, CanvasRenderer view) {
        this.world = world;
        this.simulation = simulation;
        this.view = view;
    }

    public void createWorldMap(TextField widthWorldMapField, TextField heightWorldMapField) {
        int w = Integer.parseInt(widthWorldMapField.getText());
        int h = Integer.parseInt(heightWorldMapField.getText());
        // Проверка на минимальный параметр
        w = w < 7 ? 7 : w;
        h = h < 7 ? 7 : h;
        if (world.getWorldGrid().getWorldLen() == 0) {
            world.initWorld(w, h);
            world.spawnEntitiesOnWorldGrid();
        }

    }

    public void render() {
        view.setPngSize();
        view.render();
    }

    public void clearWorldMap() {
        world.clearWorld();
    }

    public void startSimulation() {
        simulation.startSimulation(world);
    }
}
