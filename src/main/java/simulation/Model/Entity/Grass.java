package simulation.Model.Entity;

import simulation.Model.Eatable;
import simulation.Model.Entity.Entity;
import simulation.Render.EntityTypePng;

/**
 *
 * @author tunyaa
 */
public class Grass extends Entity implements Eatable{

    public Grass() {
        setEntityTypePng(EntityTypePng.GRASS);
    }
    
}
