package gida.simulators.labs.first.behaviors;
import gida.simulators.labs.first.utils.Randomizer;
//import gida.simulators.labs.first.utils.distributions.Distribution;

public class EndOfServiceBehaviorLiviano extends EndOfServiceBehavior {


    public EndOfServiceBehaviorLiviano(Randomizer randomizer) {
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
        if(r < 0.363){
            ret = 5;
        }else if(r < 0.838){
            ret = 10;
        }else{
            ret = 15;
        }        
        return ret;
    }
}
