package simulation.Model.Entity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import simulation.Model.Attack.Attackable;
import simulation.Model.Attack.Attacker;
import simulation.Model.Eat.Eatable;
import simulation.Model.PathFinder.StraightPathFinder;
import simulation.Model.Viewer.SquareViewer;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.Model.PathFinder.PathFinder;
import simulation.Model.Viewer.Viewer;

/**
 *
 * @author tunyaa
 */
public class Predator extends Creature implements Attacker {

    private Viewer viewer;
    private final PathFinder pathFinder;

    public Predator(PathFinder pathFinder, Viewer viewer) {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setRangeOfView(3);
        this.viewer = viewer;
        this.pathFinder = pathFinder;
    }

    @Override
    public void action(World world) {
        targetPosition = 0;
        getPath().clear();
// Ход хищника 
// Если умер, то удалить
        if (!isAlive()) {
//            world.removeEntity(this);
            die(world);
            return;
        }

        // размножение Сделать метод размножение 1-2 в этой же точке.
        if (getHp() > 380) {
            setHp(90);
//                world.regenertePredator();
            world.reproduceEntity(Predator.class);
        }

        // Просмотр
        viev(world);

        // Если нет цели
        if (getTargetPosition() == 0) {
            // RandomMove
            hp -= 2;
            pathFinder.randomPathFinder(world, this);
        } else {// если есть
            hp -= 2;
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

    @Override
    public void eat() {
        hp += 40;
    }

    @Override
    public void attack(Attackable attackable) {
        int nextInt = random.nextInt(1, hp + 2);
        System.out.println("Бью на - " + nextInt);
        attackable.takeDamage(nextInt);
    }

    public void viev(World world) {

        viewer.view(world, this, Herbivore.class);
    }

}
