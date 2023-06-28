package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;

public class ArrivalBehaviorPesado extends ArrivalBehavior{
    private final int N = 100; //cantidad de veces que se suma el random
    private double sigma;
    private double mu;
    public ArrivalBehaviorPesado(Randomizer randomizer, double mu, double sigma) {
        super(randomizer);
        this.mu = mu;
        this.sigma = sigma;
    }
    public void setMu(double mu){
        this.mu = mu;
    }
    @Override
    public double nextTime() {
        double sum = 0;
        for(int i = 0; i < N; i++){
            sum += super.getRandomizer().nextRandom();
        }
        return ((sum-N*(0.5)*sigma)/(Math.sqrt(12.0*N)))+mu;
    }    

}