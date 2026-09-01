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
    
////    private Creature creature;
////    private Class<?> target;
//    
//    public SquareViewer() {
////        this.creature = creature;
////        this.target = target;
//    }
    
    @Override
    public void view(World world, Creature creature, Class<?> target) {
        creature.getTargetPositions().clear();
        creature.getPath().clear();
//        creature.setTargetPosition(0);
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
                int targetPosition = firstScanPOsition += i;
                
                RowColumn rowColumn = world.getWorldGrid().getRowColumnByPosition(creature.getPosition());
                
                int LeftExtremePoint = rowColumn.getCol() - rangeOfView;
                int RightExtremePoint = rowColumn.getCol() + rangeOfView;
                int topExtremePoint = rowColumn.getRow() - rangeOfView;
                int lowerExtremePoint = rowColumn.getRow() + rangeOfView;
                
                RowColumn rowColumnByScanPosition = world.getWorldGrid().getRowColumnByPosition(targetPosition);
                int col = rowColumnByScanPosition.getCol();
                int row = rowColumnByScanPosition.getRow();
//                System.out.println("Сканирование - " + targetPosition);
                if (col >= LeftExtremePoint && col <= RightExtremePoint
                        && row >= topExtremePoint && row <= lowerExtremePoint) {
                    List<Entity> e = world.getEntitysByPosition(targetPosition);
                    if (e != null) {
                        
                        if (!e.isEmpty()) {
                            
                            for (Entity entity : e) {
                                
                                if (target.isInstance(entity)) {
                                    
                                    creature.setTargetPosition(targetPosition);
                                    creature.getTargetPositions().add(targetPosition);
                                }
                            }
                            
                        }
                    }
                    
                }
                
            }
        }
        
    }
    
}
