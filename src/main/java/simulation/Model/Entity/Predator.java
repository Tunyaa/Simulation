package simulation.Model.Entity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import simulation.Model.Attack.Attackable;
import simulation.Model.Attack.Attacker;
import simulation.Model.Eat.Eatable;
import simulation.Model.Mover.PredatorStraightPathFinder;
import simulation.Model.Mover.StraightPathMover;
import simulation.Model.Viewer.SquareViewer;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.Model.Mover.PathFinder;

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
// Ход хищника 
// Если умер, то удалить
        if (!isAlive()) {
//            world.removeEntity(this);
            die(world);
            return;
        }

        // размножение Сделать метод размножение 1-2 в этой же точке.
        if (getHp() > 180) {
            setHp(90);
//                world.regenertePredator();
            world.reproduceEntity(Predator.class);
        }

        // Просмотр
        viev(world);
        
        // Если нет цели
        if (getTargetPosition() == 0) {
            // RandomMove
            hp -= 1;
            mover.randomPathFinder(world, this);
        } else {// если есть
            hp -= 1;
            mover.pathFinderToTarget(world, this);
              if (getTargetPosition() == 0){
                  
                  mover.randomPathFinder(world, this);
              }
        }

        if (!getPath().isEmpty()) {
            // передвигаюсь
            world.moveEntityToPosition(this, getPath().getFirst());
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
                targetPosition = 0;
            }
        }

    }

    @Override
    public void eat() {
        hp += 40;
    }

    @Override
    public void attack(Attackable attackable) {
        int nextInt = random.nextInt(hp);
        System.out.println("Бью на - " + nextInt);
        attackable.takeDamage(nextInt);
    }

    public void viev(World world) {

        squareViewer.viev(world);
    }

}
