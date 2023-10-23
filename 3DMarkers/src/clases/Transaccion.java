
package clases;

import clases.Impuesto;
import java.util.Date;

/**
 *
 * @author manue
 */
public class Transaccion {
    
    Impuesto impuesto = new Impuesto();
   // CatalogoCuenta cuenta = new CatalogoCuenta();
    
    int id;
    Date fecha;    
    String cuenta;
    String concepto;
    Double  debe;
    Double haber;            
    double monto;
    
    
    
    
}
