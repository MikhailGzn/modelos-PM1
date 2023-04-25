package gida.simulators.labs.first.events;

import java.util.List;
import gida.simulators.labs.first.behaviors.EndOfServiceBehavior;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.FutureEventList;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Server;

public class EndOfService extends Event {

    public EndOfService(double clock, Entity entity, EndOfServiceBehavior behavior) {
        super(clock,entity,behavior,0);
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers,CustomReport report) {
        Entity entity = this.getEntity();
        Server server = entity.getServer();
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
            Event e = new EndOfService(this.getClock() + nextTime, headQueue,(EndOfServiceBehavior)this.getBehavior());
            fel.insert(e);
            headQueue.setWaitTime(this.getClock() - headQueue.getInitWait());//Tiempo de espera entidad X
            headQueue.setTransitory(nextTime + headQueue.getWaitTime());//Transito de entidad X
            //System.out.println(headQueue.getWaitTime()+"Tiempo de espera");
            //System.out.println(headQueue.getTransitory()+"Tiempo de transito");
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

