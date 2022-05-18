/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistence;

import java.util.ArrayList;
import tienda.instances.Producto;

/**
 *
 * @author Gustavo Torti
 */
public final class ProductoDAO extends DAO {

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("debe indicar un producto");
            }
            String sql = "INSERT INTO producto(nombre, precio, codigo_fabricante) VALUES ('"
                    + producto.getNombre() + "', " + producto.getPrecio() + ", " + producto.getIdFabricante() + ");";
            insertarModificarBorrar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto a modificar");
            }
            String url = "UPDATE producto SET nombre = '" + producto.getNombre() + "'"
                    + ", precio = " + producto.getPrecio() + ", codigo_fabricante = " + producto.getIdFabricante()
                    + " WHERE nombre= '" + producto.getNombre() +"';";
            insertarModificarBorrar(url);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("debe indicar el producto que desea eliminar");
            }
            String url = "DELETE FROM producto WHERE codigo = " + producto.getCodigo() + ";";
            insertarModificarBorrar(url);
            desconectarBase();
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE nombre = '" + nombre + "';";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setIdFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public ArrayList<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT nombre, precio, codigo_fabricante, codigo FROM producto";
            consultarBase(sql);
            ArrayList<Producto> productos = new ArrayList();
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                producto.setIdFabricante(resultado.getInt(3));
                producto.setCodigo(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public ArrayList<Producto> listarProductoPorRangoDePrecio(double menor, double mayor) throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto WHERE precio BETWEEN ? AND ?;";
            consultaPreparada(sql, menor, mayor);
            ArrayList<Producto> productos = new ArrayList();
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw new Exception("Error del fuckin system");
        }
    }

    public ArrayList<Producto> buscarProductosQueContienen(String clave) throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto WHERE nombre LIKE ?;";
            consultaPreparadaStringAmplia(sql, clave);
            ArrayList<Producto> productos = new ArrayList();
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error del fuckin system");
        }
    }

    public ArrayList<Producto> buscarMasBarato() throws Exception {
        try {
            conectarBase();
            String sql = "SELECT * FROM producto WHERE precio = (SELECT min(precio) FROM producto);";
            consultarBase(sql);
            ArrayList<Producto> productos = new ArrayList();
            Producto producto;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setIdFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
}
