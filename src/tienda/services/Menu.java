/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.services;

import java.util.Scanner;

/**
 *
 * @author Gustavo Torti
 *
 */
public class Menu {

    public Menu() {
    }

    public final void menu() throws Exception {
        Scanner scn = new Scanner(System.in).useDelimiter("\n");
        char opcion;
        ProductoServicio ps = new ProductoServicio();
        FabricanteServicio fs = new FabricanteServicio();
        String mensaje = "a) Lista el nombre de todos los productos que hay en la tabla producto.\n"
                + "b) Lista los nombres y los precios de todos los productos de la tabla producto.\n"
                + "c) Listar aquellos productos que su precio esté entre 120 y 202.\n"
                + "d) Buscar y listar todos los Portátiles de la tabla producto.\n"
                + "e) Listar el nombre y el precio del producto más barato.\n"
                + "f) Ingresar un producto a la base de datos.\n"
                + "g) Ingresar un fabricante a la base de datos\n"
                + "h) Editar un producto con datos a elección.\n"
                + "z) Salir. \n";
        try {
            do {
                System.out.println(mensaje);
                opcion = scn.next().toLowerCase().charAt(0);
                switch (opcion) {
                    case 'a':
                        ps.mostrarProductos();
                        break;
                    case 'b':
                        ps.mostrarNombreYPrecio();
                        break;
                    case 'c':
                        ps.listarPorRangoDePrecios(120d, 202d);
                        break;
                    case 'd':
                        ps.listarProductosQueContengan("Port_til");
                        break;
                    case 'e':
                        ps.listarProductoMasBarato();
                        break;
                    case 'f':
                        ps.ingresarProductoALaBD("Mother Asus extra Durable", 89.38, 5);
                        break;
                    case 'g':
                        fs.crearFabricante("EGG hardware");
                        break;
                    case 'h':
                        ps.modificarProducto("Mother Asus extra Durable", 89.38, "Asus");
                        break;
                    case 'z':
                        System.out.println("Gracias y hasta pronto");
                        break;
                    default:
                        System.out.println("no se reconoce la opción");
                }

            } while (opcion != 'z');

        } catch (Exception e) {
            throw e;
        }

    }
}
