package simulation.Model.Viewer;

import simulation.Model.Entity.Creature;
import simulation.World.World;
import simulation.World.WorldGrid;

/**
 *
 * @author tunyaa
 */
public interface Viewer {

    void viev(World world, Creature creature, Class<?> target);
}
