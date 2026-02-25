package simulation.World;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import simulation.Model.Entity;
import simulation.Model.Grass;
import simulation.Model.Herbivore;
import simulation.Model.Predator;
import simulation.Model.Stone;
import simulation.Model.Three;

/**
 *
 * @author tunyaa
 */
public class World {

    private WorldField worldMapComponent; // Поле
    private Map<Integer, Entity> positionEntityMap; // Карта <Позиция, Сущность>
    private final int STONESATURATION = 10;// Плотность камня на поле (количество клеток / cons)
    private final int THREESATURATION = 20;// Плотность камня на поле (количество клеток / cons)
    private final int GRASSSATURATION = 50;// Плотность камня на поле (количество клеток / cons)
    private final int PREDATORSATURATION = 80;// Плотность камня на поле (количество клеток / cons)
    private final int HERBIVORESATURATION = 80;// Плотность камня на поле (количество клеток / cons)

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

        generateEntity(Stone.class, STONESATURATION);
        generateEntity(Grass.class, GRASSSATURATION);
        generateEntity(Three.class, THREESATURATION);
        generateEntity(Predator.class, PREDATORSATURATION);
        generateEntity(Herbivore.class, HERBIVORESATURATION);
    }

// Заполняет карту конкретной сущностью 
    private void generateEntity(Class entityClass, int saturation) {
        // Переменная хранит количество камня на карте
        int entityCount = 0;
        // Проверяет что (количество камня < len/cons)
        int len = getWorldMap().getWorldLen();
        while (entityCount < len / saturation) {
            // Получает рандомную позицию
            int position = random.nextInt(len);
            // Проверяет что позиция не крайняя
            if (isPositionNotBorder(position, this)) {
                // Генерирует фрагмент камня
                entityCount += generateEntityFragment(position, 1, entityClass);
            }

        }

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
    }

    // Формирование линии из камня
    private int generateEntityFragment(int position, int steps, Class entityClass) {
        int entityCount = 0;// Количество резмещенного камня
        int maxSubsequence = 0;//  Максимальное количество камня в линии
        int direction = random.nextInt(4);// направление 0-вверх, 1-вниз, 2-влево, 3-вправо
        int width = getWorldMap().getWidth();
        // массив направлений вверх, вниз, влево, вправо
        int[] ary = new int[]{-width, width, -1, 1};
        steps = 1;// Количество камней в линии
//        steps = random.nextInt(steps);// Количество камней в линии
        for (int i = 0; i < steps; i++) {

            if (positionEntityMap.get(position) == null) { // Если в позиции пусто
                Entity entity = entityFactory(entityClass);
                entity.setPosition(position);
                positionEntityMap.put(position, entity);
                entityCount++;
            }

            int newPosition = position + ary[direction];
            // Если новая позиция не выходит за край поля
            if (isPositionNotOutOfBorder(position, newPosition, direction)) {
                position = newPosition;
            } else {
                break;
            }
        }
        return entityCount;
    }

    // Очищает поле и карту
    public void clearWorldMap() {
        positionEntityMap.clear();
        worldMapComponent.clearMap();
    }

    // Создает объект 
    private Entity entityFactory(Class<Entity> entityClass) {
        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
}
