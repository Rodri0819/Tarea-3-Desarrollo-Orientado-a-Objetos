package org.example.gui;
import org.example.model.*;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class PanelComprador extends JPanel {
    // Atributos para manejar el estado del comprador, como su posición, etc.
    private int x, y;

    public PanelComprador() {
        setSize(200, 200); // Dimensiones apropiadas para el panel
        x = 50; // Posición inicial X
        y = 50; // Posición inicial Y
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); // Color del comprador
        g.fillRect(x, y, 30, 30); // Dibuja al comprador como un cuadrado
    }

    // Métodos para actualizar posición, etc.
    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
        repaint(); // Repinta el panel cada vez que el comprador se mueve
    }
}
