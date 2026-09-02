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
    
    private final Viewer viewer;
    private final PathFinder pathFinder;
    
    public Predator(PathFinder pathFinder, Viewer viewer) {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setRangeOfView(3);
        this.viewer = viewer;
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
    public void view(World world) {
        
        viewer.view(world, this, Herbivore.class);
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
                // Если это травоядный, то я его ем
                if (entity instanceof Herbivore) {
                    // Атакую 
                    attack((Attackable) entity);
                    // Если убил , то съедаю 
                    if (!((Herbivore) entity).isAlive()) {
                        eat();
                        // удаляю с карты
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
//            world.reproduceEntity(Predator.class);
            world.reproduceEntityOnCurrentEntityPosition(Predator.class, this);
        }
    }
    
}
