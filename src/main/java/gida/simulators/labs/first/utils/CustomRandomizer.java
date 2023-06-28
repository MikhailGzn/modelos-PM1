package gida.simulators.labs.first.utils;

import java.util.Random;

import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.entities.Maintenance;
import gida.simulators.labs.first.resources.Airstrip;

public class CustomRandomizer implements Randomizer {

    private Random random;

    public CustomRandomizer() {
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public double nextRandom() {
        return this.random.nextDouble();
    }
}

