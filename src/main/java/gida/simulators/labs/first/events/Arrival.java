package gida.simulators.labs.first.events;

import java.util.List;

import gida.simulators.labs.first.behaviors.ArrivalBehavior;
import gida.simulators.labs.first.behaviors.ArrivalBehaviorLiviano;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Airstrip;
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
        Airstrip server = (Airstrip) this.getPolicy().selectServer(servers);            
        server.desgastar();
        if(server.isBusy()){
            // ADD TO QUEUE
            server.enqueue(this.getEntity());                        
            report.setMaxQueue(server.getLenQueue());
            this.getEntity().setInitWait(this.getClock()); //Inicio de espera entidad X
        }else{
            server.setCurrentEntity(this.getEntity());
            this.getEntity().setServer(server);
            report.sumTotalOcio(this.getClock() - server.getInitOcio(),server.getId());//Ocio Server
            //TIME OF SERVICE AND PLANIFICATION OF NEXT ARRIVAL  
            double nextTime = this.endOfServiceBehavior.nextTime();
            Event e = new EndOfService(this.getClock() + nextTime,this.getEntity(),this.endOfServiceBehavior);
            fel.insert(e);
            this.getEntity().setTransitory(nextTime);//Transito de entidad X
            report.sumTrasitoryTime(this.getEntity().getTransitory());//Suma Total Transito
        }
        //TIME OF SERVICE AND PLANIFICATION OF NEXT ENDOFSERVICE                
        double nextTime1 = this.getBehavior().nextTime();
        int reloj=(int)(this.getClock()+nextTime1);
        reloj = reloj%1440;
        ArrivalBehavior behavior = (ArrivalBehavior)this.getBehavior(); //Manejo de la hora pico
        if ((reloj<600 && reloj>420) || (reloj>1140 && reloj<1320)){
            behavior.setMu(20);
        }
        else{
            behavior.setMu(40);
        }
        Event e1 = new Arrival(this.getClock() + nextTime1, new Aircraft(this.getEntity().getId() + 1,null), behavior, this.endOfServiceBehavior, this.policy);
        fel.insert(e1);
        report.setDurabilidad(server.getDurabilidad(),server.getId());
        report.contEntity();//Cuenta Entidad
        report.contEntityXServer(server.getId());//Cuenta Entidad X Server
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "arrival - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}
