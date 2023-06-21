package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class ArrivalBehaviorLiviano implements Behavior {

    private Randomizer randomizer;

    public ArrivalBehaviorLiviano(Randomizer randomizer) {
        this.randomizer =  randomizer;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        double r = this.randomizer.nextRandom();
        return (-40)*Math.log(1-r);
    }
}
