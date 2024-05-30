package org.example.gui;

import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xE59F9F));
        Labels();
        deposito();
    }

    private void Labels() {
        JLabel titulo = new JLabel("Expendedor");
        titulo.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(titulo);

        JLabel coca = new JLabel("Coca Cola: $" + Precios.COCA_COLA.getPrecio());
        coca.setFont(new Font("Arial", Font.PLAIN, 16));
        coca.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(coca);

        JLabel sprite = new JLabel("Sprite: $" + Precios.SPRITE.getPrecio());
        sprite.setFont(new Font("Arial", Font.PLAIN, 16));
        sprite.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(sprite);

        JLabel fanta = new JLabel("Fanta: $" + Precios.FANTA.getPrecio());
        fanta.setFont(new Font("Arial", Font.PLAIN, 16));
        fanta.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(fanta);

        JLabel super8 = new JLabel("Super 8: $" + Precios.SUPER8.getPrecio());
        super8.setFont(new Font("Arial", Font.PLAIN, 16));
        super8.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(super8);

        JLabel snickers = new JLabel("Snickers: $" + Precios.SNICKERS.getPrecio());
        snickers.setFont(new Font("Arial", Font.PLAIN, 16));
        snickers.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(snickers);

        this.add(Box.createVerticalStrut(550));
    }

    private void deposito() {
        JPanel depositoPanel = new JPanel();
        depositoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        depositoPanel.setBackground(new Color(0x991C35D0));
        depositoPanel.setPreferredSize(new Dimension(200, 100));

        JLabel label = new JLabel("Área de Depósito");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        depositoPanel.add(label);

        this.add(depositoPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int xStart = 150;
        int yStart = 150;
        int x = xStart;
        int y = yStart;
        int productosPorFila = 5;
        int espacioVertical = 75;

        // Dibujar productos por tipo en filas separadas
        for (int i = 0; i < expendedor.getProductos().size(); i++) {
            Deposito<Producto> deposito = expendedor.getProductos().get(i);
            for (int j = 0; j < deposito.getAllItems().size(); j++) {
                Producto p = deposito.getAllItems().get(j);
                g.drawImage(p.getImagen(), x, y, 50, 50, this);
                x += 70;

                if ((j + 1) % productosPorFila == 0) {
                    x = xStart;
                    y += espacioVertical;
                }
            }
            // Ajustar para la próxima fila de productos del siguiente tipo
            yStart += espacioVertical;
            y = yStart;
            x = xStart;
        }

        // Dibuja el producto comprado si existe
        if (!expendedor.getDepositoProductoComprado().isEmpty()) {
            Producto comprado = expendedor.getDepositoProductoComprado().peek();
            g.drawImage(comprado.getImagen(), 300, 300, 60, 50, this);
        }
    }


    public void handleMouseEvent(MouseEvent e) {
        System.out.println("Mouse clicked in PanelExpendedor at: " + e.getX() + ", " + e.getY());
    }

    public void refreshDisplay() {
        this.repaint();
    }
}
