package org.example.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Subclase que representa una CocaCola, una bebida concreta
 * Extiende de la clase {@link Bebida}, heredando sus propiedades y métodos
 * Esta clase implementa el método consumir específicamente para CocaCola
 */
public class CocaCola extends Bebida {

    /**
     * Constructor para crear una Bebida: CocaCola
     * @param codigo El código identificador de la CocaCola
     * @param nombre El nombre de la CocaCola
     * @param precio No se utiliza, ya que el precio se establece con el valor predeterminado para CocaCola
     * @param marca La marca de la CocaCola
     */
    public CocaCola(int codigo, String nombre, int precio, String marca) {// Proporciona la ruta de la imagen
        super(codigo, nombre, precio, marca, "expendedorIcon/cocacola.png");
    }

    /**
     * Implementación metodo heredado de Bebida
     * Retorna una representación textual de consumir una CocaCola
     * @return Una cadena que representa el acto de consumir una CocaCola
     */
    @Override
    public String consumir() {
        return "Bebiendo CocaCola";
    }
    @Override
    public Image getImagen() {
        return super.getImagen();
    }
}