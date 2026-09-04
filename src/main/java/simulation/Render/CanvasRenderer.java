package simulation.Render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import simulation.Model.Entity.Entity;
import simulation.Model.Entity.Predator;
import simulation.World.RowColumn;
import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class CanvasRenderer implements Renderer {

    private final World world;
    private final Canvas canvas;

    private int pngSizeInPix; // Размер картинки PNG

    private Map<EntityTypePng, Image> imageCache;

    public CanvasRenderer(World world, Canvas canvas) {
        this.world = world;
        this.canvas = canvas;
        this.imageCache = new HashMap<>();
        loadImages();
    }

    // Изменение размера png в зависимости от размера сетки
    public void setPngSize() {
        int sizeH = (int) (canvas.getHeight() / world.getWorldGrid().getHeight());
        int sizeW = (int) (canvas.getWidth() / world.getWorldGrid().getWidth());

        pngSizeInPix = sizeH < sizeW ? sizeH : sizeW;

    }

    // Загрузка изображений
    private void loadImages() {

        for (int i = 0; i < EntityTypePng.values().length; i++) {
            try {
                imageCache.put(EntityTypePng.values()[i], new Image(getClass().getResourceAsStream(EntityTypePng.values()[i].getDisplayName())));
            } catch (NullPointerException e) {
                throw new IllegalStateException("Image not found: " + EntityTypePng.values()[i].getDisplayName());
            }

        }
    }

    // Отрисовка сетки
    @Override
    public void render() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Цвет фона сетки
        gc.setFill(Color.CADETBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        List<Entity>[] entitys = world.getEntitys();
        for (int i = 1; i < entitys.length; i++) {
            if (!entitys[i].isEmpty()) {
                RowColumn rC = world.getWorldGrid().getRowColumnByPosition(i);

                // Если в клетке есть Predator, то загружается его png
                List<Entity> entity = entitys[i];
                int predatorIndex = entity.size() - 1;
                for (int j = 0; j < entity.size(); j++) {
                    if (entity.get(j) instanceof Predator) {
                        predatorIndex = j;
                    }
                }

                gc.drawImage(
                        imageCache.get(entity.get(predatorIndex).getEntityTypePng()),// png
                        (rC.getCol() * pngSizeInPix) - pngSizeInPix,// Координата колонки
                        (rC.getRow() * pngSizeInPix) - pngSizeInPix,// Координатя ряда
                        pngSizeInPix,// Ширина png
                        pngSizeInPix);// Высота png
            }
        }
    }
}
