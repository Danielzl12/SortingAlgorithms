package sortingalgorithms.algoritmos;

// Clase que implementa el algoritmo de ordenamiento por Insercion
public class InsercionSort implements AlgoritmoOrdenamiento {

    @Override
    public String getNombre() {
        return "Insercion (Insertion Sort)";
    }

    // Como funciona: voy tomando elementos de uno en uno y los coloco
    // en su posicion correcta dentro de la parte izquierda que ya esta ordenada.
    // Es igual a como uno ordena las cartas en la mano:
    // tomas una carta nueva y la deslizas hasta donde le corresponde.
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int elementoActual = arr[i]; // El elemento que voy a insertar en su lugar correcto
            int j = i - 1;

            // Muevo hacia la derecha los elementos mayores que elementoActual
            // para hacerle espacio donde va a quedar
            while (j >= 0 && arr[j] > elementoActual) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Coloco el elemento en su posicion correcta
            arr[j + 1] = elementoActual;
        }
    }
}
