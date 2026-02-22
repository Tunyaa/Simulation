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

}
