
package com.example.demojavafx;

import Recursos.Individuo;

import Recursos.*;
import Recursos.Recursos;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
        import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TableroController {
    private int tamañoAltura;
    private int tamañoAnchura;

    private int turno = 1;
    private int n_individuos;

    @FXML
    private ComboBox<String> individuosComboBox;
    @FXML
    private Slider turnosDeVida;
    @FXML
    private Slider probReproduccion;
    @FXML
    private Slider probClonacion;
    MatrizDePosiciones MatrizIndividuos = new MatrizDePosiciones(tamañoAltura, tamañoAnchura);
    ElementoDeLaMatriz[][] listaIndividuos = MatrizIndividuos.getElementoDeLaMatriz();
    @FXML
    private Label welcomeText;
    private ListaEnlazada<Individuo> individuosAñadir=new ListaEnlazada<>();

    @FXML
    private GridPane tableroDeJuego;

    @FXML
    private TextField posicionXTextField;
    private Random random = new Random();

    @FXML
    private TextField posicionYTextField;

    private ListaEnlazada<Individuo> listaEnlazada = new ListaEnlazada<>();

    private List<Recursos> recursos;
    private int probAgua;
    private int probabilidadAparicion;
    private int probComida;
    private int probMontaña;
    private int probPozo;
    private int probTesoro;
    private int probBiblioteca;

    private int probabilidadAumentoTesoro;
    private int probabilidadReproduccion;
    private int probabilidadAumentoBiblioteca;

    @FXML
    private void initialize() {
        welcomeText.setText("Cargando el tablero de juego");
        // Añadir los tipos de individuos al ComboBox
        individuosComboBox.getItems().addAll("Básico", "Normal", "Avanzado");
        // Opcional: Seleccionar el primer elemento por defecto
        individuosComboBox.getSelectionModel().selectFirst();
        recursos = new ArrayList<>();
    }
    public void setNuevosRecursos(int pAumentoTesoro, int pReproduccion, int pAumentoBiblioteca,int pAgua, int pAparicion, int pComida, int pMontaña, int pPozo, int pBiblioteca, int probTesoro){
        this.probabilidadAumentoTesoro = pAumentoTesoro;
        this.probabilidadReproduccion = pReproduccion;
        this.probabilidadAumentoBiblioteca = pAumentoBiblioteca;
        this.probAgua = pAgua;
        this.probabilidadAparicion = pAparicion;
        this.probComida = pComida;
        this.probMontaña = pMontaña;
        this.probPozo = pPozo;
        this.probBiblioteca = pBiblioteca;
        this.probTesoro = probTesoro;
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
                for (int k = 0; k < 3 - recursosEnCelda; k++) {
                    if (random.nextDouble() < 0.2) { // Probabilidad de 20%
                        Label resourceLabel = new Label(obtenerTipoRecursoAleatorio());
                        cellLayout.getChildren().add(resourceLabel);

                        Recursos recurso = crearRecurso(resourceLabel.getText(), j, i);
                        recurso.setTiempoAparicion(random.nextInt(10) + 1);
                        recursos.add(recurso);
                    }
                }
                int row = i;
                int column = j;
                cellLayout.setOnMouseClicked(event -> onCellClicked(row, column));

                tableroDeJuego.add(cellLayout, j, i);
            }
        }
    }
    public void setRecursosIndividuos(){
        tableroDeJuego.getChildren().clear();

        // Agregar los recursos al tablero
        for (Recursos recurso : recursos) {
            agregarRecursoAlTablero(recurso);
        }

        // Agregar los individuos al tablero
        for (int i = 0; i < listaEnlazada.getNumeroElementos(); i++) {
            agregarIndividuoAlTablero(listaEnlazada.getElemento(i).getData());
        }
    }

    private void onCellClicked(int row, int column) {
        StringBuilder info = new StringBuilder();
        info.append("Celda clickeada: (").append(column+1).append(", ").append(row+1).append(")\n");
        info.append("Recursos:\n");
        for (Recursos recurso : recursos) {
            if (recurso.getX() == column && recurso.getY() == row) {
                info.append("- Tipo: ").append(recurso.getClass().getSimpleName())
                        .append(", Tiempo de vida restante: ").append(recurso.getTiempoAparicion()).append("\n");
            }
        }
        // Obtener información de los individuos en esta celda
        info.append("Individuos:\n");
        for(int j=0; j<listaEnlazada.getNumeroElementos(); j++) {
            if(listaEnlazada.getElemento(j).getData().getX()==column +1 && listaEnlazada.getElemento(j).getData().getY()==row+1){
                info.append(listaEnlazada.getElemento(j).getData());
            }
        }

        // Mostrar la información en una alerta
        showAlert("Información de la Celda", info.toString());
    }

    // Método para obtener un tipo de recurso aleatorio
    private String obtenerTipoRecursoAleatorio() {
        String[] tiposRecursos = {"Agua", "Comida", "Montaña", "Pozo", "Biblioteca", "Tesoro"};
        int index = random.nextInt(tiposRecursos.length);
        return tiposRecursos[index];
    }
    public void reproduccionIndividuos(){
        float random= (float) ((Math.random()*10000)+1);
        for(int i=0; i<listaEnlazada.getNumeroElementos();i++){
            Individuo ind1= listaEnlazada.getElemento(i).getData();
            for(int j=i+1; j<listaEnlazada.getNumeroElementos();j++){
                Individuo ind2= listaEnlazada.getElemento(j).getData();
                if(listaEnlazada.getElemento(i).getData().getX()==listaEnlazada.getElemento(j).getData().getX()&&listaEnlazada.getElemento(j).getData().getY()==listaEnlazada.getElemento(i).getData().getY()){
                    if(listaEnlazada.getElemento(i).getData().getProbabilidadReproduccion()*listaEnlazada.getElemento(i).getData().getProbabilidadReproduccion()>=random){
                        if(comprobarTamaño(listaEnlazada.getElemento(i).getData().getX(), listaEnlazada.getElemento(i).getData().getY())){
                            n_individuos++;
                            String tipo;
                            if(ind1.getTipo()==ind2.getTipo()){
                                tipo=ind1.getTipo();
                            }
                            else{
                                float nrand= (float) ((Math.random()*100)+1);
                                float probAumentoReproduccion=20;
                                if ((ind1.getTipo()=="básico"||ind1.getTipo()=="normal")&&(ind2.getTipo()=="normal"||ind2.getTipo()=="avanzado")){
                                    if(probAumentoReproduccion>nrand){
                                        tipo= ind2.getTipo();
                                    }
                                    else{
                                        tipo=ind1.getTipo();
                                    }
                                }
                                else if((ind2.getTipo()=="básico"||ind2.getTipo()=="normal")&&(ind1.getTipo()=="normal"||ind1.getTipo()=="avanzado")){
                                    if(probAumentoReproduccion>nrand){
                                        tipo= ind1.getTipo();
                                    }
                                    else{
                                        tipo=ind2.getTipo();
                                    }
                                }
                                else{
                                    tipo="básico";
                                }
                            }
                            Individuo i1= new Individuo(n_individuos,turno, ind1.getTurnosDeVida()+ ind2.getTurnosDeVida(),(ind1.getProbabilidadReproduccion()+ind2.getProbabilidadReproduccion())/2, (ind1.getProbabilidadClonacion()+ind2.getProbabilidadClonacion())/2,(ind1.getProbabilidadMuerte()+ind2.getProbabilidadMuerte())/2,ind1.getX(),ind2.getY(),tipo );
                            listaEnlazada.add(i1);
                            agregarIndividuoAlTablero(i1);
                            System.out.println("Se produjo la reproduccion");
                        }
                    }
                }
            }
        }
    }
    public boolean comprobarTamaño(int x, int y){
        int contador=0;
        for (int i=0; i<listaEnlazada.getNumeroElementos();i++){
            if(x==listaEnlazada.getElemento(i).getData().getX()&&y==listaEnlazada.getElemento(i).getData().getY()){
                contador++;
            }
        }
        if (contador>=3){
            return false;
        }
        else {
            return true;
        }
    }

    @FXML
    private void onInsertarIndividuoClick() {
        try {
            int x = Integer.parseInt(posicionXTextField.getText());
            int y = Integer.parseInt(posicionYTextField.getText());
            String tipoIndividuo = individuosComboBox.getValue();
            int turnosVida = (int) turnosDeVida.getValue();
            int probabilidadReproduccion = (int) probReproduccion.getValue();
            int probabilidadClonacion = (int) probClonacion.getValue();
            if (x > 0 && x <= tamañoAnchura && y > 0 && y <= tamañoAltura) {
                VBox cell = (VBox) getNodeByRowColumnIndex(y - 1, x - 1, tableroDeJuego);
                if (cell != null) {
                    if (cell.getChildren().stream().filter(node -> node instanceof Text).count() < 3) {
                        Text individuoText = new Text(tipoIndividuo);
                        n_individuos++;
                        individuoText.setFill(Color.RED); // Color diferente para los individuos
                        individuoText.setFont(Font.font("System", 12));
                        cell.getChildren().add(individuoText);
                        Individuo i1 = new Individuo(3, 3, 3, 3, 3, 3, x, y, tipoIndividuo);
                        i1.setIdentificacionIndividuo(n_individuos);
                        i1.setGeneracionIndividuo(turno);
                        i1.setTurnosDeVida(turnosVida);
                        i1.setProbabilidadReproduccion(probabilidadReproduccion);
                        i1.setProbabilidadClonacion(probabilidadClonacion);
                        i1.setProbabilidadMuerte(100 - probabilidadReproduccion);
                        listaEnlazada.add(i1);
                        String contenidoLista = listaEnlazada.toString();
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

    private void imprimirListaIndiviuos() {
        String contenidoLista = listaEnlazada.toString();
        System.out.println(contenidoLista);
    }

    @FXML
    private void onSiguienteTurnoButton() {
        pasarTurno();
    }

    private void pasarTurno() {
        // Decrementar el tiempo de vida de cada recurso y eliminarlos si su tiempo se ha agotado
        ActualizarTiempoDeVida();
        Iterator<Recursos> iterator = recursos.iterator();
        while (iterator.hasNext()) {
            Recursos recurso = iterator.next();
            recurso.setTiempoAparicion(recurso.getTiempoAparicion() - 1);
            if (recurso.getTiempoAparicion() <= 0) {
                iterator.remove();
                removerRecursoDeTablero(recurso);
            }
        }
        //Dependiendo el tipo del individuo se desplazaran a un lado o otro
        movimientoIndividuos();
        //Los recursos que se encuentren en la misma posicion que un individuo realizaran sus efectos
        recursoEfecto();
        //Reproduccion de individuos
        reproduccionIndividuos();
        //Clonacion de individuos;
        clonacion();
        //muerte de individuos
        muerteIndividuos();
        // Generar nuevos recursos según sus probabilidades
        generarNuevosRecursos();
        // Imprimir el estado actual de los recursos para depuración
        imprimirRecursos();
        imprimirListaIndiviuos();
        turno = turno + 1;
    }

    private void movimientoIndividuos() {
        int min=1;
        for (int i = 0; i < listaEnlazada.getNumeroElementos(); i++) {
            int r1 = random.nextInt(tamañoAnchura - min-1) + min;
            int r2 = random.nextInt(tamañoAltura - min-1) + min;
            if (listaEnlazada.getElemento(i).getData().getTipo() == "Básico") {
                moverIndividuo(listaEnlazada.getElemento(i).getData(),r1,r2);
            } else if (listaEnlazada.getElemento(i).getData().getTipo() == "Normal") {
                int indice = random.nextInt(recursos.size());
                moverIndividuo(listaEnlazada.getElemento(i).getData(),recursos.get(indice).getX(),recursos.get(indice).getY());
            }
            else if(listaEnlazada.getElemento(i).getData().getTipo()=="Avanzado"){
                int distancia=200;//introducimos un valor grande para que no haya problema
                Recursos recursoMasCercano=null;
                for (int j=0; j<recursos.size(); j++){
                    int distancia1= Math.abs(listaEnlazada.getElemento(i).getData().getX() - (recursos.get(j).getX()+1));
                    int distancia2= Math.abs(listaEnlazada.getElemento(i).getData().getY() - (recursos.get(j).getY()+1));
                    int dist= distancia1+distancia2;
                    if (dist<distancia&&dist!=0){
                        distancia=dist;
                        recursoMasCercano= recursos.get(j);
                    }
                    moverIndividuo(listaEnlazada.getElemento(i).getData(),recursoMasCercano.getX()+1,recursoMasCercano.getY()+1);
                }
            }
        }
    }
    public void efectos(Individuo i1,Recursos recurso){
        String prueba=recurso.getClass().getSimpleName();
        if (recurso.getClass().getSimpleName()=="Agua"){
            i1.setTurnosDeVida(i1.getTurnosDeVida()+2);
        }
        if(recurso.getClass().getSimpleName()=="Comida"){
            i1.setTurnosDeVida(i1.getTurnosDeVida()+10);
        }
        if(recurso.getClass().getSimpleName()=="Montaña"){
            i1.setTurnosDeVida(i1.getTurnosDeVida()-2);
        }
        if(recurso.getClass().getSimpleName()=="Pozo"){
            i1.setTurnosDeVida(0);
        }
        if (recurso.getClass().getSimpleName()=="Tesoro"){
            i1.setProbabilidadReproduccion(i1.getProbabilidadReproduccion());
            if(i1.getProbabilidadReproduccion()>100){
                i1.setProbabilidadReproduccion(100);
            }
        }
        if (recurso.getClass().getSimpleName()=="Biblioteca"){
            i1.setProbabilidadClonacion(i1.getProbabilidadClonacion());
            if( i1.getProbabilidadClonacion()>100){
                i1.setProbabilidadClonacion(100);
            }
        }
    }

    public int getTamañoAnchura() {
        return tamañoAnchura;
    }

    public int getTamañoAltura() {
        return tamañoAltura;
    }

    public ListaEnlazada<Individuo> getListaEnlazada() {
        return listaEnlazada;
    }
    public List<Recursos> getRecursos() {
        return recursos;
    }



    public void clonacion(){
        for(int i=0; i<listaEnlazada.getNumeroElementos();i++){
            float aleatorio= (float)(Math.random()*100)+1;
            Individuo ind=listaEnlazada.getElemento(i).getData();
            if (ind.getProbabilidadClonacion()>aleatorio){
                if(comprobarTamaño(ind.getX(),ind.getY())){
                    n_individuos++;
                    Individuo ind1= new Individuo(n_individuos,turno,ind.getTurnosDeVida(),ind.getProbabilidadReproduccion(),ind.getProbabilidadClonacion(),ind.getProbabilidadMuerte(),ind.getX(),ind.getY(),ind.getTipo());
                    individuosAñadir.add(ind1);
                    System.out.println("Se producio la clonacion");
                }
            }
        }
        for(int i=0; i<individuosAñadir.getNumeroElementos();i++){
            listaEnlazada.add(individuosAñadir.getElemento(i).getData());
            agregarIndividuoAlTablero(individuosAñadir.getElemento(i).getData());
        }
    }
    public void muerteIndividuos() throws NullPointerException{
        for(int i=0; i<listaEnlazada.getNumeroElementos();i++){
            float aleatoria=(float)(Math.random()*100)+1;
            Individuo ind = listaEnlazada.getElemento(i).getData();
            if(ind.getProbabilidadClonacion()>aleatoria){
                if(comprobarTamaño(ind.getX(),ind.getY())){
                    eliminarIndividuoDeTablero(ind.getX(),ind.getY(),listaEnlazada.getElemento(i).getData());
                    eliminarIndividuo(listaEnlazada.getElemento(i).getData());
                    listaEnlazada.del(i);
                    System.out.println("Se produjo la muerte");
                }
            }
        }
    }
    public void recursoEfecto(){
        for (int i = 0 ; i < listaEnlazada.getNumeroElementos();i++){
            for (int j = 0; j< recursos.size(); j++){
                if (listaEnlazada.getElemento(i).getData().getX()==(recursos.get(j).getX()+1)&&(listaEnlazada.getElemento(i).getData().getY()==(recursos.get(j).getY()+1))){
                    efectos(listaEnlazada.getElemento(i).getData(),recursos.get(j));
                }
            }
        }
    }

    private void ActualizarTiempoDeVida() {
        for (int i = 0; i < listaEnlazada.getNumeroElementos(); i++) {
            listaEnlazada.getElemento(i).getData().setTurnosDeVida(listaEnlazada.getElemento(i).getData().getTurnosDeVida() - 1);
            if (listaEnlazada.getElemento(i).getData().getTurnosDeVida() <= 0) {
                listaEnlazada.del(i);
            }
        }
    }

    private void generarNuevosRecursos() {
            for (int i = 0; i < tamañoAltura; i++) {
                for (int j = 0; j < tamañoAnchura; j++) {
                    if ((float)(Math.random()*100)+1 < probabilidadAparicion) {
                        float r=(float)(Math.random()*100)+1;
                        String tipoRecurso;
                        if (r < probAgua) tipoRecurso = "Agua";
                        else if (r < probAgua + probComida) tipoRecurso = "Comida";
                        else if (r < probAgua + probComida + probMontaña) tipoRecurso = "Montaña";
                        else if (r < probAgua + probComida + probMontaña + probPozo) tipoRecurso = "Pozo";
                        else if (r < probAgua + probComida + probMontaña + probPozo + probBiblioteca)
                            tipoRecurso = "Biblioteca";
                        else tipoRecurso = "Tesoro";

                        Recursos nuevoRecurso = crearRecurso(tipoRecurso, j, i);
                        recursos.add(nuevoRecurso);
                        agregarRecursoAlTablero(nuevoRecurso);
                    }
                }
            }

    }
    private int generarTiempoAparicionAleatorio() {
        return random.nextInt(10) + 1; // Genera un número aleatorio entre 1 y 10
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
    private void agregarIndividuoAlTablero(Individuo ind){
        VBox cell= (VBox)getNodeByRowColumnIndex(ind.getY()-1,ind.getX()-1,tableroDeJuego);
        if (cell!=null){
            Label resourceLabel =new Label(ind.getTipo());
            cell.getChildren().add(resourceLabel);
            resourceLabel.setStyle(("-fx-text-fill: red;")); // Color diferente para los individuos
            resourceLabel.setFont(Font.font("System", 12));
        }
    }
    private void removerIndividuoDeTablero(Individuo ind){
        VBox cell=(VBox) getNodeByRowColumnIndex(ind.getY(),ind.getX(),tableroDeJuego);
        if(cell!=null){
            cell.getChildren().removeIf(node -> node instanceof Label && ((Label)node).getText().equals(ind.getTipo()));
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
                    ", Posición: (" + recurso.getX() + 1 + ", " + recurso.getY()+ 1 + ")" +
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
    public void eliminarIndividuoDeTablero(int x, int y, Individuo individuo) {
        // Obtener la celda en la posición (x, y)
        VBox cell = (VBox) getNodeByRowColumnIndex(y, x, tableroDeJuego);

        if (cell != null) {
            // Buscar el Text nodo que contiene el string específico
            Text textNodeToRemove = null;
            for (Node node : cell.getChildren()) {
                if (node instanceof Text && ((Text) node).getText().equals(individuo.getTipo())) {
                    textNodeToRemove = (Text) node;
                    break;
                }
            }
            // Eliminar el Text nodo de la celda
            if (textNodeToRemove != null) {
                cell.getChildren().remove(textNodeToRemove);
            } else {
                System.out.println("String no encontrado en la celda especificada.");
            }
        } else {
            System.out.println("Celda no encontrada en la posición especificada.");
        }
    }

    // Método auxiliar para obtener el nodo en una posición específica del GridPane

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void imprimirIndividuosEnTablero(){
        for (int i=0; i<listaEnlazada.getNumeroElementos();i++){
            agregarIndividuoAlTablero(listaEnlazada.getElemento(i).getData());
        }
    }
    private void cambiarPosicionIndividuosTablero(){
        for(int i=0; i<listaEnlazada.getNumeroElementos();i++){
            eliminarIndividuoDeTablero(listaEnlazada.getElemento(i).getData().getX()-1,listaEnlazada.getElemento(i).getData().getY()-1,listaEnlazada.getElemento(i).getData());
            removerIndividuoDeTablero(listaEnlazada.getElemento(i).getData());
        }
    }
    public void moverIndividuo(Individuo individuo, int newX, int newY) {
        // Eliminar el individuo de la casilla actual
        VBox currentCell = (VBox) getNodeByRowColumnIndex(individuo.getY() - 1, individuo.getX() - 1, tableroDeJuego);
        if (currentCell != null) {
            currentCell.getChildren().removeIf(node -> node instanceof Text && ((Text) node).getText().equals(individuo.getTipo()));
        }

        // Actualizar la posición del individuo
        individuo.setX(newX);
        individuo.setY(newY);

        // Añadir el individuo a la nueva casilla
        VBox newCell = (VBox) getNodeByRowColumnIndex(newY - 1, newX - 1, tableroDeJuego);
        if (newCell != null) {
            Text individuoText = new Text(individuo.getTipo());
            individuoText.setFill(Color.RED);
            individuoText.setFont(Font.font("System", 12));
            newCell.getChildren().add(individuoText);
        }
    }
    public void eliminarIndividuo(Individuo individuo) {
        // Eliminar el individuo de su casilla actual
        VBox currentCell = (VBox) getNodeByRowColumnIndex(individuo.getY() - 1, individuo.getX() - 1, tableroDeJuego);
        if (currentCell != null) {
            currentCell.getChildren().removeIf(node -> node instanceof Text && ((Text) node).getText().equals(individuo.getTipo()));
        }
        // Eliminar el individuo de la lista enlazada
        listaEnlazada.del(listaEnlazada.getPosicion(individuo));
    }
}

   /* protected void onNextTurn() {
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
]/*/