package simulation.World;

import java.util.Map;

/**
 *
 * @author tunyaa
 */
public class World {

    private WorldMap worldMapComponent;

    public World() {
        this.worldMapComponent = new WorldMap();
    }

    // Возвращает поле
    public Map getMap() {// !null
        return worldMapComponent.getPositionEntityMap();
    }

    // Создать новую карту
    public void createNewMap(int width, int height) {
        this.worldMapComponent.createMap(width, height);
    }

    public WorldMap getWorldMap() {
        return worldMapComponent;
    }

}
