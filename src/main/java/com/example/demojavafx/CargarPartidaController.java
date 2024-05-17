package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CargarPartidaController {
    @FXML
    private TextField TextFieldCargarPartida;
    @FXML
    private Label respuesta;

    @FXML
    public void onCargarPartidaButton(){
        if(TextFieldCargarPartida.textProperty()==null){
            respuesta.setText("No has introducido ninguna partida que cargar");
        }
        //else if (TextFieldCargarPartida.textProperty()==){
          //  respuesta.setText("No existe una partida con el nombre introducido");
        //}
        //else if(TextFieldCargarPartida.textProperty()==){
        //aqui iria el abrir la partida que hemos introducido

        //}
    }
}
