package sortingalgorithms.gui;

import sortingalgorithms.ControladorOrdenamiento;
import sortingalgorithms.algoritmos.AlgoritmoOrdenamiento;
import sortingalgorithms.modelo.ResultadoOrdenamiento;
import sortingalgorithms.util.GeneradorArreglo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase solo se encarga de la interfaz grafica
public class SortingGUI extends JFrame {


    // Componentes visuales de la ventana

    private JTextField  txtTamano;
    private JTextArea   txtIngresoManual;
    private JTextArea   txtArregloOrig;
    private JTable      tablaResultados;
    private DefaultTableModel modeloTabla;

    private JRadioButton rbManual;
    private JRadioButton rbAleatorio;

    private JLabel       lblEstado;
    private JProgressBar barraProgreso;

    // Un checkbox por cada algoritmo, los guardo en lista para recorrerlos facilmente
    private final List<JCheckBox> checkboxes = new ArrayList<>();

    // ------------------------------------
    // Logica del programa
    // ------------------------------------
    private final ControladorOrdenamiento controlador;
    private int[] arregloOriginal;

    // ------------------------------------
    // Constructor
    // ------------------------------------
    public SortingGUI() {
        controlador = new ControladorOrdenamiento();

        setTitle("Algoritmos de Ordenamiento - Estructura de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 760);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 245, 255));

        construirInterfaz();
        setVisible(true);
    }

    // ------------------------------------
    // Construye toda la interfaz
    // ------------------------------------
    private void construirInterfaz() {

        // Panel superior: titulo
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(30, 80, 160));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        JLabel lblTitulo = new JLabel("Algoritmos de Ordenamiento con Medicion de Tiempo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central: controles a la izquierda, resultados a la derecha
        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.setBackground(new Color(240, 245, 255));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        panelCentro.add(construirPanelIzquierdo(), BorderLayout.WEST);
        panelCentro.add(construirPanelDerecho(),   BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        // Panel inferior: estado y barra de progreso
        JPanel panelAbajo = new JPanel(new BorderLayout(5, 0));
        panelAbajo.setBackground(new Color(220, 230, 250));
        panelAbajo.setBorder(BorderFactory.createEmptyBorder(5, 15, 8, 15));
        lblEstado = new JLabel("Listo. Ingrese el tamano del arreglo y presione Generar.");
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 12));
        barraProgreso = new JProgressBar();
        barraProgreso.setPreferredSize(new Dimension(150, 18));
        barraProgreso.setStringPainted(true);
        panelAbajo.add(lblEstado,     BorderLayout.CENTER);
        panelAbajo.add(barraProgreso, BorderLayout.EAST);
        add(panelAbajo, BorderLayout.SOUTH);
    }

