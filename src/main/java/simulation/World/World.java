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

    private WorldField worldMapComponent;
    private Map<Integer, Entity> positionEntityMap;
    private final int STONESATURATION = 10;

    private Random random = new Random();

    public World() {
        this.worldMapComponent = new WorldField();
        this.positionEntityMap = new HashMap<>();
    }

    // Возвращает поле
//    public Map getMap() {// !null
//        return worldMapComponent.getPositionEntityMap();
//    }
    // Создать новую карту
    public void createNewMap(int width, int height) {
        this.worldMapComponent.createMap(width, height);
    }

    // Заполняет карту сущностями
    public void generateEntitysOnWorldField() {

//        for (int i = 1; i <= worldMapComponent.getWorldLen(); i++) {
        ////        for (int i = 0; i < worldMapComponent.getLen() / 10; i++) {
//            int nextInt = random.nextInt(worldMapComponent.getWorldLen());
//            Stone stone = new Stone();
//            stone.setPosition(i);
//            positionEntityMap.put(i, stone);
////            stone.setPosition(nextInt);
////            positionEntityMap.put(nextInt, stone);
//        }
    generateStones();
    }

    private void generateStones() {
        // Переменная хранит количество камня на карте
        int stoneCount = 0;
        // Проверяем что (количество камня < len/10)
        int len = getWorldMap().getWorldLen();
        while (stoneCount < len / STONESATURATION) {
            // Получаем рандомную позицию
            int position = random.nextInt(len);
            // Проверяем что она не крайняя
            if (isPositionNotBorder(position, this)) {
                // Генерируем фрагмент камня
                stoneCount += generateStoneFragment(position);
            }

        }

    }

    // Возвращает карту
    public WorldField getWorldMap() {
        return worldMapComponent;
    }

    public Map<Integer, Entity> getPositionEntityMap() {
        return positionEntityMap;
    }

    // Проверка. Позиция не является крайней
    private boolean isPositionNotBorder(int position, World world) {
        int width = world.getWorldMap().getWidth();
        int height = world.getWorldMap().getHeight();
        int positionH = (int) Math.ceil((double) position / width);
        int positionW = width - (positionH * width - position);

        if (positionH == 1 || positionH == height || positionW == 1 || positionW == width) {
            return false;
        }
        return true;
    }

    // Проверка. Позиция выходит за край.
    private boolean isPositionNotOutOfBorder(int position, int newPosition, World world, int derection) {
        int width = world.getWorldMap().getWidth();
        int height = world.getWorldMap().getHeight();
        int len = world.getWorldMap().getWorldLen();
        int positionH = (int) Math.ceil((double) position / width);
        int positionW = width - (positionH * width - position);

        if (derection == 0) {
            if (newPosition >= 1) {
                position = newPosition;
                return true;
            }
        }
        if (derection == 1) {
            if (newPosition < len) {
                position = newPosition;
                return true;
            }
        }
        if (derection == 2) {
            if (newPosition > (width * (position / width))) {
                position = newPosition;
                return true;
            }
        }
        if (derection == 3) {
            if (position < (width * ((position - 1) / width + 1))) {
                position += 1;// Сделать = new Position
                return true;
            }
        }
        return false;

//            newPoint = point - width;
//            if (newPoint >= 1) {
//                point = newPoint;
//            }
//        }
//        if (direction.equals("s")) {
//            newPoint = point + width;
//            if (point < len) {
//                point = newPoint;
//            }
//        }
//        if (direction.equals("a")) {
//            newPoint = point - 1;
//            if (newPoint > (width * (point / width))) {
//                point = newPoint;
//            }
//        }
//        if (direction.equals("d")) {
//            if (point < (width * ((point - 1) / width + 1))) {
//                point += 1;
//            }
//        }
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
            if (isPositionNotOutOfBorder(position, newPosition, this, direction)) {
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
