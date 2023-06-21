package gida.simulators.labs.first;

/*import com.gluonhq.charm.glisten.*;*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;

import javafx.scene.control.Button;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.Label;

import java.lang.ProcessBuilder;
import java.util.List;

import gida.simulators.labs.first.engine.AirportSim;
import gida.simulators.labs.first.engine.CustomReport;
import gida.simulators.labs.first.engine.Engine;
import gida.simulators.labs.first.engine.Reportable;
import gida.simulators.labs.first.policies.OneToManyQueuePolicy;
import gida.simulators.labs.first.policies.OneToOneQueuePolicy;
import gida.simulators.labs.first.policies.ServerSelectionPolicy;
import gida.simulators.labs.first.policies.ShortherQueueServerSelectionPolicy;
import gida.simulators.labs.first.policies.UniqueServerSelectionPolicy;
import gida.simulators.labs.first.resources.Server;
import gida.simulators.labs.first.utils.CustomRandomizer;
import gida.simulators.labs.first.utils.Randomizer;
import gida.simulators.labs.first.utils.ScenarioBuilder;

import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

//Esta clase controla la vista del reporte de la simulacion
public class ReportController {    
  private static final float SIMULATION_LENGHT = 40320f;
    // more fields for the other report data
  @FXML private Button simularButton;
  @FXML private Button graficosButton;
  @FXML private Button atrasButton;
  @FXML private TextArea reportTextArea;
  @FXML private Text reporteText;
  @FXML private Text simulationTime;
  @FXML private TextArea textArea;
  //Inicializa el controlador despues de que se cargue el archivo fxml
  @FXML //hace que se active automaticamente
  public void initialize() {
    String string = "";
    string += "Tiempo de simulacion: \n"
    +"Cantidad de aeronaves que aterrizaron: \n"
    +"Tiempo total de espera en cola: \n"
    +"Tiempo medio de espera en cola: \n"
    + "Tiempo máximo de espera en cola: \n"
    + "Tiempo total de transito: \n"
    + "Tiempo medio de tránsito: \n"
    + "Tiempo máximo de tránsito: \n"
    + "Tiempo total de ocio de la pista: \n"
    + "Porcentaje de tiempo de ocio: \n"
    + "Tiempo máximo de ocio de la pista: \n"
    + "Porcentaje de tiempo de ocio maximo: \n"
    + "Tamaño máximo de la cola de espera: \n";    
    textArea.setVisible(false);
    textArea.setText(string);
    textArea.setEditable(false);
    reportTextArea.setVisible(false); 
    reportTextArea.setEditable(false);    
    atrasButton.setVisible(false);
    reporteText.setVisible(false);        
  }

  //Muestra un reporte de una simulacion
  public void handleButtonSimular(ActionEvent event) {    
    String[] reporte;        
    //Simula
    Engine engine = new AirportSim(SIMULATION_LENGHT, ScenarioBuilder.nServersNqueques(3), new ShortherQueueServerSelectionPolicy(), new CustomRandomizer(), new CustomReport(SIMULATION_LENGHT));    
    engine.run();      
    reporte = engine.getReportable().generateReport();    
    reportTextArea.setText(String.join("\n", reporte));
    
    
    
    simularButton.setVisible(false);
    graficosButton.setVisible(false);
    atrasButton.setVisible(true);
    textArea.setVisible(true);
    reportTextArea.setVisible(true);
    reporteText.setVisible(true);
  }  
  
  // este boton vuelve al menu principal cuando abrimos el reporte
  public void handleButtonAtras(ActionEvent event) {
    // hacer algo cuando se hace clic en el botón
    simularButton.setVisible(true);
    graficosButton.setVisible(true);
    reportTextArea.setVisible(false);
    atrasButton.setVisible(false);
    reporteText.setVisible(false);
    textArea.setVisible(false);
  }  

  // Este boton debe simular x veces y guardar los datos en un archivo csv, luego debe ejecutar el script de python
  public void handleButtonGraficos(ActionEvent event){
    // hacer algo cuando se hace clic en el botón
    try{
      String csvFile = "src\\main\\java\\gida\\simulators\\labs\\results\\report.csv";
      
      FileWriter writer = new FileWriter(csvFile);
      // Inicializa CSVWriter
      CSVWriter csvWriter = new CSVWriter(writer);
      String [] head = {"Tiempo de simulacion"
      , "Cantidad de aeronaves que aterrizaron"
      ,"Tiempo total de espera en cola"
      ,"Tiempo medio de espera en cola"
      , "Tiempo máximo de espera en cola"
      , "Tiempo total de transito"
      , "Tiempo medio de tránsito"
      , "Tiempo máximo de tránsito"
      , "Tiempo total de ocio de la pista"
      , "Porcentaje de tiempo de ocio"
      , "Tiempo máximo de ocio de la pista"
      , "Porcentaje de tiempo de ocio maximo"
      , "Tamaño máximo de la cola de espera"};
      csvWriter.writeNext(head);
      for(int i=0; i<1024; i++){
        Engine engine = new AirportSim(SIMULATION_LENGHT, ScenarioBuilder.nServersNqueques(3), new ShortherQueueServerSelectionPolicy(), new CustomRandomizer(), new CustomReport(SIMULATION_LENGHT));
        engine.run();      
        //Escribe el reporte      
        csvWriter.writeNext(engine.getReportable().generateReport());          
      }
    //cierra recursos  
    csvWriter.close();
    writer.close();    
    } catch (IOException e) {
    System.out.println("Error al crear el archivo csv");
    }
    //Intanta abrir el archivo python con el python portable
    try {
      ProcessBuilder pb = new ProcessBuilder("Python-Portable-3.9.6\\apps\\python.exe", "src\\main\\java\\gida\\simulators\\labs\\first\\python\\manejoDatos.py");
      pb.inheritIO();
      Process p = pb.start();
      p.waitFor();
    } catch (Exception e) {
      System.out.println("Error al ejecutar el script de python");
    }
    
    try {
      String archivoImagen = "src\\main\\java\\gida\\simulators\\labs\\results\\report.png";
      ProcessBuilder pb = new ProcessBuilder();
      pb.inheritIO();
      pb.command("cmd.exe", "/c", "start", archivoImagen);
      pb.start();
    }catch (Exception e) {
      System.out.println("Error al abrir la imagen");
    }
  }  
}

