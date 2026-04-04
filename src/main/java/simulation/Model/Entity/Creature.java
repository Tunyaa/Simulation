package simulation.Model.Entity;

import simulation.Model.Entity.Entity;
import java.util.ArrayList;
import simulation.Model.Action;
import simulation.Model.Eater;
import simulation.Model.Generation;
import simulation.Model.LifeCycle;

/**
 *
 * @author tunyaa
 */
public abstract class Creature extends Entity implements
        //        Reproduser,
        //        Existance,
        LifeCycle,
        Action,
        Generation,
        //        Viewer,
        //        Mover,
        Eater {

    protected int rangeOfView;
    protected int hp;
    protected int speed;
    protected int inititive;
    protected int targetPosition;

    // Массив (Путь из индексов)
    protected ArrayList<Integer> path = new ArrayList<>();

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

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

}
