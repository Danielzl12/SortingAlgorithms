package sortingalgorithms.algoritmos;

// Clase que implementa el algoritmo Heap Sort
public class HeapSort implements AlgoritmoOrdenamiento {

    @Override
    public String getNombre() {
        return "Heap Sort";
    }

    // Como funciona: usa una estructura llamada "heap" donde el numero mas grande
    // siempre esta en la cima. Primero construyo ese heap con todos los numeros,
    // luego saco el mas grande de la cima, lo mando al final del arreglo,
    // reparo el heap y repito hasta que quede todo ordenado.
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;

        // PASO 1: Construyo el max-heap (el mayor queda en arr[0])
        for (int i = n / 2 - 1; i >= 0; i--) {
            hundirPadre(arr, n, i);
        }

        // PASO 2: Saco el maximo (arr[0]) y lo mando al final, una y otra vez
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0]   = arr[i];
            arr[i]   = temp;
            hundirPadre(arr, i, 0);
        }
    }

    // Se asegura de que el elemento en 'i' sea mayor que sus hijos.
    // Si no lo es, lo intercambia con el hijo mayor y sigue revisando hacia abajo.
    private void hundirPadre(int[] arr, int n, int i) {
        int mayor   = i;
        int hijoIzq = 2 * i + 1;
        int hijoDer = 2 * i + 2;

        if (hijoIzq < n && arr[hijoIzq] > arr[mayor]) mayor = hijoIzq;
        if (hijoDer < n && arr[hijoDer] > arr[mayor]) mayor = hijoDer;

        if (mayor != i) {
            int temp   = arr[i];
            arr[i]     = arr[mayor];
            arr[mayor] = temp;
            hundirPadre(arr, n, mayor);
        }
    }
}
