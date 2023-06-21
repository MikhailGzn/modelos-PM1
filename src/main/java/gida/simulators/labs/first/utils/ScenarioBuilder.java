package gida.simulators.labs.first.utils;

import java.util.ArrayList;
import java.util.List;

import gida.simulators.labs.first.policies.OneToOneQueuePolicy;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.resources.CustomQueue;
import gida.simulators.labs.first.resources.Queue;
import gida.simulators.labs.first.resources.Server;

public class ScenarioBuilder {

    public static List<Server> OneServerOneQueue() {
        List<Server> servers = new ArrayList<>();
        List<Queue> queues = new ArrayList<>();
        queues.add(new CustomQueue(0));
        servers.add(new Airstrip(0,queues,new OneToOneQueuePolicy()));
        return servers;
     }
     public static List<Server> nServersNqueques(int n){
        List<Server> servers = new ArrayList<>();
        List<Queue> queues = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            queues.add(new CustomQueue(0));
            servers.add(new Airstrip(i,queues,new OneToOneQueuePolicy()));
            servers.get(i).setInitOcio(0.0);
        }
        return servers;
     }
//    public static List<Server> OneServerMultipleQueues(int queuesQuantity) {}

//    public static List<Server> multipleServersOneQueue(int queuesQuantity) {}
}