    // ------------------------------------
    // Panel izquierdo: configuracion y seleccion de algoritmos
    // ------------------------------------
    private JPanel construirPanelIzquierdo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 245, 255));
        panel.setPreferredSize(new Dimension(285, 0));

        // === SECCION 1: Configurar el arreglo ===
        panel.add(crearTituloSeccion("1. Configuracion del Arreglo"));

        JPanel pTamano = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pTamano.setBackground(new Color(240, 245, 255));
        pTamano.add(new JLabel("Tamano (n):"));
        txtTamano = new JTextField("20", 6);
        txtTamano.setFont(new Font("Arial", Font.PLAIN, 13));
        pTamano.add(txtTamano);
        panel.add(pTamano);

        // Opciones: manual o aleatorio
        JPanel pOpciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pOpciones.setBackground(new Color(240, 245, 255));
        ButtonGroup grupo = new ButtonGroup();
        rbManual    = new JRadioButton("Ingreso manual");
        rbAleatorio = new JRadioButton("Aleatorio", true);
        rbManual.setBackground(new Color(240, 245, 255));
        rbAleatorio.setBackground(new Color(240, 245, 255));
        grupo.add(rbManual);
        grupo.add(rbAleatorio);
        pOpciones.add(rbManual);
        pOpciones.add(rbAleatorio);
        panel.add(pOpciones);

        // Area para ingresar numeros manualmente
        JPanel pManual = new JPanel(new BorderLayout());
        pManual.setBackground(new Color(240, 245, 255));
        pManual.setBorder(BorderFactory.createTitledBorder("Numeros (separados por coma o espacio)"));
        txtIngresoManual = new JTextArea(3, 20);
        txtIngresoManual.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtIngresoManual.setLineWrap(true);
        txtIngresoManual.setEnabled(false);
        pManual.add(new JScrollPane(txtIngresoManual), BorderLayout.CENTER);
        panel.add(pManual);

        rbManual.addActionListener(e    -> txtIngresoManual.setEnabled(true));
        rbAleatorio.addActionListener(e -> txtIngresoManual.setEnabled(false));

        JButton btnGenerar = crearBoton("Generar / Cargar Arreglo", new Color(0, 120, 50));
        btnGenerar.addActionListener(e -> generarArreglo());
        panel.add(envolverBoton(btnGenerar));

        // === SECCION 2: Seleccionar algoritmos ===
        panel.add(Box.createVerticalStrut(10));
        panel.add(crearTituloSeccion("2. Seleccionar Algoritmos"));

        Color[] colores = {
            new Color(200, 60, 60),
            new Color(180, 100, 0),
            new Color(0, 130, 100),
            new Color(0, 80, 180),
            new Color(120, 0, 180),
            new Color(180, 30, 110)
        };

        JPanel pChecks = new JPanel(new GridLayout(6, 1, 2, 2));
        pChecks.setBackground(new Color(240, 245, 255));
        pChecks.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        List<AlgoritmoOrdenamiento> lista = controlador.getAlgoritmos();
        for (int i = 0; i < lista.size(); i++) {
            JCheckBox cb = crearCheckBox(lista.get(i).getNombre(), colores[i]);
            checkboxes.add(cb);
            pChecks.add(cb);
        }
        panel.add(pChecks);

        // Botones rapidos: seleccionar todos / ninguno
        JPanel pSeleccionar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pSeleccionar.setBackground(new Color(240, 245, 255));
        JButton btnTodos = new JButton("Seleccionar Todos");
        btnTodos.setFont(new Font("Arial", Font.PLAIN, 11));
        btnTodos.addActionListener(e -> checkboxes.forEach(cb -> cb.setSelected(true)));
        JButton btnNinguno = new JButton("Ninguno");
        btnNinguno.setFont(new Font("Arial", Font.PLAIN, 11));
        btnNinguno.addActionListener(e -> checkboxes.forEach(cb -> cb.setSelected(false)));
        pSeleccionar.add(btnTodos);
        pSeleccionar.add(btnNinguno);
        panel.add(pSeleccionar);

        // === Botones de accion ===
        panel.add(Box.createVerticalStrut(10));
        JButton btnOrdenar = crearBoton("ORDENAR Y MEDIR TIEMPO", new Color(30, 80, 200));
        btnOrdenar.addActionListener(e -> ordenarYMedir());
        panel.add(envolverBoton(btnOrdenar));

        JButton btnLimpiar = crearBoton("Limpiar Resultados", new Color(140, 40, 40));
        btnLimpiar.addActionListener(e -> limpiarTodo());
        panel.add(envolverBoton(btnLimpiar));

        return panel;
    }

    // ------------------------------------
    // Panel derecho: arreglo original y tabla de resultados
    // ------------------------------------
    private JPanel construirPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(5, 10));
        panel.setBackground(new Color(240, 245, 255));

        // Muestra el arreglo original
        JPanel pOrig = new JPanel(new BorderLayout());
        pOrig.setBorder(BorderFactory.createTitledBorder("Arreglo Original"));
        txtArregloOrig = new JTextArea(4, 30);
        txtArregloOrig.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtArregloOrig.setEditable(false);
        txtArregloOrig.setBackground(new Color(250, 250, 240));
        txtArregloOrig.setLineWrap(true);
        pOrig.add(new JScrollPane(txtArregloOrig));
        panel.add(pOrig, BorderLayout.NORTH);

        // Tabla de resultados
        String[] columnas = {"Algoritmo", "Tiempo (ms)", "Tiempo (ns)", "Arreglo Ordenado (primeros 15)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int f, int c) { return false; }
        };
        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        tablaResultados.setRowHeight(22);
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(160);
        tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(130);
        tablaResultados.getColumnModel().getColumn(3).setPreferredWidth(290);

        // Color diferente por fila para distinguir algoritmos
        Color[] coloresFilas = {
            new Color(255, 230, 230), new Color(255, 240, 210),
            new Color(220, 255, 235), new Color(215, 235, 255),
            new Color(240, 220, 255), new Color(255, 220, 240)
        };
        tablaResultados.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int f, int c) {
                super.getTableCellRendererComponent(t, v, sel, foc, f, c);
                if (!sel) setBackground(coloresFilas[f % coloresFilas.length]);
                return this;
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaResultados);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Resultados de Ordenamiento y Tiempos de Ejecucion"));
        panel.add(scrollTabla, BorderLayout.CENTER);

        // Nota informativa
        JTextArea nota = new JTextArea(
            "Notas:\n" +
            "- Para arreglos pequeños los tiempos pueden salir en 0 ms porque terminan muy rapido.\n"
        );
        nota.setEditable(false);
        nota.setBackground(new Color(245, 250, 255));
        nota.setFont(new Font("Arial", Font.PLAIN, 11));
        nota.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        panel.add(nota, BorderLayout.SOUTH);

        return panel;
    }

    // ------------------------------------
    // Genera el arreglo
    // ------------------------------------
    private void generarArreglo() {
        try {
            int tamano = Integer.parseInt(txtTamano.getText().trim());
            if (tamano <= 0 || tamano > 100000) {
                mostrarError("El tamano debe ser entre 1 y 100,000.");
                return;
            }

            if (rbAleatorio.isSelected()) {
                arregloOriginal = GeneradorArreglo.generarAleatorio(tamano);
            } else {
                arregloOriginal = GeneradorArreglo.parsearDesdeTexto(txtIngresoManual.getText());
                txtTamano.setText(String.valueOf(arregloOriginal.length));
            }

            txtArregloOrig.setText(GeneradorArreglo.arregloATexto(arregloOriginal, arregloOriginal.length));
            modeloTabla.setRowCount(0);
            lblEstado.setText("Arreglo generado con " + arregloOriginal.length + " elementos. Seleccione algoritmos y presione ORDENAR.");

        } catch (NumberFormatException e) {
            mostrarError("Entrada invalida. Verifique el tamano o los numeros ingresados.");
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // ------------------------------------
    // Ordena con los algoritmos seleccionados y muestra los tiempos
    // ------------------------------------
    private void ordenarYMedir() {
        if (arregloOriginal == null || arregloOriginal.length == 0) {
            mostrarError("Primero genera o ingresa un arreglo.");
            return;
        }

        // Recojo los algoritmos que tiene marcados el usuario
        List<AlgoritmoOrdenamiento> seleccionados = new ArrayList<>();
        List<AlgoritmoOrdenamiento> todos          = controlador.getAlgoritmos();
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()) seleccionados.add(todos.get(i));
        }

        if (seleccionados.isEmpty()) {
            mostrarError("Selecciona al menos un algoritmo.");
            return;
        }

        modeloTabla.setRowCount(0);
        barraProgreso.setValue(0);

        // Corro los algoritmos en segundo plano para no congelar la ventana
        SwingWorker<Void, ResultadoOrdenamiento> worker = new SwingWorker<Void, ResultadoOrdenamiento>() {
            protected Void doInBackground() throws Exception {
                int total = seleccionados.size();
                int hecho = 0;
                for (AlgoritmoOrdenamiento alg : seleccionados) {
                    publish(controlador.ejecutar(alg, arregloOriginal));
                    hecho++;
                    setProgress((int)(hecho * 100.0 / total));
                }
                return null;
            }

            protected void process(java.util.List<ResultadoOrdenamiento> resultados) {
                for (ResultadoOrdenamiento r : resultados) {
                    modeloTabla.addRow(new String[]{
                        r.getNombreAlgoritmo(),
                        r.getTiempoMsTexto(),
                        r.getTiempoNsTexto(),
                        r.getVistaPrevia()
                    });
                }
                barraProgreso.setValue(getProgress());
            }

            protected void done() {
                barraProgreso.setValue(100);
                lblEstado.setText("Listo. Se midieron " + modeloTabla.getRowCount() + " algoritmos.");
            }
        };

        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName()))
                barraProgreso.setValue((Integer) evt.getNewValue());
        });

        lblEstado.setText("Ejecutando algoritmos...");
        worker.execute();
    }

    // ------------------------------------
    // Limpia todo
    // ------------------------------------
    private void limpiarTodo() {
        modeloTabla.setRowCount(0);
        txtArregloOrig.setText("");
        arregloOriginal = null;
        barraProgreso.setValue(0);
        lblEstado.setText("Limpiado. Listo para un nuevo arreglo.");
    }

    // ------------------------------------
    // Metodos auxiliares para crear componentes con estilo
    // ------------------------------------
    private JLabel crearTituloSeccion(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(new Color(30, 60, 130));
        lbl.setBorder(BorderFactory.createEmptyBorder(8, 5, 3, 0));
        return lbl;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JCheckBox crearCheckBox(String texto, Color color) {
        JCheckBox cb = new JCheckBox(texto, true);
        cb.setBackground(new Color(240, 245, 255));
        cb.setFont(new Font("Arial", Font.PLAIN, 13));
        cb.setForeground(color);
        return cb;
    }

    private JPanel envolverBoton(JButton btn) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBackground(new Color(240, 245, 255));
        p.add(btn);
        return p;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
