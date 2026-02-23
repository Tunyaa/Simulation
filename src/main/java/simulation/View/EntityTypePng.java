package simulation.View;

/**
 *
 * @author tunyaa
 */
public enum EntityTypePng {
    PREDATOR("/static/img/Волк.png"),
    HERBIVORE("/static/img/Заяц.png"),
    GRASS("/static/img/Трава.png"),
    THREE("/static/img/Дерево.png"),
    STONE("/static/img/Скала.png");

    private String displayName;

    private EntityTypePng(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
