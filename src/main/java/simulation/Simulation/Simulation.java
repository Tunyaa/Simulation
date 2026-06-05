package simulation.Simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Herbivore;
import simulation.Model.Mover.StraightPathMover;
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
//        Map<Integer, List<Entity>> positionEntityMap = world.getPositionEntityMap();
        List<Entity>[] entitys = world.getEntitys();
        Entity el = null;
//        Collection<List<Entity>> values = positionEntityMap.values();
        for (int i = 1; i < entitys.length; i++) {
            for (Entity e : entitys[i]) {
                if (e != null) {
                    if (e instanceof Herbivore) {
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        el = e;

                    }
                }
            }
        }
        
        // не корректно(если в одной точке не трава а другая сущность) 
        if (entitys[el.getPosition()].size() > 1) {
            entitys[el.getPosition()].remove(0);
            ((Herbivore)el).setTargetPosition(0);
        }
        if (((Herbivore) el).getTargetPosition() == 0) {
            ((Herbivore) el).viev(world);
            if (((Herbivore) el).getTargetPosition() != 0) {
                System.out.println("Target is - " + ((Herbivore) el).getTargetPosition());
            }

        } else {
            System.out.println("MOVE ON");
            System.out.println("Start poition - " + el.getPosition());
           
            ((Herbivore) el).move(world);
            System.out.println("MOVE OFF");

        }   

//        for (List<Entity> value : values) {
//            for (Entity entity : value) {
//                if (entity instanceof Herbivore) {
//                    System.out.println("Herbivore" + entity.getPosition());
//
//                    ((Herbivore) entity).viev(world);
//                    if (((Herbivore) entity).getTargetPosition() != 0) {
//                        ((Herbivore) entity).move(world);
//                    }
//
//                }
    

////                System.out.println(entity.getEntityTypePng() + "  -  " + entity.getPosition());
//            }
//        }
    }

}
