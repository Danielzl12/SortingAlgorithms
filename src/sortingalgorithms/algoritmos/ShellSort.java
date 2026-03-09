package sortingalgorithms.algoritmos;

// Clase que implementa el algoritmo Shell Sort
public class ShellSort implements AlgoritmoOrdenamiento {

    @Override
    public String getNombre() {
        return "Shell Sort";
    }

    // Como funciona: es una mejora del ordenamiento por insercion.
    // En vez de comparar numeros que estan uno al lado del otro,
    // primero compara numeros que estan muy separados (distancia = gap).
    // Luego va reduciendo esa distancia hasta llegar a 1.
    // Esto hace que los numeros grandes lleguen rapido al final antes de la pasada final.
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;

        // Calculo el gap inicial con la secuencia de Knuth: 1, 4, 13, 40, 121...
        int gap = 1;
        while (gap < n / 3) gap = 3 * gap + 1;

        // Voy reduciendo el gap hasta llegar a 1
        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j    = i;

                // Muevo elementos saltando de 'gap' en 'gap'
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap /= 3;
        }
    }
}
