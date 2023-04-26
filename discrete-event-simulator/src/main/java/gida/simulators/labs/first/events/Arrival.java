package gida.simulators.labs.first.events;

import java.util.List;

import gida.simulators.labs.first.behaviors.ArrivalBehavior;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Server;

public class Arrival extends Event {
    
    private ServerSelectionPolicy policy;

    private EndOfServiceBehavior endOfServiceBehavior;

    public Arrival(double clock, Entity entity, ArrivalBehavior behavior,
            EndOfServiceBehavior endOfServiceBehavior, ServerSelectionPolicy policy) {
                super(clock,entity,behavior,2);
                this.endOfServiceBehavior = endOfServiceBehavior;
                this.policy = policy;
    }

    public ServerSelectionPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers,CustomReport report) {
        Server server = this.getPolicy().selectServer(servers);    
        if(server.isBusy()){
            // ADD TO QUEUE

            server.enqueue(this.getEntity());                        
            report.setMaxQueue(server.getMaxLenQueue());
            this.getEntity().setInitWait(this.getClock()); //Inicio de espera entidad X
        }else{
            server.setCurrentEntity(this.getEntity());
            this.getEntity().setServer(server);
            //System.out.println(server.getTotalOcio()+"Tiempo de Ocio");
            report.sumTotalOcio(this.getClock() - server.getInitOcio());//Ocio Server
            //TIME OF SERVICE AND PLANIFICATION OF NEXT ARRIVAL  
            double nextTime = this.endOfServiceBehavior.nextTime();
            Event e = new EndOfService(this.getClock() + nextTime,this.getEntity(),this.endOfServiceBehavior);
            fel.insert(e);
            this.getEntity().setTransitory(nextTime);//Transito de entidad X
            //System.out.println(this.getEntity().getTransitory()+"Tiempo de Transito");
            report.sumTrasitoryTime(this.getEntity().getTransitory());//Suma Total Transito
        }
        //TIME OF SERVICE AND PLANIFICATION OF NEXT ENDOFSERVICE
        double nextTime1 = this.getBehavior().nextTime();
        Event e1 = new Arrival(this.getClock() + nextTime1, new Aircraft(this.getEntity().getId() + 1,null), (ArrivalBehavior)this.getBehavior(), this.endOfServiceBehavior, this.policy);
        fel.insert(e1);
        //System.out.println(this.getEntity().getId()+"Id Entidad");
        report.contEntity();//Cuenta Entidad
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "arrival - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}
