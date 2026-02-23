package simulation.View;

import javafx.scene.canvas.Canvas;
import simulation.World.World;
import simulation.World.WorldMap;

/**
 *
 * @author tunyaa
 */
public interface Renderer {

    void render(WorldMap worldMap,Canvas canvas);
}
