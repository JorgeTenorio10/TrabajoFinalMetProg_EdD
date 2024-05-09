package org.example.PantallaInicio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterDataProperties {
    protected ParameterData original;

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
    public ParameterDataProperties(ParameterData original){
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
    }
    public ParameterData getOriginal() {
        return original;
    }

    public void setOriginal(ParameterData original) {
        this.original = original;
        rollback();
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
