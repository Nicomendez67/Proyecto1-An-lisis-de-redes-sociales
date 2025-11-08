/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfazproyecto1;

/**
 * Cra los atributos a la clase Nodo
 * @author JAVIER MENDEZ
 */
public class Nodo {
     private Nodo Pnext;
    private int dato;
     
     /**
      * Inicializa la clase Nodo con el constructor 
      * Pasando como parametro int dato
      */
    public Nodo(int dato) {
        this.Pnext = null;
        this.dato = dato;
    }

    /**
     * @return the Pnext
     */
    public Nodo getPnext() {
        return Pnext;
    }

    /**
     * @param Pnext the Pnext to set
     */
    public void setPnext(Nodo Pnext) {
        this.Pnext = Pnext;
    }

    /**
     * @return the dato
     */
    public int getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(int dato) {
        this.dato = dato;
    }
    
    
}

