package com.example.demojavafx;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ParameterController implements Initializable {


    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **/

    @FXML
    private TextField textfieldNombre;
    @FXML
    private Slider sliderTamañoAltura;
    @FXML
    private Slider sliderTamañoAnchura;
    @FXML
    private Slider sliderProbabilidadReproduccion;
    @FXML
    private Slider sliderProbabilidadAparicionObjetos;
    @FXML
    private Slider sliderProbabilidadAumentoTesoro;
    @FXML
    private Slider sliderProbabilidadAumentoBiblioteca;
    @FXML
    private Slider sliderProbAparicionComida;
    @FXML
    private Slider sliderProbAparicionAgua;
    @FXML
    private Slider sliderProbAparicionMontaña;
    @FXML
    private Slider sliderProbAparicionBiblioteca;
    @FXML
    private Slider sliderProbAparicionPozo;
    @FXML
    private Slider sliderProbAparicionTesoro;

    @FXML
    private GridPane tableroDeJuego;

    private TableroController tableroController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");
        if (model != null) {
            this.updateGUIwithModel();
        }
        sliderTamañoAltura.setMin(1);
        sliderTamañoAltura.setMax(10);
        sliderTamañoAltura.setValue(10); // Valor inicial

        sliderTamañoAnchura.setMin(1);
        sliderTamañoAnchura.setMax(20);
        sliderTamañoAnchura.setValue(20); // Valor inicial

        sliderTamañoAltura.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                actualizarTablero();
            }
        });

        sliderTamañoAnchura.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                actualizarTablero();
            }
        });
    }

    private void actualizarTablero() {
        if (tableroController != null) {
            int tamañoAltura = (int) sliderTamañoAltura.getValue();
            int tamañoAnchura = (int) sliderTamañoAnchura.getValue();
            tableroController.setDimensions(tamañoAltura, tamañoAnchura);
        }
    }


    /**
     * Controlador con modelo de datos en el que trabajar
     **/
    private ParameterDataModelProperties model;
    private Stage scene;


    /** Métodos de respuesta a eventos: El GUI llama a estos métodos del controlador para realizar operaciones **/
    /**
     * La convención es llamarlos on+TipoControl+operacionalaqueresponde :
     * onMiBotonEjemploClick indica que es un "manejador de evento de tipo click" del botón MiBotonEjemplo del interfaz
     */


    @FXML
    protected void onBotonGuardarClick() {
        model.commit();
    }

    @FXML
    protected void onBotonReiniciarClick() {
        model.rollback();
    }

    @FXML protected void onBotonCerrarClick(){
        scene.close();
    }
    public void setStage(Stage s){
        this.scene = s;
    }



@FXML
    protected void onAbrirTableroClick() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tablero-view.fxml"));
        Parent root = fxmlLoader.load();
        TableroController tableroController = fxmlLoader.getController();
        int altura = (int) sliderTamañoAltura.getValue();
        int anchura = (int) sliderTamañoAnchura.getValue();
        tableroController.setDimensions((int)sliderTamañoAltura.getValue(),(int)sliderTamañoAnchura.getValue());
        Stage stage = new Stage();
        int cellSize = 70;
        int windowWidth = anchura * cellSize + 70;
        int windowHeight = altura * cellSize + 150;
        Scene scene = new Scene(root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.setTitle("Tablero del Juego");
        stage.show();

        /* Configura el tamaño del tablero en el controlador del tablero
        if (sliderTamañoAltura != null && sliderTamañoAnchura != null) {
            tableroController.setTamañoAltura((int) sliderTamañoAltura.getValue());
            tableroController.setTamañoAnchura((int) sliderTamañoAnchura.getValue());
        }

        ParameterController parameterController = new ParameterController();

        // Carga los datos del modelo en el controlador de parámetros
        parameterController.loadUserData(model);

        */
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    /**
     * Métodos de configuración
     **/

    /**
     * Este método se encarga de conectar los datos del modelo con el GUI
     **/
    protected void updateGUIwithModel() {
        sliderTamañoAltura.valueProperty().bindBidirectional(model.tamañoAlturaProperty());
        sliderTamañoAnchura.valueProperty().bindBidirectional(model.tamañoAnchuraProperty());
        sliderProbabilidadAparicionObjetos.valueProperty().bindBidirectional(model.probabilidadAparicionObjetosProperty());
        sliderProbabilidadReproduccion.valueProperty().bindBidirectional(model.probabilidadReproduccionProperty());
        sliderProbabilidadAumentoTesoro.valueProperty().bindBidirectional(model.probabilidadAumentoTesoroProperty());
        sliderProbabilidadAumentoBiblioteca.valueProperty().bindBidirectional(model.probabilidadAumentoBibliotecaProperty());
        sliderProbAparicionAgua.valueProperty().bindBidirectional(model.probAparicionAguaProperty());
        sliderProbAparicionComida.valueProperty().bindBidirectional(model.probAparicionComidaProperty());
        sliderProbAparicionBiblioteca.valueProperty().bindBidirectional(model.probAparicionBibliotecaProperty());
        sliderProbAparicionPozo.valueProperty().bindBidirectional(model.probAparicionPozoProperty());
        sliderProbAparicionMontaña.valueProperty().bindBidirectional(model.probAparicionMontañaProperty());
        sliderProbAparicionTesoro.valueProperty().bindBidirectional(model.probAparicionTesoroProperty());
        textfieldNombre.textProperty().bindBidirectional(model.nombreProperty());
    }

    /**
     * Este método recibe los datos del modelo y los establece
     **/
    public void loadUserData(ParameterDataModelProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }

}
