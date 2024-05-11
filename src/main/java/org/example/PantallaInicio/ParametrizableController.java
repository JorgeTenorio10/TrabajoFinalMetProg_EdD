package org.example.PantallaInicio;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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

import java.net.URL;
import java.util.*;

public class ParametrizableController implements Initializable {
    @FXML
    private TextField campoNombre;
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
    private static final Logger log = LogManager.getLogger(MainGridApplication.class);

    private final String[] recursos = {"Agua", "Comida", "Turnos", "Montaña", "Pozo", "Tesoro"};
    private final int maxRecursosCelda = 3;
    private ParameterData parameterData;
    @FXML
    protected void onBotonIniciarClick(){
        log.info("Inicio del método de arranque de la aplicación para mostrar un grid de forma programática");
        Stage primaryStage = null;
        GridPane mainGrid = new GridPane();


        int n = parameterData.getTamañoAnchura();
        int m = parameterData.getTamañoAnchura();
        Random random = new Random();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double AnchuraCelda = screenBounds.getWidth() / n;
        double AlturaCelda = screenBounds.getHeight() / m;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                VBox layout = new VBox();
                layout.setAlignment(Pos.CENTER);
                layout.setMinSize(AnchuraCelda-1, AlturaCelda-2);
                layout.setStyle("-fx-border-color: black; -fx-text-alignment: right;");
                List<String> RecursosAleatorios = new ArrayList<>(List.of(recursos));
                Collections.shuffle(RecursosAleatorios);
                int labelsToShow = random.nextInt(maxRecursosCelda + 1);
                for (int k = 0; k < labelsToShow; k++) {
                    Label label = new Label(RecursosAleatorios.get(k));
                    layout.getChildren().add(label);
                }


                mainGrid.add(layout, i, j);

            }
        }


        Scene scene = new Scene(mainGrid, screenBounds.getWidth()+5, screenBounds.getHeight());
        primaryStage.setTitle("Tablero del juego");
        primaryStage.setScene(scene);
        primaryStage.show();



        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");


        log.info("Fin del método de arranque de la aplicación para mostrar un grid de forma programática");

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
        campoNombre.textProperty().bindBidirectional(model.nombreProperty());
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