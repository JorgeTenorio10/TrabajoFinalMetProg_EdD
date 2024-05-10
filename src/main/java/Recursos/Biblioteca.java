package Recursos;

public class Biblioteca extends Recursos {
    private int AumentoProbabilidad;

    public int getAumentoProbabilidad() {
        return AumentoProbabilidad;
    }

    public void setAumentoProbabilidad(int aumentoProbabilidad) {
        AumentoProbabilidad = aumentoProbabilidad;
    }

    @Override
    public void efecto() {
        Individuo.setProbabilidadClonacion(Individuo.getProbabilidadClonacion()+ Individuo.getProbabilidadClonacion()*getAumentoProbabilidad());
    }
}
