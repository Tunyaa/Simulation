package simulation.Model;

import simulation.View.EntityTypePng;

/**
 *
 * @author tunyaa
 */
public class Herbivore extends Creature{

    public Herbivore() {
        setEntityTypePng(EntityTypePng.HERBIVORE);
        setHp(100);
        setInititive(5);
        setRangeOfView(3);
        setSpeed(2);
    }

    @Override
    public void move() {
        
    }

    @Override
    public void viev() {
        
    }
    
    
    
    
}
