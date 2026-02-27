package simulation.Model;

/**
 *
 * @author tunyaa
 */
public abstract class Creature extends Entity implements
        //        Reproduser,
        //        Existance,
        LifeCycle,
        Generation,
        Viewer,
        Mover,
        Eater {

    protected int rangeOfView;
    protected int hp;
    protected int speed;
    protected int inititive;

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

    
    
}
