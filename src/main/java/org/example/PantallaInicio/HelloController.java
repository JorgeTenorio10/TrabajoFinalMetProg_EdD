package org.example.PantallaInicio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {
    protected StringProperty texto = new SimpleStringProperty("No Hay Nada");
    protected IntegerProperty medida = new SimpleIntegerProperty(0);
    @FXML
    protected void onNuevaPartidaClick(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(org.example.PantallaInicio.HelloApplication.class.getResource("parametrizable.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 240, 320);
            stage.setTitle("Menu " );
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected void onCargarPartidaClick(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("parametrizable.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 250, 320);
            stage.setTitle("Menu " );
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
