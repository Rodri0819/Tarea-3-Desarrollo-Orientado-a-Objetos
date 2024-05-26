package org.example.gui;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;

    public PanelPrincipal(Expendedor expendedor) {

        com = new PanelComprador();
        exp = new PanelExpendedor(expendedor);  // Pasa la instancia de Expendedor
        this.setBackground(Color.white);     // Configura el fondo del panel principal

        //SE USA GRINDBAGLAYOUT PARA PERSONALIZAR MAS LA DISPERSION DE ESPACIO DEL PANEL PRINCIPAL |
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  // Permite que el componente se expanda en ambas direcciones

        gbc.weightx = 0.3;  // El panel comprador ocupará el 30% del espacio horizontal
        gbc.weighty = 1.0;
        gbc.gridx = 0;  // Primera columna
        gbc.gridy = 0;  // Primera fila

        this.add(com, gbc);  // Añadir el panel del comprador

        gbc.weightx = 0.7;  // El panel expendedor ocupará el 70% del espacio horizontal
        gbc.gridx = 1;  // Segunda columna
        this.add(exp, gbc);  // Añadir el panel del expendedor

    }

    public void paintComponent(Graphics g) { // todo se dibuja a partir de este método
        super.paintComponent(g); //llama al método pint al que hace override en la super clase
        //el de la super clase solo pinta el fondo (background)
        com.paintComponent(g); //llama al metodo paintComponent definido en el PanelComprador
        exp.paintComponent(g); //llama al metodo paintComponent definido en el PanelExpendedor
    }
}
