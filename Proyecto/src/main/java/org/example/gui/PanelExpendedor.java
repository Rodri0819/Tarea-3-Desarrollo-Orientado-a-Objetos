package org.example.gui;

import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private JPanel depositoPanel;
    private JButton recogerProductoButton;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xE59F9F));
        Labels();
        setupUI();
        deposito();

        // Agregar MouseListener para rellenar los depósitos vacíos
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseEvent(e);
            }
        });
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

    private void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        recogerProductoButton = new JButton("Recoger Producto");
        recogerProductoButton.addActionListener(e -> {
            Producto productoRecogido = expendedor.getProducto();
            if (productoRecogido != null) {
                JOptionPane.showMessageDialog(null, "Has recogido: " + productoRecogido.getNombre());
                // Aquí puedes agregar cualquier lógica adicional como actualizar el estado del depósito visualmente.
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "No hay producto para recoger.");
            }
        });

        add(recogerProductoButton);
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
    }

    private void deposito() {
        depositoPanel = new JPanel();
        depositoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        depositoPanel.setBackground(new Color(0x991C35D0));
        depositoPanel.setPreferredSize(new Dimension(200, 100));

        JLabel tituloDeposito = new JLabel("Área de Depósito");
        tituloDeposito.setFont(new Font("Arial", Font.BOLD, 16));
        depositoPanel.add(tituloDeposito);

        // Creación de un nuevo sub-panel dentro de depositoPanel
        JPanel subPanelVuelto = new JPanel();
        subPanelVuelto.setLayout(new FlowLayout(FlowLayout.CENTER));
        subPanelVuelto.setBackground(new Color(0xFFFFFF)); // Color para diferenciarlo
        subPanelVuelto.setPreferredSize(new Dimension(180, 80));
        // Añadir componentes al subPanelVuelto
        JLabel subLabel = new JLabel("Vuelto: ");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subPanelVuelto.add(subLabel);
        // Añadir el subPanelVuelto a depositoPanel
        depositoPanel.add(subPanelVuelto);

        this.add(depositoPanel, BorderLayout.SOUTH);
    }

    public void refreshDeposito() {
        if (!expendedor.getDepositoProductoComprado().isEmpty()) {
            depositoPanel.removeAll(); // Limpia el panel antes de agregar elementos actualizados

            JLabel tituloDeposito = new JLabel("Área de Depósito");
            tituloDeposito.setFont(new Font("Arial", Font.BOLD, 16));
            depositoPanel.add(tituloDeposito);

            Producto comprado = expendedor.getDepositoProductoComprado().peek(); // Añade los productos comprados recientemente al panel
            ImageIcon imageIcon = new ImageIcon(comprado.getImagen()); // Camino al archivo

            // Crear el JLabel con el ImageIcon
            JLabel imageLabel = new JLabel(imageIcon);
            depositoPanel.add(imageLabel);

            depositoPanel.revalidate();
            depositoPanel.repaint();
        }

        // Creación de un nuevo sub-panel dentro de depositoPanel
        JPanel subPanelVuelto = new JPanel();
        subPanelVuelto.setLayout(new FlowLayout(FlowLayout.CENTER));
        subPanelVuelto.setBackground(new Color(0xFFFFFF)); // Color para diferenciarlo
        subPanelVuelto.setPreferredSize(new Dimension(180, 80));

        // Añadir componentes al subPanelVuelto
        JLabel subLabel = new JLabel("Vuelto: " +  expendedor.getVuelto()*100);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subPanelVuelto.add(subLabel);

        // Añadir el subPanelVuelto a depositoPanel
        depositoPanel.add(subPanelVuelto);

        depositoPanel.revalidate();
        depositoPanel.repaint();
    }

    public void handleMouseEvent(MouseEvent e) {
        rellenarDepositos();
    }

    private void rellenarDepositos() {
        List<Deposito<Producto>> depositos = expendedor.getProductos();
        for (int i = 0; i < depositos.size(); i++) {
            Deposito<Producto> deposito = depositos.get(i);
            while (deposito.size() < 5) {
                switch (i) {
                    case Expendedor.COCA - 1:
                        deposito.add(new CocaCola(deposito.size(), "CocaCola", Precios.COCA_COLA.getPrecio(), "CocaCola"));
                        break;
                    case Expendedor.SPRITE - 1:
                        deposito.add(new Sprite(deposito.size(), "Sprite", Precios.SPRITE.getPrecio(), "Sprite"));
                        break;
                    case Expendedor.FANTA - 1:
                        deposito.add(new Fanta(deposito.size(), "Fanta", Precios.FANTA.getPrecio(), "Fanta"));
                        break;
                    case Expendedor.SUPER8 - 1:
                        deposito.add(new Super8(deposito.size(), "Super8", Precios.SUPER8.getPrecio(), "Chocolate"));
                        break;
                    case Expendedor.SNICKERS - 1:
                        deposito.add(new Snickers(deposito.size(), "Snickers", Precios.SNICKERS.getPrecio(), "Snicker"));
                        break;
                }
            }
        }
        repaint();
    }
}
