/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases;

import java.io.Serializable;
import javax.swing.JOptionPane;
import misEstructuras.ArrayCola;

/**
 *
 * @author DaddyGianko
 */
public class Mozo implements Serializable{

    private String apellidos;
    private int añosExperiencia;
    private ArrayCola<Pedido> colaPedidos;
    private double montoRecaudado;

    public Mozo(String apellidos, int añosExperiencia) {
        this.apellidos = apellidos;
        this.añosExperiencia = añosExperiencia;
        this.colaPedidos = new ArrayCola<>();
        this.montoRecaudado = 0.0;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    public ArrayCola<Pedido> getColaPedidos() {
        return colaPedidos;
    }

    public double getMontoRecaudado() {
        return montoRecaudado;
    }

    public void encolarPedido(Pedido p) {
        if (p.estaPedidoCompleto()) {
            colaPedidos.encolar(p);
        } else {
            JOptionPane.showMessageDialog(null, "Faltan platos para agregar al pedido", "Pedido Incompleto", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void calcularMontoRecaudado() {
        //montoRecaudado = 0.0;
        while (!colaPedidos.colaVacia()) {
            Pedido refP = colaPedidos.desencolar();
            refP.calcularMontoPago();
            montoRecaudado += refP.getMontoPago();
        }
    }

    public String toString() {
        String cad = "";
        cad = "\nApellidos: " + apellidos
                + "\nAños de experiencia: " + añosExperiencia;
        return cad;
    }

}
