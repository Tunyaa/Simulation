package simulation.render;

/**
 *
 * @author tunyaa
 */
public enum EntityTypePng {
    PREDATOR("/static/img/wolf2.png"),
    HERBIVORE("/static/img/rabbit2.png"),
    GRASS("/static/img/grass2.png"),
    THREE("/static/img/tree2.png"),
    STONE("/static/img/stone2.png");

    private String displayName;

    private EntityTypePng(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
