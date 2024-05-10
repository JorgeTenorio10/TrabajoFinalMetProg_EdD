package Recursos;

import Recursos.Recursos;

public class Montaña extends Recursos {
    @Override
    public void efecto() {
        Individuo.setTurnosDeVida(Individuo.getTurnosDeVida()-2);
    }
}
