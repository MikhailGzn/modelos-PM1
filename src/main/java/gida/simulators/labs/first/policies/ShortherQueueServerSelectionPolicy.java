package gida.simulators.labs.first.policies;

import java.util.List;

import gida.simulators.labs.first.resources.Server;

public class ShortherQueueServerSelectionPolicy implements ServerSelectionPolicy{
    @Override
    public  Server selectServer(List<Server> servers) { 
        Server serverMin = servers.get(0);
        for (Server server : servers) {
            if (server.getLenQueue() < serverMin.getLenQueue()) {
                serverMin=server;
            }
        }        
        return serverMin;
    }

}
