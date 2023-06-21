package gida.simulators.labs.first.policies;
import java.util.List;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.Server;

public class MaintenancePolicy implements ServerSelectionPolicy{
    public  Server selectServer(List<Server> servers) { 
        Airstrip serverMin = (Airstrip)servers.get(0);
        Airstrip auxiliar;
        for (Server server : servers) {
            auxiliar = (Airstrip)server;       
            if (auxiliar.getDurabilidad() < serverMin.getDurabilidad()) {
                serverMin=auxiliar;
            }
        }        
        return serverMin;
    }
}
