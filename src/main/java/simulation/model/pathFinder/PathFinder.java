package simulation.model.pathFinder;

import simulation.model.entity.Creature;
import simulation.world.World;

/**
 *
 * @author tunyaa
 */
public interface PathFinder {
    void pathFinderToTarget(World world, Creature creature);
    void pathFinderRandom(World world, Creature creature);
}
