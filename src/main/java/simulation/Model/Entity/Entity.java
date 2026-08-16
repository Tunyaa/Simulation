package simulation.Model.Entity;

import simulation.Render.EntityTypePng;

/**
 *
 * @author tunyaa
 */
public abstract class Entity {

    protected EntityTypePng entityTypePng;
    protected int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public EntityTypePng getEntityTypePng() {
        return entityTypePng;
    }

    protected void setEntityTypePng(EntityTypePng entityTypePng) {
        this.entityTypePng = entityTypePng;
    }

}
