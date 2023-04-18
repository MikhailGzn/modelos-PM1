package gida.simulators.labs.first.engine;

import java.util.List;

import gida.simulators.labs.first.behaviors.ArrivalBehavior;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.events.Arrival;
import gida.simulators.labs.first.events.StopSimulation;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Server;
import gida.simulators.labs.first.utils.Randomizer;

public class AirportSim extends Engine {

    private FutureEventList fel;
    private List<Server> servers;

    public AirportSim(double endClock, List<Server> servers, ServerSelectionPolicy policy,
            Randomizer randomizer, Reportable report) {
                super(report);
                this.fel = new FutureEventList();
                this.fel.insert(new StopSimulation(endClock, this));
                this.fel.insert(new Arrival(0, new Aircraft(0, null), new ArrivalBehavior(randomizer), new EndOfServiceBehavior(randomizer), policy));
                this.servers = servers;
            }

    public FutureEventList getFEL(){
        if (this.fel == null)
            this.fel = new FutureEventList();
        return this.fel;
    }

    @Override
    public void run() {
        while(!this.getStop()){
            this.fel.getImminent().planificate(fel, servers);
            System.out.println(this.fel.toString()); 
        }
    }
}
