package ahorcado;

import ahorcado.PanelAbstracto;
import ahorcado.PanelPrincipal;

import javax.swing.*;
import java.awt.*;

public class AdministradorPaneles {

    private static AdministradorPaneles instancia;

    private final JFrame ventana;
    private final JPanel contenedorPrincipal;

    public AdministradorPaneles(){
        this.ventana = construirVentana();
        this.contenedorPrincipal = construirContenedorPrincipal();
    }

    public void mostrar(){
        ventana.add(contenedorPrincipal);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        mostrarPanel(new PanelPrincipal());
    }

    public void mostrarPanel(PanelAbstracto panel){
        panel.inicializar();
        contenedorPrincipal.removeAll();
        contenedorPrincipal.add(panel);
        contenedorPrincipal.revalidate();
        contenedorPrincipal.repaint();
    }

    public static AdministradorPaneles getInstancia(){
        if (instancia == null) {
            instancia = new AdministradorPaneles();
            return instancia;
        }
        return instancia;
    }

    private JFrame construirVentana() {
        JFrame ventana = new JFrame("Ahorcado");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1200, 800);
        return ventana;
    }

    private JPanel construirContenedorPrincipal() {
        JPanel contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BorderLayout());
        return contenedorPrincipal;
    }

}
