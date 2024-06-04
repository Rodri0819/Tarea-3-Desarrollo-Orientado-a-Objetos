package org.example.gui;

import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private JPanel depositoPanel;
    private JButton recogerProductoButton;
    private Image backgroundImage;
    int vuelto = 0;


    {
        try {
            backgroundImage = ImageIO.read(new File("expendedorIcon/expendedora2.png"));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            backgroundImage = null;
        }
    }

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setPreferredSize(new Dimension(200, 600)); // Ajusta esto según el tamaño de tu imagen o necesidades.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xE59F9F));
        this.add(Box.createVerticalStrut(20));
        Label();
        this.add(Box.createVerticalStrut(592));
        setupUI();
        deposito();


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseEvent(e);
            }
        });
    }

    private void loadImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        backgroundImage = icon.getImage();
    }

    private void Label() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setOpaque(false);

        JLabel titulo = new JLabel("Expendedor");
        titulo.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setOpaque(false);


        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(titulo);
        titlePanel.add(Box.createHorizontalGlue());

        this.add(titlePanel);
    }

    private void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        recogerProductoButton = new JButton("Recoger Producto y Vuelto");
        recogerProductoButton.addActionListener(e -> {
            Producto productoRecogido = expendedor.getProducto();
            if (productoRecogido != null) {
                JOptionPane.showMessageDialog(null, "Has recogido: " + productoRecogido.getNombre());

                depositoPanel.removeAll();
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "No hay producto para recoger.");
            }
        });

        add(recogerProductoButton);
    }


    private void deposito() {
        depositoPanel = new JPanel();
        depositoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        depositoPanel.setBackground(new Color(0x01C35D0, true));
        depositoPanel.setPreferredSize(new Dimension(200, 100));

        JLabel tituloDeposito = new JLabel("Depósito                                                        ");
        tituloDeposito.setFont(new Font("Arial", Font.BOLD, 18));
        tituloDeposito.setForeground(Color.WHITE);
        depositoPanel.add(tituloDeposito);

        this.add(depositoPanel, BorderLayout.SOUTH);
    }

    public void refreshDeposito() {
        if (!expendedor.getDepositoProductoComprado().isEmpty()) {
            depositoPanel.removeAll();

            JLabel tituloDeposito = new JLabel("Depósito");
            tituloDeposito.setFont(new Font("Arial", Font.BOLD, 14));
            tituloDeposito.setAlignmentY(Component.TOP_ALIGNMENT);
            tituloDeposito.setForeground(Color.WHITE);
            depositoPanel.add(tituloDeposito);

            Producto comprado = expendedor.getDepositoProductoComprado().peek(); // Añade los productos comprados recientemente al panel
            ImageIcon imageIcon = new ImageIcon(comprado.getImagen()); // Camino al archivo


            JLabel imageLabel = new JLabel(imageIcon);
            depositoPanel.add(imageLabel);

            depositoPanel.revalidate();
            depositoPanel.repaint();
        }

        // Creación de un nuevo sub-panel dentro de depositoPanel
        JPanel subPanelVuelto = new JPanel();
        subPanelVuelto.setLayout(new FlowLayout(FlowLayout.CENTER, -20, 0));
        subPanelVuelto.setBackground(new Color(0x0484848, true)); // Color para diferenciarlo
        subPanelVuelto.setPreferredSize(new Dimension(500, 80));

        // Añadir componentes al subPanelVuelto


        vuelto = expendedor.getVuelto() - vuelto;
        JLabel subLabel = new JLabel("$" +  vuelto*100 + "      ");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subLabel.setForeground(Color.WHITE);
        subPanelVuelto.add(subLabel);

        for (int i = 0; i < vuelto; i++) {
            // Crear el JLabel y establecer la imagen como ícono
            JLabel labelMoneda = new JLabel(new ImageIcon("expendedorIcon/moneda2.png"));
            subPanelVuelto.add(labelMoneda);
        }
        vuelto = expendedor.getVuelto();
        depositoPanel.add(subPanelVuelto);
        depositoPanel.revalidate();
        depositoPanel.repaint();
    }

    public void handleMouseEvent(MouseEvent e) {
        rellenarDepositos(expendedor.getNumProductos());
    }

    private void rellenarDepositos(int numProductos) {
        List<Deposito<Producto>> depositos = expendedor.getProductos();
        for (int i = 0; i < depositos.size(); i++) {
            Deposito<Producto> deposito = depositos.get(i);
            while (deposito.size() < numProductos) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("No hay imagen para mostrar.");
        }

        int xStart = 60;
        int yStart = 85;
        int x = xStart;
        int y = yStart;
        int productosPorFila = 8;
        int espacioVertical = 119;
        int espacioPrecio = 20;

        Font precioFont = new Font("Arial", Font.BOLD, 14);
        g.setFont(precioFont);
        Color precioColor = Color.BLACK;
        g.setColor(precioColor);

        for (int i = 0; i < expendedor.getProductos().size(); i++) {
            Deposito<Producto> deposito = expendedor.getProductos().get(i);
            for (int j = 0; j < deposito.getAllItems().size(); j++) {

                Producto p = deposito.getAllItems().get(j);

                g.drawImage(p.getImagen(), x, y, 50, 50, this);

                String precio = "$" + p.getPrecio();
                int precioX = x + (50 - g.getFontMetrics().stringWidth(precio)) / 2;
                int precioY = y + 50 + espacioPrecio;
                g.drawString(precio, precioX, precioY);
                x += 70;

                if ((j + 1) % productosPorFila == 0) {
                    x = xStart;
                    y += espacioVertical;
                }
            }

            yStart += espacioVertical;
            y = yStart;
            x = xStart;
        }

    }
}
