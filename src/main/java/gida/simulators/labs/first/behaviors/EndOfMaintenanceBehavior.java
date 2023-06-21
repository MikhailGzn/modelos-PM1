package gida.simulators.labs.first.behaviors;
import gida.simulators.labs.first.utils.Randomizer;
public class EndOfMaintenanceBehavior implements Behavior{
    private Randomizer randomizer;
    public EndOfMaintenanceBehavior(Randomizer randomizer) {
        this.randomizer = randomizer;
    }
    @Override
    public double nextTime() {
        double random = this.randomizer.nextRandom();      
        return 12*random+12;
    }
}
