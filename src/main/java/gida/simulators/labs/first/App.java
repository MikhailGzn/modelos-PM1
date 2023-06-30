package gida.simulators.labs.first;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.fxml.*;
public class App extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("report.fxml"));
        Parent root = loader.load();
        ReportController controller = loader.getController();
        
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulacion de Aeropuerto (v2.0)");
        primaryStage.show();
     }    
    public static void main(String[] args) {

        javafx.application.Application.launch(args);
    }
}