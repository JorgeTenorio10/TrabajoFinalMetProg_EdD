package org.example.PantallaInicio;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametrizableController {
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
    private ParameterDataProperties model;
    private Stage scene;
    @FXML
    protected void onBotonIniciarClick(){
    }
    @FXML
    protected void onBotonGuardarClick(){
        model.commit();
    }
    @FXML
    protected void onBotonReiniciarClick(){
        model.rollback();
    }
    @FXML
    protected void onBotonCerrarClick(){
        scene.close();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");

        if (model != null) {
            this.updateGUIwithModel();
        }
    }
    protected void updateGUIwithModel() {
        sliderTamañoAltura.valueProperty().bindBidirectional(model.tamañoAlturaProperty());
        sliderTamañoAnchura.valueProperty().bindBidirectional(model.tamañoAnchuraProperty());
        sliderProbabilidadAparicionObjetos.valueProperty().bindBidirectional(model.probabilidadAparicionObjetosProperty());
        sliderProbabilidadReproduccion.valueProperty().bindBidirectional(model.probabilidadReproduccionProperty());
        sliderProbabilidadAumentoTesoro.valueProperty().bindBidirectional(model.probabilidadAumentoTesoroProperty());
        sliderProbabilidadAumentoBiblioteca.valueProperty().bindBidirectional(model.tamañoAnchuraProperty());
        sliderProbAparicionAgua.valueProperty().bindBidirectional(model.probAparicionAguaProperty());
        sliderProbAparicionComida.valueProperty().bindBidirectional(model.probAparicionComidaProperty());
        sliderProbAparicionBiblioteca.valueProperty().bindBidirectional(model.probAparicionBibliotecaProperty());
        sliderProbAparicionPozo.valueProperty().bindBidirectional(model.probAparicionPozoProperty());
        sliderProbAparicionMontaña.valueProperty().bindBidirectional(model.probAparicionMontañaProperty());
        sliderProbAparicionTesoro.valueProperty().bindBidirectional(model.probAparicionTesoroProperty());
    }
    public void loadUserData(ParameterDataProperties parametrosData){
        this.model=parametrosData;
        this.updateGUIwithModel();
    }

    public void setScene(Stage scene) {
        this.scene = scene;
    }
}