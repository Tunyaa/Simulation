package simulation.Model.PathFinder;

import simulation.Model.Entity.Creature;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public interface PathFinder {
    void pathFinderToTarget(World world, Creature creature);
    void randomPathFinder(World world, Creature creature);
}
