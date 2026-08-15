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

    public PanelJuego(int modo) {
        this.modo = modo;
    }

    @Override
    public void inicializar() {
        panelPrincipal = prepararPanelPrincipal();

        contenedorPrincipal.add(panelPrincipal);
    }

    private JPanel prepararPanelSuperior() {

    }

    private JPanel prepararPanelImagen() {

    }

    private JPanel prepararPanelPalabra() {

    }

    private JPanel prepararPanelTeclado() {

    }

    private JPanel prepararPanelPrincipal(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        return p;
    }
}
