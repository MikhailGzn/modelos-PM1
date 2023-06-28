package gida.simulators.labs.first.events;
import gida.simulators.labs.first.behaviors.Behavior;
import gida.simulators.labs.first.behaviors.DurabilidadBehaviorLiviano;
import java.util.List;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class EndOfService extends Event {
    private Behavior durabilidadBehavior;
    public EndOfService(double clock, Entity entity, Behavior behavior, Behavior durabilidadBehavior) {
        super(clock,entity,behavior,0);
        this.durabilidadBehavior = durabilidadBehavior;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers,CustomReport report) {
        Entity entity = this.getEntity();
        Airstrip server = (Airstrip)entity.getServer();
        report.setDurabilidad(server.getDurabilidad(),server.getId());
        double changeDurabilidad = this.durabilidadBehavior.nextTime(); 
        if(changeDurabilidad<0){ //Por como construimos el DurabilidadBehavior, en airportSim, si es mantenimiento retorna negativo
            server.alterDurabilidad(server.getDurabilidad()*0.15);    
            System.out.println("Mantenimiento en server: "+server.getId()+" Durabilidad: "+server.getDurabilidad());
        }
        else{
            server.alterDurabilidad(-changeDurabilidad);
        }        
        if(server.queuesEmpty()){
            server.setCurrentEntity(null);
            this.getEntity().setServer(null);
            server.setInitOcio(this.getClock());//Incio de Ocio
        }else{
            Entity headQueue = server.dequeue();
            headQueue.setServer(server);
            server.setCurrentEntity(headQueue);
            // PLANIFICATE ENDOFSERVICE
            double nextTime = this.getBehavior().nextTime();
            Event e = new EndOfService(this.getClock() + nextTime, headQueue,(EndOfServiceBehavior)this.getBehavior(),this.durabilidadBehavior);
            fel.insert(e);
            headQueue.setWaitTime(this.getClock() - headQueue.getInitWait());//Tiempo de espera entidad X
            headQueue.setTransitory(nextTime + headQueue.getWaitTime());//Transito de entidad X
            report.sumTotalWait(headQueue.getWaitTime());//Suma Total Espera
            report.sumTrasitoryTime(headQueue.getTransitory());//Suma Total Transito
        }
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "end of service - entity id: " + this.getEntity().getId() + " - clock: " + this.getClock();
        return ret;
    }
}

