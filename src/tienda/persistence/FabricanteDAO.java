/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistence;

import tienda.instances.Fabricante;

/**
 *
 * @author Gustavo Torti
 */
public final class FabricanteDAO extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("debe indicar un fabricante");
            }
            String sql = "INSERT INTO fabricante(nombre) VALUES ('" + fabricante.getNombre() + "');";
            insertarModificarBorrar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("debe indicar el fabricante que desea eliminar");
            }
            String sql = "DELETE FROM fabricante WHERE nombre='" + fabricante.getNombre() + "';";
            insertarModificarBorrar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante WHERE nombre = '" + nombre + "';";
            consultarBase(sql);
            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setId(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    public Fabricante buscarFabricantePorId(int id) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante WHERE codigo = '" + id + "';";
            consultarBase(sql);
            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setId(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
}
