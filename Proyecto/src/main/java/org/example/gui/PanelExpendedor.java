package org.example.gui;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

import org.example.model.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;  // Guarda la referencia al Expendedor
        this.setBackground(new Color(0xFFC4C4)); //Color fondo
        this.setBounds(400, 0, 850, 670);
       Labels();
    }

    public void Labels(){
        JLabel titulo = new JLabel("Expendedor");
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
