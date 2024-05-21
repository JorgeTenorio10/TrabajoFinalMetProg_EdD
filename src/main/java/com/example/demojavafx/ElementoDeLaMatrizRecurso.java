package com.example.demojavafx;

import Recursos.Recursos;

public class ElementoDeLaMatrizRecurso {
    private Recursos recurso1;
    private Recursos recurso2;
    private Recursos recurso3;
    public ElementoDeLaMatrizRecurso(Recursos recurso1,Recursos recurso2,Recursos recurso3){
        this.recurso1=recurso1;
        this.recurso2=recurso2;
        this.recurso3=recurso3;
    }

    public Recursos getRecurso1() {
        return recurso1;
    }

    public Recursos getRecurso2() {
        return recurso2;
    }

    public Recursos getRecurso3() {
        return recurso3;
    }

    public void setRecurso1(Recursos recurso1) {
        this.recurso1 = recurso1;
    }

    public void setRecurso2(Recursos recurso2) {
        this.recurso2 = recurso2;
    }

    public void setRecurso3(Recursos recurso3) {
        this.recurso3 = recurso3;
    }
}
