public class Basico extends Individuo{
    private int[] posicion;

    public Basico(int identificacionIndividuo,int generacionIndividuo,int turnosDeVida, float probabilidadReproduccion,float probabilidadClonacion,float probabilidadMuerte,int[]posicion){
        super(identificacionIndividuo,generacionIndividuo,turnosDeVida,probabilidadReproduccion,probabilidadClonacion,probabilidadMuerte);
        this.posicion=posicion;
    }
}
