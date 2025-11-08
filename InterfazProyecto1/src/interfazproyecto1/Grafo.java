/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfazproyecto1;

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
    }

    // Valida que el vértice esté en rango
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

    // BFS (anchura) desde 'inicio' con cola manual basada en arreglo
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

    private Grafo transponer() { 
        Grafo gT = new Grafo(numVertices, esDirigido);
        for (int u = 0; u < numVertices; u++) {
            int[] vecinos = adyacentes[u].toArray();
            for (int v : vecinos) {
                gT.agregarArista(v, u);
            }
        }
        return gT;
    }

    private void dfsLlenarStack(int v, boolean[] visitado, int[] stack, IntWrapper top) {
        visitado[v] = true;
        int[] vecinos = adyacentes[v].toArray();
        for (int w : vecinos) {
            if (!visitado[w]) {
                dfsLlenarStack(w, visitado, stack, top);
            }
        }
        stack[++top.value] = v;
    }

    private void dfsRecoleccion(int v, boolean[] visitado, Grafo gT) {
        visitado[v] = true;
        System.out.print(v + " ");
        int[] vecinos = gT.adyacentes[v].toArray();
        for (int w : vecinos) {
            if (!visitado[w]) {
                dfsRecoleccion(w, visitado, gT);
            }
        }
    }

    public void kosaraju() { 
        if (!esDirigido) {
            System.out.println("Kosaraju solo aplica a grafos dirigidos.");
            return;
        }

        boolean[] visitado = new boolean[numVertices];
        int[] stack = new int[numVertices];
        IntWrapper top = new IntWrapper();

        for (int i = 0; i < numVertices; i++) {
            if (!visitado[i]) {
                dfsLlenarStack(i, visitado, stack, top);
            }
        }

        Grafo gT = transponer();
        visitado = new boolean[numVertices];

        System.out.println("Componentes fuertemente conectadas:");
        while (top.value >= 0) {
            int v = stack[top.value--];
            if (!visitado[v]) {
                dfsRecoleccion(v, visitado, gT);
                System.out.println();
            }
        }
    }
}

