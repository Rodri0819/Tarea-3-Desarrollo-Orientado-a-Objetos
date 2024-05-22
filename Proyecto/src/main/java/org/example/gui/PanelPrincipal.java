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
        this.setLayout(new BorderLayout());  // Usar BorderLayout o otro layout manager según necesidad
        this.add(com, BorderLayout.WEST);     // Agrega el PanelComprador a la izquierda
        this.add(exp, BorderLayout.EAST);     // Agrega el PanelExpendedor a la derecha
    }

    public void paintComponent (Graphics g) { // todo se dibuja a partir de este método
        super.paintComponent(g); //llama al método pint al que hace override en la super clase
        //el de la super clase solo pinta el fondo (background)
        com.paintComponent(g); //llama al metodo paintComponent definido en el PanelComprador
        exp.paintComponent(g); //llama al metodo paintComponent definido en el PanelExpendedor
    }
}
