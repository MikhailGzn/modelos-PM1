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
        servers.add(new Airstrip(0,queues,new OneToOneQueuePolicy(),0,0));
        return servers;
     }
     public static List<Server> nServersNqueques(int cantLivianos, int cantMedios, int cantPesados){
        List<Server> servers = new ArrayList<>();
        List<Queue> queues0 = new ArrayList<>();
        queues0.add(new CustomQueue(0));
        servers.add(new Airstrip(0,queues0,new OneToOneQueuePolicy(),250,-1)); //Pista auxiliar
        servers.get(0).setInitOcio(0.0);        
        for (int i = 1; i < cantLivianos+1; i++) {
            List<Queue> queues = new ArrayList<>();
            queues.add(new CustomQueue(0));
            servers.add(new Airstrip(i,queues,new OneToOneQueuePolicy(),1000,0));
            servers.get(i).setInitOcio(0.0);
        }
        for (int i = cantLivianos+1; i < cantMedios+cantLivianos+1; i++) {
            List<Queue> queues = new ArrayList<>();
            queues.add(new CustomQueue(0));
            servers.add(new Airstrip(i,queues,new OneToOneQueuePolicy(),3000,1));
            servers.get(i).setInitOcio(0.0);
        }
        for (int i = cantMedios+cantLivianos+1; i < cantPesados+cantMedios+cantLivianos+1; i++) {
            List<Queue> queues = new ArrayList<>();
            queues.add(new CustomQueue(0));
            servers.add(new Airstrip(i,queues,new OneToOneQueuePolicy(),5000,2));
            servers.get(i).setInitOcio(0.0);
        }
        return servers;
     }
//    public static List<Server> OneServerMultipleQueues(int queuesQuantity) {}

//    public static List<Server> multipleServersOneQueue(int queuesQuantity) {}
}
