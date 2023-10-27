
package clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author manue
 */
public class AjusteBalanceTableModel extends AbstractTableModel{
    
     public  ArrayList<AjusteBalanceComprobacion> balancesAjus = new ArrayList<AjusteBalanceComprobacion>();
    
    @Override
    public int getRowCount() {
       return balancesAjus.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        AjusteBalanceComprobacion balanceAj = balancesAjus.get(rowIndex);
        
        Object valor= null;
        switch (columnIndex) {
            case 0: valor = balanceAj.codigo;
            break;
            
            case 1: valor= balanceAj.nombreCuenta;
                
                break;
            case 2: valor= balanceAj.saldodeudor;
                 break;
            case 3: valor= balanceAj.saldoacredor;
                break;
           
            
        }
        return valor;
    }
    
    
}
