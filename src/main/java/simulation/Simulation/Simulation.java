package simulation.Simulation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import simulation.Model.Eatable;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Grass;
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

    private int turnCounter;
    private boolean isRunning;

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    // Описывает цикл одного хода
    private void turn(World world) {
        System.out.println("Ход - " + turnCounter++);
        if (turnCounter % 20 == 0) {
            world.regenerte();
        }

        // Ход хищника 
        List<Predator> p = world.getPredtors();
        List<Predator> predtors = new ArrayList<>(p);
        for (Predator predtor : predtors) {
            System.out.println(predtor.getHp() + " Pred HP");
            if (predtor.getHp() <= 0) {
                world.removeEntity(predtor);
                break;
            }
            if (predtor.getHp() > 180) {
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                predtor.setHp(90);
                world.regenertePredator();
            }
            predtor.viev(world);
            System.out.println(" Я " + predtor.getClass().getName() + " - " + predtor.getPosition() + " и у меня цель - " + predtor.getTargetPosition());
            if (predtor.getTargetPosition() == 0) {
                // RandomMove
                predtor.randomMove(world);
            } else {

                predtor.move(world);
            }

            world.moveEntityToPosition(predtor, turnCounter);
            if (predtor.getTargetPosition() == predtor.getPosition()) {
                System.out.println("ATTACK!!!!");
                System.out.println("Eat");
                // To herbivore meth
                int position = predtor.getPosition();
                List<Entity>[] entitys = world.getEntitys();
                List<Entity> entity = entitys[position];
                for (Entity entity1 : entity) {
                    if (entity1 instanceof Herbivore) {
                        predtor.eat((Eatable) entity1);
                        world.removeEntity(entity1);
                        predtor.setTargetPosition(0);
                        System.out.println("Я ПОЕЛ!!!!");
                        break;
                    }
                }
            }
        }

        // Ход Травоядного
        List<Herbivore> h = world.getHerbivores();
        List<Herbivore> herbivores = new ArrayList<>(h);
        for (Herbivore herbivore : herbivores) {

            System.out.println(herbivore.getHp() + " Herbi HP");
            if (herbivore.getHp() > 200) {
                herbivore.setHp(100);
                world.regenerteHerbivore();
            }
//            if (herbivore.getTargetPosition() == 0) {
//                herbivore.viev(world);
//            }
            herbivore.viev(world);// 
            if (herbivore.getTargetPosition() == 0) {
                herbivore.randomMove(world);
            } else {
                herbivore.move(world);
            }

            world.moveEntityToPosition(herbivore, turnCounter);

            if (herbivore.getTargetPosition() == herbivore.getPosition()) {
                System.out.println("Eat");
                // To herbivore meth
                int position = herbivore.getPosition();
                List<Entity>[] entitys = world.getEntitys();
                List<Entity> entity = entitys[position];
                for (Entity entity1 : entity) {
                    if (entity1 instanceof Grass) {
                        herbivore.eat((Eatable) entity1);
                        world.removeEntity(entity1);
                        herbivore.setTargetPosition(0);
                        System.out.println("Я ПОЕЛ!!!!");
                        break;
                    }
                }

            }
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
