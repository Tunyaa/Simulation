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
public class CratureAction {

    private void turnTax(Creature creature) {
        creature.setHp(creature.getHp() - 2);
    }

    private void creatureToDefault(Creature creature) {
        creature.setTargetPosition(0);
        creature.getPath().clear();
    }

    private void reproduce(Creature creature, World world) {// TODO перенести в creature
        if (creature.getHp() > 380) {
            creature.setHp(90);
            world.reproduceEntity(Predator.class);
        }
    }

    public void action(World world, Creature creature) {
        creatureToDefault(creature); // Сбросить цель и путь
        turnTax(creature);// Минус здоровье за ход

        if (!creature.isAlive()) {// Если умер, то удалить
            creature.die(world);
            return;
        }

        reproduce(creature, world);// размножение 
        // Просмотр
        creature.viev(world);

        // Если нет цели
        if (getTargetPosition() == 0) {
            // RandomMove

            pathFinder.randomPathFinder(world, this);
        } else {// если есть

            pathFinder.pathFinderToTarget(world, this);
            if (getTargetPosition() == 0) {

                pathFinder.randomPathFinder(world, this);
            }
        }

        if (!getPath().isEmpty()) {
            // передвигаюсь
            world.moveEntityToPosition(this, getPath().getFirst());
            getPath().clear();
        }

        // Если хищник на точке с целью
        if (getTargetPosition() == getPosition()) {

            List<Entity> entityList = world.getEntitys()[getPosition()];
            for (Entity entity : entityList) {
                // Если это травоядный, то я его ем
                if (entity instanceof Herbivore) {
                    // Атакую 
                    attack((Attackable) entity);
                    // Если убил , то съедаю 
                    if (!((Herbivore) entity).isAlive()) {
                        eat();
                        // удаляю с карты
                        ((Herbivore) entity).die(world);
//                        world.removeEntity(entity);

                        setTargetPosition(0);

                    }

                    break;
                }
            }
        }

    }
}
