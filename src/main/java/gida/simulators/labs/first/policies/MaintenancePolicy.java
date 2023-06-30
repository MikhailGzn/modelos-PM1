package gida.simulators.labs.first.policies;
import java.util.ArrayList;
import java.util.List;

import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class MaintenancePolicy implements ServerSelectionPolicy{
    public  Server selectServer(List<Server> servers, Entity entity) { 
        Airstrip server;
        List<Server> serversNoMaint = new ArrayList<>();
        for(int i=1; i<servers.size(); i++){
            server = (Airstrip)servers.get(i);
            if(!(server.getMaintMode())){
                serversNoMaint.add(server);
            }
        }
        if(serversNoMaint.size() == 0){
            return null;
        }
        Airstrip serverMin = (Airstrip)serversNoMaint.get(0);

        for (int i=0; i<serversNoMaint.size(); i++) {            
            server = (Airstrip)serversNoMaint.get(i);       
            if (server.getDurabilidad() < serverMin.getDurabilidad()) {
                serverMin=server;
            }
        }        
        return serverMin;
    }
}
