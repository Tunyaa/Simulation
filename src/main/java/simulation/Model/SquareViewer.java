package simulation.Model;

import java.util.List;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class SquareViewer implements Viewer {

    private Creature creature;

    public SquareViewer(Creature creature) {
        this.creature = creature;
    }

    @Override
    public void viev(World world) {
        int rangeOfView = creature.rangeOfView;
        int width = world.getWorldField().getWidth();
        // 

        for (int r = 0; r <= rangeOfView; r++) {

            int firstScanPOsition = creature.getPosition() - r - (width * r);

            System.out.println("first scan - " + firstScanPOsition);
            int[] steps = new int[]{1, width, -1, -width};
            // массив из шагов по полю для просмотра
            int[] scan = new int[r * 2 * 4];
            // заполнение массива шагами
            for (int i = 0; i < steps.length; i++) {
                for (int j = 0; j < r * 2; j++) {
                    scan[i * (r * 2) + j] = steps[i];
                }
            }

            
            // просмотр поля 
            for (int i : scan) {
                int f = firstScanPOsition += i;
                System.out.println(f);

                List<Entity> get = world.getPositionEntityMap().get(f);
                if (get != null) {
                    for (Entity entity : get) {

                        System.out.println(entity.getEntityTypePng());
                        if (entity instanceof Grass) {
                            System.out.println(" IF - " + f);
                            creature.setTargetPosition(f);
                        }

                    }
                }

            }
        }

    }

}
