package simulation.Model.Entity;

import java.util.ArrayList;
import java.util.List;
import simulation.Model.Attack.Attackable;
import simulation.Model.Attack.Attacker;
import simulation.Model.Eat.Eatable;
import simulation.Model.Mover.Mover;
import simulation.Model.Mover.PredatorStraightPathFinder;
import simulation.Model.Mover.StraightPathMover;
import simulation.Model.Viewer.SquareViewer;
import simulation.Render.EntityTypePng;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Predator extends Creature implements Attacker {

    private SquareViewer squareViewer;

    private final Mover mover;
//    private final Attacker attacker;

    public Predator() {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setInititive(5);
        setRangeOfView(5);
        setSpeed(2);
        this.squareViewer = new SquareViewer(this, Herbivore.class);
        this.mover = new PredatorStraightPathFinder();
//        this.attacker = 
//        this.action = new HerbivoreAction();
    }

    @Override
    public void action(World world) {
// Ход хищника 
// Если умер, то удалить
        if (!isAlive()) {
            world.removeEntity(this);
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

            hp -= 7;
            mover.pathFinderToTarget(world, this);
        }

        // передвигаюсь
        world.moveEntityToPosition(this, getPath().getFirst());
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
                        eat((Eatable) entity);
                        // удаляю с карты
                        world.removeEntity(entity);

                        setTargetPosition(0);

                    }

                    break;
                }
            }
        }
    }

    @Override
    public void eat(Eatable el) {
        hp += 20;
    }

    @Override
    public void attack(Attackable attackable) {
        attackable.takeDamage(10);
    }

    public void viev(World world) {

        squareViewer.viev(world);
    }

}
