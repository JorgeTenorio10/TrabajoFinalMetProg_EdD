package com.example.demojavafx;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TableroController {

    @FXML
    private Label welcomeText;

    @FXML
    private GridPane tableroDeJuego;

    private static final Logger log = LogManager.getLogger(TableroController.class);

    private final String[] recursos = {"Agua", "Comida", "Turnos", "Montaña", "Pozo", "Tesoro"};
    private final int maxRecursosCelda = 3;
    private Stage scene;

    @FXML
    protected void onTableroDeJuego() {
        welcomeText.setText("Cargando el tablero de juego");

        Stage stage = (Stage) tableroDeJuego.getScene().getWindow();

        int n = 14;
        int m = 8;
        Random random = new Random();

        double AnchuraCelda = Screen.getPrimary().getVisualBounds().getWidth() / n;
        double AlturaCelda = Screen.getPrimary().getVisualBounds().getHeight() / m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                VBox layout = new VBox();
                layout.setAlignment(Pos.CENTER);
                layout.setMinSize(AnchuraCelda - 1, AlturaCelda - 2);
                layout.setStyle("-fx-border-color: black; -fx-text-alignment: right;");
                List<String> RecursosAleatorios = new ArrayList<>(List.of(recursos));
                Collections.shuffle(RecursosAleatorios);
                int labelsToShow = random.nextInt(maxRecursosCelda + 1);
                for (int k = 0; k < labelsToShow; k++) {
                    Label label = new Label(RecursosAleatorios.get(k));
                    layout.getChildren().add(label);
                }
                tableroDeJuego.add(layout, i, j);
            }
        }

        stage.close(); // Cerrar la ventana actual después de cargar el tablero
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");

        log.info("Tablero cargado correctamente");
    }
    public void setStage(Stage s){
        this.scene = s;
    }
}