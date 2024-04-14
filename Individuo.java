public class Individuo {
    private int IdentificacionIndividuo;
    private int GeneracionIndividuo;
    private int TurnosDeVida;
    private float ProbabilidadReproduccion;
    private float ProbabilidadClonacion;
    private float ProbabilidadMuerte;
    private int x;
    private int y;

    public Individuo(int identificacionIndividuo,int generacionIndividuo,int turnosDeVida, float probabilidadReproduccion,float probabilidadClonacion,float probabilidadMuerte, int x, int y ){
        this.IdentificacionIndividuo=identificacionIndividuo;
        this.GeneracionIndividuo=generacionIndividuo;
        this.TurnosDeVida=turnosDeVida;
        this.ProbabilidadReproduccion=probabilidadReproduccion;
        this.ProbabilidadClonacion=probabilidadClonacion;
        this.ProbabilidadMuerte=probabilidadMuerte;
        this.x=x;
        this.y=y;
    }

    public int getIdentificacionIndividuo() {
        return IdentificacionIndividuo;
    }

    public int getGeneracionIndividuo() {
        return GeneracionIndividuo;
    }

    public int getTurnosDeVida() {
        return TurnosDeVida;
    }

    public float getProbabilidadReproduccion() {
        return ProbabilidadReproduccion;
    }

    public float getProbabilidadClonacion() {
        return ProbabilidadClonacion;
    }

    public float getProbabilidadMuerte() {
        return ProbabilidadMuerte;
    }

    public void setIdentificacionIndividuo(int identificacionIndividuo) {
        IdentificacionIndividuo = identificacionIndividuo;
    }

    public void setGeneracionIndividuo(int generacionIndividuo) {
        GeneracionIndividuo = generacionIndividuo;
    }

    public void setTurnosDeVida(int turnosDeVida) {
        TurnosDeVida = turnosDeVida;
    }

    public void setProbabilidadClonacion(float probabilidadClonacion) {
        ProbabilidadClonacion = probabilidadClonacion;
    }

    public void setProbabilidadReproduccion(float probabilidadReproduccion) {
        ProbabilidadReproduccion = probabilidadReproduccion;
    }

    public void setProbabilidadMuerte(float probabilidadMuerte) {
        ProbabilidadMuerte = probabilidadMuerte;
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
}
