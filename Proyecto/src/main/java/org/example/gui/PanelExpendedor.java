package org.example.gui;
import org.example.model.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import org.example.model.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;  // Guarda la referencia al Expendedor
        setSize(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
    }
}
