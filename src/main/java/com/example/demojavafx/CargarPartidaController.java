package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CargarPartidaController {
    @FXML
    private TextField TextFieldCargarPartida;
    @FXML
    private Label respuesta;
    private ParameterDataModel parametrosData = new ParameterDataModel("Juan", 10, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
    private ParameterDataModelProperties modeloParaGUICompartido = new ParameterDataModelProperties(parametrosData);
    private ListaEnlazada<ParameterDataModel> parametrosDataList = new ListaEnlazada<>();


    @FXML
    public ListaEnlazada<ParameterDataModel> onCargarPartidaButton() {
        /*
        GameDataHandler gameDataHandler = new GameDataHandler();
        ListaEnlazada<ParameterDataModel> loadedData = gameDataHandler.loadGame();
        String nombrePartida = TextFieldCargarPartida.getText().trim();
        boolean partidaEncontrada = false;
        if (loadedData == null) {
            System.out.println("Failed to load game data.");
        } else {
            System.out.println("Game data loaded successfully.");
            if (nombrePartida.isEmpty()) {
                respuesta.setText("No has introducido ninguna partida que cargar");
            } else {
                partidaEncontrada = false;
                ElementoLE<ParameterDataModel> current = loadedData.getPrimero();
                // Recorrer la lista manualmente
                while (current != null) {
                    ParameterDataModel data = current.getData();
                    if (data.getNombre().equals(nombrePartida)) { // Comparar nombres
                        partidaEncontrada = true;
                        parametrosDataList = loadedData;
                        System.out.println("Partida cargada con éxito");
                        break; // Salir del bucle si se encuentra la partida
                    }
                    // Obtener el siguiente elemento en la lista
                    current = loadedData.getSiguiente(current);
                }
            }
            if (!partidaEncontrada) {
                respuesta.setText("No existe una partida con el nombre introducido");
            }

        }
    }

         */
        ListaEnlazada<ParameterDataModel> gameData = new ListaEnlazada<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_state.dat"))) {
            while (true) {
                try {
                    EstadoJuego gameState = (EstadoJuego) ois.readObject();
                    ParameterDataModel parametrosData = null;
                    parametrosData = new ParameterDataModel(gameState.getNombrePartida(),
                            gameState.getTamañoAltura(),
                            gameState.getTamañoAnchura(),
                            gameState.getRecursos(),
                            gameState.getIndividuos());
                    gameData.add(parametrosData);
                } catch (EOFException e) {
                    break;  // Fin del archivo alcanzado
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameData;
    }
}

