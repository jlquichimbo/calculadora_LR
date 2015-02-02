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

    public Conjunto(ArrayList<Regla> reglasConjunto) {
        this.reglasConjunto = reglasConjunto;
    }

    public Conjunto(Conjunto obj) {
        this.reglasConjunto = obj.getReglasConjunto();
    }

    public Regla generarPrima(String cabeza) {
        Regla rPrimRegla = new Regla(cabeza.concat("^"), cabeza) {};
        return rPrimRegla;
    }

    public void crearPunteroInicial() {

    }

    public void moverPuntero() {

    }

    public String buscarCandidato() {
        return null;
    }

    public void recorrerReglasConjunto() {

    }

    public void crearTabla() {

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

}
