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

    public void createWorldMap(int w, int h) {
        if (w > 0 && h > 0) {
            if (world.getWorldGrid().getWorldLen() == 0) {
                world.initWorld(w, h);
                world.spawnEntitiesOnWorldGrid();
                view.setPngSize();
                render();
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

    public void startSimulation() {
        simulation.startSimulation(world);
    }
}
