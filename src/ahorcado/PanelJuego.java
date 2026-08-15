package ahorcado;

import ahorcado.PanelAbstracto;
import javax.swing.*;
import java.awt.*;
import visual.Paneles.PanelPrincipal;

public class PanelJuego extends PanelAbstracto {

    public static int PALABRA_FIJA = 0;
    public static int PALABRA_ALEATORIA = 1;
    private int modo;

    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JPanel panelImagen;
    private JPanel panelPalabra;
    private JPanel panelTeclado;

    private String[] letras = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private JLabel labelImagenAhorcado;
    private ImageIcon[] imagenesAhorcado;
    
    public PanelJuego(int modo) {
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
        // Cargar imágenes antes de construir la interfaz gráfica
        cargarImagenes();

        panelSuperior = prepararPanelSuperior();
        panelImagen = prepararPanelImagen();
        panelPalabra = prepararPanelPalabra();
        panelTeclado = prepararPanelTeclado();
        panelPrincipal = prepararPanelPrincipal();

        contenedorPrincipal.add(panelPrincipal, BorderLayout.CENTER);
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

        if (modo == 1) {
            p.add(agregarBoton("Agregar Palabra"));
        }

        return p;
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
        JPanel p = new JPanel();
        p.setOpaque(false);
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
        p.setBackground(new Color(19, 89, 87));

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

    private JPanel prepararPanelPrincipal(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);

        p.add(panelSuperior, BorderLayout.NORTH);
        p.add(panelImagen, BorderLayout.WEST);
        p.add(panelPalabra, BorderLayout.CENTER);
        p.add(panelTeclado, BorderLayout.SOUTH);
        return p;
    }
    
    public void actualizarErrores(int errores) {
        if (imagenesAhorcado != null && errores >= 0 && errores < imagenesAhorcado.length) {
            if (imagenesAhorcado[errores] != null) {
                labelImagenAhorcado.setIcon(imagenesAhorcado[errores]);
            }
        }
    }
    
    private void procesarIntento(char letra) {
    }
}
