package simulation.Model.Entity;

import java.util.ArrayDeque;
import simulation.Model.Entity.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.Model.Action.Action;
import simulation.Model.Eat.Eatable;
import simulation.Model.Eat.Eater;
import simulation.World.World;
import simulation.Model.PathFinder.PathFinder;
import simulation.Model.Viewer.Viewer;

/**
 *
 * @author tunyaa
 */
public abstract class Creature extends Entity//  implements
//        Reproduser,
//        Existance,
//        Action,
// Viewer // Eater 
{

    protected int rangeOfView;
    protected int hp;
    protected int targetPosition;
    protected ArrayList<Integer> targetPositions = new ArrayList<>();
//    protected Viewer viewer;
    protected Random random = new Random();

    // Массив (Путь из индексов)
    protected ArrayDeque<Integer> path = new ArrayDeque<>();

//    abstract public void action(World world);

    public void die(World world) {
        world.removeEntity(this);
    }

    abstract public void eat();

    public boolean isTarget(int targetPosition) {
        return this.targetPosition == targetPosition;
    }

    public int getRangeOfView() {
        return rangeOfView;
    }

    public void setRangeOfView(int rangeOfView) {
        this.rangeOfView = rangeOfView;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    public ArrayDeque<Integer> getPath() {
        return path;
    }

    public void setPath(ArrayDeque<Integer> path) {
        this.path = path;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public ArrayList<Integer> getTargetPositions() {
        return targetPositions;
    }

    public void setTargetPositions(ArrayList<Integer> targetPositions) {
        this.targetPositions = targetPositions;
    }

    abstract public void view(World world);
    abstract public void pathFinderRandom(World world);
    abstract public void pathFinderToTarget(World world);
    abstract public void actionOnTarget(World world);
    abstract public void turnTax();
    abstract public void reproduce(World world);
    
}
