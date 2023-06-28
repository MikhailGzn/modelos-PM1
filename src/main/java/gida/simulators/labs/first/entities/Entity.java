package gida.simulators.labs.first.entities;

import java.util.ArrayList;
import java.util.List;

import gida.simulators.labs.first.events.Arrival;
import gida.simulators.labs.first.events.Event;
import gida.simulators.labs.first.resources.Server;

public abstract class Entity {

    private final int id;
    private double initWait;
    private double waitTime;
    private double transitory;

    private Server server;
    private List<Event> events;
    

    public Entity(int id, Arrival arrival) {
        this.id = id;
        this.events = new ArrayList<>();
        this.events.add(arrival);
    }

    public int getId() {
        return this.id;
    }

    public Server getServer() {
        return this.server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public double getInitWait() {
        return initWait;
    }

    public void setInitWait(double initWait) {
        this.initWait = initWait;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    public double getTransitory() {
        return transitory;
    }

    public void setTransitory(double transitory) {
        this.transitory = transitory;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void addEvent(Event event) {
        this.events.add(event.getOrder(),event);
    }
}
