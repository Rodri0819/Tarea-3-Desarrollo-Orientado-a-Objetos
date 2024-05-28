package org.example.gui;
import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class PanelComprador extends JPanel {
    private List<Moneda> monedas = new ArrayList<>();
    private JPanel monedasPanel, contentPanel; // Mover contentPanel a nivel de clase
    private JLabel productoSeleccionadoLabel; // Declaración de etiquetas
    private JLabel totalMonedasLabel;
    private String productoSeleccionado = "Ninguno"; // Almacenar el producto seleccionado
    private JComboBox<String> comboBox; // ComboBox para seleccionar el producto
    private int totalMonedas = 0; // Almacenar el total de monedas
    private Expendedor expendedor;
    int temp = 1;



    public PanelComprador(Expendedor expendedor) {
        this.expendedor = expendedor;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Usar BoxLayout
        setBackground(new Color(0xDBB2FF)); // Color de fondo
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
        totalMonedasLabel.setText("Total Monedas: $" + totalMonedas);
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
        monedaPanel.setLayout(new BoxLayout(monedaPanel, BoxLayout.X_AXIS)); // Cambiar a BoxLayout
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

        // Añadir ActionListener para manejar la acción de clic en el botón
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
                    // Calcular el total de monedas
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
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);
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

    public void actualizarPantalla(){
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
            // Cambiar a una ruta relativa dentro del proyecto
            File imagePath = new File("./expendedorIcon/moneda.png"); // Asegúrate de que el directorio 'resources' está correctamente configurado en tu proyecto
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 100; // Ajusta el ancho deseado de la imagen
            int height = 100; // Ajusta el alto deseado de la imagen
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(icon);
            return label;

        } catch (IOException e) {
            e.printStackTrace();
            return new JLabel("Error loading image: " + e.getMessage()); // Proporciona detalles del error
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

        // Crear JComboBox para la selección de productos
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
                actualizarProductoSeleccionado(); // Actualizamos el producto
                System.out.println(productoSeleccionado);
                switch (productoSeleccionado) {
                    case "Ninguno":
                        JOptionPane.showMessageDialog(PanelComprador.this, "Por favor, seleccione un producto.");
                        break;
                    case "Coca Cola":
                        procesarCompra(Precios.COCA_COLA.getPrecio(), "Coca Cola");
                        break;
                    case "Sprite":
                        procesarCompra(Precios.SPRITE.getPrecio(), "Sprite");
                        break;
                    case "Fanta":
                        procesarCompra(Precios.FANTA.getPrecio(), "Fanta");
                        break;
                    case "Super8":
                        procesarCompra(Precios.SUPER8.getPrecio(), "Super8");
                        break;
                    case "Snickers":
                        procesarCompra(Precios.SNICKERS.getPrecio(), "Snickers");
                        break;
                }
            }
        });

        add(compraButton);
    }
    private boolean removerMonedas(int cantidad) {
        List<Moneda> monedasParaRemover = new ArrayList<>();
        int totalRecolectado = 0;

        // Ordena las monedas de mayor a menor para usar las de mayor valor primero
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


    private void procesarCompra(int precioProducto, String producto) {
        if (totalMonedas >= precioProducto) {
            if (removerMonedas(precioProducto)) {
                actualizarTotalMonedas(totalMonedas - precioProducto);
                JOptionPane.showMessageDialog(PanelComprador.this, "Compra realizada: " + producto);
            } else {
                JOptionPane.showMessageDialog(PanelComprador.this, "Error al procesar la compra.");
            }
        } else {
            JOptionPane.showMessageDialog(PanelComprador.this, "Lo sentimos eres de bajos recursos.");
        }
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        }
    }

