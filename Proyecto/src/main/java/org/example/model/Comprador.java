package org.example.model;

/**
 * Clase que representa un comprador para el sistema de venta de producto.
 * Esta clase maneja la compra de productos y el cálculo del vuelto.
 */
public class Comprador {
    private String sonido;  // Describe el sonido o la acción que resulta al consumir el producto.
    private int vuelto;     // Guarda el dinero del vuelto del comprador.

    /**
     * Constructor que realiza la compra de un producto.
     * @param m La moneda utilizada para la compra.
     * @param nombreProducto El nombre del producto que se desea comprar.
     * @param exp Instancia de máquina expendedora de donde se compra el producto.
     * @throws NoHayProductoException Si el producto solicitado no está disponible.
     * @throws PagoIncorrectoException Si la moneda utilizada no es aceptada.
     * @throws PagoInsuficienteException Si el valor de la moneda es insuficiente para comprar el producto.
     */
    public Comprador(Moneda m, String nombreProducto, Expendedor exp) throws NoHayProductoException, PagoIncorrectoException, PagoInsuficienteException {
        // Intenta comprar un producto con m monedas y el nombre del producto se solicita.
        exp.comprarProducto(m, nombreProducto);  // Esto ya no devuelve un Producto.

        // Recuperar el producto del depósito especial después de la compra.
        Producto producto = exp.getProducto();

        if (producto != null) {
            sonido = producto.consumir();  // A sonido se le asigna el llamado al método de producto que hará herencia dependiendo de cual producto se compró, para consumirlo.
        } else {
            sonido = null;  // Si no hay producto, sonido tomará el valor nulo.
        }

        vuelto = 0;
        int monedaVuelto;  // Declara una variable de tipo moneda.
        monedaVuelto = exp.getVuelto();  // A la variable declarada, se le asigna el valor del próximo vuelto disponible en el depósito de monedas de vuelto.

        // Mientras haya monedas de vuelto disponibles.
        while (0 != monedaVuelto) {
            vuelto = vuelto + monedaVuelto; // Sumar el valor de la moneda al vuelto total.
            monedaVuelto = exp.getVuelto(); // Obtener la siguiente moneda de vuelto.
        }
    }

    /**
     * Método que devuelve la cantidad total de vuelto recibido después de la compra.
     * @return El total de vuelto como entero.
     */
    public int cuantoVuelto() {
        return vuelto;
    }

    /**
     * Método que devuelve una descripción del producto consumido.
     * @return Una cadena describiendo el producto consumido o {@code null}.
     */
    public String queBebiste() {
        return sonido;
    }
}
