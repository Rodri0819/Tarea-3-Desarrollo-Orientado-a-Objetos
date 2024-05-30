package org.example.gui;
import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class PanelComprador extends JPanel {
    private PanelPrincipal panelPrincipal;
    private List<Moneda> monedas = new ArrayList<>();
    private JPanel monedasPanel, contentPanel; // Mover contentPanel a nivel de clase
    private JLabel productoSeleccionadoLabel; // Declaración de etiquetas
    private JLabel totalMonedasLabel;
    private String productoSeleccionado = "Ninguno"; // Almacenar el producto seleccionado
    private JComboBox<String> comboBox; // ComboBox para seleccionar el producto
    private int totalMonedas = 0; // Almacenar el total de monedas
    private Expendedor expendedor;
    int temp = 1;

    public PanelComprador(Expendedor expendedor, PanelPrincipal panelPrincipal) {
        this.expendedor = expendedor;
        this.panelPrincipal = panelPrincipal;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0xDBB2FF));
        Labels();
        this.add(Box.createVerticalStrut(15));
        TusMonedas();
        this.add(Box.createVerticalStrut(15));
        actualizarPantalla();
        this.add(Box.createVerticalStrut(15));
    }

    private void Labels() {
        JLabel titulo = new JLabel("Comprador");
        titulo.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titulo);
        this.add(Box.createVerticalStrut(5));

        productoSeleccionadoLabel = new JLabel("Producto seleccionado: Ninguno");
        productoSeleccionadoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        productoSeleccionadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(productoSeleccionadoLabel);
        this.add(Box.createVerticalStrut(5));

        totalMonedasLabel = new JLabel("Total Monedas: $0");
        totalMonedasLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalMonedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(totalMonedasLabel);
    }

    // Método para actualizar el producto seleccionado
    private void actualizarProductoSeleccionado() {
        productoSeleccionado = (String) comboBox.getSelectedItem();
        productoSeleccionadoLabel.setText("Producto seleccionado: " + productoSeleccionado);
    }

    public void actualizarTotalMonedas(int total) {
        totalMonedas = total; // Actualizar el total de monedas
        totalMonedasLabel.setText("Total Monedas $" + totalMonedas);
    }

    private void Monedas() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(new Color(0xDBB2FF));

        JPanel monedasPanel = new JPanel();
        monedasPanel.setLayout(new BoxLayout(monedasPanel, BoxLayout.Y_AXIS));
        monedasPanel.setBackground(new Color(0xDBB2FF));
        monedasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel monedasLabel = new JLabel("Añadir Monedas:");
        monedasLabel.setFont(new Font("Arial", Font.BOLD, 16));
        monedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        monedasPanel.add(monedasLabel);

        // Añade botones al monedasPanel
        monedasPanel.add(createMonedaButton("$100"));
        monedasPanel.add(createMonedaButton("$500"));
        monedasPanel.add(createMonedaButton("$1000"));
        monedasPanel.add(createMonedaButton("$1500"));

        JLabel imageLabel = loadImage();
        contentPanel.add(imageLabel);
        contentPanel.add(monedasPanel);
        add(contentPanel);
    }

    private void TusMonedas() {
        JPanel contenPanel = new JPanel();
        contenPanel.setLayout(new BoxLayout(contenPanel, BoxLayout.X_AXIS));
        contenPanel.setBackground(new Color(0xDBB2FF));

        monedasPanel = new JPanel();
        monedasPanel.setLayout(new BoxLayout(monedasPanel, BoxLayout.Y_AXIS));
        monedasPanel.setBackground(new Color(0xDBB2FF));
        monedasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        monedasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel monedasLabel = new JLabel("Tus Monedas");
        monedasLabel.setFont(new Font("Arial", Font.BOLD, 16));
        monedasPanel.add(monedasLabel);

        JScrollPane scrollPane = new JScrollPane(monedasPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contenPanel.add(scrollPane);

        add(contenPanel);
    }

    private void addMonedaToPanel(Moneda nuevaMoneda) {
        JPanel monedaPanel = new JPanel();
        monedaPanel.setLayout(new BoxLayout(monedaPanel, BoxLayout.X_AXIS));
        monedaPanel.setBackground(new Color(0xDBB2FF));

        JLabel label = new JLabel("Moneda $" + nuevaMoneda.getValor());
        label.setFont(new Font("Arial", Font.BOLD, 16));

        try {
            BufferedImage monedaImage = ImageIO.read(new File(nuevaMoneda.getImagenPath()));
            ImageIcon monedaIcon = new ImageIcon(monedaImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            JLabel monedaLabel = new JLabel(monedaIcon);
            monedaPanel.add(monedaLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        monedaPanel.add(label);
        monedasPanel.add(monedaPanel);
        monedasPanel.revalidate();
        monedasPanel.repaint();
    }

    private JButton createMonedaButton(String valor) {
        JButton button = new JButton(valor);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setBackground(new Color(0xE1E1E1));
        button.setForeground(Color.BLACK);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Moneda nuevaMoneda = null;
                switch (valor) {
                    case "$100":
                        nuevaMoneda = new Moneda100();
                        break;
                    case "$500":
                        nuevaMoneda = new Moneda500();
                        break;
                    case "$1000":
                        nuevaMoneda = new Moneda1000();
                        break;
                    case "$1500":
                        nuevaMoneda = new Moneda1500();
                        break;
                }
                if (nuevaMoneda != null) {
                    monedas.add(nuevaMoneda);
                    addMonedaToPanel(nuevaMoneda);
                    int total = 0;
                    for (Moneda moneda : monedas) {
                        total += moneda.getValor();
                    }
                    actualizarTotalMonedas(total);
                }
            }
        });

        return button;
    }

    private void GuardarMonedas() {
        JButton guardar = new JButton("Depositar monedas");
        guardar.setFont(new Font("Arial", Font.BOLD, 20));
        guardar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        guardar.setBorder(BorderFactory.createRaisedBevelBorder());
        guardar.setBackground(new Color(0x60AB90));
        guardar.setForeground(Color.WHITE);

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = 0;
                actualizarPantalla();
            }
        });
        contentPanel.add(guardar);
    }

    private void actualizarMonedasPanel() {
        monedasPanel.removeAll();
        for (Moneda moneda : monedas) {
            addMonedaToPanel(moneda);
        }
        monedasPanel.revalidate();
        monedasPanel.repaint();
    }

    public void actualizarPantalla() {
        if (temp == 1) {
            Monedas();
            this.add(Box.createVerticalStrut(15));
            GuardarMonedas();
            this.add(Box.createVerticalStrut(15));
            repaint();
        } else {
            contentPanel.removeAll();
            revalidate();
            repaint();
            Producto();
            this.add(Box.createVerticalStrut(15));
            addCompraButton();
            this.add(Box.createVerticalStrut(15));
        }
    }

    private JLabel loadImage() {
        try {
            File imagePath = new File("./expendedorIcon/moneda.png");
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 50;
            int height = 50;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            return new JLabel(icon);
        } catch (IOException e) {
            e.printStackTrace();
            return new JLabel("Error loading image: " + e.getMessage());
        }
    }

    private void Producto() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(new Color(0xDBB2FF));

        JPanel productosPanel = new JPanel();
        productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
        productosPanel.setBackground(new Color(0xDBB2FF));
        productosPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel productosLabel = new JLabel("Seleccionar Producto");
        productosLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productosPanel.add(productosLabel);
        productosPanel.add(Box.createVerticalStrut(15));

        String[] productos = {"Ninguno", "Coca Cola", "Fanta", "Sprite", "Super8", "Snickers"};
        comboBox = new JComboBox<>(productos);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBox.getPreferredSize().height));

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProductoSeleccionado();
            }
        });

        productosPanel.add(comboBox);
        contentPanel.add(productosPanel);
        add(contentPanel);
    }

    private void addCompraButton() {
        JButton compraButton = new JButton("Comprar");
        compraButton.setFont(new Font("Arial", Font.BOLD, 20));
        compraButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        compraButton.setBorder(BorderFactory.createRaisedBevelBorder());
        compraButton.setBackground(new Color(0x4CAF50));
        compraButton.setForeground(Color.WHITE);

        compraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProductoSeleccionado();
                System.out.println(productoSeleccionado);
                boolean compraExitosa = false;

                switch (productoSeleccionado) {
                    case "Ninguno":
                        JOptionPane.showMessageDialog(PanelComprador.this, "Por favor, seleccione un producto.");
                        break;
                    case "Coca Cola":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Coca Cola")) {
                            if (expendedor.eliminarProducto(CocaCola.class)) {
                                compraExitosa = true;
                            } else {
                                JOptionPane.showMessageDialog(PanelComprador.this, "No hay Coca Cola disponible.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(PanelComprador.this, "Eres de bajos recursos no te alcanza");
                        }
                        break;
                    case "Sprite":
                        if (procesarCompra(Precios.SPRITE.getPrecio(), "Sprite")) {
                            if (expendedor.eliminarProducto(Sprite.class)) {
                                compraExitosa = true;
                            } else {
                                JOptionPane.showMessageDialog(PanelComprador.this, "No hay Sprite disponible.");
                            }
                        }else {
                            JOptionPane.showMessageDialog(PanelComprador.this, "Eres de bajos recursos no te alcanza");
                        }
                        break;
                    case "Fanta":
                        if (procesarCompra(Precios.FANTA.getPrecio(), "Fanta")) {
                            if (expendedor.eliminarProducto(Fanta.class)) {
                                compraExitosa = true;
                            } else {
                                JOptionPane.showMessageDialog(PanelComprador.this, "No hay Fanta disponible.");
                            }
                        }else {
                            JOptionPane.showMessageDialog(PanelComprador.this, "Eres de bajos recursos no te alcanza");
                        }
                        break;
                    case "Super8":
                        if (procesarCompra(Precios.SUPER8.getPrecio(), "Super8")) {
                            if (expendedor.eliminarProducto(Super8.class)) {
                                compraExitosa = true;
                            } else {
                                JOptionPane.showMessageDialog(PanelComprador.this, "No hay Super8 disponible.");
                            }
                        }else {
                            JOptionPane.showMessageDialog(PanelComprador.this, "Eres de bajos recursos no te alcanza");
                        }
                        break;
                    case "Snickers":
                        if (procesarCompra(Precios.SNICKERS.getPrecio(), "Snickers")) {
                            if (expendedor.eliminarProducto(Snickers.class)) {
                                compraExitosa = true;
                            } else {
                                JOptionPane.showMessageDialog(PanelComprador.this, "No hay Snickers disponible.");
                            }
                        }else {
                            JOptionPane.showMessageDialog(PanelComprador.this, "Eres de bajos recursos no te alcanza");
                        }
                        break;
                }

                if (compraExitosa) {
                    panelPrincipal.refreshDisplay();
                }
            }
        });

        add(compraButton);
    }

    private boolean removerMonedas(int cantidad) {
        List<Moneda> monedasParaRemover = new ArrayList<>();
        int totalRecolectado = 0;

        monedas.sort((m1, m2) -> m2.getValor() - m1.getValor());

        for (Moneda moneda : monedas) {
            monedasParaRemover.add(moneda);
            totalRecolectado += moneda.getValor();
            if (totalRecolectado >= cantidad) {
                break;
            }
        }

        if (totalRecolectado >= cantidad) {
            monedas.removeAll(monedasParaRemover);
            actualizarMonedasPanel();
            return true;
        } else {
            return false;
        }
    }

    private boolean procesarCompra(int precioProducto, String producto) {
        if (totalMonedas >= precioProducto) {
            if (removerMonedas(precioProducto)) {
                actualizarTotalMonedas(totalMonedas - precioProducto);
                JOptionPane.showMessageDialog(PanelComprador.this, "Compra realizada: " + producto);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public void handleMouseEvent(MouseEvent e) {
        System.out.println("Mouse clicked in PanelComprador at x=" + e.getX() + " y=" + e.getY());
        if (e.getX() > 100 && e.getX() < 200) {
            actualizarProductoSeleccionado();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
