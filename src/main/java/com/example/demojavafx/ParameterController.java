package com.example.demojavafx;

import Recursos.Individuo;
import Recursos.Recursos;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.*;

public class ParameterController implements Initializable {

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
    private EstadoJuego estadoJuego;
    private List<Recursos> recursos;
    private ListaEnlazada<Individuo> individuos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Inicialización en ejecución del controlador de parámetros");
        sliderTamañoAltura.setMin(1);
        sliderTamañoAltura.setMax(10);
        sliderTamañoAltura.setValue(10); // Valor inicial

        sliderTamañoAnchura.setMin(1);
        sliderTamañoAnchura.setMax(20);
        sliderTamañoAnchura.setValue(20);

        sliderProbabilidadReproduccion.setMin(0);
        sliderProbabilidadReproduccion.setMax(100);
        sliderProbabilidadReproduccion.setValue(50);

        sliderProbabilidadAparicionObjetos.setMin(0);
        sliderProbabilidadAparicionObjetos.setMax(100);
        sliderProbabilidadAparicionObjetos.setValue(50);

        sliderProbabilidadAumentoTesoro.setMin(0);
        sliderProbabilidadAumentoTesoro.setMax(100);
        sliderProbabilidadAumentoTesoro.setValue(50);

        sliderProbabilidadAumentoBiblioteca.setMin(0);
        sliderProbabilidadAumentoBiblioteca.setMax(100);
        sliderProbabilidadAumentoBiblioteca.setValue(50);

        sliderProbAparicionComida.setMin(0);
        sliderProbAparicionComida.setMax(100);
        sliderProbAparicionComida.setValue(50);

        sliderProbAparicionAgua.setMin(0);
        sliderProbAparicionAgua.setMax(100);
        sliderProbAparicionAgua.setValue(50);

        sliderProbAparicionMontaña.setMin(0);
        sliderProbAparicionMontaña.setMax(100);
        sliderProbAparicionMontaña.setValue(50);

        sliderProbAparicionBiblioteca.setMin(0);
        sliderProbAparicionBiblioteca.setMax(100);
        sliderProbAparicionBiblioteca.setValue(50);

        sliderProbAparicionPozo.setMin(0);
        sliderProbAparicionPozo.setMax(100);
        sliderProbAparicionPozo.setValue(50);

        sliderProbAparicionTesoro.setMin(0);
        sliderProbAparicionTesoro.setMax(100);
        sliderProbAparicionTesoro.setValue(50);

