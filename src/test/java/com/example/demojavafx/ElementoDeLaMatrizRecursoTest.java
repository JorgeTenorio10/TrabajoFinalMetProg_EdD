package com.example.demojavafx;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import Recursos.Recursos;

class ElementoDeLaMatrizRecursoTest {

    private Recursos recurso1;
    private Recursos recurso2;
    private Recursos recurso3;
    private ElementoDeLaMatrizRecurso elemento;

    @BeforeEach
    void setUp() {
        recurso1 = new Recursos() {
            @Override
            public void efecto() {

            }
        };
        recurso2 = new Recursos() {
            @Override
            public void efecto() {

            }
        };
        recurso3 = new Recursos() {
            @Override
            public void efecto() {

            }
        };
        elemento = new ElementoDeLaMatrizRecurso(recurso1, recurso2, recurso3);
    }

    @Test
    void testGetRecurso1() {
        assertEquals(recurso1, elemento.getRecurso1());
    }

    @Test
    void testGetRecurso2() {
        assertEquals(recurso2, elemento.getRecurso2());
    }

    @Test
    void testGetRecurso3() {
        assertEquals(recurso3, elemento.getRecurso3());
    }

    @Test
    void testSetRecurso1() {
        Recursos newRecurso = new Recursos() {
            @Override
            public void efecto() {

            }
        };
        elemento.setRecurso1(newRecurso);
        assertEquals(newRecurso, elemento.getRecurso1());
    }

    @Test
    void testSetRecurso2() {
        Recursos newRecurso = new Recursos() {
            @Override
            public void efecto() {

            }
        };
        elemento.setRecurso2(newRecurso);
        assertEquals(newRecurso, elemento.getRecurso2());
    }

    @Test
    void testSetRecurso3() {
        Recursos newRecurso = new Recursos() {
            @Override
            public void efecto() {

            }
        };
        elemento.setRecurso3(newRecurso);
        assertEquals(newRecurso, elemento.getRecurso3());
    }

    @Test
    void testReducirTiemposDeAparicion() {
        recurso1.setTiempoAparicion(3);
        recurso2.setTiempoAparicion(5);
        recurso3.setTiempoAparicion(1);

        elemento.reducirTiemposDeAparicion();
        assertEquals(2, recurso1.getTiempoAparicion());
        assertEquals(4, recurso2.getTiempoAparicion());
        assertEquals(0, recurso3.getTiempoAparicion());
    }

    @Test
    void testEliminarRecursosCaducados() {
        recurso1.setTiempoAparicion(0);
        recurso2.setTiempoAparicion(1);
        recurso3.setTiempoAparicion(-1);

        elemento.eliminarRecursosCaducados();
        assertNull(elemento.getRecurso1());
        assertNotNull(elemento.getRecurso2());
        assertNull(elemento.getRecurso3());
    }
}