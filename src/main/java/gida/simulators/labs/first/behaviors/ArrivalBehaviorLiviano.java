package gida.simulators.labs.first.behaviors;
import gida.simulators.labs.first.behaviors.ArrivalBehavior;
import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class ArrivalBehaviorLiviano extends ArrivalBehavior {
    double mu;
    public ArrivalBehaviorLiviano(Randomizer randomizer, double mu) {
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
