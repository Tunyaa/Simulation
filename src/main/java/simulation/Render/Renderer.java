package simulation.Render;

import javafx.scene.canvas.Canvas;
import simulation.World.World;
import simulation.World.WorldField;

/**
 *
 * @author tunyaa
 */
public interface Renderer {

    void render(World world,Canvas canvas);
}
