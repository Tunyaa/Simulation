package simulation.Simulation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Grass;
import simulation.Model.Entity.Herbivore;
import simulation.Model.Entity.Predator;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Simulation {

//    private int turnCounter;
                            
    // Описывает цикл одного хода
    private void turn(World world) {
//        turnCounter++;

        if (world.getGrassCount() < world.getWorldGrid().getWorldLen() / 8) {
            for (int i = 0; i < 10; i++) {
                world.reproduceEntity(Grass.class);
            }
        }

        List<Predator> predators = new ArrayList<>(world.getPredators());
        for (Predator predator : predators) {
            predator.action(world);
        }

        List<Herbivore> herbivores = new ArrayList<>(world.getHerbivores());
        for (Herbivore herbivore : herbivores) {
            herbivore.action(world);
        }

    }

    public void runCycle(World world) {
        turn(world);
    }

}
