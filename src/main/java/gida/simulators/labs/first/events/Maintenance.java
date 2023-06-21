package gida.simulators.labs.first.events;

import java.util.List;

import gida.simulators.labs.first.behaviors.ArrivalBehavior;
import gida.simulators.labs.first.behaviors.ArrivalBehaviorLiviano;
import gida.simulators.labs.first.behaviors.Behavior;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.behaviors.MaintenanceBehavior;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.policies.MaintenancePolicy;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class Maintenance extends Event {
    
    private ServerSelectionPolicy policy;
    private Behavior endOfMaintenanceBehavior;
    public Maintenance(double clock, Behavior behavior,Behavior endOfMaintenanceBehavior,ServerSelectionPolicy policy) {
                super(clock,null,behavior,2);                
                this.endOfMaintenanceBehavior = endOfMaintenanceBehavior;
                this.policy = policy;
    }

    public ServerSelectionPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers,CustomReport report) {
        Airstrip server = (Airstrip) this.getPolicy().selectServer(servers);                    
        if(server.isBusy()){
        }else{
            server.setCurrentEntity(null);
            server.setMaintBoolean(true);
            //report.sumTotalOcio(this.getClock() - server.getInitOcio(),server.getId());//Ocio Server QUIZAS ACA CAMBIAR POR TIEMPO DE MANTENIMIENTO 
            //TIME OF SERVICE AND PLANIFICATION OF NEXT ARRIVAL  
            double nextTime = this.endOfMaintenanceBehavior.nextTime();            
            Event e = new EndOfMaintenance(this.getClock() + nextTime,server,this.endOfMaintenanceBehavior);
            fel.insert(e);
        }
        //TIME OF SERVICE AND PLANIFICATION OF NEXT ENDOFSERVICE                
        double nextTime1 = this.getBehavior().nextTime();
        Event e1 = new Maintenance(this.getClock() + nextTime1, this.getBehavior(), this.endOfMaintenanceBehavior, this.getPolicy());
        fel.insert(e1);
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "arrival - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}