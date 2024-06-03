package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Expendedor {
    public static final int COCA = 1;
    public static final int SPRITE = 2;
    public static final int FANTA = 3;
    public static final int SUPER8 = 4;
    public static final int SNICKERS = 5;

    private Deposito<Moneda> monedaVueltas;
    private List<Deposito<Producto>> productos;
    private Deposito<Producto> depositoProductoComprado;
    private List<Producto> productosEliminadosTemporalmente = new ArrayList<>();

    public Expendedor(int numProductos) {
        productos = new ArrayList<>();
        monedaVueltas = new Deposito<>();
        depositoProductoComprado = new Deposito<>();

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

    public void productoComprado(Producto producto) {
        depositoProductoComprado.add(producto);
    }


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
        while (cambio >= 100) {
            monedaVueltas.add(new Moneda100());
            cambio -= 100;
        }

        Producto productoComprado = depositoProducto.get(); // Obtener y eliminar el producto del depósito
        productoComprado(productoComprado); // Registrar el producto comprado
        System.out.println("Producto comprado y añadido al depósito de productos comprados: " + productoComprado.getNombre());
    }


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

    public int getVuelto() {
        return monedaVueltas.size();
    }

    public Producto getProducto() {
        return depositoProductoComprado.get();
    }

    public List<Deposito<Producto>> getProductos() {
        return productos;
    }

    public Deposito<Producto> getDepositoProductoComprado() {
        return depositoProductoComprado;
    }

    public List<Producto> getProductosEliminadosTemporalmente() {
        return new ArrayList<>(productosEliminadosTemporalmente);
    }
}
