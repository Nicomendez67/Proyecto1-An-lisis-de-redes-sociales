/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfazproyecto1;

/**
 * Crea los atributos de la clase lista 
 * @author JAVIER MENDEZ
 */
public class Lista {
     private Nodo pfirst;   
    private int size;
/**
 * Inicializa la clase Lista  
 * 
 */
    public Lista() {
        this.pfirst = pfirst;
        this.size = 0;
    }

    /**
     * @return the pfirst
     */
    public Nodo getPfirst() {
        return pfirst;
    }

    /**
     * @param pfirst the pfirst to set
     */
    public void setPfirst(Nodo pfirst) {
        this.pfirst = pfirst;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean esVacio() {
        return pfirst == null;
    }

/**
 * Inserta un nodo al final de la lista enlazada
 * @param dato
 */
    public void insertarFinal(int dato) {
        Nodo newNodo = new Nodo(dato);
        if (esVacio()) {
            pfirst = newNodo;
        } else {
            Nodo aux = pfirst;
            while (aux.getPnext() != null) {
                aux = aux.getPnext();
            }
            aux.setPnext(newNodo);
        }
        size++;
    }

     /**
      * Imprime la lista y la pasa a un cadena de tipo String
      * @return 
      */
    public String imprimir() {
        String cadena = "[";
        Nodo aux = pfirst;
        while (aux != null) {
            cadena += aux.getDato();
            if (aux.getPnext() != null) {
                cadena += ",";
            }
            aux = aux.getPnext();
        }
        cadena += "]";
        return cadena;
    }

/**
 * Elimina la primera ocurrencia de X
 * @param x
 */
    public void eliminar(int x) {
        if (esVacio()) return;

        if (pfirst.getDato() == x) {
            pfirst = pfirst.getPnext();
            size--;
            return;
        }

        Nodo actual = pfirst;
        while (actual.getPnext() != null && actual.getPnext().getDato() != x) {
            actual = actual.getPnext();
        }

        if (actual.getPnext() != null) {
            actual.setPnext(actual.getPnext().getPnext());
            size--;
        }
    }

     /**
      * Buscar si X esta dentro de la lista enlazada
      * @param x
     * @return 
      */
    public boolean busqueda(int x) {
        Nodo actual = pfirst;
        while (actual != null) {
            if (actual.getDato() == x) return true;
            actual = actual.getPnext();
        }
        return false;
    }
     /**
      * Convierte la lista a un Array o arreglo
      * @return 
      */
   public int[] toArray() {
        int[] resultado = new int[size];
        Nodo actual = pfirst;
        int i = 0;
        while (actual != null) {
            resultado[i++] = actual.getDato();
            actual = actual.getPnext();
        }
        return resultado;
    }
}





