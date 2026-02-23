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

    public World() {
        this.worldMapComponent = new WorldField();
    }

    // Возвращает поле
    public Map getMap() {// !null
        return worldMapComponent.getPositionEntityMap();
    }

    // Создать новую карту
    public void createNewMap(int width, int height) {
        this.worldMapComponent.createMap(width, height);
    }

    // Заполняет карту сущностями
    public void generateEntitysOnWorldField() {
        this.positionEntityMap = new HashMap<>();
        Random random = new Random();
        for (int i = 1; i <= worldMapComponent.getLen() ; i++) {
//        for (int i = 0; i < worldMapComponent.getLen() / 10; i++) {
            int nextInt = random.nextInt(worldMapComponent.getLen());
            Stone stone = new Stone();
            stone.setPosition(i);
            positionEntityMap.put(i, stone);
//            stone.setPosition(nextInt);
//            positionEntityMap.put(nextInt, stone);
        }
    }

    // Возвращает карту
    public WorldField getWorldMap() {
        return worldMapComponent;
    }

    public Map<Integer, Entity> getPositionEntityMap() {
        return positionEntityMap;
    }

}
