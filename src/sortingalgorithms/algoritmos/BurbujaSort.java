package sortingalgorithms.algoritmos;

// Clase que implementa el algoritmo de ordenamiento Burbuja
public class BurbujaSort implements AlgoritmoOrdenamiento {

    @Override
    public String getNombre() {
        return "Burbuja (Bubble Sort)";
    }

    // Como funciona: recorro el arreglo comparando dos numeros que estan uno al lado del otro.
    // Si el de la izquierda es mayor que el de la derecha, los cambio de lugar.
    // Repito esto varias veces. En cada vuelta, el numero mas grande "flota" hasta el final,
    // igual que una burbuja que sube. Paro cuando ya no hay nada que cambiar.
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;
        boolean huboIntercambio;

        for (int i = 0; i < n - 1; i++) {
            huboIntercambio = false;

            // Comparo pares de elementos seguidos
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // El de la izquierda es mayor: los cambio de lugar
                    int temp   = arr[j];
                    arr[j]     = arr[j + 1];
                    arr[j + 1] = temp;
                    huboIntercambio = true;
                }
            }

            // Si en toda la vuelta no movi nada, ya estaba ordenado, salgo antes
            if (!huboIntercambio) break;
        }
    }
}
