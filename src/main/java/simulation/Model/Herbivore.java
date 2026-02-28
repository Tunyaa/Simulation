package simulation.Model;

import java.util.List;
import simulation.View.EntityTypePng;
import simulation.World.World;
import simulation.World.WorldField;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature {

    private List<Integer> Path;
    private int targetPosition;

    public Herbivore() {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setInititive(5);
        setRangeOfView(3);
        setSpeed(2);
    }

    @Override
    public void move(World world) {

    }

    @Override
    public void viev(WorldField worldMap) {

        int width = worldMap.getWidth();
        int worldLen = worldMap.getWorldLen();
        int scanPosition = position - rangeOfView - (width * rangeOfView);

        for (int w = -rangeOfView; w <= rangeOfView; w++) {
            for (int i = -rangeOfView; i <= rangeOfView; i++) {
                position - i - (width * w)
            }
        }

    }

}
