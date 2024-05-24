package com.example.demojavafx;

import Recursos.Recursos;
import Recursos.Individuo;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Recursos.Comida;
import Recursos.Agua;
import Recursos.Pozo;
import Recursos.Montaña;
import Recursos.Biblioteca;
import Recursos.Tesoro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TableroController {
    private int tamañoAltura;
    private int tamañoAnchura;
    private int turno;
    private int n_individuos;

    @FXML
    private ComboBox<String> individuosComboBox;
    @FXML
    private Slider turnosDeVida;
    @FXML
    private Slider probReproduccion;
    @FXML
    private Slider probClonacion;
    MatrizDePosiciones MatrizIndividuos= new MatrizDePosiciones(tamañoAltura,tamañoAnchura);
    ElementoDeLaMatriz[][] listaIndividuos= MatrizIndividuos.getElementoDeLaMatriz();
    @FXML
    private Label welcomeText;

    @FXML
    private GridPane tableroDeJuego;

    @FXML
    private TextField posicionXTextField;
    private Random random = new Random();

    @FXML
    private TextField posicionYTextField;

    private ListaEnlazada<Individuo> listaEnlazada=new ListaEnlazada<>();

    private List<Recursos> recursos;

    @FXML
    private void initialize() {
        welcomeText.setText("Cargando el tablero de juego");
        // Añadir los tipos de individuos al ComboBox
        individuosComboBox.getItems().addAll("Básico", "Normal", "Avanzado");
        // Opcional: Seleccionar el primer elemento por defecto
        individuosComboBox.getSelectionModel().selectFirst();
        recursos = new ArrayList<>();
    }

    public void setDimensions(int altura, int anchura) {
        this.tamañoAltura = altura;
        this.tamañoAnchura = anchura;

        // Limpiar y configurar el tablero de juego
        tableroDeJuego.getChildren().clear();
        tableroDeJuego.getColumnConstraints().clear();
        tableroDeJuego.getRowConstraints().clear();

        int cellSize = 70; // Tamaño de cada celda

        // Inicializar el tablero con recursos aleatorios
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                VBox cellLayout = new VBox();
                cellLayout.setAlignment(Pos.CENTER);
                cellLayout.setMinSize(cellSize, cellSize);
                cellLayout.setMaxSize(cellSize, cellSize);
                cellLayout.setStyle("-fx-border-color: black;");

                // Generar recursos aleatorios
                int recursosEnCelda = cellLayout.getChildren().size();
                for (int k = 0; k < 3-recursosEnCelda; k++) {
                    if (random.nextDouble() < 0.2) { // Probabilidad de 20%
                        Label resourceLabel = new Label(obtenerTipoRecursoAleatorio());
                        cellLayout.getChildren().add(resourceLabel);

                        Recursos recurso = crearRecurso(resourceLabel.getText(), j, i);
                        recurso.setTiempoAparicion(random.nextInt(10) + 1);
                        recursos.add(recurso);
                    }
                }

                tableroDeJuego.add(cellLayout, j, i);
            }
        }
    }

    // Método para obtener un tipo de recurso aleatorio
    private String obtenerTipoRecursoAleatorio() {
        String[] tiposRecursos = {"Agua", "Comida", "Montaña", "Pozo", "Biblioteca", "Tesoro"};
        int index = random.nextInt(tiposRecursos.length);
        return tiposRecursos[index];
    }

    @FXML
    private void onInsertarIndividuoClick() {
        try {
            int x = Integer.parseInt(posicionXTextField.getText());
            int y = Integer.parseInt(posicionYTextField.getText());
            String tipoIndividuo = individuosComboBox.getValue();
            int turnosVida=(int)turnosDeVida.getValue();
            int probabilidadReproduccion=(int)probReproduccion.getValue();
            int probabilidadClonacion= (int)probClonacion.getValue();
            if (x > 0 && x <= tamañoAnchura && y > 0 && y <= tamañoAltura) {
                VBox cell = (VBox) getNodeByRowColumnIndex(y - 1, x - 1, tableroDeJuego);
                if (cell != null) {
                    if (cell.getChildren().stream().filter(node -> node instanceof Text).count() < 3) {
                        Text individuoText = new Text(tipoIndividuo);
                        n_individuos=n_individuos+1;
                        individuoText.setFill(Color.RED); // Color diferente para los individuos
                        individuoText.setFont(Font.font("System", 12));
                        cell.getChildren().add(individuoText);
                        Individuo i1= new Individuo(n_individuos,turno,turnosVida,probabilidadReproduccion, probabilidadClonacion, 100 - probabilidadReproduccion,x,y,tipoIndividuo);
                        ElementoLE<Individuo> ind= new ElementoLE<>(i1);
                        listaEnlazada.add(i1);
                        String contenidoLista=listaEnlazada.toString();
                        System.out.println(contenidoLista);
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

    @FXML
    private void onSiguienteTurnoButton() {
        pasarTurno();
    }

    private void pasarTurno() {
        // Decrementar el tiempo de vida de cada recurso y eliminarlos si su tiempo se ha agotado
        Iterator<Recursos> iterator = recursos.iterator();
        while (iterator.hasNext()) {
            Recursos recurso = iterator.next();
            recurso.setTiempoAparicion(recurso.getTiempoAparicion() - 1);
            if (recurso.getTiempoAparicion() <= 0) {
                iterator.remove();
                removerRecursoDeTablero(recurso);
            }
        }

        // Generar nuevos recursos según sus probabilidades
        generarNuevosRecursos();

        // Imprimir el estado actual de los recursos para depuración
        imprimirRecursos();
    }

    private void generarNuevosRecursos() {
        double probabilidadAparicion = 0.1; // Probabilidad de que aparezca un nuevo recurso en una celda
        double probAgua = 0.2;
        double probComida = 0.2;
        double probMontaña = 0.2;
        double probPozo = 0.2;
        double probBiblioteca = 0.1;
        double probTesoro = 0.1;

        for (int i = 0; i < tamañoAltura; i++) {
            for (int j = 0; j < tamañoAnchura; j++) {
                if (random.nextDouble() < probabilidadAparicion) {
                    double r = random.nextDouble();
                    String tipoRecurso;
                    if (r < probAgua) tipoRecurso = "Agua";
                    else if (r < probAgua + probComida) tipoRecurso = "Comida";
                    else if (r < probAgua + probComida + probMontaña) tipoRecurso = "Montaña";
                    else if (r < probAgua + probComida + probMontaña + probPozo) tipoRecurso = "Pozo";
                    else if (r < probAgua + probComida + probMontaña + probPozo + probBiblioteca) tipoRecurso = "Biblioteca";
                    else tipoRecurso = "Tesoro";

                    Recursos nuevoRecurso = crearRecurso(tipoRecurso, j, i);
                    recursos.add(nuevoRecurso);
                    agregarRecursoAlTablero(nuevoRecurso);
                }
            }
        }
    }
    private int generarTiempoAparicionAleatorio() {
        return random.nextInt(10)+1; // Genera un número aleatorio entre 1 y 10
    }

    private Recursos crearRecurso(String tipoRecurso, int x, int y) {
        int tiempoAparicion = generarTiempoAparicionAleatorio(); // Establecer el tiempo de vida del recurso
        switch (tipoRecurso) {
            case "Agua":
                return new Agua(x, y, tiempoAparicion);
            case "Comida":
                return new Comida(x, y, tiempoAparicion);
            case "Montaña":
                return new Montaña(x, y, tiempoAparicion);
            case "Pozo":
                return new Pozo(x, y, tiempoAparicion);
            case "Biblioteca":
                return new Biblioteca(x, y, tiempoAparicion);
            case "Tesoro":
                return new Tesoro(x, y, tiempoAparicion);
            default:
                throw new IllegalArgumentException("Tipo de recurso desconocido: " + tipoRecurso);
        }
    }

    private void agregarRecursoAlTablero(Recursos recurso) {
        VBox cell = (VBox) getNodeByRowColumnIndex(recurso.getY(), recurso.getX(), tableroDeJuego);
        if (cell != null) {
            Label resourceLabel = new Label(recurso.getClass().getSimpleName());
            cell.getChildren().add(resourceLabel);
        }
    }

    private void removerRecursoDeTablero(Recursos recurso) {
        VBox cell = (VBox) getNodeByRowColumnIndex(recurso.getY(), recurso.getX(), tableroDeJuego);
        if (cell != null) {
            cell.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().equals(recurso.getClass().getSimpleName()));
        }
    }

    private void imprimirRecursos() {
        System.out.println("Recursos actuales:");
        for (Recursos recurso : recursos) {
            System.out.println("Tipo: " + recurso.getClass().getSimpleName() +
                    ", Posición: (" + recurso.getX() + ", " + recurso.getY() + ")" +
                    ", Tiempo de vida restante: " + recurso.getTiempoAparicion());
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

    protected void onNextTurn() {
        MatrizDePosiciones MatrizIndividuos= new MatrizDePosiciones(this.tamañoAltura,this.tamañoAnchura);
        MatrizDePosicionesRecurso MatrizRecursos= new MatrizDePosicionesRecurso(this.tamañoAltura,this.tamañoAnchura);
        ElementoDeLaMatriz[][] listaIndividuos= MatrizIndividuos.getElementoDeLaMatriz();
        ElementoDeLaMatrizRecurso[][] listaRecursos = MatrizRecursos.getElementoDeLaMatrizRecurso();

        for (int i = 0; i < MatrizIndividuos.getAltura(); i++) {
            for(int j=0; j<MatrizIndividuos.getAnchura(); j++){
                if(listaIndividuos[i][j].getIndividuo3()!=null) {
                    listaIndividuos[i][j].getIndividuo3().setTurnosDeVida(Individuo.getTurnosDeVida() - 1);
                    if (listaIndividuos[i][j].getIndividuo3().getTurnosDeVida() <= 0) {
                        listaIndividuos[i][j].setIndividuo3(null);
                    }
                }

                 if(listaIndividuos[i][j].getIndividuo2()!=null){
                    listaIndividuos[i][j].getIndividuo2().setTurnosDeVida(Individuo.getTurnosDeVida()-1);
                    if (listaIndividuos[i][j].getIndividuo2().getTurnosDeVida()<=0){
                        listaIndividuos[i][j].setIndividuo2(null);
                        if(listaIndividuos[i][j].getIndividuo3()!=null){
                            listaIndividuos[i][j].setIndividuo2(listaIndividuos[i][j].getIndividuo3());
                            listaIndividuos[i][j].setIndividuo3(null);
                        }
                    }
                }
                 if(listaIndividuos[i][j].getIndividuo1()!=null){
                    listaIndividuos[i][j].getIndividuo1().
                            setTurnosDeVida(listaIndividuos[i][j].getIndividuo1().getTurnosDeVida()-1);
                    if (listaIndividuos[i][j].getIndividuo1().getTurnosDeVida()<=0){
                        listaIndividuos[i][j].setIndividuo1(null);
                        if(listaIndividuos[i][j].getIndividuo2()!=null){
                            listaIndividuos[i][j].setIndividuo1(listaIndividuos[i][j].getIndividuo2());
                            listaIndividuos[i][j].setIndividuo2(null);
                            if(listaIndividuos[i][j].getIndividuo3()!=null){
                                listaIndividuos[i][j].setIndividuo2(listaIndividuos[i][j].getIndividuo3());
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i <MatrizRecursos.getAltura(); i++) {
            for(int j=0; j<MatrizRecursos.getAnchura(); j++){
                if(MatrizRecursos.getRecurso3(i,j)!=null){

                }
            }
        }
//        for (int k = 0; k < listaIndividuos.getNumeroElementos(); k++) {
//            listaRecursos.getElemento(k).movimiento();
//        }
//        for (int p = 0; p < listaIndividuos.getNumeroElementos(); p++) {
//            for (int l = 0; l < listaRecursos.getNumeroElementos(); l++) {
//                if(listaIndividuos.getElemento(p).getPosicion==listaRecursos.getElemento(l).getPosicion){
//                    l.efecto(p);
//                }
//            }
//        }
//        for (int u = 0; u < listaPosiciones.getNumeroElementos(); u++) {
//            if(listaPosiciones.getNumeroIndividuos>=2){
//                int numeroAleatorio =(int)(Math.random()*100);
//                if (numeroAleatorio< Individuo.getProbabilidadReproduccion()){
//                    new Individuo, insertar(posicion)
//                }
//            }
//        }
//        for (int b = 0; b < listaIndividuos.getNumeroElementos(); b++) {
//            int numeroAleatorio=(int)(Math.random()*100);
//            if(numeroAleatorio<Individuo.getProbabilidadClonacion()){
//            Individuo.clonar();
//          }
//        }
//        for (int i=0; i<listaPosiciones.getNumeroElementos(); i++){
//            if(listaPosiciones.getNumeroIndividuos>=2){
//            probDeMuerte de los individuos que haya en la casilla
//            }
//        }
//        for(int i=0; i< listaPosiciones.getNumeroElementos();i++){
//            if(listaPosiciones.getNumeroDeRecursos<3){
//                probabilidades de los sliders
//            }
//        }

    }
}