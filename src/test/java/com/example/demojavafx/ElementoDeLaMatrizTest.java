package com.example.demojavafx;

import Recursos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ElementoDeLaMatrizTest {
    private Individuo individuo1;
    private Individuo individuo2;
    private Individuo individuo3;
    private ElementoDeLaMatriz elemento;

    @BeforeEach
    void setUp() {
        individuo1 = new Individuo(1,1,1,1,1,1,1,1,"Normal");
        individuo2 = new Individuo(1,1,1,1,1,1,1,1,"Normal");
        individuo3 = new Individuo(1,1,1,1,1,1,1,1,"Normal");
        elemento = new ElementoDeLaMatriz(individuo1, individuo2, individuo3);
    }

    @Test
    void testConstructor() {
        assertNotNull(elemento);
        assertEquals(individuo1, elemento.getIndividuo1());
        assertEquals(individuo2, elemento.getIndividuo2());
        assertEquals(individuo3, elemento.getIndividuo3());
    }

    @Test
    void testGetIndividuo1() {
        assertEquals(individuo1, elemento.getIndividuo1());
    }

    @Test
    void testGetIndividuo2() {
        assertEquals(individuo2, elemento.getIndividuo2());
    }

    @Test
    void testGetIndividuo3() {
        assertEquals(individuo3, elemento.getIndividuo3());
    }

    @Test
    void testSetIndividuo1() {
        Individuo newIndividuo1 = new Individuo(1,1,1,1,1,1,1,1,"Normal");
        elemento.setIndividuo1(newIndividuo1);
        assertEquals(newIndividuo1, elemento.getIndividuo1());
    }

    @Test
    void testSetIndividuo2() {
        Individuo newIndividuo2 = new Individuo(1,1,1,1,1,1,1,1,"Normal");
        elemento.setIndividuo2(newIndividuo2);
        assertEquals(newIndividuo2, elemento.getIndividuo2());
    }

    @Test
    void testSetIndividuo3() {
        Individuo newIndividuo3 = new Individuo(1,1,1,1,1,1,1,1,"Normal");
        elemento.setIndividuo3(newIndividuo3);
        assertEquals(newIndividuo3, elemento.getIndividuo3());
    }
}