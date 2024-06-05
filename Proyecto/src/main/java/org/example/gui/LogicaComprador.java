package org.example.service;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class LogicaComprador {
    private Expendedor expendedor;
    private List<Moneda> monedas = new ArrayList<>();
    private List<Moneda> listaMonedas = new ArrayList<>();
    private int totalMonedas = 0;

    public LogicaComprador(Expendedor expendedor) {
        this.expendedor = expendedor;
    }

    public void agregarMoneda(Moneda moneda) {
        monedas.add(moneda);
        totalMonedas += moneda.getValor();
    }

    public int getTotalMonedas() {
        return totalMonedas;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public List<Moneda> removerMonedas(int cantidad) {
        List<Moneda> monedasParaRemover = new ArrayList<>();
        int totalRecolectado = 0;

        // Ordena las monedas de mayor a menor valor para usar la menor cantidad de monedas posible.
        listaMonedas.sort((m1, m2) -> Integer.compare(m2.getValor(), m1.getValor()));

        for (Moneda moneda : listaMonedas) {
            monedasParaRemover.add(moneda);
            totalRecolectado += moneda.getValor();
            if (totalRecolectado >= cantidad) {
                break;
            }
        }

        if (totalRecolectado >= cantidad) {
            listaMonedas.removeAll(monedasParaRemover);
            totalMonedas -= totalRecolectado;
            return monedasParaRemover;
        } else {
            return null;
        }
    }

    public boolean comprarProducto(String producto) throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {
        int precioProducto = obtenerPrecioProducto(producto);
        if (precioProducto == -1) {
            return false;
        }

        List<Moneda> monedasUsadas = removerMonedas(precioProducto);
        if (monedasUsadas != null) {
            expendedor.comprarProducto(monedasUsadas.get(0), producto);
            return true;
        } else {
            return false;
        }
    }

    private int obtenerPrecioProducto(String nombreProducto) {
        switch (nombreProducto) {
            case "Coca Cola":
                return Precios.COCA_COLA.getPrecio();
            case "Sprite":
                return Precios.SPRITE.getPrecio();
            case "Fanta":
                return Precios.FANTA.getPrecio();
            case "Super8":
                return Precios.SUPER8.getPrecio();
            case "Snickers":
                return Precios.SNICKERS.getPrecio();
            default:
                return -1;
        }
    }

    public List<Moneda> getVuelto() {
        return expendedor.getVueltoEnMonedas();
    }
}
