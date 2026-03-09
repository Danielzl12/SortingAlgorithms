package sortingalgorithms.algoritmos;

// Clase que implementa el algoritmo de ordenamiento por Seleccion
public class SeleccionSort implements AlgoritmoOrdenamiento {

    @Override
    public String getNombre() {
        return "Seleccion (Selection Sort)";
    }

    // Como funciona: en cada vuelta busco el numero mas pequeno
    // de la parte que todavia no esta ordenada y lo llevo al inicio de esa parte.
    // Es como buscar siempre la carta mas baja de un monton y ponerla al inicio.
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int posicionMinimo = i; // Asumo que el minimo esta en la posicion i

            // Busco si hay algun numero mas pequeno en el resto del arreglo
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[posicionMinimo]) {
                    posicionMinimo = j;
                }
            }

            // Intercambio el minimo encontrado con el elemento en la posicion i
            int temp            = arr[posicionMinimo];
            arr[posicionMinimo] = arr[i];
            arr[i]              = temp;
        }
    }
}
