package sortingalgorithms.modelo;

import sortingalgorithms.util.GeneradorArreglo;

// Esta clase guarda toda la informacion del resultado de correr un algoritmo.
// En vez de andar pasando datos sueltos de un lado a otro, los empaqueto todos aqui.
public class ResultadoOrdenamiento {

    private final String nombreAlgoritmo;
    private final long   tiempoMs;
    private final long   tiempoNs;
    private final int[]  arregloOrdenado;

    // Constructor: creo un resultado con todos sus datos de una vez
    public ResultadoOrdenamiento(String nombreAlgoritmo, long tiempoMs, long tiempoNs, int[] arregloOrdenado) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.tiempoMs        = tiempoMs;
        this.tiempoNs        = tiempoNs;
        this.arregloOrdenado = arregloOrdenado;
    }

    // Getters para obtener cada dato
    public String getNombreAlgoritmo() { return nombreAlgoritmo; }
    public long   getTiempoMs()        { return tiempoMs; }
    public long   getTiempoNs()        { return tiempoNs; }
    public int[]  getArregloOrdenado() { return arregloOrdenado; }

    // Devuelve el tiempo en ms como texto listo para mostrar en la tabla
    public String getTiempoMsTexto() {
        return tiempoMs + " ms";
    }

    // Devuelve el tiempo en ns con separadores de miles para que sea mas legible
    // Ejemplo: 1234567 -> "1,234,567 ns"
    public String getTiempoNsTexto() {
        return String.format("%,d ns", tiempoNs);
    }

    // Devuelve una vista previa del arreglo ordenado (solo los primeros 15 elementos)
    public String getVistaPrevia() {
        int    limite = Math.min(15, arregloOrdenado.length);
        String base   = GeneradorArreglo.arregloATexto(arregloOrdenado, limite);
        return arregloOrdenado.length > 15 ? base + " ..." : base;
    }
}
