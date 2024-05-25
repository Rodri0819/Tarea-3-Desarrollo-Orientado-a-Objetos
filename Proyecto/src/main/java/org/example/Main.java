package org.example;
import org.example.model.*;
import org.example.gui.*;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.example.gui.PanelPrincipal;


public class Main {
    public static void main(String[] args) {
        // Creación de una máquina expendedora con capacidad para productos
        Expendedor expendedor = new Expendedor(14);  // 10 de cada producto
         //Creación de algunas monedas para usar en la máquina expendedora
        Moneda moneda1500 = new Moneda1500(); // Moneda de 1500, suficiente para cualquier producto

        // Probar la compra de cada producto
        int[] productos = {Expendedor.COCA, Expendedor.SPRITE, Expendedor.FANTA, Expendedor.SUPER8, Expendedor.SNICKERS};
        String[] nombresProductos = {"CocaCola", "Sprite", "Fanta", "Super8", "Snickers"};

        for (int i = 0; i < productos.length; i++) {
            try {
                Comprador comprador = new Comprador(moneda1500, productos[i], expendedor);
                System.out.println("Comprador compró: " + nombresProductos[i]);
                System.out.println(comprador.queBebiste());
                System.out.println("Vuelto del comprador: " + comprador.cuantoVuelto() + "\n");
            } catch (NoHayProductoException | PagoIncorrectoException | PagoInsuficienteException e) {
                System.out.println("Error al comprar " + nombresProductos[i] + ": " + e.getMessage());
            }
        }

        new Ventana(expendedor);

    }

}
