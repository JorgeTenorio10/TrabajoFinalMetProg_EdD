package Recursos;

import Recursos.Recursos;

public class Tesoro extends Recursos {
    public Tesoro(int x, int y, int tiempoAparicion) {
        super();
        setX(x);
        setY(y);
        setTiempoAparicion(tiempoAparicion);
    }
    private int AumentoProbabilidad;

    public int getAumentoProbabilidad() {
        return AumentoProbabilidad;
    }

    public void setAumentoProbabilidad(int aumentoProbabilidad) {
        AumentoProbabilidad = aumentoProbabilidad;
    }

    @Override
    public void efecto() {
        Individuo.setProbabilidadReproduccion(Individuo.getProbabilidadReproduccion()+ Individuo.getProbabilidadReproduccion()*getAumentoProbabilidad());
    }
}
