package gida.simulators.labs.first.policies;
import java.util.ArrayList;
import java.util.List;
import gida.simulators.labs.first.entities.AircraftType;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class ShortherQueueServerSelectionPolicy implements ServerSelectionPolicy{
    
    @Override
    public  Server selectServer(List<Server> servers, Entity entity) {         
        int type = ((AircraftType)entity).getType();
        List<Server> serversByType = new ArrayList<>();
        for(int i=1; i<servers.size(); i++){
            Server server = servers.get(i);
            if(((Airstrip)server).getType() == type && !((Airstrip)server).getMaintMode()){
                serversByType.add(server);
            }
        }
        if(serversByType.size() == 0){ // Caso todos estan en mantenimiento
            return servers.get(0); 
        }
        Server serverMin = serversByType.get(0);        
        for (Server server : serversByType) {
            if (server.getLenQueue() < serverMin.getLenQueue()) {
                serverMin=server;
            }
        }                                         
        return serverMin;
    }

}
