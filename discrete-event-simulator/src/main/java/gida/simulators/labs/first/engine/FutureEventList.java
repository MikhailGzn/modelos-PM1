package gida.simulators.labs.first.engine;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import gida.simulators.labs.first.events.Arrival;
import gida.simulators.labs.first.events.EndOfService;
import gida.simulators.labs.first.events.Event;
import gida.simulators.labs.first.utils.Order;

public class FutureEventList {

    private PriorityQueue<Event> fel;

    public FutureEventList() {
        this.fel = new PriorityQueue<>(new Order());
    }

    public void insert(Event event) {
        this.fel.add(event);      
    }

    public Event getImminent() {
        return this.fel.poll();
    }

    @Override
    public String toString() {
        String ret =
                "============================================================== F E L ==============================================================\n";

        Iterator<Event> it = this.fel.iterator();

        while (it.hasNext()) {
            ret += it.next().toString() + "\n";
        }

        ret += "***********************************************************************************************************************************\n\n";

        return ret;
    }
}
