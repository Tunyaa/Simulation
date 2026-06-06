package simulation.Model.Mover;

import java.util.ArrayList;
import simulation.Model.Entity.Creature;
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

    private void pathFinder(World world, Creature creature) {

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
            if (world.getWorldField().isLocatedNearby(position, targetPosition)) {

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
//                RowColumn halfRelativeRowColumn = getHalfRelativePosition(world, position, targetPosition);
//            RowColumn rowColumnByPosition = world.getWorldField().getRowColumnByPosition(position);
                targetPosition = world.getWorldField().getHalfRelativePosition(position, targetPosition);
//                targetPosition = position + halfRelativeRowColumn.getCol() + (world.getWorldField().getWidth() * halfRelativeRowColumn.getRow());

                // Записываем в промежуточный массив
                tempIndixes.add(targetPosition);
            }

        }

    }
    
    private void mover(World world, Creature creature){
        
    }

}
