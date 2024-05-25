package org.example.gui;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {

    public PanelComprador() {
        this.setBackground(new Color(0xDBB2FF));
        this.setBounds(0, 0, 400, 670);
        Labels();
    }

    public void Labels() {
        JLabel titulo = new JLabel("Comprador");
        titulo.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        titulo.setAlignmentX(CENTER_ALIGNMENT); // Centrar horizontalmente el JLabel
        this.add(Box.createVerticalGlue()); // Añadir espacio flexible arriba
        this.add(titulo);
        this.add(Box.createVerticalGlue()); // Añadir espacio flexible abajo

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
