package gida.simulators.labs.first.engine;

public class CustomReport implements Reportable {

<<<<<<< HEAD
    private String report = ""+
    "================================================================================\n"+
    "|                               R E P O R T                                    |\n"+
    "================================================================================";
=======
    private String report = "==============================================================================================\n"+
    "                                        R E P O R T                                           \n"+
    "==============================================================================================\n"+
    "\n";
    
>>>>>>> 2b0a69a6daf7b3fe8bc4836634940566b5876f1c
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
        this.maxQueue = maxQueue;         //Verifica el maximo en server
    }
    private double porcentajeOcio(){
        if(this.executeTime == 0)
            return 0;
        else
            return (this.totalOcio/this.executeTime)*100;
    }
    
    private double porcentajeOcioMax(){
        if(this.getTotalOcio() == 0)
            return 0;
        else
            return (this.getMaxOcio()/this.getTotalOcio())*100;
    }
    private double meanTimeWait(){
        if(this.contEntity == 0)
            return 0;
        else
            return this.totalWait/this.contEntity;
    }
    private double meanTransitoryTime(){
        if(this.contEntity == 0)
            return 0;
        else
            return this.totalTransitory/this.contEntity;
    }
    @Override
    public void generateReport() {
<<<<<<< HEAD
        this.report = report +                
                "\n| Tiempo de simulación                        | %30.2f |"+
                "\n| Cantidad total de aeronaves que aterrizaron | %30d |"+
                "\n| Tiempo total de espera en cola              | %30.2f |"+
                "\n| Tiempo medio de espera en cola              | %30.2f |"+
                "\n| Tiempo máximo de espera en cola             | %30.2f |"+
                "\n| Tiempo total de transito                    | %30.2f |"+
                "\n| Tiempo medio de tránsito                    | %30.2f |"+
                "\n| Tiempo máximo de tránsito                   | %30.2f |"+
                "\n| Tiempo total de ocio de la pista            | %30.2f |"+
                "\n| Porcentaje de tiempo de ocio                | %30.2f |"+ 
                "\n| Tiempo máximo de ocio de la pista           | %30.2f |"+
                "\n| Porcentaje de tiempo de ocio maximo         | %30.2f |"+ 
                "\n| Tamaño máximo de la cola de espera          | %30d |"+
                "\n================================================================================\n";
        System.out.printf(report,this.executeTime,this.getContEntity(),this.getTotalWait(),this.meanTimeWait(),this.getMaxWait(),
        this.getTotalTransitory(),this.meanTransitoryTime(),this.getMaxTransitory(),this.getTotalOcio(),this.porcentajeOcio(),
        this.getMaxOcio(),this.porcentajeOcioMax(),this.getMaxQueue());
=======
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
                "\nPorcentaje de tiempo de ocio maximo: "+(this.getMaxOcio()/this.getTotalOcio())*100+"%"+
                "\nTamaño máximo de la cola de espera: "+this.getMaxQueue()+
                "\n==============================================================================================\n");
        System.out.println(report);
>>>>>>> 2b0a69a6daf7b3fe8bc4836634940566b5876f1c
    }

}
