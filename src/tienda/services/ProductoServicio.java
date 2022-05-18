/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.services;

import tienda.instances.Fabricante;
import tienda.instances.Producto;
import tienda.persistence.FabricanteDAO;
import tienda.persistence.ProductoDAO;

/**
 *
 * @author Gustavo Torti
 */
public class ProductoServicio {

    private ProductoDAO dao;
    private FabricanteDAO fcnte;

    public ProductoServicio() {
        dao = new ProductoDAO();
        fcnte  = new FabricanteDAO();
    }

    public final void crearProducto(String nombre, double precio, int idFabricante) throws Exception {
        try {
            //validar datos
            if (nombre != null) {
                throw new Exception("El nombre no puede ser vacío");
            }
            if (dao.buscarProductoPorNombre(nombre)!=null) {
                throw new Exception("Ya existe un producto con ese nombre");
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
            
            throw e;
        }
    }

    public void mostrarProductos() throws Exception {
        try {
            for (Producto item : dao.listarProductos()) {
                System.out.println(item);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    //Lista los nombres y los precios de todos los productos de la tabla producto
    public void mostrarNombreYPrecio() throws Exception {
        try {
            for (Producto item : dao.listarProductos()) {
                System.out.println(item.getNombre() + ", " + item.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar aquellos productos que su precio esté entre 120 y 202.
    public void listarPorRangoDePrecios(Double menor, Double mayor) throws Exception {
        try {
            for (Producto e : dao.listarProductoPorRangoDePrecio(menor, mayor)) {
                System.out.println(e);
            }
        } catch (Exception e) {
        }
    }

    //Buscar y listar todos los Portátiles de la tabla producto.
    public void listarProductosQueContengan(String busqueda) throws Exception {
        try {
            for (Producto producto : dao.buscarProductosQueContienen(busqueda)) {
                System.out.println(producto.getNombre());
            }

        } catch (Exception e) {
        }
    }

    //Listar el nombre y el precio del producto más barato.
    public void listarProductoMasBarato() throws Exception {
        try {
            System.out.println(dao.buscarMasBarato());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    //Ingresar un producto a la base de datos.
    public void ingresarProductoALaBD(String nombre, double precio, int fabricante) throws Exception{
        try {
            Producto nuevo = new Producto();
            nuevo.setNombre(nombre);
            nuevo.setPrecio(precio);
            nuevo.setIdFabricante(fabricante);
            dao.guardarProducto(nuevo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    //Editar un producto con datos a elección.
    public void modificarProducto(String nombre, double precio, String fabricante) throws Exception{

        try {
            if(dao.buscarProductoPorNombre(nombre) == null){
                throw new Exception("El producto indicado no existe el la base de datos");
            }
            Producto modificable = new Producto();
            modificable.setNombre(nombre);
            modificable.setPrecio(precio);
            Fabricante fab = new Fabricante();
            fab = fcnte.buscarFabricantePorNombre(fabricante);
            if(fab == null){
                throw new Exception("El fabricante no está en la lista");
            } else{
            modificable.setIdFabricante(fab.getId());
            }
            dao.modificarProducto(modificable);
        } catch (Exception e) {
            throw e;
        }
    }
}
