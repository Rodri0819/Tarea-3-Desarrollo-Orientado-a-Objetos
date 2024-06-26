package org.example.gui;

import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * PanelPrincipal es una clase que representa el panel principal de la interfaz gráfica,
 * que contiene los paneles del comprador y del expendedor en una máquina expendedora.
 */
public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;

    /**
     * Constructor de la clase PanelPrincipal.
     *
     * @param expendedor el expendedor asociado a este panel
     */
    public PanelPrincipal(Expendedor expendedor) {
        exp = new PanelExpendedor(expendedor);
        com = new PanelComprador(expendedor, this);

        exp.setPanelComprador(com);

        // Añadir MouseListener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() < getWidth() / 2) {
                    com.handleMouseEvent(e);
                } else {
                    exp.handleMouseEvent(e);
                }
                repaint();
            }
        });

        this.setBackground(Color.white);

        // SE USA GRINDBAGLAYOUT PARA PERSONALIZAR MAS LA DISPERSION DE ESPACIO DEL PANEL PRINCIPAL
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 0.3;  // El panel comprador ocupará el 30% del espacio horizontal
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(com, gbc);  // Añadir el panel del comprador

        gbc.weightx = 0.7;  // El panel expendedor ocupará el 70% del espacio horizontal
        gbc.gridx = 1;
        this.add(exp, gbc);  // Añadir el panel del expendedor
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método paint al que hace override en la super clase
        // El de la super clase solo pinta el fondo (background)
        com.paintComponent(g); // Llama al método paintComponent definido en el PanelComprador
        exp.paintComponent(g); // Llama al método paintComponent definido en el PanelExpendedor
    }

    /**
     * Refresca la visualización del panel principal.
     */
    public void refreshDisplay() {
        exp.refreshDeposito();
        this.repaint();
    }
}