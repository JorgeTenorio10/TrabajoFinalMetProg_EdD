package com.example.demojavafx;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class MatrizDePosicionesRecursoTest {

    private MatrizDePosicionesRecurso matriz;
    private Recursos recurso1;
    private Recursos recurso2;
    private Recursos recurso3;
    private ElementoDeLaMatrizRecurso elemento;

    @BeforeEach
    void setUp() {
        matriz = new MatrizDePosicionesRecurso(3, 3);
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
    void testGetAnchura() {
        assertEquals(3, matriz.getAnchura());
    }

    @Test
    void testGetAltura() {
        assertEquals(3, matriz.getAltura());
    }

    @Test
    void testGetElementoDeLaMatrizRecurso() {
        ElementoDeLaMatrizRecurso[][] elementoMatriz = matriz.getElementoDeLaMatrizRecurso();
        assertNotNull(elementoMatriz);
        assertEquals(3, elementoMatriz.length);
        assertEquals(3, elementoMatriz[0].length);
    }

    @Test
    void testGetElemento() {
        matriz.setRecurso(0, 0, recurso1);
        assertEquals(elemento, matriz.getElemento(0, 0));
    }

    @Test
    void testGetRecurso1() {
        matriz.setRecurso(0, 0, recurso1);
        assertEquals(recurso1, matriz.getRecurso1(0, 0));
    }

    @Test
    void testGetRecurso2() {
        matriz.setRecurso(0, 0, recurso2);
        assertEquals(recurso2, matriz.getRecurso2(0, 0));
    }

    @Test
    void testGetRecurso3() {
        matriz.setRecurso(0, 0, recurso3);
        assertEquals(recurso3, matriz.getRecurso3(0, 0));
    }

    @Test
    void testSetRecurso() {
        matriz.setRecurso(0, 0, recurso1);
        assertEquals(elemento, matriz.getElemento(0, 0));
    }

    }
