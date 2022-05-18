/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gustavo Torti
 */
public abstract class DAO {
    
    private Statement sentencia = null;
    private Connection conexion = null;
    protected ResultSet resultado = null;
    private PreparedStatement sentenciaPreparada = null;
    
    private final String USER = "root";
    private final String PASSWORD = "root1234";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    protected void conectarBase() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?zeroDateTimeBehavior=convertToNull&ServerTimezone=UTC&useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException  | SQLException e) {
            throw e;
        }
    }
    
    protected void desconectarBase() throws Exception{
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            
            throw e;
        }
    }
    
    protected void insertarModificarBorrar(String url) throws Exception{
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(url);
        } catch (ClassNotFoundException  | SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
                
            desconectarBase();
        }
    }
    
    protected void consultarBase(String url) throws Exception{
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(url);
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected void consultaPreparada(String url, double dato1, double dato2) throws Exception{
        try {
            conectarBase();
            sentenciaPreparada = conexion.prepareStatement(url);
            sentenciaPreparada.setDouble(1, dato1);
            sentenciaPreparada.setDouble(2, dato2);
            resultado = sentenciaPreparada.executeQuery();
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected void consultaPreparadaStringAmplia(String url, String cadena1) throws Exception{
        try {
            String palabraABuscar = "%"+cadena1+"%";
            conectarBase();
            sentenciaPreparada = conexion.prepareStatement(url);
            sentenciaPreparada.setString(1, palabraABuscar);
            resultado = sentenciaPreparada.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
