package Recursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IndividuoTest {

    private Individuo individuo;

    @BeforeEach
    void setUp() {
        individuo = new Individuo(1, 1, 10, 0.5f, 0.3f, 0.1f, 5, 10, "TipoA");
    }

    @Test
    void testConstructorAndStaticFields() {
        assertEquals(1, Individuo.getIdentificacionIndividuo());
        assertEquals(1, Individuo.getGeneracionIndividuo());
        assertEquals(10, Individuo.getTurnosDeVida());
        assertEquals(0.5f, Individuo.getProbabilidadReproduccion());
        assertEquals(0.3f, Individuo.getProbabilidadClonacion());
        assertEquals(0.1f, Individuo.getProbabilidadMuerte());
    }

    @Test
    void testStaticSetters() {
        Individuo.setIdentificacionIndividuo(2);
        assertEquals(2, Individuo.getIdentificacionIndividuo());

        Individuo.setGeneracionIndividuo(2);
        assertEquals(2, Individuo.getGeneracionIndividuo());

        Individuo.setTurnosDeVida(20);
        assertEquals(20, Individuo.getTurnosDeVida());

        Individuo.setProbabilidadReproduccion(0.6f);
        assertEquals(0.6f, Individuo.getProbabilidadReproduccion());

        Individuo.setProbabilidadClonacion(0.4f);
        assertEquals(0.4f, Individuo.getProbabilidadClonacion());

        Individuo.setProbabilidadMuerte(0.2f);
        assertEquals(0.2f, Individuo.getProbabilidadMuerte());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(5, individuo.getX());
        assertEquals(10, individuo.getY());

        individuo.setX(15);
        assertEquals(15, individuo.getX());

        individuo.setY(20);
        assertEquals(20, individuo.getY());
    }

    @Test
    void testGetPosicion() {
        int[] posicion = individuo.getPosicion();
        assertArrayEquals(new int[]{5, 10}, posicion);
    }

    @Test
    void testSetPosicion() {
        individuo.setX(8);
        individuo.setY(12);
        int[] posicion = individuo.getPosicion();
        assertArrayEquals(new int[]{8, 12}, posicion);
    }
}

