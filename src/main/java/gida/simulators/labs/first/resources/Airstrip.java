package gida.simulators.labs.first.resources;

import java.util.List;
import gida.simulators.labs.first.policies.ServerQueuePolicy;

public class Airstrip extends Server {
    private int type;// 0 = light, 1 = medium, 2 = heavy
    private double durabilidad;    
    private Boolean maintMode;

    public Airstrip(int id, List<Queue> queues, ServerQueuePolicy serverQueuePolicy,double durabilidad, int type) {    
        super(id,queues,serverQueuePolicy);
        this.durabilidad = durabilidad;
        this.type = type;
        this.maintMode = false;
    }
    public int getType() {
        return type;
    }
    
    public double getDurabilidad(){
        return durabilidad;
    }
     public Boolean getMaintMode() {
        return maintMode;
    }
    public void setMaintMode(Boolean maintBoolean) {
        this.maintMode = maintBoolean;
    }
    public void alterDurabilidad(Double change){
        durabilidad += change;
    }
    @Override
    public String toString() {
        String ret = super.toString();

        ret += "type: airstrip";

        return ret;
    }
}