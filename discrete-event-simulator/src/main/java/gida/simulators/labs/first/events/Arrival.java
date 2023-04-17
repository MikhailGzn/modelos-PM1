package gida.simulators.labs.first.events;

import java.util.List;

import gida.simulators.labs.first.behaviors.Behavior;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Server;

public class Arrival extends Event {

    private ServerSelectionPolicy policy;
    private double hola;

    private EndOfServiceBehavior endOfServiceBehavior;

    public Arrival(double clock, Entity entity, Behavior behavior,
            EndOfServiceBehavior endOfServiceBehavior, ServerSelectionPolicy policy) {
                super(clock,entity,behavior,2);
                this.endOfServiceBehavior = endOfServiceBehavior;
                this.policy = policy;
    }

    public ServerSelectionPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers) {
        Server server = this.getPolicy().selectServer(servers);    
        if(server.isBusy()){
            // ADD TO QUEUE
            server.enqueue(this.getEntity());
        }else{
            server.setCurrentEntity(this.getEntity());
            this.getEntity().setServer(server);
            System.out.print("El servidor esta ocupado????????????");
            System.out.print(server.isBusy());
            //TIME OF SERVICE AND PLANIFICATION OF NEXT ARRIVAL  
            double clock = this.endOfServiceBehavior.nextTime();
            Event e = new Arrival(this.getClock() + clock,null,null,null,null);
            fel.insert(e);
        }
        //TIME OF SERVICE AND PLANIFICATION OF NEXT ENDOFSERVICE
        double clock1 = this.getBehavior().nextTime();
        Event e1 = new EndOfService(this.getClock() + clock1,null,null);
        fel.insert(e1);
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "arrival - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}
