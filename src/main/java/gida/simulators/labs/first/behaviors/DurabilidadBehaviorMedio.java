package gida.simulators.labs.first.behaviors;

import gida.simulators.labs.first.utils.Randomizer;

public class DurabilidadBehaviorMedio implements Behavior{
    private Randomizer randomizer;
    private double a;
    private double b;
    public DurabilidadBehaviorMedio(Randomizer randomizer, double a, double b) {
        this.randomizer =  randomizer;        
        this.a = a;
        this.b = b;
    }
    @Override
    public double nextTime() { //Como las distribuciones del desgaste de la pista son similares podemos usar solo esta clase
        double r = this.randomizer.nextRandom();
        return (b-a)*r+a;
    }
}
