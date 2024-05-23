package com.example.demojavafx;

import Recursos.Individuo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MatrizDePosicionesTest {

    private MatrizDePosiciones matriz;
    private Individuo individuo1;
    private Individuo individuo2;
    private Individuo individuo3;
    @BeforeEach
    void setUp() {
        matriz = new MatrizDePosiciones(3, 3);
        individuo1 = new Individuo(1, 1, 10, 0.5f, 0.3f, 0.1f, 0, 0, "Normal");
        individuo2 = new Individuo(2, 1, 10, 0.5f, 0.3f, 0.1f, 1, 1, "Basico");
        individuo3 = new Individuo(3, 1, 10, 0.5f, 0.3f, 0.1f, 2, 2, "Avanzado");
        }

        @Test
        void testConstructor() {
            assertNotNull(matriz.getElementoDeLaMatriz());
            assertEquals(3, matriz.getAltura());
            assertEquals(3, matriz.getAnchura());
        }

        @Test
        void testGetElemento() {
            matriz.getElementoDeLaMatriz()[0][0] = new ElementoDeLaMatriz(individuo1, null, null);
            assertEquals(individuo1, matriz.getElemento(0, 0).getIndividuo1());
        }

        @Test
        void testSetIndividuo() {
            matriz.setIndividuo(0, 0, individuo1);
            assertEquals(individuo1, matriz.getIndividuo1(0, 0));

            matriz.setIndividuo(0, 0, individuo2);
            assertEquals(individuo2, matriz.getIndividuo2(0, 0));

            matriz.setIndividuo(0, 0, individuo3);
            assertEquals(individuo3, matriz.getIndividuo3(0, 0));
        }

        @Test
        void testSetIndividuoFull() {
            matriz.setIndividuo(0, 0, individuo1);
            matriz.setIndividuo(0, 0, individuo2);
            matriz.setIndividuo(0, 0, individuo3);

            assertThrows(IllegalStateException.class, () -> {
                matriz.setIndividuo(0, 0, new Individuo(4, 1, 10, 0.5f, 0.3f, 0.1f, 3, 3, "TipoD"));
            });
        }

        @Test
        void testGetTamaño() {
            assertEquals(9, matriz.getTamaño());
        }
    }

