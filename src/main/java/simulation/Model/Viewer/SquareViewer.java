package simulation.Model.Viewer;

import simulation.Model.Entity.Grass;
import simulation.Model.Entity.Creature;
import simulation.Model.Entity.Entity;
import java.util.List;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class SquareViewer implements Viewer {

    private Creature creature;
    private  Class<?> target;

    public SquareViewer(Creature creature, Class<?> target) {
        this.creature = creature;
        this.target = target;
    }

    @Override
    public void viev(World world) {
        int rangeOfView = creature.getRangeOfView();
        int width = world.getWorldGrid().getWidth();
        // 

        for (int r = rangeOfView; r >= 0; r--) {

            int firstScanPOsition = creature.getPosition() - r - (width * r);

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

                RowColumn rowColumn = world.getWorldGrid().getRowColumnByPosition(creature.getPosition());

                int LeftExtremePoint = rowColumn.getCol() - rangeOfView;
                int RightExtremePoint = rowColumn.getCol() + rangeOfView;
                int topExtremePoint = rowColumn.getRow() - rangeOfView;
                int lowerExtremePoint = rowColumn.getRow() + rangeOfView;

//                LeftExtremePoint = LeftExtremePoint < 1 ? 1 : LeftExtremePoint;
//                RightExtremePoint = RightExtremePoint > width ? width : RightExtremePoint;
//                topExtremePoint = topExtremePoint < 1 ? 1 : topExtremePoint;
//                lowerExtremePoint = lowerExtremePoint > world.getWorldGrid().getHeight() ? 1 : lowerExtremePoint;
                RowColumn rowColumnByScanPosition = world.getWorldGrid().getRowColumnByPosition(f);
                int col = rowColumnByScanPosition.getCol();
                int row = rowColumnByScanPosition.getRow();

                if (col >= LeftExtremePoint && col <= RightExtremePoint
                        && row >= topExtremePoint && row <= lowerExtremePoint) {
//                    List<Entity> get = world.getPositionEntityMap().get(f);
                    List<Entity> e = world.getEntitysByPosition(f);
                    if (e != null) {
                        
                        if (!e.isEmpty()) {
                            
                            for (Entity entity : e) {
                               
                                if (target.isInstance(entity)) {

                                creature.setTargetPosition(f);
                            }
                            }
                            
                        }
                    }

//                    if (get != null) {
//                        for (Entity entity : get) {
//
//                            if (entity instanceof Grass) {
//
//                                creature.setTargetPosition(f);
//                            }
//
//                        }
//                    }
                } else {

                }

            }
        }

    }

}
