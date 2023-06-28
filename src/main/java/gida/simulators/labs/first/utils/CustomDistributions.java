package gida.simulators.labs.first.utils;
import gida.simulators.labs.first.entities.Entity;
import gida.simulators.labs.first.entities.Maintenance;
import gida.simulators.labs.first.resources.Airstrip;
import gida.simulators.labs.first.entities.AircraftType;
public class CustomDistributions {
    public static double calculateDurabilidad(Entity entity, Airstrip server, Randomizer rand){
        double change = 0;        
        if (AircraftType.class.equals(entity.getClass())) {
            change = - rand.nextRandom();
        /* } else if (MediumAircraft.class.equals(entity.getClass())) {            
            change = -rand.nextRandom();
        } else if (HeavyAircraft.class.equals(entity.getClass())) {
            change = - rand.nextRandom();*/
        } else if (Maintenance.class.equals(entity.getClass())){
            change = server.getDurabilidad() * 0.15;
        }
        return change;
    }

}
