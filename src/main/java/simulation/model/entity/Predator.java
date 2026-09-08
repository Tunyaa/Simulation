package simulation.model.entity;

import java.util.List;
import simulation.model.attack.Attackable;
import simulation.model.attack.Attacker;
import simulation.render.EntityTypePng;
import simulation.world.World;
import simulation.model.pathFinder.PathFinder;
import simulation.model.vision.Vision;

/**
 *
 * @author tunyaa
 */
public class Predator extends Creature implements Attacker {

    private final Vision vision;
    private final PathFinder pathFinder;

    public Predator(PathFinder pathFinder, Vision vision) {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setRangeOfView(3);
        this.vision = vision;
        this.pathFinder = pathFinder;
    }

    @Override
    public void eat() {
        hp += 40;
    }

    @Override
    public void attack(Attackable attackable) {
        int nextInt = random.nextInt(1, hp + 2);
        attackable.takeDamage(nextInt);
    }

    @Override
    public void vision(World world) {

        vision.vision(world, this, Herbivore.class);
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
        // Если хищник на точке с целью
        if (getTargetPosition() == getPosition()) {

            List<Entity> entityList = world.getEntitys()[getPosition()];
            for (Entity entity : entityList) {
                
                if (entity instanceof Herbivore) {
                    
                    attack((Attackable) entity);
                    
                    if (!((Herbivore) entity).isAlive()) {
                        eat();
                        
                        ((Herbivore) entity).die(world);
                        setTargetPosition(0);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void turnTax() {
        this.setHp(this.getHp() - 2);
    }

    @Override
    public void reproduce(World world) {
        if (this.getHp() > 380) {
            this.setHp(90);

            world.reproduceEntityOnCurrentEntityPosition(Predator.class, this);
        }
    }

}
