package org.example.gui;

import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PanelComprador es una clase que representa el panel de la interfaz gráfica
 * para el comprador en una máquina expendedora.
 */
public class PanelComprador extends JPanel {
    private PanelPrincipal panelPrincipal;
    private List<Moneda> monedas = new ArrayList<>();
    private JPanel monedasPanel, contentPanel, añadirmonedas, mainPanel;
    private JLabel productoSeleccionadoLabel;
    private JLabel totalMonedasLabel;
    private String productoSeleccionado = "Ninguno";
    private JComboBox<String> comboBox;
    private int totalMonedas = 0;
    private Expendedor expendedor;
    int temp = 1;
    private List<Moneda> listaMonedas = new ArrayList<>();

    /**
     * Constructor de la clase PanelComprador.
     *
     * @param expendedor    el expendedor asociado a este panel
     * @param panelPrincipal el panel principal de la aplicación
     */
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

    /**
     * Configura y añade las etiquetas al panel.
     */
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

        totalMonedasLabel = new JLabel("Tus Monedas: $0");
        totalMonedasLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalMonedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(totalMonedasLabel);
    }

    /**
     * Actualiza la etiqueta del producto seleccionado.
     */
    private void actualizarProductoSeleccionado() {
        productoSeleccionado = (String) comboBox.getSelectedItem();
        productoSeleccionadoLabel.setText("Producto seleccionado: " + productoSeleccionado);
    }

    /**
     * Actualiza el total de monedas en el panel.
     *
     * @param total el nuevo total de monedas
     */
    public void actualizarTotalMonedas(int total) {
        totalMonedas = total;
        totalMonedasLabel.setText("Tus Monedas: $" + totalMonedas);
    }

    /**
     * Agrega el vuelto al panel y actualiza la visualización.
     *
     * @param vueltoMonedas la lista de monedas de vuelto
     */
    public void agregarVuelto(List<Moneda> vueltoMonedas) {
        for (Moneda moneda : vueltoMonedas) {
            totalMonedas += moneda.getValor();
            monedas.add(moneda);
            addMonedaToPanel(moneda);
        }
        totalMonedasLabel.setText("Tus Monedas: $" + totalMonedas);
        monedasPanel.revalidate();
        monedasPanel.repaint();
    }

    /**
     * Configura y añade los botones de monedas al panel.
     */
    private void Monedas() {
        añadirmonedas = new JPanel();
        añadirmonedas.setLayout(new BoxLayout(añadirmonedas, BoxLayout.Y_AXIS));
        añadirmonedas.setOpaque(false);

        JLabel monedasLabel = new JLabel("Añadir Monedas:");
        monedasLabel.setFont(new Font("Arial", Font.BOLD, 20));
        monedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        añadirmonedas.add(monedasLabel);
        añadirmonedas.add(Box.createVerticalStrut(10));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setOpaque(false);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xDBB2FF, true));
        contentPanel.setOpaque(false);
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIcon = loadImage();
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));
        mainPanel.add(imageLabel);

        contentPanel.add(createMonedaButton("$100"));
        contentPanel.add(createMonedaButton("$500"));
        contentPanel.add(createMonedaButton("$1000"));
        contentPanel.add(createMonedaButton("$1500"));

        GuardarMonedas();
        mainPanel.add(contentPanel);
        add(añadirmonedas);
        add(mainPanel);
    }

    /**
     * Configura y añade el panel que muestra las monedas del usuario.
     */
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

    /**
     * Añade una nueva moneda al panel de monedas.
     *
     * @param nuevaMoneda la nueva moneda a añadir
     */
    private void addMonedaToPanel(Moneda nuevaMoneda) {
        listaMonedas.add(nuevaMoneda);
        actualizarMonedasPanel();
    }

    /**
     * Crea un botón para añadir monedas.
     *
     * @param valor el valor de la moneda
     * @return el botón creado
     */
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

    /**
     * Añade el botón para ingresar monedas al panel.
     */
    private void GuardarMonedas() {
        JButton ingresar = new JButton("Ingresar");
        ingresar.setFont(new Font("Arial", Font.BOLD, 18));
        ingresar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        ingresar.setBorder(BorderFactory.createRaisedBevelBorder());
        ingresar.setBackground(new Color(0x60AB90));
        ingresar.setForeground(Color.WHITE);
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = 0;
                actualizarPantalla();
            }
        });
        contentPanel.add(ingresar);
    }

    /**
     * Actualiza el panel de monedas, ordenando y mostrando las monedas.
     */
    private void actualizarMonedasPanel() {
        listaMonedas.sort((m1, m2) -> Integer.compare(m2.getValor(), m1.getValor()));
        monedasPanel.removeAll();

        for (Moneda moneda : listaMonedas) {
            JPanel monedaPanel = new JPanel();
            monedaPanel.setLayout(new BoxLayout(monedaPanel, BoxLayout.X_AXIS));
            monedaPanel.setBackground(new Color(0xDBB2FF));

            JLabel label = new JLabel("Moneda $" + moneda.getValor());
            label.setFont(new Font("Arial", Font.BOLD, 16));

            try {
                BufferedImage monedaImage = ImageIO.read(new File(moneda.getImagenPath()));
                ImageIcon monedaIcon = new ImageIcon(monedaImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                JLabel monedaLabel = new JLabel(monedaIcon);
                monedaPanel.add(monedaLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }

            monedaPanel.add(label);
            monedasPanel.add(monedaPanel);
        }

        monedasPanel.revalidate();
        monedasPanel.repaint();
    }

    /**
     * Actualiza la pantalla, mostrando los componentes correspondientes según el estado.
     */
    public void actualizarPantalla() {
        if (temp == 1) {
            Monedas();
            this.add(Box.createVerticalStrut(15));
            repaint();
        } else {
            mainPanel.removeAll();
            añadirmonedas.removeAll();
            revalidate();
            repaint();
            Producto();
            this.add(Box.createVerticalStrut(15));
            addCompraButton();
            this.add(Box.createVerticalStrut(15));
        }
    }

    /**
     * Carga y devuelve la imagen de la moneda.
     *
     * @return el ImageIcon con la imagen de la moneda
     */
    private ImageIcon loadImage() {
        try {
            File imagePath = new File("./expendedorIcon/moneda.png");
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 100;
            int height = 100;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Configura y añade el panel de selección de productos.
     */
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

    /**
     * Añade el botón de compra al panel.
     */
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
                if (!expendedor.getDepositoProductoComprado().isEmpty()) {
                    JOptionPane.showMessageDialog(PanelComprador.this, "El depósito ya contiene un producto. Recógelo antes de comprar otro.");
                    return;
                }

                actualizarProductoSeleccionado();
                System.out.println(productoSeleccionado);
                boolean compraExitosa = false;

                switch (productoSeleccionado) {
                    case "Ninguno":
                        JOptionPane.showMessageDialog(PanelComprador.this, "Por favor, seleccione un producto.");
                        break;
                    case "Coca Cola":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Coca Cola")) {
                            compraExitosa = true;
                        }
                        break;
                    case "Sprite":
                        if (procesarCompra(Precios.SPRITE.getPrecio(), "Sprite")) {
                            compraExitosa = true;
                        }
                        break;
                    case "Fanta":
                        if (procesarCompra(Precios.FANTA.getPrecio(), "Fanta")) {
                            compraExitosa = true;
                        }
                        break;
                    case "Super8":
                        if (procesarCompra(Precios.SUPER8.getPrecio(), "Super8")) {
                            compraExitosa = true;
                        }
                        break;
                    case "Snickers":
                        if (procesarCompra(Precios.SNICKERS.getPrecio(), "Snickers")) {
                            compraExitosa = true;
                        }
                        break;
                }

                if (compraExitosa) {
                    panelPrincipal.refreshDisplay();
                    actualizarMonedasPanel();
                } else if (!productoSeleccionado.equals("Ninguno")) {
                    if (Precios.SNICKERS.getPrecio() > totalMonedas) {
                        JOptionPane.showMessageDialog(PanelComprador.this, "No tienes monedas suficientes.");
                    }
                }
            }
        });

        add(compraButton);
    }

    /**
     * Remueve las monedas necesarias para una compra.
     *
     * @param cantidad la cantidad de dinero a remover
     * @return la lista de monedas removidas
     */
    private List<Moneda> removerMonedas(int cantidad) {
        List<Moneda> monedasParaRemover = new ArrayList<>();
        int totalRecolectado = 0;

        listaMonedas.sort((m1, m2) -> Integer.compare(m2.getValor(), m1.getValor()));

        for (Moneda moneda : listaMonedas) {
            monedasParaRemover.add(moneda);
            totalRecolectado += moneda.getValor();
            if (totalRecolectado >= cantidad) {
                break;
            }
        }

        if (totalRecolectado >= cantidad) {
            listaMonedas.removeAll(monedasParaRemover);
            return monedasParaRemover;
        } else {
            return null;
        }
    }

    /**
     * Procesa la compra de un producto.
     *
     * @param precioProducto el precio del producto
     * @param producto       el nombre del producto
     * @return true si la compra fue exitosa, false en caso contrario
     */
    private boolean procesarCompra(int precioProducto, String producto) {
        List<Moneda> monedasUsadas = removerMonedas(precioProducto);
        if (monedasUsadas != null) {
            try {
                expendedor.comprarProducto(monedasUsadas.get(0), producto);
                actualizarTotalMonedas(totalMonedas - monedasUsadas.get(0).getValor());
                JOptionPane.showMessageDialog(PanelComprador.this, "Compra realizada: " + producto);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al comprar: " + e.getMessage());
                monedas.addAll(monedasUsadas);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Maneja los eventos de ratón en el panel.
     *
     * @param e el evento de ratón
     */
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
