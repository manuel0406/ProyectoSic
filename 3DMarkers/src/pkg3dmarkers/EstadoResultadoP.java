/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3dmarkers;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author samue
 */
public class EstadoResultadoP extends javax.swing.JFrame {

    /**
     * Creates new form EstadoResultadoP
     */
    public EstadoResultadoP() {
        initComponents();
        
        mostrarEstado(tableEstado);
        centrarVentanaEnPantalla();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstado = new javax.swing.JTable();
        BtnImprimir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(780, 530));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(787, 482));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableEstado.setFocusable(false);
        tableEstado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tableEstado.getTableHeader().setResizingAllowed(false);
        tableEstado.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableEstado);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 680, 350));

        BtnImprimir.setBackground(new java.awt.Color(0, 89, 255));
        BtnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        BtnImprimir.setText("Imprimir");
        BtnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(BtnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 104, -1));

        jLabel1.setText("Estado de resultados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        getContentPane().add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 104, -1));

        btnTransacciones.setText("Transacciones");
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        btnInventario.setText("Inventario");
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 104, -1));

        lblInfo.setText("Hola, soy informacion");
        getContentPane().add(lblInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 465, -1, -1));

        btnCerrar.setBackground(new java.awt.Color(255, 0, 0));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar Sesión");
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 104, -1));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, 20, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed
        
        String tituloTabla = "Reporte - Estado de resultados";
        imprimirTabla(tableEstado, tituloTabla);
        
    }//GEN-LAST:event_BtnImprimirActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        // TODO add your handling code here:
        Transacciones transaccion = new Transacciones();
        transaccion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
        InventarioCRUD inventario = new InventarioCRUD();
        inventario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        // Crea una nueva instancia de NuevoVentana
        Login login = new Login();
        login.setVisible(true);
        // Cierra la ventana actual
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(255,102,102));
        btnCerrar.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        // Regresa al color original fuera
        btnCerrar.setBackground(new Color(255, 0, 0));
        btnCerrar.setForeground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    public void mostrarEstado(JTable tablaEstado) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Debe");
        modelo.addColumn("Haber");

        String sql = "SELECT cc.codigo, cc.nombreCuenta, abc.saldodeudor, abc.saldoacredor "
                + "FROM ajustebalancecomprobacion abc "
                + "JOIN catalogoCuenta cc ON cc.codigo = abc.codigocuenta "
                + "WHERE cc.nombreCuenta ILIKE '%gasto%' "
                + "OR cc.nombreCuenta ILIKE '%ingreso%' "
                + "OR cc.nombreCuenta ILIKE '%venta%' "
                + "OR cc.nombreCuenta ILIKE '%costo' "
                + "ORDER BY codigo::text;";

        String[] datos = new String[4];
        List<Double> debe = new ArrayList<>();
        List<Double> haber = new ArrayList<>();
        double resultado = 0;

        Statement statement = null;
        Conexion objetoConexion = new Conexion();

        try {
            statement = objetoConexion.conectar().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                //Guardando datos
                double saldoDeudor = resultSet.getDouble(3);
                double saldoAcredor = resultSet.getDouble(4);
                debe.add(saldoDeudor);
                haber.add(saldoAcredor);

                //Mostrando datos
                datos[0] = resultSet.getString(1);
                datos[1] = resultSet.getString(2);
                datos[2] = resultSet.getString(3);
                datos[3] = resultSet.getString(4);
                modelo.addRow(datos);
            }

            tablaEstado.setModel(modelo);

            //Operando las listas
            double sumaDebe = 0.0;
            for (Double valor : debe) {
                sumaDebe += valor;
            }

            double sumaHaber = 0.0;
            for (Double valor : haber) {
                sumaHaber += valor;
            }

            resultado = sumaHaber - sumaDebe;
            String mensaje = "";
            
            if (resultado > 0) {
                mensaje = "se tiene una utilidad por el valor de $" + resultado;
                datos[0] = "611";
                datos[1] = "Resultado del ejercicio";
                datos[2] = "0";
                datos[3] = "" + resultado;
                modelo.addRow(datos);
            } else if (resultado == 0) {
                datos[0] = "611";
                datos[1] = "Resultado del ejercicio";
                datos[2] = "0";
                datos[3] = "0";
                modelo.addRow(datos);
            } else {
                mensaje = "se tienen pérdidas por el valor de $" + Math.abs(resultado);
                datos[0] = "611";
                datos[1] = "Resultado del ejercicio";
                datos[2] = "" + Math.abs(resultado);
                datos[3] = "0";
                modelo.addRow(datos);
            }

            lblInfo.setText("Para el ejercicio actual " + mensaje);

            //Metiendo los datos pertinentes a la base de datos
            String consulta = "UPDATE estadoderesultado SET utilidadneta = ? WHERE idestadoresultado = 1;";

            try {
                CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
                cs.setDouble(1, resultado);

                cs.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + e.toString());
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    private void imprimirTabla(JTable tabla, String tituloTabla) {
        try {
            MessageFormat header = new MessageFormat(tituloTabla);
            MessageFormat footer = new MessageFormat("Dmakers contabilidad");

            tabla.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Impresión fallida: " + e.getMessage());
        } catch (IllegalArgumentException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error al imprimir la tabla: " + e.getMessage());
        }
    }

    private void centrarVentanaEnPantalla() {
        // Obtiene el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Obtiene el tamaño de la ventana
        Dimension windowSize = getSize();
        // Calcula la posición en la que se debe colocar la ventana para centrarla
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;
        // Establece la ubicación de la ventana
        setLocation(x, y);
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EstadoResultadoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstadoResultadoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstadoResultadoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstadoResultadoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstadoResultadoP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JTable tableEstado;
    // End of variables declaration//GEN-END:variables
}
