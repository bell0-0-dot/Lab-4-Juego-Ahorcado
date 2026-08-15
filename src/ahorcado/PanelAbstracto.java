package ahorcado;



import javax.swing.*;
import java.awt.*;

public abstract class PanelAbstracto extends JPanel {

    protected final AdministradorPaneles adminPaneles;
    protected final JPanel contenedorPrincipal;

    public PanelAbstracto() {
        this.adminPaneles = AdministradorPaneles.getInstancia();
        contenedorPrincipal = obtenerContenedorPrincipal();
        prepararPanel();
    }

    public abstract void inicializar();

    private void prepararPanel() {
        setLayout(new BorderLayout());
        add(contenedorPrincipal, BorderLayout.CENTER);
    }

    protected JPanel obtenerContenedorPrincipal() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new GridBagLayout());
        contenedor.setBackground(new Color(41, 165, 162));
        return contenedor;
    }

    public JPanel agregarPanel(LayoutManager l) {
        JPanel p = new JPanel(l);
        p.setOpaque(false);
        return p;
    }

    public JLabel agregarLabel(String t) {
        JLabel l = new JLabel(t);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setForeground(Color.WHITE);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }

    public JLabel agregarTitulo(String t) {
        JLabel l = agregarLabel(t);
        l.setFont(new Font("Arial", Font.BOLD, 40));
        l.setForeground(Color.WHITE);
        return l;
    }

    public JButton agregarBoton(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(true);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.setPreferredSize(new Dimension(200, 30));
        b.setMaximumSize(new Dimension(200, 30));

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setForeground(Color.WHITE);
                b.setBackground(new Color(16, 89, 89));
                b.setBorderPainted(true);
                b.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setForeground(Color.BLACK);
                b.setBackground(Color.WHITE);
            }
        });
        return b;
    }

    public JButton agregarBoton(String texto, Runnable accion) {
        JButton b = agregarBoton(texto);
        b.addActionListener(e -> accion.run());
        return b;
    }
}
