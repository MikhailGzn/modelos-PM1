package gida.simulators.labs.first.resources;

import java.util.List;
import gida.simulators.labs.first.policies.ServerQueuePolicy;
import gida.simulators.labs.first.utils.CustomRandomizer;

public class Airstrip extends Server {
    private double durabilidad;    
    private Boolean maintBoolean = false;

    public Airstrip(int id, List<Queue> queues, ServerQueuePolicy serverQueuePolicy) {    
        super(id,queues,serverQueuePolicy);
        durabilidad = 1000;
    }
    public void desgastar(){
        CustomRandomizer random = new CustomRandomizer();
        durabilidad -= random.nextRandom();
    }
    public double getDurabilidad(){
        return durabilidad;
    }
     public Boolean getMaintBoolean() {
        return maintBoolean;
    }
    public void setMaintBoolean(Boolean maintBoolean) {
        this.maintBoolean = maintBoolean;
    }
    public void reparar(){
        durabilidad += durabilidad*0.15;
    }
    @Override
    public String toString() {
        String ret = super.toString();

        ret += "type: airstrip";

        return ret;
    }
}