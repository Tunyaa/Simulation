package simulation.Model.Entity;

import simulation.Model.Action.HerbivoreAction;
import simulation.Model.Mover.Mover;
import simulation.Model.Mover.StraightPathMover;
import simulation.Model.Viewer.SquareViewer;
import simulation.View.EntityTypePng;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Predator extends Creature {

    private SquareViewer squareViewer;

    private final Mover mover = new StraightPathMover();

    public Predator() {
        setEntityTypePng(EntityTypePng.PREDATOR);
        setHp(100);
        setInititive(5);
        setRangeOfView(3);
        setSpeed(2);
        this.squareViewer = new SquareViewer(this);
//        this.action = new HerbivoreAction();
    }

    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void viev(World world) {

        squareViewer.viev(world);
    }

}
