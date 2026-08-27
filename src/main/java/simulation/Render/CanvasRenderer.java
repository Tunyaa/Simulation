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
import simulation.Model.Entity.Predator;
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

    int pngSizeInPix; // Размер картинки PNG

    public CanvasRenderer(World world, Canvas canvas) {
        this.world = world;
        this.canvas = canvas;

    }

    // Изменение размера png в зависимости от размера сетки
    public void setPngSize() {
        int sizeH = (int) (canvas.getHeight() / world.getWorldGrid().getHeight());
        int sizeW = (int) (canvas.getHeight() / world.getWorldGrid().getWidth());

        pngSizeInPix = sizeH < sizeW ? sizeH : sizeW;

    }

    // TODO сделать загрузку картинок 1 раз в старте
    @Override
    public void render() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Цвет фона сетки
        gc.setFill(Color.CADETBLUE);
//        gc.setFill(Color.BISQUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        List<Entity>[] entitys = world.getEntitys();
        for (int i = 1; i < entitys.length; i++) {
            if (!entitys[i].isEmpty()) {
                RowColumn rC = world.getWorldGrid().getRowColumnByPosition(i);
                // Загружает картинку
//                Image image = new Image(getClass().getResourceAsStream(entitys[i].get(0).getEntityTypePng().getDisplayName()));

// Если в клетке есть Predator, то загружается его png
                List<Entity> entity = entitys[i];
                int predatorIndex = entity.size() - 1;
                for (int j = 0; j < entity.size(); j++) {
                    if (entity.get(j) instanceof Predator) {
                        predatorIndex = j;
                    }
                }

                Image image = new Image(getClass().getResourceAsStream(entitys[i].get(predatorIndex).getEntityTypePng().getDisplayName()));

                gc.drawImage(image, (rC.getCol() * pngSizeInPix) - pngSizeInPix, (rC.getRow() * pngSizeInPix) - pngSizeInPix, pngSizeInPix, pngSizeInPix);
//                gc.drawImage(image, rC.getCol() * pngSizeInPix, rC.getRow() * pngSizeInPix, pngSizeInPix, pngSizeInPix);
            }
        }
    }
}
