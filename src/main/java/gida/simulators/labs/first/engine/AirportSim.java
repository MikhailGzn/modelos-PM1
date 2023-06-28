package gida.simulators.labs.first.engine;
import java.util.List;
import gida.simulators.labs.first.behaviors.*;
import gida.simulators.labs.first.entities.Aircraft;
import gida.simulators.labs.first.entities.AircraftType;
import gida.simulators.labs.first.events.Arrival;
import gida.simulators.labs.first.events.StopSimulation;
import gida.simulators.labs.first.policies.MaintenancePolicy;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.resources.Server;
import gida.simulators.labs.first.utils.Randomizer;
public class AirportSim extends Engine {

    private FutureEventList fel;
    private List<Server> servers;

    public AirportSim(double endClock, List<Server> servers, ServerSelectionPolicy policy,
            Randomizer randomizer, Reportable report) {
                super(report);
                this.fel = new FutureEventList();
                this.fel.insert(new StopSimulation(endClock, this));
                //Primer aircraft liviano
                this.fel.insert(new Arrival(0, new AircraftType(1,0, null), new ArrivalBehaviorLiviano(randomizer,40), new EndOfServiceBehaviorLiviano(randomizer), new DurabilidadBehaviorLiviano(randomizer, 0, 1),policy));
                //Primer aircraft Medio
                this.fel.insert(new Arrival(0, new AircraftType(1, 1,null), new ArrivalBehaviorMedio(randomizer,30), new EndOfServiceBehaviorMedio(randomizer), new DurabilidadBehaviorMedio(randomizer, 1, 4),policy));
                // Primer Aircraft Pesado
                this.fel.insert(new Arrival(0, new AircraftType(1, 2,null), new ArrivalBehaviorPesado(randomizer,60,2), new EndOfServiceBehavior(randomizer), new DurabilidadBehaviorPesado(randomizer, 3, 6),policy));
                // primer maintenance 
                MaintenanceBehavior maintenanceBehavior = new MaintenanceBehavior(randomizer,18000 , 1800); //5dias=18000min  0.5dias=1800min
                //Para no arrancar haciendo el maintenance, lo inserto en la fel con el tiempo del nextTime.
                this.fel.insert(new Arrival(maintenanceBehavior.nextTime(), new Aircraft(1, null), maintenanceBehavior, new EndOfMaintenanceBehavior(randomizer), new DurabilidadBehaviorLiviano(randomizer, -1, -1),new MaintenancePolicy()));
                this.servers = servers;
            }

    public FutureEventList getFEL(){
        if (this.fel == null)
            this.fel = new FutureEventList();
        return this.fel;
    }

    @Override
    public void run() {
        while(!this.getStop()){
            this.fel.getImminent().planificate(fel, servers,(CustomReport)this.getReportable());
        }
    }
}
