package simulation.Model.Entity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import simulation.Model.Attack.Attackable;
import simulation.Model.Attack.Attacker;
import simulation.Model.Eat.Eatable;
import simulation.Model.PathFinder.PredatorStraightPathFinder;
import simulation.Model.Viewer.SquareViewer;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.Model.PathFinder.PathFinder;

/**
 *
 * @author tunyaa
 */
public class Predator extends Creature implements Attacker {

    private SquareViewer squareViewer;

    private final PathFinder mover;
    private int atkDmg;

    public Predator() {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setRangeOfView(3);
        this.squareViewer = new SquareViewer(this, Herbivore.class);// TODO
        this.mover = new PredatorStraightPathFinder();// TODO вынести один объект для всех сущностей Di
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
//        if (getPath().isEmpty()) {
//            System.out.println("Путь пустой");
//        }
//        ArrayList<Integer> targetPositions1 = getTargetPositions();
//        if (!targetPositions1.isEmpty()) {
//            for (Integer integer : targetPositions1) {
//                System.out.println("Цель  - " + integer);
//
//                setTargetPosition(integer);
//                mover.pathFinderToTarget(world, this);
//                if (!getPath().isEmpty()) {
//                    System.out.println(" Есть путь");
//                    break;
//                }
//            }
//            setTargetPosition(0);
//        }
//
//        if (getPath().isEmpty()) {
//            for (int i = 0; i < 3; i++) {
//                mover.randomPathFinder(world, this);
//                if (!getPath().isEmpty()) {
//                    System.out.println("рандомный путь найден");
//                    break;
//                }
//            }
//        }

        // Если нет цели
        if (getTargetPosition() == 0) {
            // RandomMove
            hp -= 2;
            mover.randomPathFinder(world, this);
        } else {// если есть
            hp -= 2;
            mover.pathFinderToTarget(world, this);
            if (getTargetPosition() == 0) {

                mover.randomPathFinder(world, this);
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

        squareViewer.viev(world);
    }

}
