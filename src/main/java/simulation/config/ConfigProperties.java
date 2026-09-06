package simulation.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author oldca
 */
public class ConfigProperties {

    private final Properties props = new Properties();

    private int stoneInitialCount;// Плотность камня на поле (количество клеток / cons)
    private int treeInitialCount;
    private int grassInitialCount;
    private int predatorInitialCount;
    private int herbivoreInitialCount;

    public ConfigProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Not found config.properties in resources");
            }
            props.load(input);

            stoneInitialCount = getIntValueFromProperty("stoneInitialCount");
            treeInitialCount = getIntValueFromProperty("treeInitialCount");
            grassInitialCount = getIntValueFromProperty("grassInitialCount");
            predatorInitialCount = getIntValueFromProperty("predatorInitialCount");
            herbivoreInitialCount = getIntValueFromProperty("herbivoreInitialCount");

        } catch (IOException e) {
            throw new RuntimeException("Exception in load config.properties");
        }
    }

    private int getIntValueFromProperty(String key) {

        return Integer.parseInt(props.getProperty(key));
    }

    public int getStoneInitialCount() {
        return stoneInitialCount;
    }

    public int getTreeInitialCount() {
        return treeInitialCount;
    }

    public int getGrassInitialCount() {
        return grassInitialCount;
    }

    public int getPredatorInitialCount() {
        return predatorInitialCount;
    }

    public int getHerbivoreInitialCount() {
        return herbivoreInitialCount;
    }

}
