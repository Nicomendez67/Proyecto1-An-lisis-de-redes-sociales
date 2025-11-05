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
    // Agregar arista u -> v
    public void agregarArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        // Evita duplicados si ya existe la arista
        if (!adyacentes[origen].busqueda(destino)) {
            adyacentes[origen].insertarFinal(destino);
        }

        if (!esDirigido) {
            if (!adyacentes[destino].busqueda(origen)) {
                adyacentes[destino].insertarFinal(origen);  
            }
        }
    }

    // Eliminar arista u -> v
    public void eliminarArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        adyacentes[origen].eliminar(destino);               

        if (!esDirigido) {
            adyacentes[destino].eliminar(origen);
        }
    }
    // Verificar si existe u -> v
    public boolean existeArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);
        return adyacentes[origen].busqueda(destino);
    }

    // Grado de un vértice (saliente en grafos dirigidos)
    public int grado(int v) {
        validarVertice(v);
        return adyacentes[v].toArray().length;
    }

    // Mostrar lista de adyacencia
    public void mostrar() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " → ");
            adyacentes[i].imprimir();
            System.out.println();
        }
    }
    // DFS (profundidad) desde 'inicio'
    public void DFS(int inicio) {
        validarVertice(inicio);
        boolean[] visitado = new boolean[numVertices];
        DFSRecursivo(inicio, visitado);
        System.out.println();
    }
     private void DFSRecursivo(int v, boolean[] visitado) {
        if (visitado[v]) return; // evita reprocesar por seguridad
        visitado[v] = true;
        System.out.print(v + " ");

        int[] vecinos = adyacentes[v].toArray();
        for (int i = 0; i < vecinos.length; i++) {
            int vecino = vecinos[i];
            if (!visitado[vecino]) {
                DFSRecursivo(vecino, visitado);
            }
        }
    }
    public void BFS(int inicio) {
        validarVertice(inicio);
        boolean[] visitado = new boolean[numVertices];
        int[] cola = new int[numVertices];
        int frente = 0, fin = 0;

        visitado[inicio] = true;
        cola[fin++] = inicio;

        while (frente < fin) {
            int v = cola[frente++];
            System.out.print(v + " ");

            int[] vecinos = adyacentes[v].toArray();
            for (int i = 0; i < vecinos.length; i++) {
                int vecino = vecinos[i];
                if (!visitado[vecino]) {
                    visitado[vecino] = true;

                    // Protección por si se intentara encolar más de numVertices (no debería pasar)
                    if (fin < numVertices) {
                        cola[fin++] = vecino;
                    }
                }
            }
        }
        System.out.println();
    }
     public int getNumVertices() {
        return numVertices;
    }
    
    public boolean esDirigido() {
        return esDirigido;
    }
    
    private static class IntWrapper {
        int value = -1;
    }

}






