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
            productos.get(SUPER8 - 1).add(new Super8(i,"Super8",Precios.SUPER8.getPrecio(),"Chocolate"));
            productos.get(SNICKERS - 1).add(new Snickers(i,"Snickers",Precios.SNICKERS.getPrecio(),"Snicker"));
        }
    }

    public boolean eliminarProducto(Class<? extends Producto> tipoProducto) {
        for (Deposito<Producto> deposito : productos) {
            for (int i = 0; i < deposito.size(); i++) {
                Producto producto = deposito.getAllItems().get(i);
                if (tipoProducto.isInstance(producto)) {
                    deposito.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public Producto comprarProducto(Moneda m, int cual) throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {
        if (m == null) {
            throw new PagoIncorrectoException("La moneda no puede ser nula");
        }

        if (cual < COCA || cual > SNICKERS) {
            throw new NoHayProductoException("Número de producto incorrecto o producto no disponible");
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

        Producto productoComprado = depositoProducto.get();
        depositoProductoComprado.add(productoComprado);
        return productoComprado;
    }

    public Moneda getVuelto() {
        return monedaVueltas.get();
    }

    public List<Deposito<Producto>> getProductos() {
        return productos;
    }

    public Deposito<Producto> getDepositoProductoComprado() {
        return depositoProductoComprado;
    }
}
