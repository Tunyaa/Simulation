package simulation.Model.Action;

import simulation.Model.Entity.Creature;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public interface Action {

    void action(World world, Creature creature);
}
