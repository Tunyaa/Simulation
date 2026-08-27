package simulation.Model.Entity;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import simulation.Model.Action.Action;
import simulation.Model.Attack.Attackable;
import simulation.Model.Eat.Eatable;
import simulation.Model.PathFinder.PredatorStraightPathFinder;
import simulation.Model.Viewer.SquareViewer;
import simulation.Model.PathFinder.StraightPathMover;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.World.WorldGrid;
import simulation.Model.PathFinder.PathFinder;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature implements Eatable, Attackable {

    private List<Integer> Path;
//    private int targetPosition;

    // Тестовое зрение
    private SquareViewer squareViewer;
    private final PathFinder mover = new PredatorStraightPathFinder();
//    private final PathFinder mover = new StraightPathMover();

    public Herbivore() {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setRangeOfView(4);
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

        this.hp += 10;

    }

    @Override
    public void takeDamage(int damage) {
        System.out.println("Меня задамажили на - " + damage);
        hp -= damage;
        System.out.println("моё HP - " + hp);
    }

    @Override
    public void action(World world) {
        targetPosition = 0;
        getPath().clear();
        if (!isAlive()) {
//            world.removeEntity(this);
            die(world);
            return;
        }

        if (getHp() > 300) {
            setHp(100);
//                world.regenerteHerbivore();
            world.reproduceEntity(simulation.Model.Entity.Herbivore.class);
        }

//            if (herbivore.getTargetPosition() == 0) {
//                herbivore.viev(world);
//            }
        viev(world);// 
        
//        if (getTargetPosition() == 0) {
//            hp -= 1;
//            randomMove(world);
//        } else {
//            hp -= 1;
//            move(world);
        int i = 0;
        // Если нет цели
        if (getTargetPosition() == 0) {
            i += 1;
            // RandomMove
            hp -= 1;
            mover.randomPathFinder(world, this);
        } else {// если есть
            i += 2;
            hp -= 1;
            mover.pathFinderToTarget(world, this);
            if (getTargetPosition() == 0) {
                i += 3;
                mover.randomPathFinder(world, this);
            }
        }

//        String s = "S";
//        ArrayDeque<Integer> path1 = getPath();
//        if (position == path1.getFirst()) {
//            
//            System.out.println("FFFFFFFFFFFFFFFFFFFFF ------- " + i);
//        }
        if (!getPath().isEmpty()) {
            // передвигаюсь
//            s = "G";
            world.moveEntityToPosition(this, getPath().getFirst());
        }
//        if (s.equals("S")) {
//            System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT ------- " + i);
//        }
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

    public void action1(World world) {
        targetPosition = 0;
        getPath().clear();

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
        viev(world);// 

        // Если нет цели
        if (getTargetPosition() == 0) {
            // RandomMove
            hp -= 1;
            mover.randomPathFinder(world, this);
        } else {// если есть
            hp -= 1;
            mover.pathFinderToTarget(world, this);
            if (getTargetPosition() == 0) {

                mover.randomPathFinder(world, this);
            }
        }

        if (!getPath().isEmpty()) {
            // передвигаюсь
            world.moveEntityToPosition(this, getPath().getFirst());
        }

        if (getTargetPosition() == getPosition()) {
            // To herbivore meth
            List<Entity> entityList = world.getEntitys()[getPosition()];
            for (Entity entity : entityList) {
                if (entity instanceof Grass) {
                    eat();
                    world.removeEntity(entity);
                    setTargetPosition(0);
                    break;
                }
            }
        }

    }

    public void viev(World world) {
        squareViewer.viev(world);

    }

}
