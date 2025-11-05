/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author JAVIER MENDEZ
 */
public class Lista {
    private Nodo pfirst;
    private int size;

     public Lista() {
        this.pfirst = null;
        this.size = 0;
    }

    public Nodo getPfirst() {
        return pfirst;
    }

    public void setPfirst(Nodo pfirst) {
        this.pfirst = pfirst;
    }

    public int getSize() {
        return size;
    }

    public boolean esVacio() {
        return pfirst == null;
    }

    // Inserta al final
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

     // Imprime la lista como cadena
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




