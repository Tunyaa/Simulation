package simulation.Model.Mover;

import simulation.Model.Entity.Creature;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public interface Mover {
    void move(World world, Creature creature);
}
