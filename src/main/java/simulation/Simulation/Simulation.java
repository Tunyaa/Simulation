package simulation.Simulation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Herbivore;
import simulation.Model.Entity.Predator;
import simulation.Model.Mover.StraightPathMover;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Simulation {

    private TurnProcessor turnProcessor;
    private int turnCounter;
    private boolean isRunning;

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public Simulation() {
        this.turnProcessor = new TurnProcessor();
    }

    private void turn(World world) {
        List<Predator> predtors = world.getPredtors();
        for (Predator predtor : predtors) {
            
                predtor.viev(world);
                System.out.println(" Я " + predtor.getClass().getName() + " - " + predtor.getPosition() + " и у меня цель - " + predtor.getTargetPosition());
                if (predtor.getTargetPosition() == 0) {
                    // RandomMove
                    predtor.randomMove(world);
                }else{
                    
                predtor.move(world);
                }
                //-
            ArrayDeque<Integer> path = predtor.getPath();
            
            System.out.println("Мой путь");
            for (Integer integer : path) {
                System.out.println(integer);
            }
            //-
            
            world.moveEntityToPosition(predtor, turnCounter);
        }
    }

    public void startSimulation(World world) {
        turn(world);
    }
//    
//    public void startSimulation(World world) {

////        Map<Integer, List<Entity>> positionEntityMap = world.getPositionEntityMap();
//        List<Entity>[] entitys = world.getEntitys();
//        Entity el = null;
////        Collection<List<Entity>> values = positionEntityMap.values();
//        for (int i = 1; i < entitys.length; i++) {
//            for (Entity e : entitys[i]) {
//                if (e != null) {
//                    if (e instanceof Herbivore) {
//                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//                        el = e;
//
//                    }
//                }
//            }
//        }
//        
//        // не корректно(если в одной точке не трава а другая сущность) 
//        if (entitys[el.getPosition()].size() > 1) {
//            entitys[el.getPosition()].remove(0);
//            ((Herbivore)el).setTargetPosition(0);
//        }
//        if (((Herbivore) el).getTargetPosition() == 0) {
//            ((Herbivore) el).viev(world);
//            if (((Herbivore) el).getTargetPosition() != 0) {
//                System.out.println("Target is - " + ((Herbivore) el).getTargetPosition());
//            }
//
//        } else {
//            System.out.println("MOVE ON");
//            System.out.println("Start poition - " + el.getPosition());
//           
//            ((Herbivore) el).move(world);
//            System.out.println("MOVE OFF");
//
//        }   
//
//
//    }

}
