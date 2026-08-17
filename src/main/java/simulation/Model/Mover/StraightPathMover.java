package simulation.Model.Mover;

import java.util.ArrayDeque;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class StraightPathMover implements Mover {

    // Массив (Путь из индексов)
    ArrayDeque<Integer> path = new ArrayDeque<>();
    // Массив (возможные точки) промежуточный массив
    ArrayList<Integer> tempIndixes = new ArrayList<>();

    @Override
    public void randomMove(World world, Creature creature) {

        creature.getPath().clear();
        System.out.println("Move Else");
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

    @Override
    public void move(World world, Creature creature) {
        System.out.println("Metod start");
        System.out.println("Проверка пути");
        for (Integer integer : creature.getPath()) {
            System.out.println(integer);
        }
        // Проверка: есть путь?
//        if (!path.isEmpty()) {
        if (!creature.getPath().isEmpty()) {
            System.out.println("Path != Empty");
            // Передвижение
//            Map<Integer, List<Entity>> map = world.getPositionEntityMap();
//            
//            // Удалить существо из карты
//            List<Entity> list = map.get(creature.getPosition());
//            list.remove(creature);
            // Добавить существо в новую позицию
            // Следующая позиция в пути
            int newPosition = creature.getPath().getFirst();
//            int newPosition = path.getFirst();
            creature.getPath().removeFirst();
//            path.removeFirst();

            world.moveEntityToPosition(creature, creature.getPath().getFirst());
//            creature.setPosition(newPosition);
//            map.putIfAbsent(newPosition, new ArrayList<>());
//            map.get(newPosition).add(creature);

        } else {
            System.out.println("Move Else");
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
                System.out.println("MOVE WHILE");
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
                        System.out.println("Move IsTarget");
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
            System.out.println("PATH IS");
            for (Integer integer : creature.getPath()) {
                System.out.println(integer);
            }

        }
    }

}
