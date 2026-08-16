package simulation.Render;

/**
 *
 * @author tunyaa
 */
public enum EntityTypePng {
    PREDATOR("/static/img/wolf.png"),
    HERBIVORE("/static/img/rabbit.png"),
    GRASS("/static/img/grass.png"),
    THREE("/static/img/three.png"),
    STONE("/static/img/stone.png");

    private String displayName;

    private EntityTypePng(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
