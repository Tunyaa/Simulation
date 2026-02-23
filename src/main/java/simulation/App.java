package simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import simulation.Controller.PrimaryController;
import simulation.Service.PrimaryService;
import simulation.Simulation.Simulation;
import simulation.View.View;
import simulation.World.World;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        World world = new World();
        Simulation simulation = new Simulation();
        View view = new View();
        PrimaryService primaryService = new PrimaryService(world, simulation, view);

        // 2. Загружаем FXML и получаем контроллер
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        PrimaryController controller = fxmlLoader.getController();

        // 3. Передаём сервис в контроллер
        controller.setPrimaryService(primaryService);

        // 4. Передаём Canvas во View (после того как FXML загружен)
        // Для этого нужно, чтобы у контроллера был метод getCanvas() или
        // чтобы контроллер сам передал Canvas во View в initialize()
        // Например, можно добавить в контроллер:
        // view.setCanvas(canvas); прямо в initialize()
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
