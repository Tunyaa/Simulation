package simulation.model.entity;

import java.util.List;
import simulation.model.attack.Attackable;
import simulation.model.eat.Eatable;
import simulation.render.EntityTypePng;
import simulation.world.World;
import simulation.model.pathFinder.PathFinder;
import simulation.model.vision.Vision;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature implements Eatable, Attackable {
    
    private final Vision vision;
    private final PathFinder pathFinder;
    
    public Herbivore(PathFinder pathFinder, Vision vision) {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setRangeOfView(4);
        this.vision = vision;
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
    public void vision(World world) {
        vision.vision(world, this, Grass.class);
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

            world.reproduceEntityOnCurrentEntityPosition(Herbivore.class, this);
        }
    }
    
}
