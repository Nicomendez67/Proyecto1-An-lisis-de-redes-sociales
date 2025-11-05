/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author JAVIER MENDEZ
 */
public class Grafo {

    private final int numVertices;
    private final boolean esDirigido;
    private final Lista[] adyacentes;

    public Grafo(int numVertices, boolean esDirigido) {
        if (numVertices <= 0) {
            throw new IllegalArgumentException("El número de vértices debe ser positivo.");
        }
        this.numVertices = numVertices;
        this.esDirigido = esDirigido;
        this.adyacentes = new Lista[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adyacentes[i] = new Lista();
        }
    private void validarVertice(int v) {
        if (v < 0 || v >= numVertices) {
            throw new IndexOutOfBoundsException("Vértice fuera de rango: " + v);
        }
    }
    
}


