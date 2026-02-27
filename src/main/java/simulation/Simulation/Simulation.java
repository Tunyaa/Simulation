package simulation.Simulation;

import simulation.World.World;

/**
 *
 * @author tunyaa
 */
public class Simulation {
    private TurnProcessor turnProcessor;

    public Simulation() {
        this.turnProcessor = new TurnProcessor();
    }

    public void startSimulation(World world) {
        world.getPositionEntityMap();
    }
    
    
}
