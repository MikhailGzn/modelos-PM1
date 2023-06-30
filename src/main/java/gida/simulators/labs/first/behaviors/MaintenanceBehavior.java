package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;

public class MaintenanceBehavior implements Behavior{
    private final int N = 100; //cantidad de veces que se suma el random
    private Randomizer randomizer;
    private double mu;
    private double sigma;

    public MaintenanceBehavior(Randomizer randomizer, double mu, double sigma) {
        this.randomizer =  randomizer;
        this.mu = mu;
        this.sigma = sigma;
    }
    //Aproximamos la distribucion normal con la distribucion uniforme, para ello usamos el teorema del limite central
    @Override
    public double nextTime() {
        double sum = 0.0;
        for(int i = 0; i < N; i++){
            sum += this.randomizer.nextRandom();
        }
        double auxiliar = ((sum-N*(0.5))/(Math.sqrt(N/12.0)));
        return auxiliar*sigma+mu;
        
    }    
}
