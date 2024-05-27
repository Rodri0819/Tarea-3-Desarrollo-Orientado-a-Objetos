package org.example.gui;
import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class Ventana extends JFrame {

    public Ventana(Expendedor expendedor) {
        PanelPrincipal panelPrincipal = new PanelPrincipal(expendedor);
        this.add(panelPrincipal);
        configurarVentana();
    }

    private void configurarVentana() {
        this.setTitle("Maquina Expendedora"); //Titulo ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar ventana terminal programa
        this.setSize(1100, 900); // tamaño de la ventana
        this.setResizable(false); //Impide que la ventana sea redimensionada
        this.setLocationRelativeTo(null); //Centra la ventana
        this.setVisible(true); //Hace visible la ventana
        // Cargar el icono de la ventana
        try {
            File iconFile = new File("icon/icon.png");
            if (iconFile.exists()) {
                this.setIconImage(ImageIO.read(iconFile));
            } else {
                System.err.println("Archivo no encontrado: " + iconFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
