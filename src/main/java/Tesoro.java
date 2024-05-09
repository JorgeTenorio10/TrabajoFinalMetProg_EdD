public class Tesoro extends Recursos{
    private int AumentoProbabilidad;

    public int getAumentoProbabilidad() {
        return AumentoProbabilidad;
    }

    public void setAumentoProbabilidad(int aumentoProbabilidad) {
        AumentoProbabilidad = aumentoProbabilidad;
    }

    @Override
    public void efecto() {
        Individuo.setProbabilidadReproduccion(Individuo.getProbabilidadReproduccion()+Individuo.getProbabilidadReproduccion()*getAumentoProbabilidad());
    }
}
