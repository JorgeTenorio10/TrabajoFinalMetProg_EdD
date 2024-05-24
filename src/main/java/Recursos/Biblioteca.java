package Recursos;

public class Biblioteca extends Recursos {
    public Biblioteca(int x, int y, int tiempoAparicion) {
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


}
