package simulation.Model;

import java.util.ArrayList;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class StraightPathMover implements Mover {

    // Массив (Путь из индексов)
    ArrayList<Integer> path = new ArrayList<>();
    // Массив (возможные точки) промежуточный массив
    ArrayList<Integer> tempIndixes = new ArrayList<>();
    private Creature creature;
    private World world;

    public StraightPathMover(Creature creature, World world) {
        this.creature = creature;
        this.world = world;
    }

    @Override
    public void move() {
        System.out.println("MOVE start");
        // Текущая позиция
        int position = creature.getPosition();
        System.out.println("Моя позиция - " + position);
        // Целевая точка
        // ОБРАТОБАТЬ ЕСЛИ ТОЧКИ ЦЕЛЕВОЙ НЕТ!!!!
        int targetPosition = creature.getTargetPosition();
        System.out.println("Цель - " + targetPosition);
        // Добавляем целевую точку в промежуточный путь
        tempIndixes.add(targetPosition);

        // Проверка: массив пустой?
        // ПРОВЕРИТЬ ОКОНЧАНИЕ МЕТОДЕ ЧЕРЕЗ УСЛОВИЕ
        while (!tempIndixes.isEmpty()) {

            System.out.println("Точка рядом");
            // 1: Проверка: Точка рядом?
            if (isLocatedNearby(position, targetPosition)) {
                System.out.println("Да");
                // 2.2 Записать точку в массив
                path.add(targetPosition);
                // Удалить эту точку из промежуточного массива
                tempIndixes.remove(tempIndixes.size() - 1);
                // Проверка: Эта точка целевая?
                // SET targetPosition
                System.out.println("Дошли до цели?");
                if (isTarget(targetPosition)) {
                    System.out.println("Да");
                    // Дошли до целевой точки.
                    // Конец метода
                    break;
                }
                System.out.println("нет");

                position = targetPosition;
//                position = path.get(path.size() - 1);
                targetPosition = tempIndixes.get(tempIndixes.size() - 1);
            } else {
                System.out.println("Нет");
                // 2.1: Поиск пути:
                // Берем координату от текущей позиции (+6w+7) w - шаг по высоте; 1 - шаг по ширене
                RowColumn halfRelativeRowColumn = getHalfRelativeRowColumn(position, targetPosition);

//            RowColumn rowColumnByPosition = world.getWorldField().getRowColumnByPosition(position);
                targetPosition = position + halfRelativeRowColumn.getCol() + (world.getWorldField().getWidth() * halfRelativeRowColumn.getRow());
                System.out.println("Новая цель - " + targetPosition);
                // Записываем в промежуточный массив
                tempIndixes.add(targetPosition);
            }

            System.out.println("временный массив - ");
            for (Integer tempIndixe : tempIndixes) {
                System.out.print(tempIndixe);
            }
            System.out.println("");
        }

        System.out.println("Путь ");
        for (Integer integer : path) {
            System.out.print(integer + " ");
        }
        System.out.println("");
    }

    public boolean isLocatedNearby(int position, int targetPosition) {
        RowColumn relativeRowColumn = world.getWorldField().getRelativeRowColumn(position, targetPosition);
        System.out.println(relativeRowColumn.getCol() + " - " + relativeRowColumn.getRow());
        if (Math.abs(relativeRowColumn.getCol()) <= 1 && Math.abs(relativeRowColumn.getRow()) <= 1) {
            return true;
        }
        return false;
    }

    private void pathFiner() {
        // Берем координату (+6w+7) w - шаг по высоте; 1 - шаг по ширене
        // Берем половину     (+3w+3) округление в меньшую сторону
        // Берем позицию
        // Записываем в промежуточный массив

    }

    private boolean isTarget(Integer targetPosition) {
        System.out.println("Цель существа -" + creature.getTargetPosition());
        System.out.println("Цель промежуточная -" + targetPosition);
        return creature.getTargetPosition() == targetPosition;
    }

    // Возвражает половину(округление в меньшую сторону)
    // от относительной координаты(+6w+7 => +3w+3)
    public RowColumn getHalfRelativeRowColumn(int position, int targetPosition) {

        RowColumn relativeRowColumn = world.getWorldField().getRelativeRowColumn(position, targetPosition);

        // Берем половину     (+3w+3) округление в меньшую сторону
        // Берем позицию
        int row = relativeRowColumn.getRow() / 2;
        int col = relativeRowColumn.getCol() / 2;
        relativeRowColumn.setRow(row);
        relativeRowColumn.setCol(col);

        return relativeRowColumn;
    }

}
