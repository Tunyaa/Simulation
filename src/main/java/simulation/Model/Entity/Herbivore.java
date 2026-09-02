package simulation.Model.Entity;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import simulation.Model.Attack.Attackable;
import simulation.Model.Eat.Eatable;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.World.WorldGrid;
import simulation.Model.PathFinder.PathFinder;
import simulation.Model.Viewer.Viewer;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature implements Eatable, Attackable {
    
    private final Viewer viewer;
    private final PathFinder pathFinder;
    
    public Herbivore(PathFinder pathFinder, Viewer viewer) {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setRangeOfView(4);
        this.viewer = viewer;
        this.pathFinder = pathFinder;
    }
    
    public void move(World world) {
        pathFinder.pathFinderToTarget(world, this);
    }
    
    public void randomMove(World world) {
        pathFinder.pathFinderRandom(world, this);
    }
    
    @Override
    public void eat() {
        this.hp += 10;
    }
    
    @Override
    public void takeDamage(int damage) {
        hp -= damage;
    }
    
    @Override
    public void view(World world) {
        viewer.view(world, this, Grass.class);
    }
    
    @Override
    public void pathFinderRandom(World world) {
        pathFinder.pathFinderRandom(world, this);
    }
    
    @Override
    public void pathFinderToTarget(World world) {
        pathFinder.pathFinderToTarget(world, this);
    }
    
    @Override
    public void actionOnTarget(World world) {
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
    
    @Override
    public void turnTax() {
        this.setHp(this.getHp() - 1);
    }
    
    @Override
    public void reproduce(World world) {
        if (this.getHp() > 200) {
            this.setHp(100);
//            world.reproduceEntity(Herbivore.class);
            world.reproduceEntityOnCurrentEntityPosition(Herbivore.class, this);
        }
    }
    
}
