package simulation.Model.Entity;

import java.util.List;
import java.util.Map;
import simulation.Model.Action.Action;
import simulation.Model.Action.HerbivoreAction;
import simulation.Model.Eatable;
import simulation.Model.Mover.Mover;
import simulation.Model.Mover.PredatorStraightPathMover;
import simulation.Model.Viewer.SquareViewer;
import simulation.Model.Mover.StraightPathMover;
import simulation.Render.EntityTypePng;
import simulation.World.World;
import simulation.World.WorldField;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature implements Eatable {

    private List<Integer> Path;
    private int targetPosition;

    // Тестовое зрение
    private SquareViewer squareViewer;
    private final Mover mover = new PredatorStraightPathMover();
//    private final Mover mover = new StraightPathMover();
    private final Action action;

    public Herbivore() {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setInititive(5);
        setRangeOfView(4);
        setSpeed(2);
        this.squareViewer = new SquareViewer(this, Grass.class);
        this.action = new HerbivoreAction();

    }

//    @Override
    public void move(World world) {
        mover.move(world, this);
    }

    public void randomMove(World world) {
        mover.randomMove(world, this);
    }

    @Override
    public void eat(Eatable el) {
        if (el instanceof Grass) {
            this.hp += 50;
        }
    }

    public void action(World world) {

        //      Есть путь?               --->                Передвижение
        //          |                                                           ^
        //     Смотреть                                         Добавить в путь
        //          |                                                           ^                   ^
        //      Есть цель?              --->                Поиск пути      |
        //          |                                                                                |
        //      Случайное передвижение                  -----------^
    }

    public void viev(World world) {
        squareViewer.viev(world);

    }

}
