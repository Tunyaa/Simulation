package simulation.World;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import simulation.Config.ConfigProperties;
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

    private List<Entity>[] entities;              // Общий список сущностей сетки
    private List<Predator> predators;        // Лист хищников
    private List<Herbivore> herbivores;     // Лист Тровоядных

    private Random random = new Random();

    private final ConfigProperties configProperties;
    // Значения для генерации при создании мира.
    // stoneInitialCount;
    // treeInitialCount;
    // grassInitialCount;
    // predatorInitialCount;
    // herbivoreInitialCount;

    public World(ConfigProperties configProperties) {

        this.worldGrid = new WorldGrid();
        this.predators = new ArrayList<>();
        this.herbivores = new ArrayList<>();
        this.configProperties = configProperties;

    }

    // Создаёт список индексов и задаёт ширину и высоту сетки
    public void initWorld(int width, int height) {
        if (worldGrid.getWorldLen() == 0) {
            initEntitys(width, height);
            initWorldGrid(width, height);
        }
    }

    //  Создаёт список размером с количество клеток на поле
    private void initEntitys(int width, int height) {
        entities = new List[width * height + 1];
        for (int i = 0; i < entities.length; i++) {
            entities[i] = new ArrayList<>();
        }
    }

    // Задаёт ширину и высоту сетки
    private void initWorldGrid(int width, int height) {
        this.worldGrid.initField(width, height);
    }

    // Заполняет карту сущностями TODO
    public void spawnEntitiesOnWorldGrid() {

        // Значение берется из конфига
//        spawnEntity(Stone.class, stoneInitialCount);
        spawnEntity(Grass.class, configProperties.getGrassInitialCount());
        spawnEntity(Tree.class, configProperties.getTreeInitialCount());
        spawnEntity(Predator.class, configProperties.getPredatorInitialCount());
        spawnEntity(Herbivore.class, configProperties.getHerbivoreInitialCount());

//        buildPredatorsList();
//        buildHerbivoresList();
        buildEntityList(Predator.class, predators);
        buildEntityList(Herbivore.class, herbivores);
    }

// Заполняет карту конкретной сущностью TODO
    private void spawnEntity(Class entityClass, int saturation) {
        // Переменная хранит количество камня на карте
        int entityCount = 0;
        // Проверяет что (количество камня < len/cons)
        int len = getWorldGrid().getWorldLen();
        while (entityCount < saturation) {
//        while (saturation > 0) {
            // Получает рандомную позицию
            int position = random.nextInt(len);
            // Проверяет что позиция не крайняя
//            if (isPositionNotBorder(position, this)) {
            // Генерирует фрагмент камня
//            generateEntityFragment(position, 1, Herbivore);
            entityCount += generateEntityFragment(position, 1, entityClass);
//            saturation--;
//            }

        }

    }

    // Создаёт сущность в позиции TODO
    private int generateEntityFragment(int position, int steps, Class entityClass) {
        int entityCount = 0;// Количество резмещенного камня
//        int maxSubsequence = 0;//  Максимальное количество камня в линии
        int direction = random.nextInt(4);// направление 0-вверх, 1-вниз, 2-влево, 3-вправо
        int width = getWorldGrid().getWidth();
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

    // Передвигат сущность на позицию
    public void moveEntityToPosition(Creature creature, int moveToPosition) {
        entities[creature.getPosition()].remove(creature);
        entities[moveToPosition].add(creature);
        creature.setPosition(moveToPosition);
    }

    // Возвращает лист сущностей по позиции
    public List<Entity> getEntitysByPosition(int position) {
        int length = entities.length;
        if (position >= 1 && position <= length - 1) {

            return entities[position];
        }
        return null;
    }

    // Возвращает сетку сущностей
    public List<Entity>[] getEntitys() {
        return entities;
    }

    // TODO Удалить , если не используется
    public void setEntitys(List<Entity>[] entitys) {
        System.out.println("НЕ УДАЛЯЙ МЕНЯ!!!");
        this.entities = entitys;
    }

    // Возвращает  поле
    public WorldGrid getWorldGrid() {
        return worldGrid;
    }

//    // Формирует лист хищников из общего листа
//    private void buildPredatorsList() {
//        for (List<Entity> entity : entities) {
//            for (Entity entity1 : entity) {
//                if (entity1 instanceof Predator) {
//                    this.predators.add((Predator) entity1);
//                }
//            }
//        }
//    }
//
//    // Формирует лист тровоядныйх из общего листа
//    private void buildHerbivoresList() {
//        for (List<Entity> entity : entities) {
//            for (Entity entity1 : entity) {
//                if (entity1 instanceof Herbivore) {
//                    this.herbivores.add((Herbivore) entity1);
//                }
//            }
//        }
//    }
    // Заполняет выбранный список из общего списка, сущностями выбранного типа
    private <T extends Entity> void buildEntityList(Class<T> entityType, List<T> targetList) {
        for (List<Entity> entitiesList : entities) {
            for (Entity entity : entitiesList) {
                if (entityType.isInstance(entity)) {
                    targetList.add(entityType.cast(entity));
                }
            }
        }
    }

// TODO УДАЛИТЬ
//    public void regenerteGrass() {
//        spawnEntity(Grass.class, grassInitialCount);
//    }
//
//    public void regenerteHerbivore() {
//        spawnEntity(Herbivore.class, herbivoreInitialCount);
//
//        buildEntityList(Herbivore.class, herbivores);
//    }
//
//    public void regenertePredator() {
//        spawnEntity(Predator.class, predatorInitionCount);
//
//        buildEntityList(Predator.class, predators);
//    }
    // Воспроизводство сущности TODO переделать count, под рандом 1-3. 
//    Переделать количество воспроизведения в количество вызова метода в цикле
    public <T extends Entity> void reproduceEntity(Class<T> entityType) {
        int count = 2;
        spawnEntity(entityType, count);
        if (entityType == Herbivore.class) {
            buildEntityList(Herbivore.class, herbivores);
        }
        if (entityType == Predator.class) {
            buildEntityList(Predator.class, predators);
        }
    }

    public List<Predator> getPredtors() {
        return predators;
    }

    public List<Herbivore> getHerbivores() {
        return herbivores;
    }
    
    // Заполняет карту конкретной сущностью TODO
    private void newspawnEntity(Class entityClass, int saturation) {
        // Переменная хранит количество камня на карте
        int entityCount = 0;
        // Проверяет что (количество камня < len/cons)
        int len = getWorldGrid().getWorldLen();
        while (entityCount < saturation) {
//        while (saturation > 0) {
            // Получает рандомную позицию
            int position = random.nextInt(len);
            // Проверяет что позиция не крайняя
//            if (isPositionNotBorder(position, this)) {
            // Генерирует фрагмент камня
//            generateEntityFragment(position, 1, Herbivore);
            entityCount += generateEntityFragment(position, 1, entityClass);
//            saturation--;
//            }

        }

    }

    // Создаёт сущность в позиции TODO
    private int newgenerateEntityFragment(int position, int steps, Class entityClass) {
        int entityCount = 0;// Количество резмещенного камня
//        int maxSubsequence = 0;//  Максимальное количество камня в линии
        int direction = random.nextInt(4);// направление 0-вверх, 1-вниз, 2-влево, 3-вправо
        int width = getWorldGrid().getWidth();
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

}
