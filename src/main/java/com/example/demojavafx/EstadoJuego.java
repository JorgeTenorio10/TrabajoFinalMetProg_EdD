package com.example.demojavafx;

import Recursos.Individuo;
import Recursos.Recursos;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

import java.io.Serializable;
import java.util.List;

public class EstadoJuego implements Serializable {
    private List<Recursos> recursos;
    private ListaEnlazada<Individuo> individuos;
    private String nombrePartida;
    private int tamañoAltura;
    private int tamañoAnchura;
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

    public EstadoJuego(String nombrePartida, int tamañoAltura, int tamañoAnchura,int pAumentoTesoro, int pReproduccion, int pAumentoBiblioteca,int pAgua, int pAparicion, int pComida, int pMontaña, int pPozo, int pBiblioteca, int probTesoro,  Object recursos, Object individuos) {
        this.nombrePartida = nombrePartida;
        this.tamañoAltura = tamañoAltura;
        this.tamañoAnchura = tamañoAnchura;
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

    public String getNombrePartida() {
        return nombrePartida;
    }

    public int getTamañoAltura() {
        return tamañoAltura;
    }

    public int getTamañoAnchura() {
        return tamañoAnchura;
    }

    public List<Recursos> getRecursos() {
        return recursos;
    }

    public ListaEnlazada<Individuo> getIndividuos() {
        return individuos;
    }

    public int getProbabilidadAparicion() {
        return probabilidadAparicion;
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

    public int getProbAgua() {
        return probAgua;
    }

    public int getProbBiblioteca() {
        return probBiblioteca;
    }

    public int getProbComida() {
        return probComida;
    }

    public int getProbMontaña() {
        return probMontaña;
    }

    public int getProbPozo() {
        return probPozo;
    }

    public int getProbTesoro() {
        return probTesoro;
    }
}
