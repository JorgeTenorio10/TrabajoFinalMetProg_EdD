package Recursos;

public abstract class Recursos {
    private int x;
    private int y;
    private int tiempoAparicion;

    public int getTiempoAparicion() {
        return tiempoAparicion;
    }

    public void setTiempoAparicion(int tiempoAparicion) {
        this.tiempoAparicion = tiempoAparicion;
    }
    public void reducirTiempoAparicion() {
        this.tiempoAparicion--;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int[] getPosicion(){
        int[] posicion= new int[2];
        posicion[0]=this.x;
        posicion[1]=this.y;
        return posicion;
    }
    public void setPosicion(){
        int[] posicion = new int[2];
        posicion[0]=getX();
        posicion[1]=getY();
    }
    public abstract void efecto();
}
