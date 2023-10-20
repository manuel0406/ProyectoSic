
package pkg3dmarkers;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author manue
 */
public class Main {

    /**
     * @param args the command line arguments
     */
     private Connection conexion;
    public static void main(String[] args) {
        // TODO code application logic here
     
       
        
    }
     public void conectar() { 
        try { 
        conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dmakers", "dmakers", "1234"); 
            System.out.println("exito");
        } 
        catch (SQLException ex) {
            System.out.println("ocurrio un error");
        
        } 
        
     } 
     
    
    
}
