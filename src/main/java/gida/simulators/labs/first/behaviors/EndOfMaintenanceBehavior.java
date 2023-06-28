package gida.simulators.labs.first.behaviors;
import gida.simulators.labs.first.utils.Randomizer;
public class EndOfMaintenanceBehavior extends EndOfServiceBehavior{
    public EndOfMaintenanceBehavior(Randomizer randomizer) {
        super(randomizer);
    }
    @Override
    public double nextTime() {
        double random = super.getRandomizer().nextRandom();      
        return 720*random+720;
    }
}
