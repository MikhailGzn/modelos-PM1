package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class EndOfServiceBehavior implements Behavior {

    private Randomizer randomizer;

    public EndOfServiceBehavior(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        double r = this.randomizer.nextRandom();
        double ret;
        if(r < 0.363){
            ret = 5;
        }else if(r < 0.475){
            ret = 10;
        }else{
            ret = 15;
        }        
        return ret;
    }
    public Randomizer getRandomizer() {
        return randomizer;
    }
}
