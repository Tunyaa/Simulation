package simulation.Model.Mover;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Stone;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class PredatorStraightPathFinder implements PathFinder {

    // Массив (возможные точки) промежуточный массив
    ArrayList<Integer> tempIndixes = new ArrayList<>();

    @Override
    public void pathFinderToTarget(World world, Creature creature) {
        pathFinder(world, creature);
    }

    @Override
    public void randomPathFinder(World world, Creature creature) {

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

        int r = world.getWorldGrid().getPositionByRowСolumn(3, 3);
        int positionByRowСolumn = world.getWorldGrid().getPositionByRowСolumn(row, col);
        if (world.isStone(positionByRowСolumn)) {
            System.out.println("Сработал IF  randomPathFinder");
            creature.getPath().clear();
            return;
        }
        ArrayDeque<Integer> path = creature.getPath();
        path.addFirst(positionByRowСolumn);
    }

    private void pathFinder(World world, Creature creature) {
        // Очищает Путь
        creature.getPath().clear();

        // Текущая позиция
        int creaturePosition = creature.getPosition();

        // Целевая точка
        // ОБРАТОБАТЬ ЕСЛИ ТОЧКИ ЦЕЛЕВОЙ НЕТ!!!!
        int targetPosition = creature.getTargetPosition();

        // Добавляем целевую точку в промежуточный путь
        tempIndixes.add(targetPosition);
        if (world.isStone(targetPosition)) {
            System.out.println("IF1 отработал");
            creature.getPath().clear();
            return;
        }

        // Проверка: массив пустой?
        // ПРОВЕРИТЬ ОКОНЧАНИЕ МЕТОДЕ ЧЕРЕЗ УСЛОВИЕ
        while (!tempIndixes.isEmpty()) {

            // Проверка: Точка рядом?
            if (world.getWorldGrid().isLocatedNearby(creaturePosition, targetPosition)) {

                // Записать точку путь
                creature.getPath().add(targetPosition);
                // Удалить эту точку из промежуточного массива
                tempIndixes.remove(tempIndixes.size() - 1);

                // Проверка: Эта точка целевая?
                if (creature.isTarget(targetPosition)) {
                    // Дошли до целевой точки.
                    // Конец метода
                    break;
                }

                creaturePosition = targetPosition;
                targetPosition = tempIndixes.get(tempIndixes.size() - 1);
            } else {

                // Берем промежуточную точку между сущностью и целью
                targetPosition = world.getWorldGrid().getMidPosition(creaturePosition, targetPosition);
                if (world.isStone(targetPosition)) {
                    System.out.println("IF2 отработал");
                    creature.getPath().clear();
                    return;
                }
                tempIndixes.add(targetPosition);
            }

        }

    }

}
