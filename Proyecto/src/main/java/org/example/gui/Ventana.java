package org.example.gui;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;


public class Ventana extends JFrame {

    public Ventana(Expendedor expendedor) {
        PanelPrincipal panelPrincipal = new PanelPrincipal(expendedor);
        this.add(panelPrincipal);
        configurarVentana();
    }

    private void configurarVentana() {
        this.setTitle("Maquina Expendedora"); //Titulo ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar ventana terminal programa
        this.setSize(1100, 700); // tamaño de la ventana
        this.setResizable(false); //Impide que la ventana sea redimensionada
        this.setLocationRelativeTo(null); //Centra la ventana
        this.setVisible(true); //Hace visible la ventana
    }
}
