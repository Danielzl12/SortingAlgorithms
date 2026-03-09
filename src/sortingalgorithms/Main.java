package sortingalgorithms;

import sortingalgorithms.gui.SortingGUI;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// Punto de entrada del programa. Su unica responsabilidad es arrancar la ventana.
public class Main {

    public static void main(String[] args) {

        // Intento usar el estilo visual del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, Java usa su estilo por defecto
        }

        // Arranco la ventana en el hilo correcto para interfaces graficas
        SwingUtilities.invokeLater(SortingGUI::new);
    }
}
