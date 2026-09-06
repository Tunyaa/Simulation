package simulation.model.entity;

import simulation.model.eat.Eatable;
import simulation.model.entity.Entity;
import simulation.render.EntityTypePng;

/**
 *
 * @author tunyaa
 */
public class Grass extends Entity implements Eatable{

    public Grass() {
        setEntityTypePng(EntityTypePng.GRASS);
    }
    
}
