package ahorcado;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends PanelAbstracto {

    private JPanel panelPrincipal;
    private JPanel panelBotones;

    @Override
    public void inicializar() {
        panelBotones = obtenerPanelBotones();
        panelPrincipal = obtenerPanelPrincipal();
        contenedorPrincipal.add(panelPrincipal);
    }

    private JPanel obtenerPanelPrincipal(){
         JPanel p = new JPanel();
         p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
         p.setOpaque(false);
         p.setAlignmentX(CENTER_ALIGNMENT);
         p.add(agregarTitulo("AHORCADO"));
         p.add(Box.createVerticalStrut(40));
         p.add(panelBotones);
         return p;
    }

    private JPanel obtenerPanelBotones(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 1, 0, 12));
        p.setOpaque(false);
        p.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnFijo = agregarBoton("Jugar Modo Palabra Fija", new Runnable() {
            @Override
            public void run() {
                adminPaneles.mostrarPanel(new PanelJuego(PanelJuego.PALABRA_FIJA));
            }
        });

        JButton btnAleatorio = agregarBoton("Jugar Modo Palabra Aleatoria", new Runnable() {
            @Override
            public void run() {
                adminPaneles.mostrarPanel(new PanelJuego(PanelJuego.PALABRA_ALEATORIA));
            }
        });

        JButton btnSalir = agregarBoton("Salir", new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        });

        p.add(btnFijo);
        p.add(btnAleatorio);
        p.add(btnSalir);

        return p;
    }
}