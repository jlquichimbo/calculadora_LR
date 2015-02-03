/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoralr;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Gramatica {

    private ArrayList<Regla> reglasGramatica;
    private ArrayList<Conjunto> conjuntosGramatica;
    private ArrayList<Integer> aceptadas;
    private ArrayList<String> cabeceraTabla;
    private Conjunto primeraCerradura;
    public int recorrido;
    private boolean conjRecorrido;

    private boolean first = true;

    public Gramatica(ArrayList<Regla> reglasGramatica) {
        this.reglasGramatica = reglasGramatica;
        this.conjuntosGramatica = new ArrayList<>();
        this.cabeceraTabla = new ArrayList<>();
        this.conjRecorrido = false;
//        this.conjuntosGramatica.add(new Conjunto(reglasGramatica));
    }

    public void recorrerConjuntos() {
        //Ciclo hasta que no se puedan agregar mas conjuntos
        conjuntosGramatica.add(recorrerReglas(new Conjunto(reglasGramatica)));
        conjuntosGramatica.add(recorrerReglas(getPrimeraCerradura()));
        for (int i = 0; i < conjuntosGramatica.size(); i++) {

        }

    }

    public Conjunto recorrerReglas(Conjunto objConjBase) {
        try {
            Conjunto objConjJ;

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
                int i = 1;

                while (!conjRecorrido) {
                    simbIrX = recorrerReglasConjunto(objConjBase.getReglasConjunto(), getRecorrido());
                    cabeceraTabla.add(simbIrX);
                    objConjJ = new Conjunto(ir_A(simbIrX, objConjBase));
//                    if (ir_A(simbIrX, objConjBase) == null) {
//                        break;
//                    }
                    conjuntosGramatica.add(objConjJ);
                    System.out.println("Conjunto: I" + i);
                    objConjJ.imprimirConjunto();

//                    return objConjJ;
                    i++;
                }
                System.out.println("CABECERA:");
                imprimirArray(null, cabeceraTabla);
                setRecorrido(0);
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String recorrerReglasConjunto(ArrayList<Regla> reglasConjunto, int start) {
        String simbolo = null;

        //Recorre las reglas a partir de la regla donde se encontro el ultimo simbolo
        for (int i = start; i < reglasConjunto.size(); i++) {

            //Busca un nuevo simbolo despues del punto
            simbolo = buscarCandidato(reglasConjunto.get(i).getCuerpo());
            break;

        }
        return simbolo;

    }

    public String buscarCandidato(String cuerpo) {
        String simbolo;
        int posPuntero;
        posPuntero = cuerpo.indexOf(".");
        simbolo = String.valueOf(cuerpo.charAt(posPuntero + 1));

        return simbolo;
    }

    /**
     * @param simboloNT
     * @return the trasero
     */
    public ArrayList<Regla> cerradura(String simboloNT) {
        ArrayList<Regla> derivacionesNT = new ArrayList<>();
        for (Regla derivacionesI0 : getPrimeraCerradura().getReglasConjunto()) {
            if (derivacionesI0.getCabeza().equals(simboloNT)) {
                derivacionesNT.add(derivacionesI0);
            }
        }
        return derivacionesNT;
    }

    /**
     * @param simbolo
     * @param conjBase
     * @return the otro trasero
     * @throws java.lang.CloneNotSupportedException
     */
    public ArrayList<Regla> ir_A(String simbolo, Conjunto conjBase) throws CloneNotSupportedException {
        try {
            String cuerpoRegla;
            ArrayList<Regla> reglasNew = new ArrayList<>();
//        System.out.println("Nuevo conj de reglas");
            int i = 0;
            //Recorremos las reglas recibidas
            for (int j = getRecorrido(); j < conjBase.getReglasConjunto().size(); j++) {
                cuerpoRegla = conjBase.getReglasConjunto().get(j).getCuerpo();

                //Si existe ese simbolo despues del '.' agregamos la regla a la lista
                if (String.valueOf(cuerpoRegla.charAt(cuerpoRegla.indexOf(".") + 1)).equals(simbolo)) {
                    reglasNew.add(conjBase.getReglasConjunto().get(j));
                    reglasNew.get(i).setCuerpo(moverPuntero(simbolo, cuerpoRegla));
                    String cuerpoNew = reglasNew.get(i).getCuerpo();
                    try {
                        char nextSimbol = cuerpoNew.charAt(cuerpoNew.indexOf(".")+1);

                        /*Si lo que sigue del '.' es un NT*/
                        if (Character.isUpperCase(nextSimbol)) {
                            ArrayList<Regla> derivacionesNT = cerradura(String.valueOf(nextSimbol));
                            for (Regla derivaciones : derivacionesNT) {
                                reglasNew.add(derivaciones);
                            }

                        }
                    } catch (Exception ex) {
                        
                    }
                    /*Si es el ultimo elemento del conjunto*/
                    if ((reglasNew.get(i).getCuerpo().indexOf(".")) == cuerpoRegla.length() - 1 && (j == conjBase.getReglasConjunto().size() - 1)) {
                        setConjRecorrido(true);
                    }
                } else {
                    //Si ya no hay mas simbolos despues del punto rompemos el ciclo
                    setRecorrido(j);
                    break;
                }

                i++;
            }

            return reglasNew;
        } catch (Exception ex) {
            System.out.println("Error con el puntero ->" + ex.getMessage());
            return null;

        }
    }

    /*Generar nuevo conjunto de reglas
     */
    public static String moverPuntero(String simbolo, String cuerpoRegla) {
        String cuerpoNew;
        cuerpoNew = cuerpoRegla.replace("." + simbolo, simbolo + ".");

        return cuerpoNew;
    }

    public static void imprimirArray(String[] array, ArrayList list) {
        if (array != null) {
            for (String array1 : array) {
                System.out.print(array1 + '\t');
            }
        }
        if (list != null) {
            for (Object list1 : list) {
                System.out.print((String) list1 + "\t");
            }
        }
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

    /**
     * @return the aceptadas
     */
    public ArrayList<Integer> getAceptadas() {
        return aceptadas;
    }

    /**
     * @param aceptadas the aceptadas to set
     */
    public void setAceptadas(ArrayList<Integer> aceptadas) {
        this.aceptadas = aceptadas;
    }

    /**
     * @return the conjRecorrido
     */
    public boolean isConjRecorrido() {
        return conjRecorrido;
    }

    /**
     * @param conjRecorrido the conjRecorrido to set
     */
    public void setConjRecorrido(boolean conjRecorrido) {
        this.conjRecorrido = conjRecorrido;
    }

    /**
     * @return the cabeceraTabla
     */
    public ArrayList<String> getCabeceraTabla() {
        return cabeceraTabla;
    }

    /**
     * @param cabeceraTabla the cabeceraTabla to set
     */
    public void setCabeceraTabla(ArrayList<String> cabeceraTabla) {
        this.cabeceraTabla = cabeceraTabla;
    }

}
