package simulation.Simulation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import simulation.Model.Entity;
import simulation.Model.Herbivore;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Simulation {
    private TurnProcessor turnProcessor;

    public Simulation() {
        this.turnProcessor = new TurnProcessor();
    }

    public void startSimulation(World world) {
        Map<Integer, List<Entity>> positionEntityMap = world.getPositionEntityMap();
        
        Collection<List<Entity>> values = positionEntityMap.values();
        
        for (List<Entity> value : values) {
            for (Entity entity : value) {
                if (entity instanceof Herbivore) {
                    System.out.println("Herbivore" + entity.getPosition());
                    ((Herbivore) entity).viev(world);
                }
                System.out.println(entity.getEntityTypePng() + " -  " + entity.getPosition());
            }
        }
    }
    
    
}
