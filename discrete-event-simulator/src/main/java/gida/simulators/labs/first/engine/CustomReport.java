package gida.simulators.labs.first.engine;

public class CustomReport implements Reportable {

    private String report = "==============================================================================================\n"+
    "                                        R E P O R T                                           \n"+
    "==============================================================================================\n"+
    "\n";
    private double executeTime;

    private int contEntity = 0;
    private double totalWait = 0;
    private double totalTransitory = 0;
    private double totalOcio = 0;
    private double maxWait = 0;
    private double maxTransitory = 0;
    private double maxOcio = 0;
    private int maxQueue = 0;

    public CustomReport(double executeTime){
        this.executeTime = executeTime;
    }

    public double getTotalWait(){
        return this.totalWait;
    }

    public void sumTotalWait(double partWait){
        this.setMaxWait(partWait);
        this.totalWait += partWait;
    }

    public double getTotalTransitory(){
        return this.totalTransitory;
    }

    public void sumTrasitoryTime(double partTransitory){
        this.setMaxTransitory(partTransitory);
        this.totalTransitory += partTransitory;
    }

    public double getTotalOcio(){
        return this.totalOcio;
    }

    public void sumTotalOcio(double totalOcio){
        this.setMaxOcio(totalOcio);
        this.totalOcio += totalOcio;
    }

    public int getContEntity(){
        return this.contEntity;
    }

    public void contEntity(){
        this.contEntity += 1;
    }

    public double getMaxWait(){
        return this.maxWait;
    }

    public void setMaxWait(double timeWait){
        if(this.maxWait < timeWait){
            this.maxWait = timeWait;
        }
    }

    public double getMaxTransitory(){
        return this.maxTransitory;
    }

    public void setMaxTransitory(double timeTransitory){
        if(this.maxTransitory < timeTransitory){
            this.maxTransitory = timeTransitory;
        }
    }

    public double getMaxOcio() {
        return this.maxOcio;
    }

    public void setMaxOcio(double timeOcio) {
        if(this.maxOcio < timeOcio){
            this.maxOcio = timeOcio;
        }
    }

    public int getMaxQueue() {
        return this.maxQueue;
    }

    public void setMaxQueue(int maxQueue) {
        if(this.maxQueue < maxQueue){
            this.maxQueue = maxQueue;
        }
    }
 
    @Override
    public void generateReport() {
        this.report = report +("                               DURACION DE LA SIMULACION: "+this.executeTime+
                "\n==============================================================================================\n"+
                "\nCantidad total de aeronaves que aterrizaron: "+this.getContEntity()+
                "\nTiempo total de espera en cola: "+this.getTotalWait()+
                "\nTiempo medio de espera en cola: "+this.getTotalWait()/this.getContEntity()+
                "\nTiempo máximo de espera en cola: "+this.getMaxWait()+
                "\nTiempo total de transito: "+this.getTotalTransitory()+
                "\nTiempo medio de tránsito: "+this.getTotalTransitory()/this.getContEntity()+
                "\nTiempo máximo de tránsito: "+this.getMaxTransitory()+
                "\nTiempo total de ocio de la pista: "+this.getTotalOcio()+
                "\nPorcentaje de tiempo de ocio respecto al tiempo de simulación: "+(this.getTotalOcio()*100)/this.executeTime+"%"+
                "\nTiempo máximo de ocio de la pista: "+this.getMaxOcio()+
                "\nPorcentaje de tiempo de ocio: "+this.getTotalOcio()/100+"%"+
                "\nTamaño máximo de la cola de espera: "+this.getMaxQueue()+
                "\n==============================================================================================\n");
        System.out.println(report);
    }

}
