package org.example;
import org.example.model.*;
import org.example.gui.*;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.example.gui.PanelPrincipal;


public class Main {
    public static void main(String[] args) {
        Expendedor expendedor = new Expendedor(5);
        new Ventana(expendedor);

    }

}
