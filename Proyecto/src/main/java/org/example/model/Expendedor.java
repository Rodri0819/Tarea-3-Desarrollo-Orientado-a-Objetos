package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Expendedor es una clase que representa una máquina expendedora que dispensa productos y maneja monedas.
 */
public class Expendedor {
    public static final int COCA = 1;
    public static final int SPRITE = 2;
    public static final int FANTA = 3;
    public static final int SUPER8 = 4;
    public static final int SNICKERS = 5;
    int numProductos;

    private Deposito<Moneda> monedaVueltas;
    private List<Deposito<Producto>> productos;
    private Deposito<Producto> depositoProductoComprado;

    /**
     * Constructor de la clase Expendedor.
     *
     * @param numProductos el número de productos iniciales en cada depósito
     */
    public Expendedor(int numProductos) {
        productos = new ArrayList<>();
        monedaVueltas = new Deposito<>();
        depositoProductoComprado = new Deposito<>();
        this.numProductos = numProductos;

        for (int i = 0; i < 5; i++) {
            productos.add(new Deposito<>());
        }

        for (int i = 0; i < numProductos; i++) {
            productos.get(COCA - 1).add(new CocaCola(i, "CocaCola", Precios.COCA_COLA.getPrecio(), "CocaCola"));
            productos.get(SPRITE - 1).add(new Sprite(i, "Sprite", Precios.SPRITE.getPrecio(), "Sprite"));
            productos.get(FANTA - 1).add(new Fanta(i, "Fanta", Precios.FANTA.getPrecio(), "Fanta"));
            productos.get(SUPER8 - 1).add(new Super8(i, "Super8", Precios.SUPER8.getPrecio(), "Chocolate"));
            productos.get(SNICKERS - 1).add(new Snickers(i, "Snickers", Precios.SNICKERS.getPrecio(), "Snicker"));
        }
    }

    /**
     * Registra el producto comprado en el depósito correspondiente.
     *
     * @param producto el producto comprado
     */
    public void productoComprado(Producto producto) {
        depositoProductoComprado.add(producto);
    }

    /**
     * Realiza la compra de un producto si el pago es correcto.
     *
     * @param m              la moneda utilizada para la compra
     * @param nombreProducto el nombre del producto a comprar
     * @throws PagoIncorrectoException   si la moneda es nula
     * @throws NoHayProductoException    si el producto no está disponible
     * @throws PagoInsuficienteException si el valor de la moneda es insuficiente
     */
    public void comprarProducto(Moneda m, String nombreProducto) throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {
        if (m == null) {
            throw new PagoIncorrectoException("La moneda no puede ser nula");
        }

        int cual = obtenerIndiceProducto(nombreProducto);
        if (cual == -1) {
            throw new NoHayProductoException("Producto no disponible");
        }

        int precioProducto = Precios.values()[cual - 1].getPrecio();
        Deposito<Producto> depositoProducto = productos.get(cual - 1);

        if (depositoProducto.isEmpty()) {
            throw new NoHayProductoException("No hay producto disponible en el depósito");
        }

        int valorMoneda = m.getValor();
        if (valorMoneda < precioProducto) {
            throw new PagoInsuficienteException("El valor de la moneda no es suficiente para comprar el producto");
        }

        int cambio = valorMoneda - precioProducto;
        while (cambio >= 1500) {
            monedaVueltas.add(new Moneda1500());
            cambio -= 1500;
        }
        while (cambio >= 1000) {
            monedaVueltas.add(new Moneda1000());
            cambio -= 1000;
        }
        while (cambio >= 500) {
            monedaVueltas.add(new Moneda500());
            cambio -= 500;
        }
        while (cambio >= 100) {
            monedaVueltas.add(new Moneda100());
            cambio -= 100;
        }

        Producto productoComprado = depositoProducto.get(); // Obtener y eliminar el producto del depósito
        productoComprado(productoComprado); // Registrar el producto comprado
        System.out.println("Producto comprado y añadido al depósito de productos comprados: " + productoComprado.getNombre());
    }

    /**
     * Obtiene el índice del producto basado en su nombre.
     *
     * @param nombreProducto el nombre del producto
     * @return el índice del producto o -1 si no se encuentra
     */
    private int obtenerIndiceProducto(String nombreProducto) {
        switch (nombreProducto) {
            case "Coca Cola":
                return COCA;
            case "Sprite":
                return SPRITE;
            case "Fanta":
                return FANTA;
            case "Super8":
                return SUPER8;
            case "Snickers":
                return SNICKERS;
            default:
                return -1;
        }
    }

    /**
     * Obtiene el vuelto en monedas.
     *
     * @return la lista de monedas del vuelto
     */
    public List<Moneda> getVueltoEnMonedas() {
        List<Moneda> vueltoMonedas = new ArrayList<>(monedaVueltas.getAllItems());
        monedaVueltas.clear();
        return vueltoMonedas;
    }

    /**
     * Obtiene el total del vuelto.
     *
     * @return el total del vuelto en valor monetario
     */
    public int getVuelto() {
        int totalVuelto = 0;
        for (Moneda moneda : monedaVueltas.getAllItems()) {
            totalVuelto += moneda.getValor();
        }
        return totalVuelto;
    }

    /**
     * Obtiene el producto comprado más reciente.
     *
     * @return el producto comprado
     */
    public Producto getProducto() {
        return depositoProductoComprado.get();
    }

    /**
     * Obtiene la lista de depósitos de productos.
     *
     * @return la lista de depósitos de productos
     */
    public List<Deposito<Producto>> getProductos() {
        return productos;
    }

    /**
     * Obtiene el depósito de productos comprados.
     *
     * @return el depósito de productos comprados
     */
    public Deposito<Producto> getDepositoProductoComprado() {
        return depositoProductoComprado;
    }

    /**
     * Obtiene el número de productos iniciales.
     *
     * @return el número de productos
     */
    public int getNumProductos() {
        return numProductos;
    }
}
