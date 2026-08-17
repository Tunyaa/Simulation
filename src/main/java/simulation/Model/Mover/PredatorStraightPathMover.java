package simulation.Model.Mover;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import simulation.Model.Entity.Creature;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class PredatorStraightPathMover implements Mover {

    // Массив (возможные точки) промежуточный массив
    ArrayList<Integer> tempIndixes = new ArrayList<>();

    @Override
    public void move(World world, Creature creature) {
        pathFinder(world, creature);
    }

    @Override
    public void randomMove(World world, Creature creature) {

        creature.getPath().clear();
//        System.out.println("RAndom MOVE");
        int row = ThreadLocalRandom.current().nextInt(-1, 2);
        int col = ThreadLocalRandom.current().nextInt(-1, 2);

        RowColumn rowColumnByPosition = world.getWorldGrid().getRowColumnByPosition(creature.getPosition());
//        System.out.println(rowColumnByPosition.getRow() + " & " + rowColumnByPosition.getCol());
        col = rowColumnByPosition.getCol() + col;
        row = rowColumnByPosition.getRow() + row;
        col = col >= 1 ? col : 1;
        col = col <= world.getWorldGrid().getWidth() ? col : world.getWorldGrid().getWidth();

        row = row >= 1 ? row : 1;
        row = row <= world.getWorldGrid().getHeight() ? row : world.getWorldGrid().getHeight();

//        System.out.println(row + " & " + col);
        int r = world.getWorldGrid().getPositionByRowСolumn(3, 3);
//        System.out.println("Проверка позиции 3-3 " + r);
        int positionByRowСolumn = world.getWorldGrid().getPositionByRowСolumn(row, col);
//        System.out.println(positionByRowСolumn + " рандомная цель");
        ArrayDeque<Integer> path = creature.getPath();
        path.addFirst(positionByRowСolumn);
    }

    private void pathFinder(World world, Creature creature) {
        creature.getPath().clear();
//        System.out.println("Move Else");
        // Поиск пути
        // Текущая позиция
        int position = creature.getPosition();

        // Целевая точка
        // ОБРАТОБАТЬ ЕСЛИ ТОЧКИ ЦЕЛЕВОЙ НЕТ!!!!
        int targetPosition = creature.getTargetPosition();

        // Добавляем целевую точку в промежуточный путь
        tempIndixes.add(targetPosition);

        // Проверка: массив пустой?
        // ПРОВЕРИТЬ ОКОНЧАНИЕ МЕТОДЕ ЧЕРЕЗ УСЛОВИЕ
        while (!tempIndixes.isEmpty()) {

            // 1: Проверка: Точка рядом?
            if (world.getWorldGrid().isLocatedNearby(position, targetPosition)) {

                // 2.2 Записать точку в массив
                creature.getPath().add(targetPosition);
//                    path.add(targetPosition);
                // Удалить эту точку из промежуточного массива
                tempIndixes.remove(tempIndixes.size() - 1);
                // Проверка: Эта точка целевая?
                // SET targetPosition

                if (creature.isTarget(targetPosition)) {
                    // Дошли до целевой точки.

//                        creature.setPath(path.toArray());
                    // Конец метода
                    break;
                }

                position = targetPosition;
                targetPosition = tempIndixes.get(tempIndixes.size() - 1);
            } else {

                // 2.1: Поиск пути:
                // Берем координату от текущей позиции (+6w+7) w - шаг по высоте; 1 - шаг по ширене
//                RowColumn halfRelativeRowColumn = getMidPosition(world, position, targetPosition);
//            RowColumn rowColumnByPosition = world.getWorldGrid().getRowColumnByPosition(position);
                targetPosition = world.getWorldGrid().getMidPosition(position, targetPosition);
//                targetPosition = position + halfRelativeRowColumn.getCol() + (world.getWorldGrid().getWidth() * halfRelativeRowColumn.getRow());

                // Записываем в промежуточный массив
                tempIndixes.add(targetPosition);
            }

        }

    }

    private void mover(World world, Creature creature) {

    }

}
