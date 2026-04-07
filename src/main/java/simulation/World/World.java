package simulation.World;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Grass;
import simulation.Model.Entity.Herbivore;
import simulation.Model.Entity.Predator;
import simulation.Model.Entity.Stone;
import simulation.Model.Entity.Three;

/**
 *
 * @author tunyaa
 */
public class World {

    private WorldField worldFeld; // Поле
    private Map<Integer, List<Entity>> positionEntityMap; // Карта <Позиция, Сущность>
    private List<Entity>[] entitys;
    private final int STONESATURATION = 10;// Плотность камня на поле (количество клеток / cons)
    private final int THREESATURATION = 5;
    private final int GRASSSATURATION = 5;
    private final int PREDATORSATURATION = 5;
    private final int HERBIVORESATURATION = 5;

    private Random random = new Random();

    public World() {
        this.worldFeld = new WorldField();
        this.positionEntityMap = new HashMap<>();
    }

    // Создать новое поле
    public void createNewMap(int width, int height) {
        // !!!!!!!!!!СДЕЛАТЬ ПРОВЕРКУ на размер поля
        entitys = new List[width * height + 1];
        for (int i = 0; i < entitys.length; i++) {
            entitys[i] = new ArrayList<>();
        }
        this.worldFeld.createField(width, height);
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
        int len = getWorldField().getWorldLen();
        while (entityCount < saturation) {
//        while (saturation > 0) {
            // Получает рандомную позицию
            int position = random.nextInt(len);
            // Проверяет что позиция не крайняя
//            if (isPositionNotBorder(position, this)) {
            // Генерирует фрагмент камня
//            generateEntityFragment(position, 1, entityClass);
            entityCount += generateEntityFragment(position, 1, entityClass);
//            saturation--;
//            }

        }

    }

    // Создаёт сущность в позиции
    private int generateEntityFragment(int position, int steps, Class entityClass) {
        int entityCount = 0;// Количество резмещенного камня
//        int maxSubsequence = 0;//  Максимальное количество камня в линии
        int direction = random.nextInt(4);// направление 0-вверх, 1-вниз, 2-влево, 3-вправо
        int width = getWorldField().getWidth();
        // массив направлений вверх, вниз, влево, вправо
        int[] ary = new int[]{-width, width, -1, 1};
        steps = 1;// Количество камней в линии
//        steps = random.nextInt(steps);// Количество камней в линии
        for (int i = 0; i < steps; i++) {

            if (entitys[position].isEmpty()) {
                Entity entity = entityFactory(entityClass);
                entity.setPosition(position);
                entitys[position].add(entity);
                entityCount++;
            }
            if (positionEntityMap.get(position) == null) { // Если в позиции пусто
                Entity entity = entityFactory(entityClass);
                entity.setPosition(position);
                positionEntityMap.putIfAbsent(position, new ArrayList<>());
                positionEntityMap.get(position).add(entity);
//                entityCount++;
            }

//            int newPosition = position + ary[direction];
            // Если новая позиция не выходит за край поля
//            if (isPositionNotOutOfBorder(position, newPosition, direction)) {
//                position = newPosition;
//            } else {
//                break;
//            }
        }
        return entityCount;
    }

    // Очищает поле и карту
    public void clearWorldMap() {
//        positionEntityMap.clear();
        for (List<Entity> entity : entitys) {
            entity.clear();
        }
        worldFeld.clearField();
    }

    // Создает объект // !!!!!!!!!!!!!!!!!!!!!
    private Entity entityFactory(Class<Entity> entityClass) {
        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Передвигат сужность на позицию
    public void moveEntityToPosition(Entity e, int toPosition) {
        if (entitys[e.getPosition()].contains(e)) {
            entitys[e.getPosition()].remove(e);
            e.setPosition(toPosition);
            entitys[e.getPosition()].add(e);
        }
    }

    // Возвращает сущности по позиции
    public List<Entity> getEntitysByPosition(int position) {
        return entitys[position];
    }

    // Возвращает карту сущностей
    public List<Entity>[] getEntitys() {
        return entitys;
    }

    public void setEntitys(List<Entity>[] entitys) {
        this.entitys = entitys;
    }

    // Возвращает  поле
    public WorldField getWorldField() {
        return worldFeld;
    }

    // Возвращает карту
    public Map<Integer, List<Entity>> getPositionEntityMap() {
        return positionEntityMap;
    }

    // Проверка. Позиция не выходит за край.
//    public boolean isPositionNotOutOfBorder(int position, int newPosition, int direction) {
//        System.out.println("isPositionNotOutOfBorder");
//        int width = worldFeld.getWidth();
//        int len = worldFeld.getWorldLen();
//
//        switch (direction) {
//            case 0: {// Если движение вверх
//                if (newPosition >= 1) {// Новая точка не выходит за первую линию вверх
//                    return true;
//                }
//            }
//            case 1: {// Если движение вниз
//                if (newPosition < len) {// Новая точка не выходит за последнюю линию вниз
//                    return true;
//                }
//            }
//            case 2: {// Если движение влево
//                if (newPosition > (width * (position / width))) {// Новая точка не выходит за первый ряд влево
//                    return true;
//                }
//            }
//            case 3: {// Если движение вправо
//                if (position < (width * ((position - 1) / width + 1))) {// Новая точка не выходит за последний ряд вправо
//                    return true;
//                }
//            }
//
//            return false;
//            default:
//                throw new AssertionError();
//        }
//    }
    // Проверка. Позиция не является крайней
//    private boolean isPositionNotBorder(int position, World world) {
//        int width = worldFeld.getWidth();// Ширина поля
//        int height = worldFeld.getHeight();// Высота поля
//        int positionH = (int) Math.ceil((double) position / width); // Координата по высоте
//        int positionW = width - (positionH * width - position); // Координата по ширине
//
//        if (positionH == 1 || positionH == height || positionW == 1 || positionW == width) {
//            return false;
//        }
//        return true;
//    }
}
