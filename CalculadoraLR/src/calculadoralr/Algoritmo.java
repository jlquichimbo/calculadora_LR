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
public class Algoritmo {

    private String[][] tabla;
    private String cadenaInput;
    private String[] cabeceraTabla;
    private ArrayList<String> pila;//se puede crear en el metodo para no instanciar

    public static void imprimirArray(String[] array, ArrayList list) {
        if (array != null) {
            for (String array1 : array) {
                System.out.print(array1 + '\t');
            }

        }
        if (list != null) {
            for (Object list1 : list) {
                System.out.print((String)list1 + "\t");
            }
        }
    }

    public String getAccion(int fila, int columna) {
        String accion = tabla[fila][columna];
        return accion;
    }

    public int getNumeroColumna(String simbolo) {
        int indexCab = 0;
        for (int i = 0; i < cabeceraTabla.length; i++) {
            String simboloCab = cabeceraTabla[i];
            if (simbolo.equalsIgnoreCase(simboloCab)) {
                indexCab = i;
                return indexCab;
            }
        }
//        System.out.println("Encontrado en la posicion:" + indexCab);
        return indexCab;
    }

    public boolean correrAlgoritmo(String cadena, ArrayList<Regla> reglas) {
        int punteroAe = 1, quitar = 0;
        boolean valido = true;
        String s, a, accion, sP;
        Regla regla;
//        String[] recorrer = new String[4];
        ArrayList<ArrayList<Object>> tblRecorrer = new ArrayList<>();
        ArrayList<Object> recorrer;
        ArrayList<String> pila = new ArrayList();

        String ae;
        cadena = cadena.concat("$");//Anadimos $ al final
        String[] cadenaArr = cadena.split("");//Convertimos a array para manejar el puntero

        //Inicio del algoritmo
        try{
        pila.add("0");
        while (valido) {
            recorrer = new ArrayList<>();
            ae = cadenaArr[punteroAe];
            //El array recorrer=[s,  a,  accion,  s',   regla]
            s = pila.get(pila.size() - 1);//sea s el estado en la cima(final) de la pila
            if (esEntero(ae)) {//Si es un numero le asignamos id 
                ae = "n";
            }
            a = ae;//sea a el simbolo apuntado por ae
            accion = getAccion(Integer.parseInt(s), getNumeroColumna(a));//sea a el simbolo apuntado por ae

            recorrer.add(s);
            recorrer.add(a);
            recorrer.add(accion);

            //Si es un desplazamiento
            if (accion.charAt(0) == 'd') {
                sP = accion.substring(1, accion.length());//S' toma el valor del d
                pila.add(a);
                pila.add(sP);//meter a y despues s' en la cima de la pila
                recorrer.add(sP);
                punteroAe++;//avanzar ae al siguiente simbolo de entrada
            }
            if (accion.charAt(0) == 'r') {
                regla = reglas.get(Integer.parseInt(String.valueOf(accion.charAt(1))) - 1);
                recorrer.add(regla);//meter la regla 8
                quitar = regla.getCuerpo().length() * 2;
                //quitar 2*cuerpo elementos de la pila
                for (int i = 0; i < quitar; i++) {
                    pila.remove(pila.size() - 1);
                }
                sP = pila.get(pila.size() - 1);//sea s' el estado en la cima(final) de la pila
                recorrer.add(recorrer.size() - 1, sP);//anadir sP antes de regla

                pila.add(regla.getCabeza());//meter A
                pila.add(getAccion(Integer.parseInt(sP), getNumeroColumna(regla.getCabeza())));//meter ir_a[s', A]
            }
            if (accion.equalsIgnoreCase("OK")) {
                break;
            } 
            if(accion.equalsIgnoreCase("")){
                valido = false;
            }

            tblRecorrer.add(recorrer);

        }
        
        if(valido){
            System.out.println("Cadena aceptada");
        }else
            System.out.println("Cadena incorrecta");
        }catch(Exception ex){
            System.out.println("Cadena No Aceptada");
        }

//        imprimirArray(cadenaArr);
        return false;
    }

    public void imprimirCabecera() {
        for (String simbolo : cabeceraTabla) {
            System.out.print(simbolo + "\t");
        }
    }

    public void imprimirTablaAcciones() {
        for (String[] tabla1 : tabla) {
            System.out.println("");
            for (String tabla11 : tabla1) {
                System.out.print(tabla11 + "\t");
            }
        }
    }

    //Devuelve true si en una cadena que llega todos son numeros, false en caso contrario
    public boolean esEntero(String cad) {
        for (int i = 0; i < cad.length(); i++) {
            if (!Character.isDigit(cad.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the tabla
     */
    public String[][] getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String[][] tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the cadenaInput
     */
    public String getCadenaInput() {
        return cadenaInput;
    }

    /**
     * @param cadenaInput the cadenaInput to set
     */
    public void setCadenaInput(String cadenaInput) {
        this.cadenaInput = cadenaInput;
    }

    /**
     * @return the pila
     */
    public ArrayList<String> getPila() {
        return pila;
    }

    /**
     * @param pila the pila to set
     */
    public void setPila(ArrayList<String> pila) {
        this.pila = pila;
    }

    /**
     * @return the cabeceraTabla
     */
    public String[] getCabeceraTabla() {
        return cabeceraTabla;
    }

    /**
     * @param cabeceraTabla the cabeceraTabla to set
     */
    public void setCabeceraTabla(String[] cabeceraTabla) {
        this.cabeceraTabla = cabeceraTabla;
    }

}
