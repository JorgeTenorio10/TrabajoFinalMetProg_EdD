package Recursos;

import Recursos.Recursos;

public class Montaña extends Recursos {
    public Montaña(int x, int y, int tiempoAparicion) {
        setX(x);
        setY(y);
        setTiempoAparicion(tiempoAparicion);
    }
    @Override
    public void efecto() {
        Individuo.setTurnosDeVida(Individuo.getTurnosDeVida()-2);
    }
}
