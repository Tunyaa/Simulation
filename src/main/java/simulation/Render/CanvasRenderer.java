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

    int pngSizeInPix = 20; // Размер картинки СДЕЛАТЬ ПЛАВАЮЩЕЕ ЗНАЧЕНИЕ TODO

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
                RowColumn rC = world.getWorldGrid().getRowColumnByPosition(i);
                // получаем картинку
                Image image = new Image(getClass().getResourceAsStream(entitys[i].get(0).getEntityTypePng().getDisplayName()));
                gc.drawImage(image, rC.getCol() * pngSizeInPix, rC.getRow() * pngSizeInPix, pngSizeInPix, pngSizeInPix);
            }
        }
    }
}
