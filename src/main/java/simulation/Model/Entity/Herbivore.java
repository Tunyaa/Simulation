package simulation.Model.Entity;

import java.util.List;
import java.util.Map;
import simulation.Model.Mover.Mover;
import simulation.Model.Viewer.SquareViewer;
import simulation.Model.Mover.StraightPathMover;
import simulation.View.EntityTypePng;
import simulation.World.World;
import simulation.World.WorldField;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature {

    private List<Integer> Path;
    private int targetPosition;

    // Тестовое зрение
    private SquareViewer squareViewer;
    private final Mover mover = new StraightPathMover();

    public Herbivore() {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setInititive(5);
        setRangeOfView(6);
        setSpeed(2);
        this.squareViewer = new SquareViewer(this);

    }

//    @Override
    public void move(World world) {
        mover.move(world, this);
    }

    @Override
    public void action() {
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

        System.out.println("View complete");
        System.out.println("Target is " + getTargetPosition());
//
//        int width = world.getWorldField().getWidth();
//        System.out.println("width - " + width);
//        int worldLen = world.getWorldField().getWorldLen();
//        System.out.println("len - " + worldLen);
//        int scanPosition = position - rangeOfView - (width * rangeOfView);
//        System.out.println("scan - " + scanPosition);
//
//        int positionH = (int) Math.ceil((double) position / width); // Координата по высоте
//        int positionW = width - (positionH * width - position); // Координата по ширине
//
//        for (int w = -rangeOfView; w <= rangeOfView; w++) {
//            System.out.println("w - " + w);
//            for (int i = -rangeOfView; i <= rangeOfView; i++) {
//                System.out.println("i - " + i);
//
//                // Точка которая сейчас проверяется
//                int viewPoint = position + i + (width * w);
//                if (viewPoint < 1 || viewPoint > worldLen) {
//                    System.out.println("Out of Map");
//                    continue;
//                }
//                System.out.println("Position - " + viewPoint);
//                int viewPointH = (int) Math.ceil((double) viewPoint / width);
//                int viewPointW = width - (viewPointH * width - viewPoint);
//
//                // Если просматриаемая точка в облати видимости и не выходит за пределы
//                System.out.println("viewPointW - " + viewPointW + " positionW - " + positionW);
//                System.out.println("viewPointH - " + viewPointH + " positionH - " + positionH);
//                int topLeftExtremePoint = positionW - rangeOfView;
//                int topRightExtremePoint = positionW + rangeOfView;
//                int lowerLeftExtremePoint = positionH - rangeOfView;
//                int lowerRightExtremePoint = positionH + rangeOfView;
//
//                if (viewPointW >= topLeftExtremePoint && viewPointW <= topRightExtremePoint
//                        && viewPointH >= lowerLeftExtremePoint && viewPointH <= lowerRightExtremePoint) {
//                    System.out.println(positionH + " - " + positionW);
//                    System.out.println("IF VIEW");
//                    Map<Integer, List<Entity>> positionEntityMap = world.getPositionEntityMap();
//                    List<Entity> entities = positionEntityMap.get(viewPoint);
//
//                    if (entities != null) {
//                        for (Entity entity : entities) {
//                            if (entity instanceof Grass) {
//
//                                System.out.println("scan Grass" + entity.getPosition());
//                            }
//                            if (entity instanceof Predator) {
//                                System.out.println("scan Predator" + entity.getPosition());
//                            }
//                            if (entity instanceof Three) {
//                                System.out.println("scan Three" + entity.getPosition());
//                            }
//                            if (entity instanceof Stone) {
//                                System.out.println("scan Stone" + entity.getPosition());
//                            }
//                        }
//                    }
//
//                }
//
//            }
//        }

    }

}
