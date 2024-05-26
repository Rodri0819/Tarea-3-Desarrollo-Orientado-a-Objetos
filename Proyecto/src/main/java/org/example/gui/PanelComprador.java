package org.example.gui;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    public PanelComprador() {
        // Establece BoxLayout para alinear los componentes verticalmente
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xDBB2FF)); // Color de fondo

        Labels();
    }

    private void Labels() {
        JLabel titulo = new JLabel("Comprador");
        titulo.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        this.add(titulo);

        JLabel productoSeleccionadoLabel = new JLabel("Producto seleccionado: Ninguno");
        productoSeleccionadoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        productoSeleccionadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        this.add(productoSeleccionadoLabel);

        JLabel totalMonedasLabel = new JLabel("Total Monedas: $0");
        totalMonedasLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalMonedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        this.add(totalMonedasLabel);
    }

    public void Botones(){


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Mmoneda de 1500
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10.f));
        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(30, 150, 100, 100);  // Rellenar el oval con color amarillo
        g2d.setPaint(Color.BLACK);
        g2d.drawOval(30, 150, 100, 100);  // Dibujar el borde del oval con color negro

        // Dibujar texto dentro de la moneda
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("1500", 57, 205);  // Ajusta las coordenadas según sea necesario
    }
}
