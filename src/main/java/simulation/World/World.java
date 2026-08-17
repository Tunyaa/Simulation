package simulation.World;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Grass;
import simulation.Model.Entity.Herbivore;
import simulation.Model.Entity.Predator;
import simulation.Model.Entity.Stone;
import simulation.Model.Entity.Tree;

/**
 *
 * @author tunyaa
 */
// Настройки мира
public class World {

    private WorldGrid worldGrid; // Сетка

    private List<Entity>[] entities;
    private List<Predator> predators;
    private List<Herbivore> herbivores;

    private final int STONESATURATION = 5;// Плотность камня на поле (количество клеток / cons)
    private final int TREESATURATION = 1;
    private final int GRASSSATURATION = 10;
    private final int PREDATORSATURATION = 1;
    private final int HERBIVORESATURATION = 1;

    private Random random = new Random();

    public World() {
        this.worldGrid = new WorldGrid();
        this.predators = new ArrayList<>();
        this.herbivores = new ArrayList<>();
    }

    // Создаёт список индексов и задаёт ширину и высоту сетки
    public void initWorld(int width, int height) {
        initEntitys(width, height);
        initWorldGrid(width, height);
    }

    //  Создаёт список размером с количество клеток на поле
    private void initEntitys(int width, int height) {
        // !!!!!!!!!!СДЕЛАТЬ ПРОВЕРКУ на размер поля
        entities = new List[width * height + 1];
        for (int i = 0; i < entities.length; i++) {
            entities[i] = new ArrayList<>();
        }
    }

    // Задаёт ширину и высоту сетки
    private void initWorldGrid(int width, int height) {
        this.worldGrid.initField(width, height);
    }

    // Заполняет карту сущностями
    public void spawnEntitiesOnWorldGrid() {

//        spawnEntity(Stone.class, STONESATURATION);
//        spawnEntity(Grass.class, GRASSSATURATION);
        spawnEntity(Grass.class, 2);
        spawnEntity(Tree.class, TREESATURATION);
        spawnEntity(Predator.class, 1);
//        spawnEntity(Predator.class, PREDATORSATURATION);
//        spawnEntity(Herbivore.class, HERBIVORESATURATION);
        spawnEntity(Herbivore.class, 5);

        buildPredatorsList();
        buildHerbivoresList();
    }

// Заполняет карту конкретной сущностью 
    private void spawnEntity(Class entityClass, int saturation) {
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

            if (entities[position].isEmpty()) {
                Entity entity = createEntity(entityClass);
                entity.setPosition(position);
                entities[position].add(entity);
                entityCount++;
            }
        }

        return entityCount;
    }

    // Очищает поле и карту
    public void clearWorld() {
        for (List<Entity> entity : entities) {
            entity.clear();
        }
        predators.clear();
        herbivores.clear();
        worldGrid.clearField();
    }

    // Создает сущность
    private Entity createEntity(Class<Entity> entityClass) {
        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to create entity", ex);
        }
    }

    // Удаляет сущность
    public void removeEntity(Entity entity) {
        if (entity instanceof Herbivore) {
            herbivores.remove(entity);
        } else if (entity instanceof Predator) {
            predators.remove(entity);
        }

        entities[entity.getPosition()].remove(entity);
    }

    // Передвигат сужность на позицию
    public void moveEntityToPosition(Creature creature, int moveToPosition) {
        int moveTo = creature.getPath().getFirst();

        entities[creature.getPosition()].remove(creature);
        entities[moveTo].add(creature);
        creature.setPosition(moveTo);
    }

    // Возвращает сущности по позиции
    public List<Entity> getEntitysByPosition(int position) {
        int length = entities.length;
        if (position >= 1 && position <= length - 1) {

            return entities[position];
        }
        return null;
    }

    // Возвращает карту сущностей
    public List<Entity>[] getEntitys() {
        return entities;
    }

    public void setEntitys(List<Entity>[] entitys) {
        this.entities = entitys;
    }

    // Возвращает  поле
    public WorldGrid getWorldField() {
        return worldGrid;
    }

    // Проверка. Позиция не выходит за край.
//    public boolean isPositionNotOutOfBorder(int position, int newPosition, int direction) {
//        System.out.println("isPositionNotOutOfBorder");
//        int width = worldGrid.getWidth();
//        int len = worldGrid.getWorldLen();
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
//        int width = worldGrid.getWidth();// Ширина поля
//        int height = worldGrid.getHeight();// Высота поля
//        int positionH = (int) Math.ceil((double) position / width); // Координата по высоте
//        int positionW = width - (positionH * width - position); // Координата по ширине
//
//        if (positionH == 1 || positionH == height || positionW == 1 || positionW == width) {
//            return false;
//        }
//        return true;
//    }
    private void buildPredatorsList() {
        for (List<Entity> entity : entities) {
            for (Entity entity1 : entity) {
                if (entity1 instanceof Predator) {
                    this.predators.add((Predator) entity1);
                }
            }
        }
    }

    private void buildHerbivoresList() {
        for (List<Entity> entity : entities) {
            for (Entity entity1 : entity) {
                if (entity1 instanceof Herbivore) {
                    this.herbivores.add((Herbivore) entity1);
                }
            }
        }
    }

    public List<Predator> getPredtors() {
        return predators;
    }

    public List<Herbivore> getHerbivores() {
        return herbivores;
    }

    public void regenerte() {
        spawnEntity(Grass.class, GRASSSATURATION);
    }

    public void regenerteHerbivore() {

        spawnEntity(Herbivore.class, HERBIVORESATURATION);

        buildHerbivoresList();
    }

    public void regenertePredator() {

        spawnEntity(Predator.class, PREDATORSATURATION);

        buildPredatorsList();
    }

}
