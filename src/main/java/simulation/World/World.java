package simulation.World;

import java.util.Map;

/**
 *
 * @author tunyaa
 */
public class World {

    private WorldMap worldMapComponent;

    // Возвращает поле
    public Map getMap() {// !null
        return worldMapComponent.getPositionEntityMap();
    }
    
    // Создать новую карту
    public void createNewMap(int width, int height){
        this.worldMapComponent = new WorldMap(width, height);
    }
}
