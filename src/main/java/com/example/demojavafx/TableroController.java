package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TableroController {
    private int tamañoAltura;
    private int tamañoAnchura;

    @FXML
    private ComboBox<String> individuosComboBox;

    @FXML
    private Label welcomeText;

    @FXML
    private GridPane tableroDeJuego;

    @FXML
    private TextField posicionXTextField;

    @FXML
    private TextField posicionYTextField;

    @FXML
    private void initialize() {
        welcomeText.setText("Cargando el tablero de juego");
        // Añadir los tipos de individuos al ComboBox
        individuosComboBox.getItems().addAll("Básico", "Normal", "Avanzado");
        // Opcional: Seleccionar el primer elemento por defecto
        individuosComboBox.getSelectionModel().selectFirst();
    }

    public void setDimensions(int altura, int anchura) {
        this.tamañoAltura = altura;
        this.tamañoAnchura = anchura;

        // Limpiar y configurar el tablero de juego
        tableroDeJuego.getChildren().clear();
        tableroDeJuego.getColumnConstraints().clear();
        tableroDeJuego.getRowConstraints().clear();

        int cellSize = 70; // Tamaño de cada celda
        Random random = new Random();

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                VBox cellLayout = new VBox();
                cellLayout.setAlignment(Pos.CENTER);
                cellLayout.setMinSize(cellSize, cellSize);
                cellLayout.setMaxSize(cellSize, cellSize);
                cellLayout.setStyle("-fx-border-color: black;");

                // Añadir recursos a la celda
                String[] recursos = {"Agua", "Comida", "Montaña", "Pozo", "Biblioteca", "Tesoro"};
                int labelsToShow = random.nextInt(4); // Entre 0 y 3 recursos por celda
                for (int k = 0; k < labelsToShow; k++) {
                    Label resourceLabel = new Label(recursos[random.nextInt(recursos.length)]);
                    cellLayout.getChildren().add(resourceLabel);
                }

                tableroDeJuego.add(cellLayout, j, i);
            }
        }
    }

    @FXML
    private void onInsertarIndividuoClick() {
        try {
            int x = Integer.parseInt(posicionXTextField.getText());
            int y = Integer.parseInt(posicionYTextField.getText());
            String tipoIndividuo = individuosComboBox.getValue();

            if (x >= 0 && x < tamañoAnchura && y >= 0 && y < tamañoAltura) {
                VBox cell = (VBox) getNodeByRowColumnIndex(y, x, tableroDeJuego);
                if (cell != null) {
                    if (cell.getChildren().stream().filter(node -> node instanceof Text).count() < 3) {
                        Text individuoText = new Text(tipoIndividuo);
                        individuoText.setFill(Color.RED); // Color diferente para los individuos
                        individuoText.setFont(Font.font("System", 12));
                        cell.getChildren().add(individuoText);
                    } else {
                        showAlert("Error", "No se pueden agregar más de 3 individuos por casilla.");
                    }
                } else {
                    showAlert("Error", "No se pudo encontrar la celda especificada.");
                }
            } else {
                showAlert("Error", "Posición fuera de los límites del tablero.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor, introduce valores válidos para las posiciones.");
        }
    }

    private VBox getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (VBox) node;
            }
        }
        return null;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}