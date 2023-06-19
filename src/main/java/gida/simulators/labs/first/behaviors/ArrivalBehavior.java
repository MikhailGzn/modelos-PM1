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
        double r = this.randomizer.nextRandom();
        double ret;
        if(r < 0.3){
            ret = 10;
        }else if(r < 0.7){
            ret = 15;
        }else{
            ret = 20;
        }
        return ret;
    }
}
