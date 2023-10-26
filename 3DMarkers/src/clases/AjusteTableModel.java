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
public class AjusteTableModel extends AbstractTableModel {

    public ArrayList<Ajuste> ajustes = new ArrayList<Ajuste>();

    @Override
    public int getRowCount() {
        return ajustes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Ajuste ajuste = ajustes.get(rowIndex);

        Object valor = null;

        switch (columnIndex) {
            case 0:
                valor = ajuste.idAjuste;
                break;

            case 1:
                valor = ajuste.codigo;

                break;
            case 2:
                valor = ajuste.cuenta;
                break;
            case 3:
                valor = ajuste.debe;
                break;
            case 4:
                valor = ajuste.haber;
                break;

        }
        return valor;
    }

}
