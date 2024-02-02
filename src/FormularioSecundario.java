import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioSecundario extends JFrame {
    public FormularioSecundario(String nombre, String color) {
        setTitle("Formulario Secundario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Configurar el color de fondo según la selección del usuario
        Color colorFondo = Color.BLACK; // Color predeterminado
        if (color.equals("Rojo")) {
            colorFondo = Color.RED;
        } else if (color.equals("Verde")) {
            colorFondo = Color.GREEN;
        } else if (color.equals("Azul")) {
            colorFondo = Color.BLUE;
        }

        getContentPane().setBackground(colorFondo);

        // Crear componentes
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel nombreValorLabel = new JLabel(nombre);

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarFormulario();
            }
        });

        // Crear panel y agregar componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(nombreLabel);
        panel.add(nombreValorLabel);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(cerrarButton);

        // Agregar panel al JFrame
        add(panel);

        // Mostrar el JFrame
        setVisible(true);
    }

    private void cerrarFormulario() {
        dispose(); // Cerrar el formulario secundario
    }
}
