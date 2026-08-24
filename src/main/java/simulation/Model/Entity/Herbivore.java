package simulation.Model.Entity;

import java.util.List;
import java.util.Map;
import simulation.Model.Action.Action;
import simulation.Model.Attack.Attackable;
import simulation.Model.Eat.Eatable;
import simulation.Model.Mover.Mover;
import simulation.Model.Mover.PredatorStraightPathFinder;
import simulation.Model.Viewer.SquareViewer;
import simulation.Model.Mover.StraightPathMover;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.World.WorldGrid;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature implements Eatable, Attackable {

    private List<Integer> Path;
    private int targetPosition;

    // Тестовое зрение
    private SquareViewer squareViewer;
    private final Mover mover = new PredatorStraightPathFinder();
//    private final Mover mover = new StraightPathMover();

    public Herbivore() {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setInititive(5);
        setRangeOfView(4);
        setSpeed(2);
        this.squareViewer = new SquareViewer(this, Grass.class);

    }

//    @Override
    public void move(World world) {
        mover.pathFinderToTarget(world, this);
    }

    public void randomMove(World world) {
        mover.randomPathFinder(world, this);
    }

    @Override
    public void eat() {

        this.hp += 50;

    }

    @Override
    public void takeDamage(int damage) {
        System.out.println("Меня задамажили на - " + damage);
        hp -= damage;
        System.out.println("моё HP - " + hp);
    }

    @Override
    public void action(World world) {
        if (!isAlive()) {
//            world.removeEntity(this);
            die(world);
            return;
        }

        if (getHp() > 200) {
            setHp(100);
//                world.regenerteHerbivore();
            world.reproduceEntity(simulation.Model.Entity.Herbivore.class);
        }
//            if (herbivore.getTargetPosition() == 0) {
//                herbivore.viev(world);
//            }
        viev(world);// 
        if (getTargetPosition() == 0) {
            hp -= 1;
            randomMove(world);
        } else {
            hp -= 1;
            move(world);
        }

        world.moveEntityToPosition(this, getPath().getFirst());

        if (getTargetPosition() == getPosition()) {
            // To herbivore meth
            int position = getPosition();
            List<Entity>[] entitys = world.getEntitys();
            List<Entity> entity = entitys[position];
            for (Entity entity1 : entity) {
                if (entity1 instanceof Grass) {
                    eat();
                    world.removeEntity(entity1);
                    setTargetPosition(0);
                    break;
                }
            }
            targetPosition = 0;
        }

    }

    public void viev(World world) {
        squareViewer.viev(world);

    }

}
