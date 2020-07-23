/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author DaddyGianko
 */
public class Plato implements Serializable{

    private String descripcion;
    private int cantidad;
    private double precioUnitario;

    public Plato(String descripcion) {
        this.descripcion = descripcion;
        this.cantidad = 0;
        this.precioUnitario = 0.0;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void calcularCantidad() {
        Random r = new Random();
        this.cantidad = r.nextInt(6) + 1;
    }

    public void asignarPrecio() {        
        if(descripcion.equalsIgnoreCase("Lomo saltado")){
            precioUnitario=28.0;
        }else if(descripcion.equalsIgnoreCase("Pollo enrollado")){
            precioUnitario=7.0;
        }else if(descripcion.equalsIgnoreCase("Pollo a la brasa")){
            precioUnitario=15.0;
        }else if(descripcion.equalsIgnoreCase("Chicharron de pescado")){
            precioUnitario=25.0;
        }else if(descripcion.equalsIgnoreCase("Papa rellena")){
            precioUnitario=12.0;
        }else if(descripcion.equalsIgnoreCase("Arroz con pollo")){
            precioUnitario=13.0;
        }else if(descripcion.equalsIgnoreCase("Ceviche")){
            precioUnitario=25.0;
        }else if(descripcion.equalsIgnoreCase("Causa")){
            precioUnitario=7.0;
        }else {
            precioUnitario=11.0;
        }
    }

    @Override
    public String toString() {
        return "\nDescripción: " + descripcion
                + "\nCantidad: " + cantidad
                + "\nPrecio Unitario: " + precioUnitario;
    }

}
