package com.example.demojavafx;

import java.io.Serializable;

public class EstadoJuego implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombrePartida;
    private int tamañoAltura;
    private int tamañoAnchura;
    private Object recursos;  // Usa el tipo correcto en lugar de Object
    private Object individuos;  // Usa el tipo correcto en lugar de Object

    public EstadoJuego(String nombrePartida, int tamañoAltura, int tamañoAnchura, Object recursos, Object individuos) {
        this.nombrePartida = nombrePartida;
        this.tamañoAltura = tamañoAltura;
        this.tamañoAnchura = tamañoAnchura;
        this.recursos = recursos;
        this.individuos = individuos;
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

    public Object getRecursos() {
        return recursos;
    }

    public Object getIndividuos() {
        return individuos;
    }
}
