package sortingalgorithms.util;

// Clase de utilidad: mide el tiempo que tarda un algoritmo en ordenar.
// Usa los dos metodos que pide la guia: currentTimeMillis y nanoTime.
public class MedidorTiempo {

    private long inicioMs; // tiempo de inicio en milisegundos
    private long inicioNs; // tiempo de inicio en nanosegundos
    private long tiempoMs; // cuanto tardo en milisegundos
    private long tiempoNs; // cuanto tardo en nanosegundos

    // Llamo a este metodo justo ANTES de que empiece el algoritmo
    public void iniciar() {
        inicioMs = System.currentTimeMillis(); // punto de partida en ms
        inicioNs = System.nanoTime();          // punto de partida en ns (mas preciso)
    }

    // Llamo a este metodo justo DESPUES de que termina el algoritmo
    public void detener() {
        tiempoNs = System.nanoTime()          - inicioNs;
        tiempoMs = System.currentTimeMillis() - inicioMs;
    }

    public long   getTiempoMs()      { return tiempoMs; }
    public long   getTiempoNs()      { return tiempoNs; }
    public String getTiempoMsTexto() { return tiempoMs + " ms"; }
    public String getTiempoNsTexto() { return String.format("%,d ns", tiempoNs); }
}
