module gida.simulators.labs.first {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    opens gida.simulators.labs.first to javafx.fxml;
    exports gida.simulators.labs.first;
}
