/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.services;

import tienda.instances.Fabricante;
import tienda.persistence.FabricanteDAO;

/**
 *
 * @author Gustavo Torti
 */
public class FabricanteServicio {
    private FabricanteDAO dao;

    public FabricanteServicio() {
        this.dao = new FabricanteDAO();
    }
    
    public void crearFabricante(String nombre) throws Exception{
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar el nombre del fabricante");
            }
            Fabricante fabricante = new Fabricante();
            fabricante.setNombre(nombre);
            dao.guardarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }
}
