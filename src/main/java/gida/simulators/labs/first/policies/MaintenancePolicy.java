package gida.simulators.labs.first.policies;
import java.util.List;

import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class MaintenancePolicy implements ServerSelectionPolicy{
    public  Server selectServer(List<Server> servers, Entity entity) { 
        Airstrip serverMin = (Airstrip)servers.get(1);
        Airstrip auxiliar;
        for (int i=1; i<servers.size(); i++) {            
            auxiliar = (Airstrip)servers.get(i);       
            if (auxiliar.getDurabilidad() < serverMin.getDurabilidad() && !auxiliar.getMaintMode()) {
                serverMin=auxiliar;
            }
        }    
        if(serverMin.getMaintMode()){ // Caso todos estan en mantenimiento
            return null; 
        }    
        return serverMin;
    }
}
