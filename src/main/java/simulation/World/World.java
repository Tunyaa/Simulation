package simulation.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import simulation.Model.Entity;
import simulation.Model.Stone;

/**
 *
 * @author tunyaa
 */
public class World {

    private WorldField worldMapComponent; // Поле
    private Map<Integer, Entity> positionEntityMap; // Карта <Позиция, Сущность>
    private final int STONESATURATION = 10;// Плотность камня на поле (количество клеток / cons)

    private Random random = new Random();

    public World() {
        this.worldMapComponent = new WorldField();
        this.positionEntityMap = new HashMap<>();
    }

    // Создать новое поле
    public void createNewMap(int width, int height) {
        this.worldMapComponent.createMap(width, height);
    }

    // Заполняет карту сущностями
    public void generateEntitysOnWorldField() {

        generateStones();
    }

// Заполняет карту камнем
    private void generateStones() {
        // Переменная хранит количество камня на карте
        int stoneCount = 0;
        // Проверяет что (количество камня < len/cons)
        int len = getWorldMap().getWorldLen();
        while (stoneCount < len / STONESATURATION) {
            // Получает рандомную позицию
            int position = random.nextInt(len);
            // Проверяет что позиция не крайняя
            if (isPositionNotBorder(position, this)) {
                // Генерирует фрагмент камня
                stoneCount += generateStoneFragment(position);
            }

        }

    }

    // Возвращает  поле
    public WorldField getWorldMap() {
        return worldMapComponent;
    }

    // Возвращает карту
    public Map<Integer, Entity> getPositionEntityMap() {
        return positionEntityMap;
    }

    // Проверка. Позиция не является крайней
    private boolean isPositionNotBorder(int position, World world) {
        int width = worldMapComponent.getWidth();// Ширина поля
        int height = worldMapComponent.getHeight();// Высота поля
        int positionH = (int) Math.ceil((double) position / width); // Координата по высоте
        int positionW = width - (positionH * width - position); // Координата по ширине

        if (positionH == 1 || positionH == height || positionW == 1 || positionW == width) {
            return false;
        }
        return true;
    }

    // Проверка. Позиция не выходит за край.
    private boolean isPositionNotOutOfBorder(int position, int newPosition, int direction) {
        int width = worldMapComponent.getWidth();
        int len = worldMapComponent.getWorldLen();

        switch (direction) {
            case 0: {// Если движение вверх
                if (newPosition >= 1) {// Новая точка не выходит за первую линию вверх
                    return true;
                }
            }
            case 1: {// Если движение вниз
                if (newPosition < len) {// Новая точка не выходит за последнюю линию вниз
                    return true;
                }
            }
            case 2: {// Если движение влево
                if (newPosition > (width * (position / width))) {// Новая точка не выходит за первый ряд влево
                    return true;
                }
            }
            case 3: {// Если движение вправо
                if (position < (width * ((position - 1) / width + 1))) {// Новая точка не выходит за последний ряд вправо
                    return true;
                }
            }

            return false;
            default:
                throw new AssertionError();
        }
//        if (direction == 0) {// Если движение вверх
//            if (newPosition >= 1) {// Новая точка не выходит за первую линию вверх
//                return true;
//            }
//        }
//        if (direction == 1) {// Если движение вниз
//            if (newPosition < len) {// Новая точка не выходит за последнюю линию вниз
//                return true;
//            }
//        }
//        if (direction == 2) {// Если движение влево
//            if (newPosition > (width * (position / width))) {// Новая точка не выходит за первый ряд влево
//                return true;
//            }
//        }
//        if (direction == 3) {// Если движение вправо
//            if (position < (width * ((position - 1) / width + 1))) {// Новая точка не выходит за последний ряд вправо
//                return true;
//            }
//        }
//        return false;
    }

    // Формирование линии из камня
    private int generateStoneFragment(int position) {
        int stoneCount = 0;
        int maxSubsequence = 0;
        int direction = random.nextInt(4);
        int height = getWorldMap().getHeight();
        // вверх, вниз, влево, вправо
        int[] ary = new int[]{-height, height, -1, 1};
        int steps = random.nextInt(4);
        for (int i = 0; i < steps; i++) {

            if (positionEntityMap.get(position) == null) {
                Stone stone = new Stone();
                stone.setPosition(position);
                positionEntityMap.put(position, stone);
                stoneCount++;
            }

            int newPosition = position + ary[direction];
            if (isPositionNotOutOfBorder(position, newPosition, direction)) {
                position = newPosition;
            } else {
                break;
            }
        }
        return stoneCount;
    }

    public void clearWorldMap() {
        positionEntityMap.clear();
        worldMapComponent.clearMap();
    }

}
