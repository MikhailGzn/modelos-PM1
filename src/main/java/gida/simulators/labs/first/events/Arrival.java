package gida.simulators.labs.first.events;

import java.util.List;

import gida.simulators.labs.first.behaviors.*;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.AircraftType;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.entities.Maintenance;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;


import gida.simulators.labs.first.behaviors.ArrivalBehaviorLiviano;
public class Arrival extends Event {
    
    private ServerSelectionPolicy policy;

    private Behavior endOfServiceBehavior;
    private Behavior durabilidadBehavior;
    public Arrival(double clock, Entity entity, Behavior behavior,
            Behavior endOfServiceBehavior,Behavior durabilidadBehavior, ServerSelectionPolicy policy) {
                super(clock,entity,behavior,2);
                this.endOfServiceBehavior = endOfServiceBehavior;
                this.policy = policy;
                this.durabilidadBehavior = durabilidadBehavior;
    }

    public ServerSelectionPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers,CustomReport report) {
        Airstrip server = (Airstrip) this.getPolicy().selectServer(servers,this.getEntity());                    
        if (server != null){ //server es null si todos estan en mantenimiento y se busca hacer otro mantenimiento, en ese caso no lo pongo en cola ni lo atiendo
            if(this.getEntity() instanceof Maintenance){ //Si se planifica un mantenimiento se setea el modo de mantenimiento
                server.setMaintMode(true);
                report.contCantMaintenance();
                System.out.println("Mantenimiento en server "+server.getId());
            }
            if(server.isBusy()){
                // ADD TO QUEUE
                server.enqueue(this.getEntity());   //Aqui set server?                
                report.setMaxQueue(server.getLenQueue(),server.getId()); //Maxima cola en server X
                this.getEntity().setInitWait(this.getClock()); //Inicio de espera entidad X
            }else{
                server.setCurrentEntity(this.getEntity());
                this.getEntity().setServer(server);
                report.sumTotalOcioXserver(this.getClock() - server.getInitOcio(),server.getId());//Ocio Server
                //TIME OF SERVICE AND PLANIFICATION OF NEXT ARRIVAL  
                double nextTime = this.endOfServiceBehavior.nextTime();
                Event e = new EndOfService(this.getClock() + nextTime,this.getEntity(),this.endOfServiceBehavior,this.durabilidadBehavior);
                fel.insert(e);
                this.getEntity().setTransitory(nextTime);//Transito de entidad X
                if(this.getEntity() instanceof AircraftType){
                    report.sumTrasitoryTime(this.getEntity().getTransitory());//Suma Total Transito
                    report.sumTrasitoryTimeXtype(this.getEntity().getTransitory(), ((AircraftType)this.getEntity()).getType());
                }
            }
        }
        //TIME OF SERVICE AND PLANIFICATION OF NEXT ENDOFSERVICE                        
        int reloj=(int)(this.getClock());
        reloj = reloj%1440; //modulo 24hs=1440min, asi vemos que hora es. 
        Entity entity = null;
        if (this.getEntity() instanceof AircraftType){
            report.contEntityXServer(server.getId());//Cuenta Entidad X Server
            entity = new AircraftType(this.getEntity().getId()+1,((AircraftType)this.getEntity()).getType(), null);
            if ((reloj<600 && reloj>420) || (reloj>1140 && reloj<1320)){  //Las horas pico pasadas a minutos           
                if(this.getBehavior() instanceof ArrivalBehaviorLiviano){
                    ((ArrivalBehaviorLiviano)this.getBehavior()).setMu(20);
                }
                if(this.getBehavior() instanceof ArrivalBehaviorMedio){
                    ((ArrivalBehaviorMedio)this.getBehavior()).setMu(15);
                }                           
                if(this.getBehavior() instanceof ArrivalBehaviorPesado){
                    ((ArrivalBehaviorPesado)this.getBehavior()).setMu(30);
                }                 
            }
            else{
                if(this.getBehavior() instanceof ArrivalBehaviorLiviano){
                    ((ArrivalBehaviorLiviano)this.getBehavior()).setMu(40);
                }
                if(this.getBehavior() instanceof ArrivalBehaviorMedio){
                    ((ArrivalBehaviorMedio)this.getBehavior()).setMu(30);
                }                           
                if(this.getBehavior() instanceof ArrivalBehaviorPesado){
                    ((ArrivalBehaviorPesado)this.getBehavior()).setMu(60);
                }                 
            }
        }
        else{
            entity = new Maintenance(this.getEntity().getId()+1, null);
        }
        double nextTime1 = this.getBehavior().nextTime();
        Event e1 = new Arrival(this.getClock() + nextTime1, entity, this.getBehavior(), this.endOfServiceBehavior, this.durabilidadBehavior,this.policy);
        fel.insert(e1);
        report.contEntity();//Cuenta Entidad
        if(this.getEntity() instanceof AircraftType){
            report.contEntityXtype(((AircraftType)this.getEntity()).getType());//Cuenta Entidad X Tipo no cuenta 
        }
        
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "arrival - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}
