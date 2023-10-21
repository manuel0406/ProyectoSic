/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg3dmarkers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manue
 */
public class Conexion {
    
     private Connection conectado;
     
     
     public Connection conectar() { 
        try { 
        conectado = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dmakers", "dmakers", "1234"); 
            System.out.println("exito");
        } 
        catch (SQLException ex) {
            System.out.println("ocurrio un error");
        
        }      
        return conectado;
        
     } 
    
}
