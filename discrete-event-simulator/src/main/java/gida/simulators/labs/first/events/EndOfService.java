package gida.simulators.labs.first.events;

import java.util.List;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Server;

public class EndOfService extends Event {

    public EndOfService(double clock, Entity entity, EndOfServiceBehavior behavior) {
        super(clock,entity,behavior,0);
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers) {
        Entity entity = this.getEntity();
        Server server = entity.getServer();
        if(server.queuesEmpty()){
            server.setCurrentEntity(null);
            this.getEntity().setServer(null);
            //COLLECT STATS
        }else{
            Entity headQueue = server.dequeue();
            headQueue.setServer(server);
            server.setCurrentEntity(headQueue);
            // PLANIFICATE ENDOFSERVICE
            double nextTime = this.getBehavior().nextTime();
            Event e = new EndOfService(this.getClock() + nextTime, headQueue,(EndOfServiceBehavior)this.getBehavior());
            fel.insert(e);
            //COLLECT STATS
        }
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "end of service - entity id: " + this.getEntity().getId() + " - clock: "
                + this.getClock();
        return ret;
    }
}
