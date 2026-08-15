package visual.Paneles;

import javax.swing.*;
import java.awt.*;

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


    public PanelJuego(int modo) {
        this.modo = modo;
    }

    @Override
    public void inicializar() {
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
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;

    }

    private JPanel prepararPanelPalabra() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;

    }

    private JPanel prepararPanelTeclado() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 6, 40, 10));
        p.setOpaque(false);

        for (String letra : letras) {
            JButton b = agregarBoton(letra);
            p.add(b);
        }

        return p;
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
}
