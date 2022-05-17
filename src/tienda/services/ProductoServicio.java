/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.services;

import tienda.instances.Producto;
import tienda.persistence.FabricanteDAO;
import tienda.persistence.ProductoDAO;

/**
 *
 * @author Gustavo Torti
 */
public class ProductoServicio {
    private ProductoDAO dao;
    private FabricanteDAO fcnte = new FabricanteDAO();

    public ProductoServicio() {
        dao = new ProductoDAO();
    }
    
    public final void crearProducto(String nombre, double precio, int idFabricante){
        try {
            //validar datos
            if (nombre != null) {
                throw new Exception("El nombre no puede ser vacío");
            }
            if (precio < 0) {
                throw new Exception("El precio debe ser un número positivo (o 0)");
            }
            if (fcnte.buscarFabricantePorId(idFabricante) != null) {
                throw new Exception("Debe ingresar un código de fabricante válido");
            }
            
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setIdFabricante(idFabricante);
            dao.guardarProducto(producto);
        } catch (Exception e) {
        }
    }
    public void mostrarProductos() throws Exception{
        try {
            System.out.println(dao.listarProductos());
        } catch (Exception e) {
            throw e;
        }
       
    }
    
    //Lista los nombres y los precios de todos los productos de la tabla producto
    public void mostrarNombreYPrecio() throws Exception{
        try {
            System.out.println(dao.listarNombreYPrecioProducto());
        } catch (Exception e) {
            throw e;
        }
    }
    
}
