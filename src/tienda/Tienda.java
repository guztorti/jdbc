/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import tienda.services.Menu;

/**
 *
 * @author Gustavo Torti
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Menu menu = null;
        try {
            menu = new Menu();
            menu.menu();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    
}
