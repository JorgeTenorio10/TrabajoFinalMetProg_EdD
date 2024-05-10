package tablerofinal;

import javafx.application.Application;
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

public class MainTableroApplication extends Application {

    private static final Logger log = LogManager.getLogger(MainTableroApplication.class);

    private final String[] recursos = {"Agua", "Comida", "Turnos", "Montaña", "Pozo", "Tesoro"};
    private final int maxRecursosCelda = 3;
    @Override
    public void start(Stage primaryStage) {

        log.info("Inicio del método de arranque de la aplicación para mostrar un grid de forma programática");

        GridPane mainGrid = new GridPane();


        int n = 3;
        int m = 8;
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

    public static void main(String[] args) {
        launch(args);
    }
}

