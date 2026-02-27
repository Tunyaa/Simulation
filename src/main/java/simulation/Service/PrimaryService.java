package simulation.Service;

import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import simulation.Simulation.Simulation;
import simulation.View.View;
import simulation.World.World;
import simulation.World.WorldField;

/**
 *
 * @author tunyaa
 */
public class PrimaryService {

    private World world;
    private Simulation simulation;
    private View view;

    public PrimaryService(World world, Simulation simulation, View view) {
        this.world = world;
        this.simulation = simulation;
        this.view = view;
    }

    
    public void createWorldMap(TextField widthWorldMapField, TextField heightWorldMapField) {
        int w = Integer.parseInt(widthWorldMapField.getText());
        int h = Integer.parseInt(heightWorldMapField.getText());
        world.createNewMap(w, h);
        world.generateEntitysOnWorldField();
    }

    public void render(Canvas canvas) {
        view.render(world,canvas);
    }

    public void clearWorldMap() {
        world.clearWorldMap();
    }

    public void startSimulation() {
        simulation.startSimulation(world);
    }
}
