package org.example.PantallaInicio;

public class ParameterData {
    private String nombre;
    private int tamañoAltura;
    private int tamañoAnchura;
    private int probabilidadReproduccion;
    private int probabilidadAparicionObjetos;
    private int probabilidadAumentoTesoro;
    private int probabilidadAumentoBiblioteca;
    private int probAparicionComida;
    private int probAparicionAgua;
    private int probAparicionMontaña;
    private int probAparicionBiblioteca;
    private int probAparicionPozo;
    private int probAparicionTesoro;
    public ParameterData(String nombre,int tamañoAltura, int tamañoAnchura, int probabilidadReproduccion, int probabilidadAparicionObjetos, int probabilidadAumentoBiblioteca,int probabilidadAumentoTesoro, int probAparicionAgua, int probAparicionBiblioteca, int probAparicionComida, int probAparicionMontaña, int probAparicionPozo, int probAparicionTesoro){
        this.nombre=nombre;
        this.tamañoAltura=tamañoAltura;
        this.tamañoAnchura=tamañoAnchura;
        this.probabilidadAumentoBiblioteca=probabilidadAumentoBiblioteca;
        this.probabilidadAparicionObjetos=probabilidadAparicionObjetos;
        this.probAparicionAgua=probAparicionAgua;
        this.probabilidadReproduccion=probabilidadReproduccion;
        this.probAparicionComida=probAparicionComida;
        this.probabilidadAumentoTesoro=probabilidadAumentoTesoro;
        this.probAparicionBiblioteca=probAparicionBiblioteca;
        this.probAparicionTesoro=probAparicionTesoro;
        this.probAparicionPozo=probAparicionPozo;
        this.probAparicionMontaña=probAparicionMontaña;
    }

    public int getTamañoAltura() {
        return tamañoAltura;
    }

    public int getTamañoAnchura() {
        return tamañoAnchura;
    }

    public int getProbabilidadAparicionObjetos() {
        return probabilidadAparicionObjetos;
    }

    public int getProbabilidadAumentoBiblioteca() {
        return probabilidadAumentoBiblioteca;
    }

    public int getProbabilidadAumentoTesoro() {
        return probabilidadAumentoTesoro;
    }

    public int getProbabilidadReproduccion() {
        return probabilidadReproduccion;
    }

    public int getProbAparicionAgua() {
        return probAparicionAgua;
    }

    public int getProbAparicionBiblioteca() {
        return probAparicionBiblioteca;
    }

    public int getProbAparicionComida() {
        return probAparicionComida;
    }

    public int getProbAparicionMontaña() {
        return probAparicionMontaña;
    }

    public int getProbAparicionPozo() {
        return probAparicionPozo;
    }

    public int getProbAparicionTesoro() {
        return probAparicionTesoro;
    }

    public void setTamañoAnchura(int tamañoAnchura) {
        this.tamañoAnchura = tamañoAnchura;
    }

    public void setTamañoAltura(int tamañoAltura) {
        this.tamañoAltura = tamañoAltura;
    }

    public void setProbabilidadReproduccion(int probabilidadReproduccion) {
        this.probabilidadReproduccion = probabilidadReproduccion;
    }

    public void setProbabilidadAparicionObjetos(int probabilidadAparicionObjetos) {
        this.probabilidadAparicionObjetos = probabilidadAparicionObjetos;
    }

    public void setProbabilidadAumentoBiblioteca(int probabilidadAumentoBiblioteca) {
        this.probabilidadAumentoBiblioteca = probabilidadAumentoBiblioteca;
    }

    public void setProbabilidadAumentoTesoro(int probabilidadAumentoTesoro) {
        this.probabilidadAumentoTesoro = probabilidadAumentoTesoro;
    }

    public void setProbAparicionAgua(int probAparicionAgua) {
        this.probAparicionAgua = probAparicionAgua;
    }

    public void setProbAparicionBiblioteca(int probAparicionBiblioteca) {
        this.probAparicionBiblioteca = probAparicionBiblioteca;
    }

    public void setProbAparicionComida(int probAparicionComida) {
        this.probAparicionComida = probAparicionComida;
    }

    public void setProbAparicionMontaña(int probAparicionMontaña) {
        this.probAparicionMontaña = probAparicionMontaña;
    }

    public void setProbAparicionPozo(int probAparicionPozo) {
        this.probAparicionPozo = probAparicionPozo;
    }

    public void setProbAparicionTesoro(int probAparicionTesoro) {
        this.probAparicionTesoro = probAparicionTesoro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
