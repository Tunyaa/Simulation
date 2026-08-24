package simulation.Model.Entity;

import java.util.ArrayDeque;
import simulation.Model.Entity.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.Model.Action.Action;
import simulation.Model.Eat.Eatable;
import simulation.Model.Eat.Eater;
import simulation.Model.Mover.Mover;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public abstract class Creature extends Entity // implements
//        Reproduser,
//        Existance,
//        Action,
//        Viewer,
// Eater 
{

    protected int rangeOfView;
    protected int hp;
    protected int speed;
    protected int inititive;
    protected int targetPosition;
    protected int attackDamage;

    protected Random random = new Random();

    // Массив (Путь из индексов)
    protected ArrayDeque<Integer> path = new ArrayDeque<>();

    abstract public void action(World world);

    public void die(World world) {
        System.out.println("die Я умер");
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getInititive() {
        return inititive;
    }

    public void setInititive(int inititive) {
        this.inititive = inititive;
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

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public boolean isAlive() {
        System.out.println("isAlive Моё hp = " + hp);
        return hp > 0;
    }

}
