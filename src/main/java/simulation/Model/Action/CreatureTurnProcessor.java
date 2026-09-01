package simulation.Model.Action;

import java.util.List;
import simulation.Model.Attack.Attackable;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Herbivore;
import simulation.Model.Entity.Predator;
import simulation.World.World;

/**
 *
 * @author oldca
 */
public class CreatureTurnProcessor {

    private void creatureToDefault(Creature creature) {
        creature.setTargetPosition(0);
        creature.getPath().clear();
    }

    private void pathFinder(Creature creature, World world) {
        // Если нет цели
        if (creature.getTargetPosition() == 0) {
            // RandomMove
            creature.pathFinderRandom(world);
        } else {// если есть
            creature.pathFinderToTarget(world);
            if (creature.getTargetPosition() == 0) {
                creature.pathFinderRandom(world);
            }
        }

    }

    private void move(Creature creature, World world) {

        if (!creature.getPath().isEmpty()) {
            // передвигаюсь
            world.moveEntityToPosition(creature, creature.getPath().getFirst());
            creature.getPath().clear();
        }

    }

    public void turnProcess(World world, Creature creature) {
        creatureToDefault(creature); // Сбросить цель и путь
        creature.turnTax();// Минус здоровье за ход

        if (!creature.isAlive()) {// Если умер, то удалить
            creature.die(world);
            return;
        }

        creature.reproduce(world);// размножение 

        creature.view(world);  // Просмотр

        pathFinder(creature, world);// Поиск пути

        move(creature, world); // Перемещение сущности

        creature.actionOnTarget(world); // действие над целью Атаковать\ Съесть 

    }
}
