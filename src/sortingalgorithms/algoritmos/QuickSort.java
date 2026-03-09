package sortingalgorithms.algoritmos;

// Clase que implementa el algoritmo Quick Sort
public class QuickSort implements AlgoritmoOrdenamiento {

    @Override
    public String getNombre() {
        return "Quick Sort";
    }

    // Como funciona: elige un numero llamado "pivote" y divide el arreglo en dos partes:
    // los menores al pivote a la izquierda, los mayores a la derecha.
    // Luego hace lo mismo recursivamente con cada parte hasta que todo quede ordenado
    // Es uno de los algoritmos mas rapidos en la practica.
    @Override
    public void ordenar(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // Metodo recursivo: ordena el subarreglo entre 'inicio' y 'fin'
    private void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int posicionPivote = partir(arr, inicio, fin);
            quickSort(arr, inicio, posicionPivote - 1); // Parte izquierda
            quickSort(arr, posicionPivote + 1, fin);    // Parte derecha
        }
    }

    // Coloca el pivote en su posicion correcta.
    // Los menores quedan a su izquierda y los mayores a su derecha.
    private int partir(int[] arr, int inicio, int fin) {
        // Uso "mediana de tres" para elegir un mejor pivote y evitar el peor caso
        int medio = inicio + (fin - inicio) / 2;
        if (arr[medio] < arr[inicio]) { int t = arr[medio]; arr[medio] = arr[inicio]; arr[inicio] = t; }
        if (arr[fin]   < arr[inicio]) { int t = arr[fin];   arr[fin]   = arr[inicio]; arr[inicio] = t; }
        if (arr[medio] < arr[fin])    { int t = arr[medio]; arr[medio] = arr[fin];    arr[fin]    = t; }

        int pivote = arr[fin];
        int i      = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }

        // Coloco el pivote en su posicion final
        int temp   = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin]   = temp;

        return i + 1;
    }
}
