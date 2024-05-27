package org.example.gui;
import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.example.model.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;


    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Usa BoxLayout
        this.setBackground(new Color(0xFFC4C4)); //Color fondo

        Labels();

        deposito();
    }

    private void Labels() {
        JLabel titulo = new JLabel("Expendedor");
        titulo.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar horizontalmente
        this.add(titulo);

        JLabel coca = new JLabel("Coca Cola: $" + Precios.COCA_COLA.getPrecio());
        coca.setFont(new Font("Arial", Font.PLAIN, 16));
        coca.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar horizontalmente
        this.add(coca);
        JLabel sprite = new JLabel("Sprite: $" + Precios.SPRITE.getPrecio());
        sprite.setFont(new Font("Arial", Font.PLAIN, 16));
        sprite.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar horizontalmente
        this.add(sprite);
        JLabel fanta = new JLabel("Fanta: $" + Precios.FANTA.getPrecio());
        fanta.setFont(new Font("Arial", Font.PLAIN, 16));
        fanta.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar horizontalmente
        this.add(fanta);
        JLabel super8 = new JLabel("Super 8: $" + Precios.SUPER8.getPrecio());
        super8.setFont(new Font("Arial", Font.PLAIN, 16));
        super8.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar horizontalmente
        this.add(super8);
        JLabel snickers = new JLabel("Snickers: $" + Precios.SNICKERS.getPrecio());
        snickers.setFont(new Font("Arial", Font.PLAIN, 16));
        snickers.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar horizontalmente
        this.add(snickers);
        this.add(Box.createVerticalStrut(550));
    }
    private void deposito() {
        JPanel depositoPanel = new JPanel();
        depositoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinear a la derecha
        depositoPanel.setBackground(new Color(0x991C35D0)); // Color sólido sin transparencia
        depositoPanel.setPreferredSize(new Dimension(200, 100)); // Tamaño visible

        JLabel label = new JLabel("Área de Depósito");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        depositoPanel.add(label);

        // Añade el panel deposito al sur del BorderLayout
        this.add(depositoPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int x = 150;
        int y = 150;
        int productosPorFila = 5;  // Número de productos por fila
        int productoActual = 0;

        for (Deposito<Producto> deposito : expendedor.getProductos()) {
            for (Producto p : deposito.getAllItems()) {
                g.drawImage(p.getImagen(), x, y, 50, 50, this);
                x += 70;  // Ajusta la posición x para el siguiente producto
                productoActual++;

                // Cambiar a la siguiente fila después de cierto número de productos
                if (productoActual % productosPorFila == 0) {
                    x = 150;  // Reinicia la posición x
                    y += 100;  // Ajusta la posición y para la siguiente fila
                }
            }
            // Después de cada depósito, asegurarse de que se inicie en la siguiente columna
            if (productoActual % productosPorFila != 0) {
                x += 70;  // Ajusta la posición x para el siguiente depósito
                y = 30;  // Reinicia y para el siguiente depósito
            }
        }

        // Dibuja el producto comprado si existe
        if (!expendedor.getDepósitoProductoComprado().isEmpty()) {
            Producto comprado = expendedor.getDepósitoProductoComprado().peek();
            g.drawImage(comprado.getImagen(), 300, 300, 60, 50, this);  // Asumiendo que el depósito está en (300, 200)
        }
    }
}