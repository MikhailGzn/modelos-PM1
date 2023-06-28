package gida.simulators.labs.first.entities;
import gida.simulators.labs.first.events.Arrival;
public class AircraftType extends Aircraft{
    private int type;
    public AircraftType(int id, int type, Arrival arrival) {
        super(id, arrival);        
        this.type = type; //light = 0, medium = 1, heavy = 2
    }
    public int getType() {
        return type;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.getClass().getName().equals("AircraftType") || obj.getClass().getName().equals("gida.simulators.labs.first.entities.AircraftType");
    }

    @Override
    public String toString() {
        return "type: aircraft - id: " + this.getId() + " - type: " + this.getType();
    }
}
