package gida.simulators.labs.first.behaviors;
import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class EndOfServiceBehaviorMedio extends EndOfServiceBehavior {


    public EndOfServiceBehaviorMedio(Randomizer randomizer) {
        super(randomizer);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        return 10*super.getRandomizer().nextRandom()+10;
    
    }
}