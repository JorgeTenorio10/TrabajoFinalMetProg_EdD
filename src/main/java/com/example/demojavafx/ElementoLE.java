package com.example.demojavafx;

public class ElementoLE <TipodeDato> {
    private TipodeDato data;
    private ElementoLE siguiente;
    private ElementoLE<TipodeDato> dcho;
    private ElementoLE<TipodeDato> izdo;

    public ElementoLE(TipodeDato dato) {
    }

    public ElementoLE() {
    }

    protected void insertarmeEn(ElementoLE el) {
        this.siguiente = el.siguiente;
        el.siguiente = this;
    }

    protected ElementoLE getSiguiente() {
        return this.siguiente;
    }

    protected void setSiguiente(ElementoLE el) {
        this.siguiente = el;
    }

    public TipodeDato getData() {
        return this.data;
    }

    public void setData(TipodeDato newData) {
        this.data = newData;
    }

    public void setIzdo(ElementoLE<TipodeDato> izdo) {
        this.izdo = izdo;
    }

    public void setDcho(ElementoLE<TipodeDato> dcho) {
        this.dcho = dcho;
    }

    public int getGradoElArbol() {
        int grado = 0;
        if (izdo != null) {
            grado++;
        }
        if (dcho != null) {
            grado++;
        }
        return grado;
    }
}