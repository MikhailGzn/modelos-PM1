package gida.simulators.labs.first;

import gida.simulators.labs.first.engine.AirportSim;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.Engine;
import gida.simulators.labs.first.policies.UniqueServerSelectionPolicy;
import gida.simulators.labs.first.utils.CustomRandomizer;
import gida.simulators.labs.first.utils.ScenarioBuilder;
public class App {

    private static final float SIMULATION_LENGHT = 200f;

    public static void main(String[] args) {
        Engine engine = new AirportSim(SIMULATION_LENGHT, ScenarioBuilder.OneServerOneQueue(), new UniqueServerSelectionPolicy(), new CustomRandomizer(), new CustomReport(SIMULATION_LENGHT));
        engine.run();
        engine.getReportable().generateReport();
    }
}