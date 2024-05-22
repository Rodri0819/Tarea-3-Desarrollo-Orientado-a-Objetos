package org.example.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Pruebas extends JFrame {
    public Pruebas() {
        this.setSize(500, 500); // Tamaño de ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Panel principal");
        this.setLocationRelativeTo(null); // Centrar la ventana
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.magenta);
        this.getContentPane().add(panelPrincipal); // Agregamos el panel a la ventana

        JLabel etiqueta = new JLabel("Dulces", SwingConstants.CENTER);
        etiqueta.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        etiqueta.setForeground(Color.YELLOW);
        etiqueta.setBounds(85, 10, 300, 80); // Establece la posición y tamaño
        panelPrincipal.add(etiqueta);

        // Etiqueta 2 (Imagen super8)
        // Ruta relativa al directorio del proyecto
        File su = new File("super8.png"); // Asume que super8.png está en el directorio raíz del proyecto
        if (su.exists()) {
            ImageIcon imageIcon = new ImageIcon(su.getAbsolutePath());
            JLabel super8 = new JLabel(imageIcon);
            super8.setBounds(10, 80, 380, 380); // Establece la posición y tamaño
            panelPrincipal.add(super8);
        } else {
            System.out.println("Archivo no encontrado: " + su.getAbsolutePath());
        }
    }

}
