/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoralr;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class CalculadoraLR {

    /**
     * @param args the command line arguments mu
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lector = new Scanner(System.in);

        Algoritmo objAlgoritmo = new Algoritmo();
        
        //Tabla Quemada
        String[][] tabla = { 
            {"d5","",  "", "","","d4","","","1","2","3"},
            {"", "d6","d7","","","","","OK","","",""},
            {"", "r3","r3","d8","d9","","r3","r3","","",""},
            {"", "r6","r6","r6","r6","","r6","r6","","",""},
            {"d5","", "",  "",  "", "d4","","","10","2","3"},
            {"", "r8","r8","r8","r8", "","r8","r8","","",""},
            {"d5","", "",  "",  "",  "d4","", "","","11","3"},
            {"d5","", "",  "",  "",  "d4","", "","","12","3"},
            {"d5","", "",  "",  "",  "d4","", "","","","13"},
            {"d5","", "",  "",  ""  ,"d4","", "","","","14"},
            {"","d6","d7", "",  "",  "", "d15","","","",""},
            {"","r1","r1","d8","d9", "", "r1","r1","","",""},
            {"","r2","r2","d8","d9", "", "r2","r2","","",""},
            {"","r4","r4","r4","r4", "", "r4","r4","","",""},
            {"","r5","r5","r5","r5", "", "r5","r5","","",""},
            {"","r7","r7","r7","r7", "", "r7","r7","","",""},
        };
        String[] cabecera = {"id","+","-","*","/","(",")","$","E","T","F"};
        ArrayList<Regla> reglas = new ArrayList<>();
        reglas.add(new Regla("E", "E+T"));
        reglas.add(new Regla("E", "E-T"));
        reglas.add(new Regla("E", "T"));
        reglas.add(new Regla("T", "T*F"));
        reglas.add(new Regla("T", "T/F"));
        reglas.add(new Regla("T", "F"));
        reglas.add(new Regla("F", "(E)"));
        reglas.add(new Regla("F", "n"));
        objAlgoritmo.setCabeceraTabla(cabecera);
        objAlgoritmo.setTabla(tabla);
//        objAlgoritmo.imprimirCabecera();
//        objAlgoritmo.imprimirTablaAcciones();
        
        String cadena = JOptionPane.showInputDialog("Ingrese Cadena:");
        objAlgoritmo.correrAlgoritmo(cadena, reglas);
        
       
        Gramatica objGram = new Gramatica(reglas);
        objGram.recorrerConjuntos();
        
//        String str = "t.+f";
//        str = str.replace(".+", "+.");
//        System.out.println("cambio: "+str);
//        
//       
        
        
    }
    
}
