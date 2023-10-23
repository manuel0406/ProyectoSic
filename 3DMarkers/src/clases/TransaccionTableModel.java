
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author manue
 */
public class TransaccionTableModel extends AbstractTableModel {

    List<Transaccion> transaccion = new ArrayList<Transaccion>();
    
    @Override
    public int getRowCount() {
       return transaccion.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        Transaccion transacciones = transaccion.get(rowIndex);
        
        Object valor= null;
        switch (columnIndex) {
            case 0: valor= transacciones.fecha;
                
                break;
            case 1: valor= transacciones.cuenta;
                 break;
            case 2: valor= transacciones.concepto;
                break;
            case 3: valor=transacciones.debe;
                break;
            case 4: valor=transacciones.haber;
                break;
            default:
                throw new AssertionError();
        }
        return valor;
    }
    
}
