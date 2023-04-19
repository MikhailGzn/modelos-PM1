package gida.simulators.labs.first.engine;

public class Estadistics {
    private long totalLandAircrafts; // total de aviones
    private double totalQueueTime;   // tiempo total de cola, el medio se saca de este
    private double maxQueueTime; // tiempo max cola
    private double totalTransitoryTime; // tiempo total de transito, el medio se saca de este
    private double maxTransitoryTime; // tiempo max transito
    private double totalOcio; // Con esto sacamos el porcentaje de ocio
    private double maxOcio;    // tiempo max ocio
    private long maxLengthQueue; // longitud maxima de cola 
    
    public Estadistics() {
        this.totalLandAircrafts = 0;
        this.totalQueueTime = 0;
        this.maxQueueTime = 0;
        this.totalTransitoryTime = 0;
        this.maxTransitoryTime = 0;
        this.totalOcio = 0;
        this.maxOcio = 0;
        this.maxLengthQueue = 0;
    }
    
    public long getTotalLandAircrafts() {
        return this.totalLandAircrafts;
    }
    //cuenta cuantos aviones llegan
    public void landAircraft() {
        this.totalLandAircrafts += 1;
    }

    public double getTotalQueueTime() {
        return this.totalQueueTime;
    }


    public void sumQueueTime(double totalQueueTime) {
        this.totalQueueTime += totalQueueTime;
    }

    public double getMaxQueueTime() {
        return this.maxQueueTime;
    }

    public void maxQueueTime(double maxQueueTime) {
        if(maxQueueTime > this.maxQueueTime)
            this.maxQueueTime = maxQueueTime;
    }

    public double getTotalTransitoryTime() {
        return this.totalTransitoryTime;
    }

    public void sumTotalTransitoryTime(double totalTransitoryTime) {
        this.totalTransitoryTime += totalTransitoryTime;
    }

    public double getMaxTransitoryTime() {
        return this.maxTransitoryTime;
    }

    public void maxTransitoryTime(double maxTransitoryTime) {
        if(maxTransitoryTime > this.maxTransitoryTime)
            this.maxTransitoryTime = maxTransitoryTime;
    }

    public double getTotalOcio() {
        return this.totalOcio;
    }

    public void sumTotalOcio(double totalOcio) {
        this.totalOcio += totalOcio;
    }

    public double getMaxOcio() {
        return this.maxOcio;
    }

    public void maxOcio(double maxOcio) {
        if(maxOcio > this.maxOcio)
            this.maxOcio = maxOcio;
    }

    public long getMaxLengthQueue() {
        return this.maxLengthQueue;
    }

    public void maxLengthQueue(long maxLengthQueue) {
        if(maxLengthQueue > this.maxLengthQueue)
            this.maxLengthQueue = maxLengthQueue;
    }

}
