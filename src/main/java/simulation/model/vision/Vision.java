package simulation.model.vision;

import simulation.model.entity.Creature;
import simulation.world.World;

/**
 *
 * @author tunyaa
 */
public interface Vision {

    void view(World world, Creature creature, Class<?> target);
}
