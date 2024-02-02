import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablaPersonaApp extends JFrame {
    private JTextField idTextField, nombreTextField, apellidoTextField;
    private JComboBox<String> buscarPorComboBox;
    private DefaultTableModel tablaModelo;
    private JTable tabla;

    public TablaPersonaApp() {
        // Configuración del JFrame
        setTitle("Tabla de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear componentes del formulario
        JLabel idLabel = new JLabel("ID:");
        idTextField = new JTextField(5);
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreTextField = new JTextField(10);
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoTextField = new JTextField(10);

        // Combo box para buscar
        JLabel buscarPorLabel = new JLabel("Buscar por:");
        String[] opcionesBuscarPor = {"ID", "Nombre", "Apellido"};
        buscarPorComboBox = new JComboBox<>(opcionesBuscarPor);

        // Botones
        JButton añadirButton = new JButton("Añadir");
        JButton actualizarButton = new JButton("Actualizar");
        JButton limpiarButton = new JButton("Limpiar");

        // Configuración de la tabla
        tablaModelo = new DefaultTableModel();
        tablaModelo.addColumn("ID");
        tablaModelo.addColumn("Nombre");
        tablaModelo.addColumn("Apellido");
        tabla = new JTable(tablaModelo);

        // Configuración de eventos de botones
        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                añadirPersona();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPersona();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // Configuración de diseño
        JPanel formularioPanel = new JPanel(new GridLayout(4, 2));
        formularioPanel.add(idLabel);
        formularioPanel.add(idTextField);
        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreTextField);
        formularioPanel.add(apellidoLabel);
        formularioPanel.add(apellidoTextField);
        formularioPanel.add(new JLabel()); // Espacio en blanco
        formularioPanel.add(buscarPorComboBox);

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(añadirButton);
        botonesPanel.add(actualizarButton);
        botonesPanel.add(limpiarButton);

        // Agregar componentes al JFrame
        add(formularioPanel, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);

        // Mostrar el JFrame
        setVisible(true);
    }

    private void añadirPersona() {
        String id = idTextField.getText();
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();

        tablaModelo.addRow(new Object[]{id, nombre, apellido});

        limpiarCampos();
    }

    private void actualizarPersona() {
        String buscarPor = buscarPorComboBox.getSelectedItem().toString();
        String valorBuscado = JOptionPane.showInputDialog(this, "Introduce " + buscarPor + " a buscar:");

        for (int i = 0; i < tablaModelo.getRowCount(); i++) {
            if (tablaModelo.getValueAt(i, getColumnIndex(buscarPor)).equals(valorBuscado)) {
                tabla.setRowSelectionInterval(i, i);
                JOptionPane.showMessageDialog(this, "Persona encontrada:\n" +
                        "ID: " + tablaModelo.getValueAt(i, 0) + "\n" +
                        "Nombre: " + tablaModelo.getValueAt(i, 1) + "\n" +
                        "Apellido: " + tablaModelo.getValueAt(i, 2));
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Persona no encontrada");
    }

    private int getColumnIndex(String columnName) {
        for (int i = 0; i < tablaModelo.getColumnCount(); i++) {
            if (tablaModelo.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private void limpiarCampos() {
        idTextField.setText("");
        nombreTextField.setText("");
        apellidoTextField.setText("");
        idTextField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TablaPersonaApp();
            }
        });
    }
}
