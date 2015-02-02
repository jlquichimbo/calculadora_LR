/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoralr;

import java.util.ArrayList;
import java.lang.Object;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Gramatica   {

    private ArrayList<Regla> reglasGramatica;
    private ArrayList<Conjunto> conjuntosGramatica;
    boolean first = true;

    public Gramatica(ArrayList<Regla> reglasGramatica) {
        this.reglasGramatica = reglasGramatica;
        this.conjuntosGramatica = new ArrayList<>();
        this.conjuntosGramatica.add(new Conjunto(reglasGramatica));
    }


    public void generarConjuntos(){
        //Ciclo hasta que no se puedan agregar mas conjuntos
        cerradura(conjuntosGramatica.get(0));
        
    }
    public Conjunto cerradura(Conjunto objConjI) {
        try {
            Conjunto objConjJ ;
            objConjJ = objConjI.clone();
            
            if(first){
                Regla nuevaReglaPrima;
                nuevaReglaPrima= objConjJ.generarPrima(objConjI.getReglasConjunto().get(0).getCabeza());//Enviamos la cabeza de la regla 0
                objConjJ.getReglasConjunto().add(0, nuevaReglaPrima);
                return objConjJ;
            }
            return null;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * @return the reglasGramatica
     */
    public ArrayList<Regla> getReglasGramatica() {
        return reglasGramatica;
    }

    /**
     * @param reglasGramatica the reglasGramatica to set
     */
    public void setReglasGramatica(ArrayList<Regla> reglasGramatica) {
        this.reglasGramatica = reglasGramatica;
    }

    /**
     * @return the conjuntosGramatica
     */
    public ArrayList<Conjunto> getConjuntosGramatica() {
        return conjuntosGramatica;
    }

    /**
     * @param conjuntosGramatica the conjuntosGramatica to set
     */
    public void setConjuntosGramatica(ArrayList<Conjunto> conjuntosGramatica) {
        this.conjuntosGramatica = conjuntosGramatica;
    }

}
