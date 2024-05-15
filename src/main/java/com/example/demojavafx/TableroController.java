package com.example.demojavafx;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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

public class TableroController  {
    /*
    private int tamañoAltura;
    private int tamañoAnchura;

    public void setTamañoAltura(int tamañoAltura) {
        this.tamañoAltura = tamañoAltura;
    }

    public void setTamañoAnchura(int tamañoAnchura) {
        this.tamañoAnchura = tamañoAnchura;
    }

     */


    @FXML
    private Label welcomeText;

    @FXML
    private GridPane tableroDeJuego;

    @FXML
    protected void initialize() {
        welcomeText.setText("Cargando el tablero de juego");

        int n = 10;//tamañoAnchura; // Usar el valor de tamañoAnchura del slider
        int m = 10;//tamañoAltura; // Usar el valor de tamañoAltura del slider
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                VBox cellLayout = new VBox();
                cellLayout.setAlignment(Pos.CENTER);
                cellLayout.setMinSize(50, 50);
                cellLayout.setStyle("-fx-border-color: black;");

                List<String> recursos = new ArrayList<>(List.of("Agua", "Comida", "Turnos", "Montaña", "Pozo", "Tesoro"));
                Collections.shuffle(recursos);
                int labelsToShow = random.nextInt(4); // Entre 0 y 3 recursos por celda
                for (int k = 0; k < labelsToShow; k++) {
                    Label label = new Label(recursos.get(k));
                    cellLayout.getChildren().add(label);
                }

                tableroDeJuego.add(cellLayout, i, j);
            }
        }
    }

}

