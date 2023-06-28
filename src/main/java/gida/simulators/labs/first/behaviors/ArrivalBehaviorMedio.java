package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class ArrivalBehaviorMedio extends ArrivalBehavior {
    double mu;
    public ArrivalBehaviorMedio(Randomizer randomizer, double mu) {
        super(randomizer);
        this.mu = mu;
    }
    public void setMu(double mu){
        this.mu = mu;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        double r = super.getRandomizer().nextRandom();
        return (-mu)*Math.log(1-r);
    }
}
