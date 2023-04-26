package gida.simulators.labs.first.resources;

import java.util.LinkedList;

import gida.simulators.labs.first.entities.Entity;

public class CustomQueue implements Queue {

    private int id;
    private java.util.Queue<Entity> queue;
    
    public CustomQueue(int id){
        this.id = id;
        this.queue = new LinkedList<>();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public void enqueue(Entity entity) {
        queue.add(entity);
        
    }

    @Override
    public Entity checkNext() {
        return queue.peek();
    }

    @Override
    public Entity next() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return "id: " + this.id + " -> " + this.queue.toString();
    }
    @Override
    public int size() {
        return this.queue.size();
    }
}
