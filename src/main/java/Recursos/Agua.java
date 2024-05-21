package Recursos;

public class Agua extends Recursos {
    public Agua(int x, int y, int tiempoAparicion) {
        setX(x);
        setY(y);
        setTiempoAparicion(tiempoAparicion);
    }

    @Override
    public void efecto() {
            Individuo.setTurnosDeVida(Individuo.getTurnosDeVida()+2);
    }
}
