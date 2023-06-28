package gida.simulators.labs.first.behaviors;
import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class EndOfServiceBehaviorPesado extends EndOfServiceBehavior {

    public EndOfServiceBehaviorPesado(Randomizer randomizer) {
        super(randomizer);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public double nextTime() {
        double r = super.getRandomizer().nextRandom();
        double ret;
        if(r < 0.65){
            ret = 40;        
        }else{
            ret = 50;
        }        
        return ret;
    }
}