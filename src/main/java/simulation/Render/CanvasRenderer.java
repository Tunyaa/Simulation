package simulation.Render;

import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simulation.Model.Entity.Entity;
import simulation.World.RowColumn;
import simulation.World.World;
import simulation.World.WorldGrid;

/**
 *
 * @author tunyaa
 */
public class CanvasRenderer implements Renderer {

    final World world;
    final Canvas canvas;

    public CanvasRenderer(World world, Canvas canvas) {
        this.world = world;
        this.canvas = canvas;
    }
    
    @Override
    public void render() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BISQUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        List<Entity>[] entitys = world.getEntitys();
        for (int i = 1; i < entitys.length; i++) {
            if (!entitys[i].isEmpty()) {
                RowColumn rC = world.getWorldField().getRowColumnByPosition(i);
                int pngFormat = 20;
                // получаем картинку
                Image image = new Image(getClass().getResourceAsStream(entitys[i].get(0).getEntityTypePng().getDisplayName()));
                gc.drawImage(image, rC.getCol() * pngFormat, rC.getRow() * pngFormat, pngFormat, pngFormat);
            }
        }

    }

}
//    @Override
//    public void render(World world, Canvas canvas) {
//
////        Image p = new Image(getClass().getResourceAsStream(EntityTypePng.PREDATOR.getDisplayName()));
////        Image pp = new Image(getClass().getResourceAsStream(EntityTypePng.HERBIVORE.getDisplayName()));
////        Image g = new Image(getClass().getResourceAsStream(EntityTypePng.GRASS.getDisplayName()));
////        Image s = new Image(getClass().getResourceAsStream(EntityTypePng.STONE.getDisplayName()));
////        Image t = new Image(getClass().getResourceAsStream(EntityTypePng.THREE.getDisplayName()));
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//        gc.setFill(Color.BISQUE);
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
////        int h = world.getWorldField().getHeight();
////        int w = world.getWorldField().getWidth();
////        Map<Integer, List<Entity>> map = world.getPositionEntityMap();
//        List<Entity>[] entitys = world.getEntitys();
//        for (int i = 1; i < entitys.length; i++) {
//            if (!entitys[i].isEmpty()) {
//                RowColumn rC = world.getWorldField().getRowColumnByPosition(i);
//                int pngFormat = 20;
//                // получаем картинку
//                Image image = new Image(getClass().getResourceAsStream(entitys[i].get(0).getEntityTypePng().getDisplayName()));
//                gc.drawImage(image, rC.getCol() * pngFormat, rC.getRow() * pngFormat, pngFormat, pngFormat);
//            }
//        }
//
////        map.forEach((k, v) -> {
//    
//
//////            for (int i = 0; i < 10; i++) {
//////                
//////            }
//////Взять позицию первого элемента, если он есть.
////            double position = v.get(0).getPosition();//  На 0 позиции может не быть элемента использовать деку
//////            полчаем координаты
////            int positionH = (int) Math.ceil(position / w);
////            double positionW = w - (positionH * w - position);
////            // формат в пикчелях
////            int pngFormat = 20;
////            // получаем картинку
////            Image image = new Image(getClass().getResourceAsStream(v.get(0).getEntityTypePng().getDisplayName()));
////
////            gc.drawImage(image, positionW * pngFormat, positionH * pngFormat, pngFormat, pngFormat);
////        });
//
//    }
//
//}

//    @Override
//    public void render(World world, Canvas canvas) {
//
//        Image p = new Image(getClass().getResourceAsStream(EntityTypePng.PREDATOR.getDisplayName()));
//        Image pp = new Image(getClass().getResourceAsStream(EntityTypePng.HERBIVORE.getDisplayName()));
//        Image g = new Image(getClass().getResourceAsStream(EntityTypePng.GRASS.getDisplayName()));
//        Image s = new Image(getClass().getResourceAsStream(EntityTypePng.STONE.getDisplayName()));
//        Image t = new Image(getClass().getResourceAsStream(EntityTypePng.THREE.getDisplayName()));
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        // Очистка (важно!)
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
////        gc.setFill(Color.BLUE);
//        int x = 0;
//        int r = 20;
//
//        Random random = new Random();
//        for (int i = 1; i <= worldMap.getHeight(); i++) {
//            for (int j = 1; j <= worldMap.getWidth(); j++) {
////                System.out.println("i - " + i);
////                System.out.println("j - " + j);
//                int nextInt = random.nextInt(47);
//                if (nextInt == 0) {
//                    gc.drawImage(s, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 1) {
//                    gc.drawImage(p, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 2) {
//                    gc.drawImage(pp, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 3) {
//                    gc.drawImage(t, j * (r + 1), i * (r + 1), r, r);
//                }
//                if (nextInt == 4) {
//                    gc.drawImage(g, j * (r + 1), i * (r + 1), r, r);
//                }
//            }
//        }
//    }

