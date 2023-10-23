
package clases;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author manue
 */
public class TransaccionTableModel extends AbstractTableModel {

  public  ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
    
    @Override
    public int getRowCount() {
       return transacciones.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        Transaccion transaccion = transacciones.get(rowIndex);
        
        Object valor= null;
        switch (columnIndex) {
            case 0: valor = transaccion.idTransaccion;
            break;
            
            case 1: valor= transaccion.codigo;
                
                break;
            case 2: valor= transaccion.cuenta;
                 break;
            case 3: valor= transaccion.concepto;
                break;
            case 4: valor=transaccion.debe;
                break;
            case 5: valor=transaccion.haber;
                break;
            
        }
        return valor;
    }
    
}
