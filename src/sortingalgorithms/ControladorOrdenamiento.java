package sortingalgorithms;

import sortingalgorithms.algoritmos.AlgoritmoOrdenamiento;
import sortingalgorithms.algoritmos.BurbujaSort;
import sortingalgorithms.algoritmos.InsercionSort;
import sortingalgorithms.algoritmos.SeleccionSort;
import sortingalgorithms.algoritmos.ShellSort;
import sortingalgorithms.algoritmos.HeapSort;
import sortingalgorithms.algoritmos.QuickSort;
import sortingalgorithms.modelo.ResultadoOrdenamiento;
import sortingalgorithms.util.MedidorTiempo;

import java.util.ArrayList;
import java.util.List;

// Esta clase es el "puente" entre la interfaz grafica y los algoritmos.
// La GUI no necesita saber como funciona cada algoritmo, solo le pide al controlador que los ejecute.
// El controlador se encarga de: correr el algoritmo, medir el tiempo y devolver el resultado.
public class ControladorOrdenamiento {

    // Lista con todos los algoritmos disponibles
    // Si en el futuro quiero agregar uno nuevo, solo lo agrego aqui
    private final List<AlgoritmoOrdenamiento> algoritmos;

    public ControladorOrdenamiento() {
        algoritmos = new ArrayList<>();
        algoritmos.add(new BurbujaSort());
        algoritmos.add(new InsercionSort());
        algoritmos.add(new SeleccionSort());
        algoritmos.add(new ShellSort());
        algoritmos.add(new HeapSort());
        algoritmos.add(new QuickSort());
    }

    // Devuelve la lista de algoritmos para que la GUI pueda mostrar los checkboxes
    public List<AlgoritmoOrdenamiento> getAlgoritmos() {
        return algoritmos;
    }

    // Ejecuta UN algoritmo sobre una copia del arreglo original, mide el tiempo y devuelve el resultado
    public ResultadoOrdenamiento ejecutar(AlgoritmoOrdenamiento algoritmo, int[] arregloOriginal) {

        // Hago una copia del arreglo para que cada algoritmo empiece desde cero
        int[] copia = arregloOriginal.clone();

        // Mido el tiempo justo antes y despues de ordenar
        MedidorTiempo medidor = new MedidorTiempo();
        medidor.iniciar();
        algoritmo.ordenar(copia);
        medidor.detener();

        return new ResultadoOrdenamiento(
            algoritmo.getNombre(),
            medidor.getTiempoMs(),
            medidor.getTiempoNs(),
            copia
        );
    }
}
