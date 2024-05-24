package Recursos;
import com.example.demojavafx.TableroController;
public class Individuo {
    private  int nextId = 1;
    private  int identificacionIndividuo;
    private  int GeneracionIndividuo;
    private int TurnosDeVida;
    private float ProbabilidadReproduccion;
    private  float ProbabilidadClonacion;
    private  float ProbabilidadMuerte;
    private int x;
    private int y;
    private String tipo;

    public Individuo(int identificacionIndividuo,int generacionIndividuo,int turnosDeVida,
                     float probabilidadReproduccion,float probabilidadClonacion,
                     float probabilidadMuerte,int x,int y, String tipo){
        this.identificacionIndividuo = nextId++;
        this.GeneracionIndividuo=generacionIndividuo;
        this.TurnosDeVida=turnosDeVida;
        this.ProbabilidadReproduccion=probabilidadReproduccion;
        this.ProbabilidadClonacion=probabilidadClonacion;
        this.ProbabilidadMuerte=probabilidadMuerte;
        this.x=x;
        this.y=y;
        this.tipo=tipo;
    }

    public  int getIdentificacionIndividuo() {
        return identificacionIndividuo;
    }

    public  int getGeneracionIndividuo() {
        return GeneracionIndividuo;
    }

    public   int getTurnosDeVida() {
        return TurnosDeVida;
    }

    public  float getProbabilidadReproduccion() {
        return ProbabilidadReproduccion;
    }

    public  float getProbabilidadClonacion() {
        return ProbabilidadClonacion;
    }

    public  float getProbabilidadMuerte() {
        return ProbabilidadMuerte;
    }

    public  void setIdentificacionIndividuo(int identificacionIndividuo) {
        identificacionIndividuo = identificacionIndividuo;
    }

    public  void setGeneracionIndividuo(int generacionIndividuo) {
        GeneracionIndividuo = generacionIndividuo;
    }

    public  void setTurnosDeVida(int turnosDeVida) {
        TurnosDeVida = turnosDeVida;
    }

    public  void setProbabilidadClonacion(float probabilidadClonacion) {
        ProbabilidadClonacion = probabilidadClonacion;
    }

    public  void setProbabilidadReproduccion(float probabilidadReproduccion) {
        ProbabilidadReproduccion = probabilidadReproduccion;
    }

    public  void setProbabilidadMuerte(float probabilidadMuerte) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void decrementarTurnosDeVida() {
        this.TurnosDeVida--;
    }

    public boolean estaVivo() {
        return this.TurnosDeVida > 0;
    }
    @Override
    public String toString() {
        return "Individuo{" +
                "identificacion="+ identificacionIndividuo+
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
