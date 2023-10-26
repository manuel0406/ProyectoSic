/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author manue
 */
public class BalanceCTableModel extends AbstractTableModel{

    public  ArrayList<BalanceComprobacion> balances = new ArrayList<BalanceComprobacion>();
    
    @Override
    public int getRowCount() {
       return balances.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        BalanceComprobacion balance = balances.get(rowIndex);
        
        Object valor= null;
        switch (columnIndex) {
            case 0: valor = balance.codigo;
            break;
            
            case 1: valor= balance.nombreCuenta;
                
                break;
            case 2: valor= balance.saldodeudor;
                 break;
            case 3: valor= balance.saldoacredor;
                break;
           
            
        }
        return valor;
    }
    
}
