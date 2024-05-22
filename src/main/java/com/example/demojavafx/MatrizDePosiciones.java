package com.example.demojavafx;

import Recursos.Recursos;
import Recursos.Individuo;

public class MatrizDePosiciones {

    private ElementoDeLaMatriz[][] elementoDeLaMatriz;
    private Recursos[][] recursos;
    private int altura;
    private int anchura;

    public  MatrizDePosiciones(int altura, int anchura){//Al crear la matriz que enlace
        // todo tendremos que poner el valor de los sliders en n y m
        ElementoDeLaMatriz el1[][]=new ElementoDeLaMatriz[altura][anchura];
        for(int h=0;h<altura;h++){
            for(int i=0; i<anchura; i++){
                el1[h][i]=null;
            }
        }
        this.elementoDeLaMatriz= el1;
        this.altura=altura;
        this.anchura=anchura;
    }

    public ElementoDeLaMatriz[][] getElementoDeLaMatriz() {
        return this.elementoDeLaMatriz;
    }

    public ElementoDeLaMatriz getElemento(int x, int y){
        return this.elementoDeLaMatriz[x][y];
    }
    public Individuo getIndividuo1(int x , int y){
        return this.elementoDeLaMatriz[x][y].getIndividuo1();
    }
    public Individuo getIndividuo2(int x, int y){
        return this.elementoDeLaMatriz[x][y].getIndividuo2();
    }
    public Individuo getIndividuo3(int x, int y){
        return this.elementoDeLaMatriz[x][y].getIndividuo3();
    }
    public void setIndividuo(int x, int y, Individuo individuo){
        if(elementoDeLaMatriz[x][y].getIndividuo1()==null){
            elementoDeLaMatriz[x][y].setIndividuo1(individuo);
        }
        else if(elementoDeLaMatriz[x][y].getIndividuo1()!=null){
            if(elementoDeLaMatriz[x][y].getIndividuo2()==null){
                elementoDeLaMatriz[x][y].setIndividuo2(individuo);
            }
            else if(elementoDeLaMatriz[x][y].getIndividuo2()!=null){
                if(elementoDeLaMatriz[x][y].getIndividuo3()==null){
                    elementoDeLaMatriz[x][y].setIndividuo3(individuo);
                }
                else if(elementoDeLaMatriz[x][y].getIndividuo3()!=null){
                    System.out.println("La casilla esta llena, no caben mas individuos");
                }
            }
        }
        else {
            System.out.println("No se ha podido añadir el recurso");
        }
    }

    public int getAltura() {
        return altura;
    }

    public int getAnchura() {
        return anchura;
    }

    public int getTamaño(){
        return this.altura*this.anchura;
    }


}
