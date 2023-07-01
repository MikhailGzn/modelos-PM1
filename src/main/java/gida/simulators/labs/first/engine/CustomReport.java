package gida.simulators.labs.first.engine;
import java.util.Locale;

import gida.simulators.labs.first.resources.Server;
public class CustomReport implements Reportable {

    private String report;
    private double executeTime;

    private int contEntity = 0;
    private int contEntityXserver[] = {0,0,0,0,0,0,0,0,0,0};
    private int contEntityXtype[] = {0,0,0};
    
    private double totalWait = 0;
    private double totalWaitXtype[] = {0.0,0.0,0.0};
    private double maxWaitXtype[] = {0.0,0.0,0.0};
    private double maxWait = 0;

    private double totalOcio[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    private double maxOcio[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    
    private double totalTransitory = 0;
    private double maxTransitory = 0;
    private double totalTransitoryXtype[] = {0.0,0.0,0.0};
    private double maxTransitoryXtype[] = {0.0,0.0,0.0};

    private int maxQueue[] = {0,0,0,0,0,0,0,0,0,0};
    private double durabilidad[] = {250.0,1000.0,1000.0,1000.0,1500.0,1500.0,1500.0,1500.0,2000.0,2000.0};

    private int cantMaintenance = 0;

    public CustomReport(double executeTime){
        this.executeTime = executeTime;
    }
    /*------------------------------------------ */
    /*Funciones relacionadas con durabilidad */
    /*----------------------------------------- */
    public void setDurabilidadXserver(double durabilidad,int server){
        this.durabilidad[server]= durabilidad;
    }
    public double getDurabilidadXserver(int server){
        return this.durabilidad[server];
    }
    /*--------------------------------- */
    /*Funciones relacionadas con ocio */
    /*--------------------------------- */
    public double getTotalOcioXserver(int server){
        if(this.getContEntityXserver(server) == 0)
            return this.executeTime;
        else
            return this.totalOcio[server];
    }

    public void sumTotalOcioXserver(double totalOcio, int server){
        this.setMaxOcioXserver(totalOcio,server);
        this.totalOcio[server] += totalOcio;
    }
    public double getMaxOcioXserver(int server) {
        if(this.getContEntityXserver(server) == 0)
            return this.executeTime;
        else
            return this.maxOcio[server];
    }

    public void setMaxOcioXserver(double timeOcio, int server) {
        if(this.maxOcio[server] < timeOcio){
            this.maxOcio[server] = timeOcio;
        }
    }
    private double porcentajeOcioXserver(int server){
        if(this.executeTime == 0)
            return 0;
        else
            return (this.getTotalOcioXserver(server)/this.executeTime)*100;
    }
    
    private double porcentajeOcioMaxXserver(int server){
        if(this.getTotalOcioXserver(server) == 0)
            return 0;
        else
            return (this.getMaxOcioXserver(server)/this.getTotalOcioXserver(server))*100;
    }
    /*---------------------------------------------------------- */
    /* Funciones relacionadas con contar entidades */
    /*---------------------------------------------------------- */
    public int getContEntity(){
        return this.contEntity;
    }

    public void contEntity(){
        this.contEntity += 1;
    }

    public void contEntityXServer(int id){
        this.contEntityXserver[id] += 1;
    }
    public void contEntityXtype(int type){
        this.contEntityXtype[type] += 1;
    }
    public int getContEntityXserver(int id){
        return this.contEntityXserver[id];
    }
    public int getContEntityXtype(int type){
        return this.contEntityXtype[type];
    }
    /*---------------------------------------------------------- */
    /* Funciones relacionadas con tiempo de espera de entidades */
    /*---------------------------------------------------------- */
    public double getTotalWait(){
        return this.totalWait;
    }
    public double getTotalWaitXtype(int type){
        return this.totalWaitXtype[type];
    }

    public void sumTotalWait(double partWait){
        this.setMaxWait(partWait);
        this.totalWait += partWait;
    }

    public void sumTotalWaitXtype(double partWait,int type){
        this.setMaxWaitXtype(partWait,type);
        this.totalWaitXtype[type] += partWait;
    }

    public double getMaxWait(){
        return this.maxWait;
    }

    public void setMaxWait(double timeWait){
        if(this.maxWait < timeWait){
            this.maxWait = timeWait;
        }
    }

    public void setMaxWaitXtype(double timeWait,int type){
        if(this.maxWaitXtype[type] < timeWait){
            this.maxWaitXtype[type] = timeWait;
        }
    }

    private double meanTimeWait(){
        if(this.contEntity == 0)
            return 0;
        else
            return this.totalWait/this.contEntity;
    }
    private double meanTimeWaitXtype(int type){
        if(this.contEntityXtype[type] == 0)
            return 0;
        else
            return this.totalWaitXtype[type]/this.contEntityXtype[type];

    }
    /*----------------------------------------------- */
    /* Funciones relacionadas con tiempo de transito */
    /*----------------------------------------------- */
    public double getMaxTransitory(){
        return this.maxTransitory;
    }
    public double getMaxTransitoryXtype(int type){
        return this.maxTransitoryXtype[type];
    }
    private double getMeanTransitoryTime(){
        if(this.contEntity == 0)
            return 0;
        else
            return this.totalTransitory/this.contEntity;
    }
    private double getMeanTransitoryTimeXtype(int type){
        if(this.contEntityXtype[type] == 0)
            return 0;
        else
            return this.totalTransitoryXtype[type]/this.contEntityXtype[type];
    }
    
    public void setMaxTransitory(double timeTransitory){
        if(this.maxTransitory < timeTransitory){
            this.maxTransitory = timeTransitory;
        }
    }

    public void setMaxTransitoryXtype(double timeTransitory, int type){
        if(this.maxTransitoryXtype[type] < timeTransitory){
            this.maxTransitoryXtype[type] = timeTransitory;
        }
    }

    public double getTotalTransitory(){
        return this.totalTransitory;
    }

    public double getTotalTransitoryXtype(int type){
        return this.totalTransitoryXtype[type];
    }


    public void sumTrasitoryTime(double partTransitory){
        this.setMaxTransitory(partTransitory);
        this.totalTransitory += partTransitory;
    }
    

    public void sumTrasitoryTimeXtype(double partTransitory, int type){
        this.setMaxTransitoryXtype(partTransitory, type);
        this.totalTransitoryXtype[type] += partTransitory;
    }
    /*------------------------------------ */
    /* Funciones relacionadas con cola */
    /*------------------------------------*/
    public int getMaxQueue(int server) {
        return this.maxQueue[server];
    }

    public void setMaxQueue(int maxQueue, int server) {
        if(this.maxQueue[server] < maxQueue){
            this.maxQueue[server] = maxQueue;
        }
    }
    public void contCantMaintenance(){
        this.cantMaintenance += 1;
    }
    public int getCantMaintenance(){
        return this.cantMaintenance;
    }

    @Override
    public String[] generateReport() {        
        String[] reportStrings = {String.format(Locale.US, "%.2f", this.executeTime),
                          String.valueOf(this.getContEntity()),
                            "Cantidad entidades aircraft Livianas "+String.valueOf(this.getContEntityXtype(0)),
                            "Cantidad entidades aircraft Medias "+String.valueOf(this.getContEntityXtype(1)),
                            "Cantidad entidades aircraft Pesadas "+String.valueOf(this.getContEntityXtype(2)),
                            "Cantidad de entidades mantenimiento "+String.valueOf(this.getCantMaintenance()),
                            "\n",                            
                            "Total ocio 1 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(0)),
                            "Total ocio 2 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(1)),
                            "Total ocio 3 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(2)),
                            "Total ocio 4 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(3)),
                            "Total ocio 5 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(4)),
                            "Total ocio 6 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(5)),
                            "Total ocio 7 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(6)),
                            "Total ocio 8 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(7)),
                            "Total ocio 9 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(8)),
                            "Total ocio 10 "+String.format(Locale.US, "%.2f", this.getTotalOcioXserver(9)),                            
                            "\n",
                            "Porcentaje de ocio 1 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(0)),
                            "Porcentaje de ocio 2 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(1)),
                            "Porcentaje de ocio 3 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(2)),
                            "Porcentaje de ocio 4 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(3)),
                            "Porcentaje de ocio 5 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(4)),
                            "Porcentaje de ocio 6 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(5)),
                            "Porcentaje de ocio 7 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(6)),
                            "Porcentaje de ocio 8 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(7)),
                            "Porcentaje de ocio 9 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(8)),
                            "Porcentaje de ocio 10 "+String.format(Locale.US, "%.2f", this.porcentajeOcioXserver(9)),
                            "\n",
                            "Porcentaje de ocio Max 1 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(0)),
                            "Porcentaje de ocio Max 2 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(1)),
                            "Porcentaje de ocio Max 3 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(2)),
                            "Porcentaje de ocio Max 4 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(3)),
                            "Porcentaje de ocio Max 5 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(4)),
                            "Porcentaje de ocio Max 6 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(5)),
                            "Porcentaje de ocio Max 7 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(6)),
                            "Porcentaje de ocio Max 8 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(7)),
                            "Porcentaje de ocio Max 9 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(8)),
                            "Porcentaje de ocio Max 10 "+String.format(Locale.US, "%.2f", this.porcentajeOcioMaxXserver(9)),
                            "\n",
                            "Total durabilidad 1 "+String.format(Locale.US, "%.2f", this.durabilidad[0]),
                            "Total durabilidad 2 "+String.format(Locale.US, "%.2f", this.durabilidad[1]),
                            "Total durabilidad 3 "+String.format(Locale.US, "%.2f", this.durabilidad[2]),
                            "Total durabilidad 4 "+String.format(Locale.US, "%.2f", this.durabilidad[3]),
                            "Total durabilidad 5 "+String.format(Locale.US, "%.2f", this.durabilidad[4]),
                            "Total durabilidad 6 "+String.format(Locale.US, "%.2f", this.durabilidad[5]),   
                            "Total durabilidad 7 "+String.format(Locale.US, "%.2f", this.durabilidad[6]),
                            "Total durabilidad 8 "+String.format(Locale.US, "%.2f", this.durabilidad[7]),
                            "Total durabilidad 9 "+String.format(Locale.US, "%.2f", this.durabilidad[8]),
                            "Total durabilidad 10 "+String.format(Locale.US, "%.2f", this.durabilidad[9]),
                            "\n",
                            "Max Cola server 1 "+String.valueOf(this.getMaxQueue(0)),
                            "Max Cola server 2 "+String.valueOf(this.getMaxQueue(1)),
                            "Max Cola server 3 "+String.valueOf(this.getMaxQueue(2)),
                            "Max Cola server 4 "+String.valueOf(this.getMaxQueue(3)),
                            "Max Cola server 5 "+String.valueOf(this.getMaxQueue(4)),
                            "Max Cola server 6 "+String.valueOf(this.getMaxQueue(5)),
                            "Max Cola server 7 "+String.valueOf(this.getMaxQueue(6)),
                            "Max Cola server 8 "+String.valueOf(this.getMaxQueue(7)),
                            "Max Cola server 9 "+String.valueOf(this.getMaxQueue(8)),
                            "Max Cola server 10 "+String.valueOf(this.getMaxQueue(9)),
                            "\n",
                            "Total de entidades pista 1 "+String.valueOf(this.getContEntityXserver(0)),
                            "Total de entidades pista 2 "+String.valueOf(this.getContEntityXserver(1)),
                            "Total de entidades pista 3 "+String.valueOf(this.getContEntityXserver(2)),
                            "Total de entidades pista 4 "+String.valueOf(this.getContEntityXserver(3)),
                            "Total de entidades pista 5 "+String.valueOf(this.getContEntityXserver(4)),
                            "Total de entidades pista 6 "+String.valueOf(this.getContEntityXserver(5)),
                            "Total de entidades pista 7 "+String.valueOf(this.getContEntityXserver(6)),
                            "Total de entidades pista 8 "+String.valueOf(this.getContEntityXserver(7)),
                            "Total de entidades pista 9 "+String.valueOf(this.getContEntityXserver(8)),
                            "Total de entidades pista 10 "+String.valueOf(this.getContEntityXserver(9)),
                            "\n",
                            "Total de maximo de espera en cola "+ String.format(Locale.US, "%.2f", this.getTotalWait()),
                            "Tiempo de maximo de espera en cola liviano"+ String.format(Locale.US, "%.2f", this.getTotalWaitXtype(0)),
                            "Tiempo de maximo de espera en cola medio"+ String.format(Locale.US, "%.2f", this.getTotalWaitXtype(1)),
                            "Tiempo de maximo de espera en cola pesado"+ String.format(Locale.US, "%.2f", this.getTotalWaitXtype(2)),
                            "\n",
                            "Tiempo medio de espera en cola "+ String.format(Locale.US, "%.2f", this.meanTimeWait()),
                            "Tiempo medio de espera en cola liviano "+ String.format(Locale.US, "%.2f", this.meanTimeWaitXtype(0)),
                            "Tiempo medio de espera en cola medio "+ String.format(Locale.US, "%.2f", this.meanTimeWaitXtype(1)),
                            "Tiempo medio de espera en cola pesado" + String.format(Locale.US, "%.2f", this.meanTimeWaitXtype(2)),
                            "\n",
                            "Tiempo total de transito "+ String.format(Locale.US, "%.2f", this.getTotalTransitory()),
                            "Tiempo total de transito liviano "+ String.format(Locale.US, "%.2f", this.getTotalTransitoryXtype(0)),
                            "Tiempo total de transito medio "+ String.format(Locale.US, "%.2f", this.getTotalTransitoryXtype(1)),
                            "Tiempo total de transito pesado "+ String.format(Locale.US, "%.2f", this.getTotalTransitoryXtype(2)),
                            "\n",
                            "Tiempo medio de transito "+ String.format(Locale.US, "%.2f", this.getMeanTransitoryTime()),
                            "Tiempo medio de transito liviano "+ String.format(Locale.US, "%.2f", this.getMeanTransitoryTimeXtype(0)),
                            "Tiempo medio de transito medio "+ String.format(Locale.US, "%.2f", this.getMeanTransitoryTimeXtype(1)),
                            "Tiempo medio de transito peasdo "+ String.format(Locale.US, "%.2f", this.getMeanTransitoryTimeXtype(2)),
                            "\n",
                            "Tiempo maximo de transito "+String.format(Locale.US, "%.2f", this.getMaxTransitory()),
                            "Tiempo maximo de transito liviano "+String.format(Locale.US, "%.2f", this.getMaxTransitoryXtype(0)),
                            "Tiempo maximo de transito medio "+String.format(Locale.US, "%.2f", this.getMaxTransitoryXtype(1)),
                            "Tiempo maximo de transito pesado "+String.format(Locale.US, "%.2f", this.getMaxTransitoryXtype(2)),
                        };
        return reportStrings;
    }    
}

//this.report = report +                
//"                         %30.2f "+
/* "\n Cantidad total de aeronaves que aterrizaron  %30d "+
"\n Tiempo total de espera en cola               %30.2f "+
"\n Tiempo medio de espera en cola               %30.2f "+
"\n Tiempo máximo de espera en cola              %30.2f "+
"\n Tiempo total de transito                     %30.2f "+
"\n Tiempo medio de tránsito                     %30.2f "+
"\n Tiempo máximo de tránsito                    %30.2f "+
"\n Tiempo total de ocio de la pista             %30.2f "+
"\n Porcentaje de tiempo de ocio                 %30.2f "+ 
"\n Tiempo máximo de ocio de la pista            %30.2f "+
"\n Porcentaje de tiempo de ocio maximo          %30.2f "+ 
"\n Tamaño máximo de la cola de espera           %30d "; */
