package sortingalgorithms.algoritmos;

// Esta es una interfaz: es como un "contrato" que todos los algoritmos deben cumplir.
// Cualquier clase de algoritmo que yo cree TIENE que tener estos dos metodos.
// Asi puedo tratar a todos los algoritmos de la misma forma desde el controlador,
// sin importar cual sea ni como funcione por dentro.
public interface AlgoritmoOrdenamiento {

    // Metodo que recibe el arreglo y lo ordena
    // Cada algoritmo lo implementa a su manera
    void ordenar(int[] arreglo);

    // Metodo que devuelve el nombre del algoritmo para mostrarlo en pantalla
    String getNombre();
}
