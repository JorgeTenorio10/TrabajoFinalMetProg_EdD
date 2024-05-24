package Recursos;
import com.example.demojavafx.TableroController;
public class Individuo {
    private static int IdentificacionIndividuo;
    private static int GeneracionIndividuo;
    private static int TurnosDeVida;
    private static float ProbabilidadReproduccion;
    private static float ProbabilidadClonacion;
    private static float ProbabilidadMuerte;
    private int x;
    private int y;
    private String tipo;

    public Individuo(int identificacionIndividuo,int generacionIndividuo,int turnosDeVida,
                     float probabilidadReproduccion,float probabilidadClonacion,
                     float probabilidadMuerte,int x,int y, String tipo){
        this.IdentificacionIndividuo=identificacionIndividuo;
        this.GeneracionIndividuo=generacionIndividuo;
        this.TurnosDeVida=turnosDeVida;
        this.ProbabilidadReproduccion=probabilidadReproduccion;
        this.ProbabilidadClonacion=probabilidadClonacion;
        this.ProbabilidadMuerte=probabilidadMuerte;
        this.x=x;
        this.y=y;
        this.tipo=tipo;
    }

    public static int getIdentificacionIndividuo() {
        return IdentificacionIndividuo;
    }

    public static int getGeneracionIndividuo() {
        return GeneracionIndividuo;
    }

    public  static int getTurnosDeVida() {
        return TurnosDeVida;
    }

    public static float getProbabilidadReproduccion() {
        return ProbabilidadReproduccion;
    }

    public static float getProbabilidadClonacion() {
        return ProbabilidadClonacion;
    }

    public static float getProbabilidadMuerte() {
        return ProbabilidadMuerte;
    }

    public static void setIdentificacionIndividuo(int identificacionIndividuo) {
        IdentificacionIndividuo = identificacionIndividuo;
    }

    public static void setGeneracionIndividuo(int generacionIndividuo) {
        GeneracionIndividuo = generacionIndividuo;
    }

    public static void setTurnosDeVida(int turnosDeVida) {
        TurnosDeVida = turnosDeVida;
    }

    public static void setProbabilidadClonacion(float probabilidadClonacion) {
        ProbabilidadClonacion = probabilidadClonacion;
    }

    public static void setProbabilidadReproduccion(float probabilidadReproduccion) {
        ProbabilidadReproduccion = probabilidadReproduccion;
    }

    public static void setProbabilidadMuerte(float probabilidadMuerte) {
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
    public void setPosicion(){
        int[] posicion = new int[2];
        posicion[0]=getX();
        posicion[1]=getY();
    }
    @Override
    public String toString() {
        return "Individuo{" +
                "identificacion="+ IdentificacionIndividuo+
                ",generacion=" + GeneracionIndividuo+
                ",turnos de vida= "+ TurnosDeVida+
                ",probabilidad de reproduccion="+ ProbabilidadReproduccion+
                ",probabilidad de clonacion="+ProbabilidadClonacion+
                ",probabilidad de muerte="+ ProbabilidadMuerte+
                ",x=" + x +
                ", y=" + y +
                ", tipo='" + tipo + '\'' +
                '}';
    }




    //public void movimiento(){
      //  if(this.tipo=="Basico"){
        //    int numeroAleatorio1 =(int)(Math.random()*);
          //  int numeroAleatorio2;
           // this.setX(Math.random(int x));
        //}
       // else if(this.tipo=="Normal"){

        //}
       // else if (this.tipo == "Avanzado"){

       // }

    //}

}
