package simulation.simulation;

import java.util.ArrayList;
import java.util.List;
import simulation.simulation.turnProcessor.CreatureTurnProcessor;
import simulation.model.entity.Grass;
import simulation.model.entity.Herbivore;
import simulation.model.entity.Predator;
import simulation.world.World;

/**
 *
 * @author tunyaa
 */
public class Simulation {

    //    private int turnCounter;
    private CreatureTurnProcessor turnProcessor = new CreatureTurnProcessor();

    // Описывает цикл одного хода
    private void turn(World world) {

        // Генерация травы
        int treeSpawnQuantity = (world.getWorldGrid().getWorldLen() / 190) + 1;
        if (world.getGrassCount() < world.getWorldGrid().getWorldLen() / 8) {
            for (int i = 0; i < treeSpawnQuantity; i++) {
                world.reproduceEntity(Grass.class);
            }
        }

        // Действия хищников
        List<Predator> predators = new ArrayList<>(world.getPredators());
        for (Predator predator : predators) {
            turnProcessor.turnProcess(world, predator);
        }

        // Действия травоядных
        List<Herbivore> herbivores = new ArrayList<>(world.getHerbivores());
        for (Herbivore herbivore : herbivores) {
            turnProcessor.turnProcess(world, herbivore);
        }

    }

    public void runCycle(World world) {
//        turnCounter++;
        turn(world);
    }

}
