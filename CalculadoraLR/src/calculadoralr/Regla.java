/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoralr;

/**
 *
 * @author USUARIO
 */
public class Regla implements Cloneable{
    private String cabeza;
    private String cuerpo;
    private int idRegla;

    public Regla(String cabeza, String cuerpo) {
        this.cabeza = cabeza;
        this.cuerpo = cuerpo;
    }
    @Override
    public Regla clone() throws CloneNotSupportedException {
        Regla clone = null;

        return clone = (Regla) super.clone();

    }
    
    public void imprimirRegla(){
        System.out.println(getCabeza() + " -> "+ getCuerpo());
    }

    /**
     * @return the cabeza
     */
    public String getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(String cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * @return the cuerpo
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * @return the idRegla
     */
    public int getIdRegla() {
        return idRegla;
    }

    /**
     * @param idRegla the idRegla to set
     */
    public void setIdRegla(int idRegla) {
        this.idRegla = idRegla;
    }
    
}
