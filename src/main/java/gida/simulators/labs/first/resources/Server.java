package gida.simulators.labs.first.resources;

import java.util.List;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.policies.ServerQueuePolicy;

public abstract class Server {

    private int id;
    private double initOcio;
    //private double totalOcio;
    //private double maxOcio;  
    private int lenQueue;

    private Entity currentEntity;
    private List<Queue> queues;
    
    private ServerQueuePolicy policy;
    
    public Server(int id, List<Queue> queues, ServerQueuePolicy policy) {
        this.id = id;
        this.lenQueue = 0;
        this.queues = queues;
        this.policy = policy;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entity getCurrentEntity() {
        return this.currentEntity;
    }

    public void setCurrentEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
    }

    public boolean isBusy() {
        return this.currentEntity == null ? false : true;
    }

    public boolean queuesEmpty() {
        return this.policy.queuesEmpty(this.queues);
    }
    // cuando encolas te fijas la long de cola
    public void enqueue(Entity entity) {
        this.policy.enqueue(this.queues, entity);
        setLenQueue(this.queues.get(0).size());
    }
    
    public Entity dequeue() {
        return this.policy.dequeue(this.queues);
    }

    public double getInitOcio() {
        return initOcio;
    }

    public void setInitOcio(double initOcio) {
        this.initOcio = initOcio;
    }
    // Para mi esto ya lo hace report

    // public double getTotalOcio() {
    //     return this.totalOcio;
    // }

    // public void sumTotalOcio(double totalOcio) {
    //     this.totalOcio += totalOcio;
    // }

    // public double getMaxOcio() {
    //     return this.maxOcio;
    // }

    // public void maxOcio(double maxOcio) {
    //     if(maxOcio > this.maxOcio)
    //         this.maxOcio = maxOcio;
    // }
     public int getLenQueue(){
        return this.lenQueue;
    }
    public void setLenQueue(int lenQueue) {        
        this.lenQueue = lenQueue;
    }

        @Override
    public String toString() {
        String ret = "id: " + this.id + " -- current entity: " + this.currentEntity.getId() + "\n"
                + "queues:\n";

        for (Queue q : queues)
            ret += "\t" + q.toString() + "\n";

        return ret;
    }
}