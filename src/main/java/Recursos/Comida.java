package Recursos;

public class Comida extends Recursos {
    public Comida(int x, int y, int tiempoAparicion) {
        setX(x);
        setY(y);
        setTiempoAparicion(tiempoAparicion);
    }
    @Override
    public void efecto() {
        Individuo.setTurnosDeVida(Individuo.getTurnosDeVida()+10);
    }
}
