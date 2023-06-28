package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class ArrivalBehavior implements Behavior {

    private Randomizer randomizer;
    public ArrivalBehavior(Randomizer randomizer) {
        this.randomizer =  randomizer;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        return 1;
    }    
    public Randomizer getRandomizer() {
        return randomizer;
    }
}
