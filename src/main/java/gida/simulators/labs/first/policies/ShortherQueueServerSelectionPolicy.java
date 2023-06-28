package gida.simulators.labs.first.policies;
import java.util.List;
import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.entities.AircraftType;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class ShortherQueueServerSelectionPolicy implements ServerSelectionPolicy{
    @Override
    public  Server selectServer(List<Server> servers, Entity entity) {         
        int type = ((AircraftType)entity).getType();
        Server serverMin = servers.get(1);        
        Server server; // Variable auxiliar
        for (int i=1;i<servers.size();i++) {
            server=servers.get(i);
            if (server.getLenQueue() < serverMin.getLenQueue() && !((Airstrip)server).getMaintMode() && ((Airstrip)server).getType() == type) {
                serverMin=server;
            }
        }     
        if(((Airstrip)serverMin).getMaintMode() || ((Airstrip)serverMin).getType()!=type){ // Caso todos estan en mantenimiento
            return servers.get(0); 
        }
        return serverMin;
    }

}
