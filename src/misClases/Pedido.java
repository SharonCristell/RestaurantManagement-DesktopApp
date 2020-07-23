/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author DaddyGianko
 */
public class Pedido implements Serializable{
    private int contador;
    private Plato[] listaPlatos;
    private double montoPago;

    public Pedido() {
        this.contador=0;
        this.listaPlatos = new Plato[4];
        this.montoPago = 0.0;
    }

    public Plato[] getListaPlatos() {
        return listaPlatos;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void agregarPlato(Plato p) {
        if(contador<4){
         listaPlatos[contador]=p;
         contador++;
        }       
        
    }

    public void calcularMontoPago() {
        
        for (int i = 0; i < listaPlatos.length; i++) {
            listaPlatos[i].calcularCantidad();
            montoPago+=listaPlatos[i].getPrecioUnitario()*listaPlatos[i].getCantidad();
            
        }
    }

    public boolean estaPedidoCompleto() {
        return (listaPlatos.length == 4);
    }
    public void calcularPrecioU(){
        for (int i = 0; i < listaPlatos.length; i++) {
            listaPlatos[i].asignarPrecio();
        }
    }

    @Override
    public String toString() {
        String cad = "";

        for (int i = 1; i <= listaPlatos.length; i++) {
            cad+= "\nPlato N°: "+i
                    +listaPlatos[i-1].toString()+"\n";
        }
        cad += "\nMonto total a  pagar: " + montoPago;
        return cad;
    }

}
