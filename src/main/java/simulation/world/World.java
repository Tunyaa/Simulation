package simulation.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.config.ConfigProperties;
import simulation.model.entity.Creature;
import simulation.model.entity.Entity;
import simulation.model.entity.Grass;
import simulation.model.entity.Herbivore;
import simulation.model.entity.Predator;
import simulation.model.entity.Stone;
import simulation.model.entity.Tree;
import simulation.simulation.pathFinder.StraightPathFinder;
import simulation.simulation.vision.SquareVision;

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
    private int grassCount;     // количество травы на сетке.

    private Random random = new Random();
    private StraightPathFinder pathFinder;
    private SquareVision squareViewer;

    private final ConfigProperties configProperties;
    // Значения для генерации при создании мира.
    private int stoneInitialCount;// Плотность камня на поле (количество клеток / cons)
    private int treeInitialCount;
    private int grassInitialCount;
    private int predatorInitialCount;
    private int herbivoreInitialCount;

    public World(ConfigProperties configProperties) {

        this.worldGrid = new WorldGrid();
        this.predators = new ArrayList<>();
        this.herbivores = new ArrayList<>();
        this.configProperties = configProperties;
        this.pathFinder = new StraightPathFinder();
        this.squareViewer = new SquareVision();
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

    private void autoconfigurator() {
        stoneInitialCount = configProperties.getStoneInitialCount() == -1 ? getWorldGrid().getWorldLen() / 10 : configProperties.getStoneInitialCount();
        treeInitialCount = configProperties.getGrassInitialCount() == -1 ? getWorldGrid().getWorldLen() / 40 : configProperties.getTreeInitialCount();
        grassInitialCount = configProperties.getGrassInitialCount() == -1 ? 1 : configProperties.getGrassInitialCount();
        predatorInitialCount = configProperties.getPredatorInitialCount() == -1 ? 1 : configProperties.getPredatorInitialCount();
        herbivoreInitialCount = configProperties.getHerbivoreInitialCount() == -1 ? getWorldGrid().getWorldLen() / 20 : configProperties.getHerbivoreInitialCount();
    }

    // Заполняет карту сущностями 
    public void spawnEntitiesOnWorldGrid() {
        autoconfigurator();
        // Размещает сущности на сетке, количество берется из конфига
        spawnEntityOnGrid(Stone.class, stoneInitialCount);
        spawnEntityOnGrid(Tree.class, treeInitialCount);
        spawnEntityOnGrid(Grass.class, grassInitialCount);
        spawnEntityOnGrid(Predator.class, predatorInitialCount);
        spawnEntityOnGrid(Herbivore.class, herbivoreInitialCount);
//        spawnEntityOnGrid(Stone.class, configProperties.getStoneInitialCount());
//        spawnEntityOnGrid(Grass.class, configProperties.getGrassInitialCount());
//        spawnEntityOnGrid(Tree.class, configProperties.getTreeInitialCount());
//        spawnEntityOnGrid(Predator.class, configProperties.getPredatorInitialCount());
//        spawnEntityOnGrid(Herbivore.class, configProperties.getHerbivoreInitialCount());

        buildEntityList(Predator.class, predators);
        buildEntityList(Herbivore.class, herbivores);
    }

    // Очищает поле и карту
    public void clearWorld() {
        if (entities != null) {
            for (List<Entity> entity : entities) {
                entity.clear();
            }
            predators.clear();
            herbivores.clear();
            worldGrid.clearField();
        }

    }

    // Создает сущность
    private Entity createEntity(Class<? extends Entity> entityClass) {
        try {
            if (entityClass == Predator.class) {
                return new Predator(pathFinder, squareViewer);
            }
            if (entityClass == Herbivore.class) {
                return new Herbivore(pathFinder, squareViewer);
            }
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
        if (position >= 1 && position <= length - 1) {// 

            return entities[position];
        }
        return null;
    }

    // Возвращает сетку сущностей
    public List<Entity>[] getEntitys() {
        return entities;
    }

    // Возвращает  поле
    public WorldGrid getWorldGrid() {
        return worldGrid;
    }

    // Заполняет выбранный список из общего списка, сущностями выбранного типа
    private <T extends Entity> void buildEntityList(Class<T> entityType, List<T> targetList) {
        targetList.clear();
        for (List<Entity> entitiesList : entities) {
            for (Entity entity : entitiesList) {
                if (entityType.isInstance(entity)) {
                    targetList.add(entityType.cast(entity));
                }
            }
        }
    }

    // Размножение сущностей в рандомных клетках
    public <T extends Entity> void reproduceEntity(Class<T> entityType) {
        int count = random.nextInt(3);
        spawnEntityOnGrid(entityType, count);
        if (entityType == Herbivore.class) {
            buildEntityList(Herbivore.class, herbivores);
        }
        if (entityType == Predator.class) {
            buildEntityList(Predator.class, predators);
        }
    }

    // Заполняет карту конкретной сущностью 
    private void spawnEntityOnGrid(Class entityClass, int count) {
        int spawnedCount = 0;
        while (spawnedCount < count) {
            int position = random.nextInt(getWorldGrid().getWorldLen());
            if (entities[position].isEmpty()) {
                Entity entity = createEntity(entityClass);
                entity.setPosition(position);
                entities[position].add(entity);
                spawnedCount++;
            }
        }
    }

    // Размножение сущностей в одной клетке
    public <T extends Entity> void reproduceEntityOnCurrentEntityPosition(Class<T> entityType, Creature creature) {
        int count = random.nextInt(6);
        spawnEntityOnGridOnCurrentEntityPosition(entityType, count, creature);
        if (entityType == Herbivore.class) {
            buildEntityList(Herbivore.class, herbivores);
        }
        if (entityType == Predator.class) {
            buildEntityList(Predator.class, predators);
        }
    }

    private <T extends Entity> void spawnEntityOnGridOnCurrentEntityPosition(Class<T> entityType, int count, Creature creature) {
        int spawnedCount = 0;
        while (spawnedCount < count) {
            int position = creature.getPosition();
//            int position = random.nextInt(getWorldGrid().getWorldLen());

            Entity entity = createEntity(entityType);
            entity.setPosition(position);
            entities[position].add(entity);
            spawnedCount++;

        }
    }

    public boolean isStone(int position) {
        List<Entity> entitysByPosition = getEntitysByPosition(position);
        for (Entity entity : entitysByPosition) {
            if (entity instanceof Stone) {
                return true;
            }
        }
        return false;
    }

    public int getGrassCount() {
        this.grassCount = 0;
        List<Entity>[] entitysAry = getEntitys();
        for (List<Entity> entityList : entitysAry) {
            for (Entity entity : entityList) {

                if (entity instanceof Grass) {
                    this.grassCount++;
                }
            }

        }
        return this.grassCount;
    }

    public List<Predator> getPredators() {
        return predators;
    }

    public List<Herbivore> getHerbivores() {
        return herbivores;
    }

}
