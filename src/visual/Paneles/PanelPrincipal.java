package visual.Paneles;

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
        p.add(agregarBoton("Jugar Modo Palabra Fija", () -> adminPaneles.mostrarPanel(new PanelJuego(PanelJuego.PALABRA_FIJA))));
        p.add(agregarBoton("Jugar Modo Palabra Aleatoria", () -> adminPaneles.mostrarPanel(new PanelJuego(PanelJuego.PALABRA_ALEATORIA))));
        p.add(agregarBoton("Salir", () -> System.exit(0)));
        return p;
    }
}
