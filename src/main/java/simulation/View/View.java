package simulation.View;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simulation.World.World;
import simulation.World.WorldMap;

/**
 *
 * @author tunyaa
 */
public class View implements Renderer {

    
    

    @Override
    public void render(WorldMap worldMap, Canvas canvas) {

        Image p = new Image(getClass().getResourceAsStream(EntityTypePng.PREDATOR.getDisplayName()));
        Image pp = new Image(getClass().getResourceAsStream(EntityTypePng.HERBIVORE.getDisplayName()));
        Image g = new Image(getClass().getResourceAsStream(EntityTypePng.GRASS.getDisplayName()));
        Image s = new Image(getClass().getResourceAsStream(EntityTypePng.STONE.getDisplayName()));
        Image t = new Image(getClass().getResourceAsStream(EntityTypePng.THREE.getDisplayName()));

        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Очистка (важно!)
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        gc.setFill(Color.BLUE);
        int x = 0;
        int r = 20;

        Random random = new Random();
        for (int i = 1; i <= worldMap.getHeight(); i++) {
            for (int j = 1; j <= worldMap.getWidth(); j++) {
//                System.out.println("i - " + i);
//                System.out.println("j - " + j);
                int nextInt = random.nextInt(47);
                if (nextInt == 0) {
                    gc.drawImage(s, j * (r + 1), i * (r + 1), r, r);
                }
                if (nextInt == 1) {
                    gc.drawImage(p, j * (r + 1), i * (r + 1), r, r);
                }
                if (nextInt == 2) {
                    gc.drawImage(pp, j * (r + 1), i * (r + 1), r, r);
                }
                if (nextInt == 3) {
                    gc.drawImage(t, j * (r + 1), i * (r + 1), r, r);
                }
                if (nextInt == 4) {
                    gc.drawImage(g, j * (r + 1), i * (r + 1), r, r);
                }
            }
        }
    }

}
