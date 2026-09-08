package simulation.model.entity;

import java.util.ArrayDeque;
import simulation.model.entity.Entity;
import java.util.ArrayList;
import java.util.Random;
import simulation.model.vision.Vision;
import simulation.world.World;

/**
 *
 * @author tunyaa
 */
public abstract class Creature extends Entity {

    protected int rangeOfView;
    protected int hp;
    protected int targetPosition;
    protected ArrayList<Integer> targetPositions = new ArrayList<>();
    protected Random random = new Random();
    protected Vision vision;
    protected Class targetClass;

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

//    abstract public void vision(World world);
    abstract public void pathFinderRandom(World world);

    abstract public void pathFinderToTarget(World world);

    abstract public void actionOnTarget(World world);

    abstract public void turnTax();

    abstract public void reproduce(World world);

    public Vision getVision() {
        return vision;
    }

    public void setVision(Vision vision) {
        this.vision = vision;
    }

    public Class getTargetClass() {
        return targetClass;
    }

}
