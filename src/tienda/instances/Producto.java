/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.instances;

/**
 *
 * @author Gustavo Torti
 */
public class Producto {
    
    private int codigo;
    private String nombre;
    private double precio;
    private int idFabricante;

    public Producto() {
    }

    public Producto(int codigo, String nombre, double precio, int idFabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.idFabricante = idFabricante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    @Override
    public String toString() {
        return "Producto{ nombre=" + nombre + ", precio=" + precio + ", idFabricante=" + idFabricante + "}\n";
    }
    
    
    
}
