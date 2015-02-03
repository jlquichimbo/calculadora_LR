/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoralr;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Conjunto implements Cloneable {

    public ArrayList<Regla> reglasConjunto;
    private String[] aceptadas;
    private String[] enlaces;
    private int recorrido;

    public Conjunto() {
    }

    public Conjunto(ArrayList<Regla> reglasConjunto) {
        this.reglasConjunto = reglasConjunto;
    }

    public Conjunto(Conjunto obj) {
        this.reglasConjunto = obj.getReglasConjunto();
    }

    public Regla generarPrima(String cabeza) {
        Regla rPrimRegla = new Regla(cabeza.concat("^"), cabeza) {
        };
        return rPrimRegla;
    }

    public void crearPunteroInicial() {

        for (Regla regla : reglasConjunto) {
            regla.setCuerpo("." + regla.getCuerpo());
        }
    }

    
    /*Generar nuevo conjunto de reglas
     */

   

   

  

    public void crearTabla() {

    }

    public void imprimirConjunto() {
        for (Regla regla : reglasConjunto) {
            regla.imprimirRegla();
        }
    }

    @Override
    public Conjunto clone() throws CloneNotSupportedException {
        Conjunto clone = (Conjunto) super.clone();
        clone.reglasConjunto = (ArrayList<Regla>) reglasConjunto.clone();

        return clone;

    }

    /**
     * @return the reglasConjunto
     */
    public ArrayList<Regla> getReglasConjunto() {
        return reglasConjunto;
    }

    /**
     * @param reglasConjunto the reglasConjunto to set
     */
    public void setReglasConjunto(ArrayList<Regla> reglasConjunto) {
        this.reglasConjunto = reglasConjunto;
    }

    /**
     * @return the aceptadas
     */
    public String[] getAceptadas() {
        return aceptadas;
    }

    /**
     * @param aceptadas the aceptadas to set
     */
    public void setAceptadas(String[] aceptadas) {
        this.aceptadas = aceptadas;
    }

    /**
     * @return the enlaces
     */
    public String[] getEnlaces() {
        return enlaces;
    }

    /**
     * @param enlaces the enlaces to set
     */
    public void setEnlaces(String[] enlaces) {
        this.enlaces = enlaces;
    }

    /**
     * @return the recorrido
     */
    public int getRecorrido() {
        return recorrido;
    }

    /**
     * @param recorrido the recorrido to set
     */
    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }

}
