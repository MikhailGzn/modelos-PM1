package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class ArrivalBehavior implements Behavior {

    private Randomizer randomizer;
    private double mu;
    public ArrivalBehavior(Randomizer randomizer, double mu) {
        this.randomizer =  randomizer;
        this.mu = mu;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        double r = this.randomizer.nextRandom();
        return (-mu)*Math.log(1-r);
    }    
    public void setMu(double mu){
        this.mu = mu;
    }
}
