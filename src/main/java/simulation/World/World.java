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

        for (int i = 1; i <= worldMapComponent.getWorldLen(); i++) {
//        for (int i = 0; i < worldMapComponent.getLen() / 10; i++) {
            int nextInt = random.nextInt(worldMapComponent.getWorldLen());
            Stone stone = new Stone();
            stone.setPosition(i);
            positionEntityMap.put(i, stone);
//            stone.setPosition(nextInt);
//            positionEntityMap.put(nextInt, stone);
        }
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
    private boolean isPositionNotOutOfBorder(int position, int newPosition, World world) {
        int width = world.getWorldMap().getWidth();
        int height = world.getWorldMap().getHeight();
        int len = world.getWorldMap().getWorldLen();
        int positionH = (int) Math.ceil((double) position / width);
        int positionW = width - (positionH * width - position);

        if (newPosition >= 1) {
            position = newPosition;
            return true;
        } else if (position < len) {
            position = newPosition;
            return true;
        } else if (newPosition > (width * (position / width))) {
            position = newPosition;
            return true;
        } else if (position < (width * ((position - 1) / width + 1))) {
            position += 1;
            return true;
        }
        return false;

    }
    // Формирование линии из камня

    private int generateStoneFragment(int position) {
        // 0-3x3		Space ==max
        int stoneCount = 0;
        int maxSubsequence;
        int direction = random.nextInt(4);
        int len = getWorldMap().getWorldLen();
        int[] ary = new int[]{-len, len, -1, 1};

        int steps = random.nextInt(4);
        for (int i = 0; i < steps; i++) {

            Stone stone = new Stone();
            stone.setPosition(position);
            positionEntityMap.put(position, stone);
            stoneCount++;
            int newPosition = position + ary[direction];
            if (isPositionNotOutOfBorder(position, newPosition, this)) {
                position = newPosition;
            } else {
                break;
            }
        }
        return stoneCount;
    }

}
