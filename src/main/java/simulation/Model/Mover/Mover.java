package simulation.Model.Mover;

import simulation.Model.Entity.Creature;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public interface Mover {
    void pathFinderToTarget(World world, Creature creature);
    void randomPathFinder(World world, Creature creature);
}
