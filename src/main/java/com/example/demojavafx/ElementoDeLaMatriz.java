package com.example.demojavafx;

import Recursos.Individuo;

public class ElementoDeLaMatriz {
    private Individuo Individuo1;
    private Individuo Individuo2;
    private Individuo Individuo3;

    public ElementoDeLaMatriz(Individuo Individuo1,Individuo Individuo2, Individuo Individuo3){
        this.Individuo1=Individuo1;
        this.Individuo2=Individuo2;
        this.Individuo3=Individuo3;
    }

    public Individuo getIndividuo2() {
        return Individuo2;
    }

    public Individuo getIndividuo1() {
        return Individuo1;
    }

    public Individuo getIndividuo3() {
        return Individuo3;
    }

    public void setIndividuo3(Individuo individuo3) {
        Individuo3 = individuo3;
    }

    public void setIndividuo2(Individuo individuo2) {
        Individuo2 = individuo2;
    }

    public void setIndividuo1(Individuo individuo1) {
        Individuo1 = individuo1;
    }
}
