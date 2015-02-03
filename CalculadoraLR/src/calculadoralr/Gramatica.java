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
public class Gramatica {

    private ArrayList<Regla> reglasGramatica;
    private ArrayList<Conjunto> conjuntosGramatica;
    private Conjunto primeraCerradura;
    private boolean first = true;

    public Gramatica(ArrayList<Regla> reglasGramatica) {
        this.reglasGramatica = reglasGramatica;
        this.conjuntosGramatica = new ArrayList<>();
//        this.conjuntosGramatica.add(new Conjunto(reglasGramatica));
    }

    public void generarConjuntos() {
        //Ciclo hasta que no se puedan agregar mas conjuntos
        conjuntosGramatica.add(cerradura(new Conjunto(reglasGramatica)));
        conjuntosGramatica.add(cerradura(getPrimeraCerradura()));

    }

    public Conjunto cerradura(Conjunto objConjBase) {
        try {
            Conjunto objConjJ = new Conjunto();

            if (first) {
                objConjJ = objConjBase.clone();
                Regla nuevaReglaPrima;
                nuevaReglaPrima = objConjJ.generarPrima(objConjBase.getReglasConjunto().get(0).getCabeza());//Enviamos la cabeza de la regla 0
                objConjJ.getReglasConjunto().add(0, nuevaReglaPrima);
                setFirst(false);
                objConjJ.crearPunteroInicial();
                objConjJ.imprimirConjunto();
                setPrimeraCerradura(objConjJ);
                return objConjJ;
            } else {
                String simbIrX;
                simbIrX = objConjBase.recorrerReglasConjunto();
                objConjJ = new Conjunto(ir_A(simbIrX, objConjBase));
//                objConjJ = (getPrimeraCerradura().ir_A(simbIrX));
                objConjJ.imprimirConjunto();

                return null;
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static ArrayList<Regla> ir_A(String simbolo, Conjunto conjBase) throws CloneNotSupportedException {
        Conjunto conjNuevo = null;
        String cuerpoRegla;
        ArrayList<Regla> reglasNew = new ArrayList<>();
        System.out.println("Nuevo conj de reglas");
        int i = 0;
        for (Regla reglaBase : conjBase.getReglasConjunto()) {
            cuerpoRegla = reglaBase.getCuerpo();
            if (String.valueOf(cuerpoRegla.charAt(cuerpoRegla.indexOf(".") + 1)).equals(simbolo)) {
                reglasNew.add(reglaBase);
                reglasNew.get(i).setCuerpo(moverPuntero(simbolo, cuerpoRegla));
            } else {
                break;
            }
            i++;
        }
        return reglasNew;
    }

    /*Generar nuevo conjunto de reglas
     */
    public static String moverPuntero(String simbolo, String cuerpoRegla) {
        String cuerpoNew;
        cuerpoNew = cuerpoRegla.replace("." + simbolo, simbolo + ".");

        return cuerpoNew;
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

    /**
     * @return the first
     */
    public boolean isFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(boolean first) {
        this.first = first;
    }

    /**
     * @return the primeraCerradura
     */
    public Conjunto getPrimeraCerradura() {
        return primeraCerradura;
    }

    /**
     * @param primeraCerradura the primeraCerradura to set
     */
    public void setPrimeraCerradura(Conjunto primeraCerradura) {
        this.primeraCerradura = primeraCerradura;
    }

}
