package com.example.demojavafx;

import javafx.beans.property.*;


/** Esta es una clase de utilidad, que permite generar unas propiedades observables para el GUI a partir
 *  de los datos del modelo original de java.
 *  Tiene los métodos de commit y rollback para establecer la operación final de traspasar los datos modificados
 *  o reiniciarlos según se quiera.
 */
public class ParameterDataModelProperties {
    //Modelo de datos original
    protected ParameterDataModel original;

    private IntegerProperty tamañoAltura= new SimpleIntegerProperty();
    private IntegerProperty tamañoAnchura= new SimpleIntegerProperty();
    private IntegerProperty probabilidadReproduccion= new SimpleIntegerProperty();
    private IntegerProperty probabilidadAparicionObjetos= new SimpleIntegerProperty();
    private IntegerProperty probabilidadAumentoTesoro= new SimpleIntegerProperty();
    private IntegerProperty probabilidadAumentoBiblioteca= new SimpleIntegerProperty();
    private IntegerProperty probAparicionComida= new SimpleIntegerProperty();
    private IntegerProperty probAparicionAgua= new SimpleIntegerProperty();
    private IntegerProperty probAparicionMontaña= new SimpleIntegerProperty();
    private IntegerProperty probAparicionBiblioteca= new SimpleIntegerProperty();
    private IntegerProperty probAparicionPozo= new SimpleIntegerProperty();
    private IntegerProperty probAparicionTesoro= new SimpleIntegerProperty();

    private IntegerProperty velocidad = new SimpleIntegerProperty();
    private IntegerProperty vida = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();

    public ParameterDataModelProperties(ParameterDataModel original){
        setOriginal(original);
    }

    public void commit(){
        original.setTamañoAltura(tamañoAltura.get());
        original.setTamañoAnchura(tamañoAnchura.get());
        original.setProbabilidadAparicionObjetos(probabilidadAparicionObjetos.get());
        original.setProbabilidadReproduccion(probabilidadReproduccion.get());
        original.setProbabilidadAumentoBiblioteca(probabilidadAumentoBiblioteca.get());
        original.setProbabilidadAumentoTesoro(probabilidadAumentoTesoro.get());
        original.setProbAparicionAgua(probAparicionAgua.get());
        original.setProbAparicionBiblioteca(probAparicionBiblioteca.get());
        original.setProbAparicionComida(probAparicionComida.get());
        original.setProbAparicionMontaña(probAparicionMontaña.get());
        original.setProbAparicionTesoro(probAparicionTesoro.get());
        original.setProbAparicionPozo(probAparicionPozo.get());
        original.setVelocidad(velocidad.get());
        original.setVida(vida.get());
        original.setNombre(nombre.get());
    }

    public void rollback(){
        tamañoAltura.set(original.getTamañoAltura());
        tamañoAnchura.set(original.getTamañoAnchura());
        probabilidadAparicionObjetos.set(original.getProbabilidadAparicionObjetos());
        probabilidadReproduccion.set(original.getProbabilidadReproduccion());
        probabilidadAumentoBiblioteca.set(original.getProbabilidadAumentoBiblioteca());
        probabilidadAumentoTesoro.set(original.getProbabilidadAumentoTesoro());
        probAparicionAgua.set(original.getProbAparicionAgua());
        probAparicionBiblioteca.set(original.getProbAparicionBiblioteca());
        probAparicionComida.set(original.getProbAparicionComida());
        probAparicionMontaña.set(original.getProbAparicionMontaña());
        probAparicionTesoro.set(original.getProbAparicionTesoro());
        probAparicionPozo.set(original.getProbAparicionPozo());
        velocidad.set(original.getVelocidad());
        vida.set(original.getVida());
        nombre.set(original.getNombre());
    }

    public ParameterDataModel getOriginal(){
        return original;
    }

    public void setOriginal(ParameterDataModel original){
        this.original = original;
        rollback(); //Se inicializan los properties.

    }

    public Property<Number> velocidadProperty() {
        return velocidad;
    }

    public Property<Number> vidaProperty() {
        return vida;
    }

    public Property<String> nombreProperty() {
        return nombre;
    }
    public Property<Number> tamañoAlturaProperty(){
        return tamañoAltura;
    }
    public Property<Number> tamañoAnchuraProperty(){
        return tamañoAnchura;
    }
    public Property<Number> probabilidadAparicionObjetosProperty(){
        return probabilidadAparicionObjetos;
    }
    public Property<Number> probabilidadReproduccionProperty(){
        return probabilidadReproduccion;
    }
    public Property<Number> probabilidadAumentoTesoroProperty(){
        return probabilidadAumentoTesoro;
    }
    public Property<Number> probabilidadAumentoBibliotecaProperty(){
        return probabilidadAumentoBiblioteca;
    }
    public Property<Number> probAparicionAguaProperty(){
        return probAparicionAgua;
    }
    public Property<Number> probAparicionComidaProperty(){
        return probAparicionComida;
    }
    public Property<Number> probAparicionPozoProperty(){
        return probAparicionPozo;
    }
    public Property<Number> probAparicionTesoroProperty(){
        return probAparicionTesoro;
    }
    public Property<Number> probAparicionMontañaProperty(){
        return probAparicionMontaña;
    }
    public Property<Number> probAparicionBibliotecaProperty(){
        return probAparicionBiblioteca;
    }
}
