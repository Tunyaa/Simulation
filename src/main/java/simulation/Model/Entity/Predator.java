package simulation.Model.Entity;

import simulation.Model.Action.HerbivoreAction;
import simulation.Model.Eatable;
import simulation.Model.Mover.Mover;
import simulation.Model.Mover.PredatorStraightPathMover;
import simulation.Model.Mover.StraightPathMover;
import simulation.Model.Viewer.SquareViewer;
import simulation.Render.EntityTypePng;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Predator extends Creature {

    private SquareViewer squareViewer;

    private final Mover mover = new PredatorStraightPathMover();

    public Predator() {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setInititive(5);
        setRangeOfView(5);
        setSpeed(2);
        this.squareViewer = new SquareViewer(this, Herbivore.class);
//        this.action = new HerbivoreAction();
    }

    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eat(Eatable el) {
        hp += 20;
    }

    public void viev(World world) {

        squareViewer.viev(world);
    }

    public void move(World world) {
        hp -= 1;
        mover.move(world, this);
    }

    public void randomMove(World world) {
        hp -= 1;
        mover.randomMove(world, this);
    }

}
