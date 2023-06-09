package gida.simulators.labs.first.policies;

import java.util.List;
import gida.simulators.labs.first.resources.Server;
import gida.simulators.labs.first.entities.Entity;
public class UniqueServerSelectionPolicy implements ServerSelectionPolicy {

    @Override
    public Server selectServer(List<Server> servers, Entity entity) {
        return servers.get(0);
    }

}
