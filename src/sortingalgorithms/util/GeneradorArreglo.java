package sortingalgorithms.util;

import java.util.Random;

// Clase de utilidad: se encarga de crear y convertir arreglos.
// La separo en su propia clase para que nadie mas tenga que preocuparse
// por como se generan o convierten los numeros.
public class GeneradorArreglo {

    // Genera un arreglo de 'tamano' numeros aleatorios entre -5000 y 4999
    public static int[] generarAleatorio(int tamano) {
        int[]  arreglo = new int[tamano];
        Random rand    = new Random();
        for (int i = 0; i < tamano; i++) {
            arreglo[i] = rand.nextInt(10000) - 5000;
        }
        return arreglo;
    }

    // Convierte un texto con numeros separados por comas o espacios a un arreglo de enteros
    // Ejemplo: "5, 3, 8 2 -1" -> [5, 3, 8, 2, -1]
    public static int[] parsearDesdeTexto(String texto) {
        String[] partes = texto.trim().split("[,\\s]+");

        if (partes.length == 0 || (partes.length == 1 && partes[0].isEmpty())) {
            throw new IllegalArgumentException("No ingresaste ningun numero.");
        }

        int[] arreglo = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            arreglo[i] = Integer.parseInt(partes[i].trim());
        }
        return arreglo;
    }

    // Convierte un arreglo a texto para mostrarlo en pantalla
    // Ejemplo: [5, 3, 8] con limite 2 -> "[5, 3]"
    public static String arregloATexto(int[] arr, int limite) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < limite; i++) {
            sb.append(arr[i]);
            if (i < limite - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
