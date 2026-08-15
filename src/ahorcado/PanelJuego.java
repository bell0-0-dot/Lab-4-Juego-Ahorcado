package ahorcado;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends PanelAbstracto {

    public static final int PALABRA_FIJA = 0;
    public static final int PALABRA_ALEATORIA = 1;
    private final int modo;

    private JuegoAhorcadoBase juego;
    private static AdministradorPalabras adminPalabras = new AdministradorPalabras();

    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JPanel panelImagen;
    private JPanel panelPalabra;
    private JPanel panelTeclado;

    private final String[] letras = new String[]{
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
        "U", "V", "W", "X", "Y", "Z"
    };

    private JLabel labelImagenAhorcado;
    private JLabel labelPalabraOculta;
    private ImageIcon[] imagenesAhorcado;

    public PanelJuego(int modo) {
        super();
        this.modo = modo;
    }

    private void cargarImagenes() {
        imagenesAhorcado = new ImageIcon[7];
        for (int i = 0; i < 7; i++) {
            String ruta = "/recursos/img" + i + ".png";
            java.net.URL imgURL = getClass().getResource(ruta);

            if (imgURL != null) {
                ImageIcon iconoOriginal = new ImageIcon(imgURL);
                Image imgEscalada = iconoOriginal.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
                imagenesAhorcado[i] = new ImageIcon(imgEscalada);
            } else {
                System.err.println("No se encontró la imagen: " + ruta);
            }
        }
    }

    @Override
    public void inicializar() {
        cargarImagenes();
        configurarJuego();

        panelSuperior = prepararPanelSuperior();
        panelImagen = prepararPanelImagen();
        panelPalabra = prepararPanelPalabra();
        panelTeclado = prepararPanelTeclado();
        panelPrincipal = prepararPanelPrincipal();

        contenedorPrincipal.add(panelPrincipal, BorderLayout.CENTER);
        actualizarPantalla();
    }

    private void configurarJuego() {
        if (modo == PALABRA_FIJA) {
            String palabraIngresada = JOptionPane.showInputDialog(
                this, 
                "Ingrese la palabra secreta para jugar:", 
                "Modo Palabra Fija", 
                JOptionPane.QUESTION_MESSAGE
            );

            if (palabraIngresada == null || palabraIngresada.trim().isEmpty()) {
                palabraIngresada = "JAVA";
            }
            juego = new JuegoAhorcadoFijo(palabraIngresada);
        } else {
            JuegoAhorcadoAzar juegoAzar = new JuegoAhorcadoAzar(adminPalabras);
            juegoAzar.inicializarPalabraSecretayOculta();
            juego = juegoAzar;
        }
    }

    @Override
    protected JPanel obtenerContenedorPrincipal() {
        JPanel contenedor = super.obtenerContenedorPrincipal();
        contenedor.setLayout(new BorderLayout());
        return contenedor;
    }

    private JPanel prepararPanelSuperior() {
        JPanel p = new JPanel();
        p.setBackground(new Color(19, 89, 87));
        p.setPreferredSize(new Dimension(0, 50));

        p.add(agregarBoton("Retirarse", () -> {
            adminPaneles.mostrarPanel(new PanelPrincipal());
        }));

        if (modo == PALABRA_ALEATORIA) {
            p.add(agregarBoton("Agregar Palabra", this::accionAgregarPalabra));
        }

        return p;
    }

    private void accionAgregarPalabra() {
        String nuevaPalabra = JOptionPane.showInputDialog(
            this, 
            "Ingrese una nueva palabra para el banco:", 
            "Agregar Palabra", 
            JOptionPane.QUESTION_MESSAGE
        );

        if (nuevaPalabra != null && !nuevaPalabra.trim().isEmpty()) {
            try {
                adminPalabras.agregarPalabra(nuevaPalabra);
                JOptionPane.showMessageDialog(this, "¡Palabra agregada con éxito!");
            } catch (PalabraDuplicadaException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel prepararPanelImagen() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        p.setPreferredSize(new Dimension(300, 0));

        labelImagenAhorcado = new JLabel();
        if (imagenesAhorcado != null && imagenesAhorcado[0] != null) {
            labelImagenAhorcado.setIcon(imagenesAhorcado[0]);
        }

        p.add(labelImagenAhorcado);
        return p;
    }

    private JPanel prepararPanelPalabra() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);

        labelPalabraOculta = new JLabel();
        labelPalabraOculta.setFont(new Font("Monospaced", Font.BOLD, 36));
        labelPalabraOculta.setForeground(Color.WHITE);

        p.add(labelPalabraOculta);
        return p;
    }

    private JPanel prepararPanelTeclado() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));

        contenedor.setOpaque(true);
        contenedor.setBackground(new Color(19, 89, 87));
        contenedor.add(agregarLabel("Seleccione una letra: "));
        contenedor.add(Box.createVerticalStrut(10));

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 6, 10, 10));
        p.setOpaque(false);

        for (String letra : letras) {
            JButton b = agregarBoton(letra);
            b.addActionListener(e -> {
                b.setEnabled(false);
                procesarIntento(letra.charAt(0));
            });
            p.add(b);
        }

        contenedor.add(p);
        return contenedor;
    }

    private JPanel prepararPanelPrincipal() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);

        p.add(panelSuperior, BorderLayout.NORTH);
        p.add(panelImagen, BorderLayout.WEST);
        p.add(panelPalabra, BorderLayout.CENTER);
        p.add(panelTeclado, BorderLayout.SOUTH);
        return p;
    }

    private void procesarIntento(char letra) {
        try {
            juego.jugar(letra);
            actualizarPantalla();
            verificarEstadoJuego();
        } catch (LetraInvalidaException | LetraRepetidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarPantalla() {
        String formatoVisual = juego.getPalabraIngresada().replace("", " ").trim();
        labelPalabraOculta.setText(formatoVisual);

        int errores = 6 - juego.getIntentos();
        if (imagenesAhorcado != null && errores >= 0 && errores < imagenesAhorcado.length) {
            if (imagenesAhorcado[errores] != null) {
                labelImagenAhorcado.setIcon(imagenesAhorcado[errores]);
            }
        }
    }

    private void verificarEstadoJuego() {
        if (juego.esGanador()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Has adivinado la palabra: " + juego.getPalabraSecreta(), "¡Victoria!", JOptionPane.INFORMATION_MESSAGE);
            adminPaneles.mostrarPanel(new PanelPrincipal());
        } else if (juego.getIntentos() <= 0) {
            JOptionPane.showMessageDialog(this, "¡Has perdido! La palabra era: " + juego.getPalabraSecreta(), "Fin del juego", JOptionPane.ERROR_MESSAGE);
            adminPaneles.mostrarPanel(new PanelPrincipal());
        }
    }
}