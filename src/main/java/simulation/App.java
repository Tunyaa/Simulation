package simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import simulation.Controller.PrimaryController;
import simulation.Service.PrimaryService;
import simulation.Simulation.Simulation;
import simulation.Render.CanvasRenderer;
import simulation.World.World;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Создание постоянных объектов
        World world = new World();
        Simulation simulation = new Simulation();

        // Загружаем FXML и получаем контроллер
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        PrimaryController controller = fxmlLoader.getController();

        // Создание рендерера
        Canvas canvas = controller.getCanvas();
        CanvasRenderer renderer = new CanvasRenderer(world, canvas);
        PrimaryService primaryService = new PrimaryService(world, simulation, renderer);

        //  Передаём сервис в контроллер
        controller.setPrimaryService(primaryService);

        //  Передаём Canvas во CanvasRenderer (после того как FXML загружен)
        // Для этого нужно, чтобы у контроллера был метод getCanvas() или
        // чтобы контроллер сам передал Canvas во CanvasRenderer в initialize()
        // Например, можно добавить в контроллер:
        // renderer.setCanvas(canvas); прямо в initialize()
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
