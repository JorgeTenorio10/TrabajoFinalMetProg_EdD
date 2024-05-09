public class Comida extends Recursos{
    @Override
    public void efecto() {
        Individuo.setTurnosDeVida(Individuo.getTurnosDeVida()+10);
    }
}
