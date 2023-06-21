package gida.simulators.labs.first.events;

import java.util.List;

import gida.simulators.labs.first.behaviors.Behavior;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class EndOfMaintenance extends Event {
    Airstrip server;
    public EndOfMaintenance(double clock, Airstrip server, Behavior behavior) {
        super(clock,null,behavior,0);
        this.server = server;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers,CustomReport report) {
        server.reparar();
        server.setMaintBoolean(false);
    }
    

    @Override
    public String toString() {
        String ret = "";
        ret += "end of service - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}

