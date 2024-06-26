package com.example.demojavafx;

import Recursos.Recursos;

public class MatrizDePosicionesRecurso {
    private ElementoDeLaMatrizRecurso[][] ElementoDeLaMatrizRecurso;
    private int altura;
    private int anchura;

    public  MatrizDePosicionesRecurso(int altura, int anchura){//Al crear la matriz que enlace
        // todo tendremos que poner el valor de los sliders en n y m
        ElementoDeLaMatrizRecurso el1[][]=new ElementoDeLaMatrizRecurso[altura][anchura];
        for(int h=0;h<altura;h++){
            for(int i=0; i<anchura; i++){
                el1[h][i]=null;
            }
        }
        this.ElementoDeLaMatrizRecurso= el1;
        this.altura=altura;
        this.anchura=anchura;
    }

    public int getAnchura() {
        return anchura;
    }

    public int getAltura() {
        return altura;
    }

    public ElementoDeLaMatrizRecurso[][] getElementoDeLaMatrizRecurso() {
        return this.ElementoDeLaMatrizRecurso;
    }

    public ElementoDeLaMatrizRecurso getElemento(int x, int y){
        return this.ElementoDeLaMatrizRecurso[x][y];
    }
    public Recursos getRecurso1(int x, int y){
        return  this.ElementoDeLaMatrizRecurso[x][y].getRecurso1();
    }
    public Recursos getRecurso2(int x, int y){
        return this.ElementoDeLaMatrizRecurso[x][y].getRecurso2();
    }
    public Recursos getRecurso3(int x, int y){
        return this.ElementoDeLaMatrizRecurso[x][y].getRecurso3();
    }
    public void setRecurso(int x, int y, Recursos recursos){
        if(ElementoDeLaMatrizRecurso[x][y].getRecurso1()==null){
            ElementoDeLaMatrizRecurso[x][y].setRecurso1(recursos);
        }
        else if(ElementoDeLaMatrizRecurso[x][y].getRecurso1()!=null){
            if(ElementoDeLaMatrizRecurso[x][y].getRecurso2()==null){
                ElementoDeLaMatrizRecurso[x][y].setRecurso2(recursos);
            }
            else if(ElementoDeLaMatrizRecurso[x][y].getRecurso2()!=null){
                if(ElementoDeLaMatrizRecurso[x][y].getRecurso3()==null){
                    ElementoDeLaMatrizRecurso[x][y].setRecurso3(recursos);
                }
                else if(ElementoDeLaMatrizRecurso[x][y].getRecurso3()!=null){
                    System.out.println("No se pueden añadir mas recursos en esta posicion");
                }
            }
        }
        else {
            System.out.println("No se ha podido añadir el recurso");
        }
    }
}
