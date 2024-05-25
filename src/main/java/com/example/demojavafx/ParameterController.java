package com.example.demojavafx;


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
        sliderProbabilidadReproduccion.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbabilidadAparicionObjetos.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbabilidadAumentoTesoro.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbabilidadAumentoBiblioteca.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });sliderProbAparicionComida.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbAparicionAgua.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbAparicionMontaña.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbAparicionBiblioteca.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbAparicionPozo.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });
        sliderProbAparicionTesoro.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                generarNuevosRecursos();
            }
        });

    }
    private void generarNuevosRecursos() {
        if (tableroController != null){
            int probabilidadReproduccion =  (int) sliderProbabilidadReproduccion.getValue() ;
            int probabilidadAumentoTesoro =  (int) sliderProbabilidadAumentoTesoro.getValue() ;
            int probabilidadAumentoBiblioteca =  (int) sliderProbabilidadAumentoBiblioteca.getValue() ;
            int probabilidadAparicion = (int) sliderProbabilidadAparicionObjetos.getValue(); // Probabilidad de que aparezca un nuevo recurso en una celda
            int probAgua =  (int) sliderProbAparicionAgua.getValue() ;
            int probComida =  (int) sliderProbAparicionComida.getValue();
            int probMontaña =  (int) sliderProbAparicionMontaña.getValue();
            int probPozo =  (int) sliderProbAparicionPozo.getValue();
            int probBiblioteca =  (int) sliderProbAparicionBiblioteca.getValue();
            int probTesoro =  (int) sliderProbAparicionTesoro.getValue();
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
        /*try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_state.dat"))) {
            oos.writeObject(tableroController.getTamañoAltura());
            oos.writeObject(tableroController.getTamañoAnchura());
            oos.writeObject(tableroController.getRecursos());
            oos.writeObject(tableroController.getIndividuos());

            showAlert("Guardar partida", "La partida ha sido guardada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo guardar la partida.");
        }

         */
        String nombrePartida = textfieldNombre.getText(); // Obtener el nombre de la partida de algún lugar, por ejemplo, un campo de texto
        EstadoJuego gameState = new EstadoJuego(nombrePartida, tableroController.getTamañoAltura(),
                tableroController.getTamañoAnchura(),
                tableroController.getRecursos(),
                tableroController.getListaEnlazada());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_state.dat"))) {
            oos.writeObject(gameState);
            showAlert("Guardar partida", "La partida ha sido guardada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo guardar la partida.");
        }


    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
        int tamañoAltura = (int) sliderTamañoAltura.getValue();
        int tamañoAnchura = (int) sliderTamañoAnchura.getValue();
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
