package gida.simulators.labs.first.entities;

import gida.simulators.labs.first.events.Arrival;

public class Maintenance extends Entity{
    public Maintenance(int id, Arrival arrival) {
        super(id,arrival);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().getName().equals("Maintenance") || obj.getClass().getName().equals("gida.simulators.labs.first.entities.Maintenance");
    }
    @Override
    public String toString() {
        String ret = super.toString();

        ret += "type: maintenance";

        return ret;
    }   
}
