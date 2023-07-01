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
        double sum = 0.0;
        double auxiliar =-1d;
        while (auxiliar < 0 ){ //No tiene sentido si da negativo, es poco probable pero consideramos el caso
            sum=0.0;
            for(int i = 0; i < N; i++){
                sum += super.getRandomizer().nextRandom();
            }
            auxiliar = ((sum-N*(0.5))/(Math.sqrt(N/12.0)));
            auxiliar = auxiliar*sigma+mu;
        }
        return auxiliar;
    }    

}