        sliderTamañoAltura.valueProperty().addListener((observableValue, oldValue, newValue) -> actualizarTablero());
        sliderTamañoAnchura.valueProperty().addListener((observableValue, oldValue, newValue) -> actualizarTablero());
        sliderProbabilidadReproduccion.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbabilidadAparicionObjetos.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbabilidadAumentoTesoro.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbabilidadAumentoBiblioteca.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbAparicionComida.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbAparicionAgua.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbAparicionMontaña.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbAparicionBiblioteca.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbAparicionPozo.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
        sliderProbAparicionTesoro.valueProperty().addListener((observableValue, oldValue, newValue) -> generarNuevosRecursos());
    }

    private void generarNuevosRecursos() {
        if (tableroController != null) {
            int probabilidadReproduccion = (int) sliderProbabilidadReproduccion.getValue();
            int probabilidadAumentoTesoro = (int) sliderProbabilidadAumentoTesoro.getValue();
            int probabilidadAumentoBiblioteca = (int) sliderProbabilidadAumentoBiblioteca.getValue();
            int probabilidadAparicion = (int) sliderProbabilidadAparicionObjetos.getValue();
            int probAgua = (int) sliderProbAparicionAgua.getValue();
            int probComida = (int) sliderProbAparicionComida.getValue();
            int probMontaña = (int) sliderProbAparicionMontaña.getValue();
            int probPozo = (int) sliderProbAparicionPozo.getValue();
            int probBiblioteca = (int) sliderProbAparicionBiblioteca.getValue();
            int probTesoro = (int) sliderProbAparicionTesoro.getValue();
            tableroController.setNuevosRecursos(probabilidadAumentoTesoro, probabilidadReproduccion, probabilidadAumentoBiblioteca, probAgua, probabilidadAparicion, probComida, probMontaña, probPozo, probBiblioteca, probTesoro);
        }
    }

    private void actualizarTablero() {
        if (tableroController != null) {
            int tamañoAltura = (int) sliderTamañoAltura.getValue();
            int tamañoAnchura = (int) sliderTamañoAnchura.getValue();
            tableroController.setDimensions(tamañoAltura, tamañoAnchura);
        }
    }

    public void setEstadoJuego(EstadoJuego estadoJuego, List<Recursos> recursos, ListaEnlazada<Individuo> individuos) {
        this.estadoJuego = estadoJuego;
        // Asignar los recursos e individuos al tablero de juego
        // Configurar los controles con los valores del estado de juego
        textfieldNombre.setText(estadoJuego.getNombrePartida());
        sliderTamañoAltura.setValue(estadoJuego.getTamañoAltura());
        sliderTamañoAnchura.setValue(estadoJuego.getTamañoAnchura());
        sliderProbabilidadReproduccion.setValue(estadoJuego.getProbabilidadReproduccion());
        sliderProbabilidadAumentoTesoro.setValue(estadoJuego.getProbabilidadAumentoTesoro());
        sliderProbabilidadAumentoBiblioteca.setValue(estadoJuego.getProbabilidadAumentoBiblioteca());
        sliderProbabilidadAparicionObjetos.setValue(estadoJuego.getProbabilidadAparicion());
        sliderProbAparicionAgua.setValue(estadoJuego.getProbAgua());
        sliderProbAparicionComida.setValue(estadoJuego.getProbComida());
        sliderProbAparicionMontaña.setValue(estadoJuego.getProbMontaña());
        sliderProbAparicionPozo.setValue(estadoJuego.getProbPozo());
        sliderProbAparicionBiblioteca.setValue(estadoJuego.getProbBiblioteca());
        sliderProbAparicionTesoro.setValue(estadoJuego.getProbTesoro());
    }

    public void setRecursosIndividuos(List<Recursos> recursos, ListaEnlazada<Individuo> individuos) {
        this.recursos = recursos;
        this.individuos = individuos;
    }

    @FXML
    protected void onBotonGuardarClick() {
        // Lógica para guardar el estado del juego
        showAlert("Guardar partida", "La partida ha sido guardada correctamente.");
    }

    @FXML
    protected void onBotonReiniciarClick() {
        // Lógica para reiniciar el juego
        // model.rollback();
    }

    @FXML
    protected void onBotonCerrarClick() {
        // Lógica para cerrar la ventana
        Stage stage = (Stage) textfieldNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onAbrirTableroClick() {
        // Lógica para abrir el tablero
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tablero-view.fxml"));
            Parent root = fxmlLoader.load();
            TableroController tableroController = fxmlLoader.getController();
            tableroController.setDimensions((int) sliderTamañoAltura.getValue(), (int) sliderTamañoAnchura.getValue());
            tableroController.setNuevosRecursos((int) sliderProbabilidadAumentoTesoro.getValue(),
                    (int) sliderProbabilidadReproduccion.getValue(),
                    (int) sliderProbabilidadAumentoBiblioteca.getValue(),
                    (int) sliderProbAparicionAgua.getValue(),
                    (int) sliderProbabilidadAparicionObjetos.getValue(),
                    (int) sliderProbAparicionComida.getValue(),
                    (int) sliderProbAparicionMontaña.getValue(),
                    (int) sliderProbAparicionPozo.getValue(),
                    (int) sliderProbAparicionBiblioteca.getValue(),
                    (int) sliderProbAparicionTesoro.getValue());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Tablero del Juego");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loadUserData(ParameterDataModelProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }

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

    private ParameterDataModelProperties model;

    public void setStage(Stage stage) {
    }
}