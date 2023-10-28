/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3dmarkers;

import clases.AjusteBalanceComprobacion;
import clases.AjusteBalanceTableModel;
import clases.BalanceCTableModel;
import clases.BalanceComprobacion;
import clases.Cuenta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
public class BalanceComprobacionP extends javax.swing.JFrame {

    /**
     * Creates new form BalanceComprobacionP
     */
    public BalanceCTableModel balanceTModel = new BalanceCTableModel();
    public AjusteBalanceTableModel balanceAjTModel = new AjusteBalanceTableModel();

    Cuenta cuenta = new Cuenta();
    ArrayList<Cuenta> listCuenta = new ArrayList<Cuenta>();
    ArrayList<AjusteBalanceComprobacion> listaAjustes = new ArrayList<AjusteBalanceComprobacion>();

    Conexion conexion = new Conexion();

    public BalanceComprobacionP() {
        initComponents();
        extrayendoCuentas();
        inicializarColumnas();
        consultaInicial();
        ajusteBalance();
        centrarVentanaEnPantalla();

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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     *
     */
    public void extrayendoCuentas() {

        // Conexion conexion = new Conexion();
        try {
            String setenciaSql = "Select * from cuenta";
            Statement statement = conexion.conectar().createStatement();
            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.idCuenta = resultado.getInt("idcuenta");
                cuenta.codigo = resultado.getInt("codigo");
                cuenta.totalizacion = resultado.getDouble("totalizacion");
                cuenta.deudor = resultado.getBoolean("deudor");
                listCuenta.add(cuenta);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Error al extraer los datos de la tabla cuenta " + e);
        }

        insertandoDatos(listCuenta);
    }

    public void insertandoDatos(ArrayList<Cuenta> listCuenta) {

        //Conexion conexion = new Conexion();
        double totalDebe = 0, totalHaber = 0;

        try {
            PreparedStatement statement = null;

            for (Cuenta cuenta : listCuenta) {

                String sentenciaSql = "Select * from balancecomprobacion where idcuenta=?";
                statement = conexion.conectar().prepareStatement(sentenciaSql);
                statement.setInt(1, cuenta.idCuenta);
                ResultSet resultado = statement.executeQuery();

                if (resultado.next()) {

                    try {

                        String sentencia = " UPDATE balancecomprobacion SET saldoacredor= ?, saldodeudor= ? WHERE idcuenta=? ";

                        PreparedStatement preparedStatement = conexion.conectar().prepareCall(sentencia);

                        if (cuenta.deudor) {
                            totalDebe += cuenta.totalizacion;
                            preparedStatement.setDouble(1, 0.00);
                            preparedStatement.setDouble(2, cuenta.totalizacion);

                        } else {
                            totalHaber += cuenta.totalizacion;
                            preparedStatement.setDouble(1, cuenta.totalizacion);
                            preparedStatement.setDouble(2, 0.00);
                        }

                        preparedStatement.setInt(3, cuenta.idCuenta);
                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {

                        JOptionPane.showMessageDialog(this, "Error al actualizar " + e);
                    }

                } else {

                    try {
                        String sentenciaIn = " INSERT INTO balancecomprobacion(idcuenta, saldoacredor, saldodeudor) Values(?, ?, ?)";

                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaIn);
                        preparedStatement.setInt(1, cuenta.idCuenta);
                        if (cuenta.deudor) {
                            totalDebe += cuenta.totalizacion;
                            preparedStatement.setDouble(2, 0.00);
                            preparedStatement.setDouble(3, cuenta.totalizacion);

                        } else {
                            totalHaber += cuenta.totalizacion;
                            preparedStatement.setDouble(2, cuenta.totalizacion);
                            preparedStatement.setDouble(3, 0.00);

                        }

                        preparedStatement.execute();

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error insertar la totalizacion " + e);
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);

        }

        labelTotalDebe.setText("Total Debe: $ " + totalDebe);
        labelTotalHaber.setText("Total Haber: $ " + totalHaber);

    }

    public void inicializarColumnas() {

        TableColumnModel tColumnModel = new DefaultTableColumnModel();

        for (int i = 0; i < 4; i++) {

            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Codigo");
                    break;

                case 1:
                    col.setHeaderValue("Nombre");

                    break;
                case 2:
                    col.setHeaderValue("Debe");
                    break;
                case 3:
                    col.setHeaderValue("Haber");
                    break;

            }
            tColumnModel.addColumn(col);

        }
        tableBcomprobacion.setColumnModel(tColumnModel);
        tableBalanceAjustado.setColumnModel(tColumnModel);

    }

    public void consultaInicial() {
        // Conexion conexion = new Conexion();
        try {

            String setenciaSql = "SELECT p.codigo,cc.nombreCuenta, s.saldodeudor, s.saldoacredor FROM balancecomprobacion s \n"
                    + "JOIN cuenta p ON s.idcuenta = p.idcuenta\n"
                    + "JOIN catalogoCuenta cc ON p.codigo = cc.codigo;";

            Statement statement = conexion.conectar().createStatement();

            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {

                BalanceComprobacion balance = new BalanceComprobacion();

                balance.codigo = resultado.getInt("codigo");
                balance.nombreCuenta = resultado.getString("nombrecuenta");
                balance.saldodeudor = resultado.getDouble("saldodeudor");
                balance.saldoacredor = resultado.getDouble("saldoacredor");

                this.balanceTModel.balances.add(balance);

            }

            //   tablaTransacion.repaint();
            tableBcomprobacion.repaint();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base comprobacion");

            ex.printStackTrace();
        }

        try {

            String setenciaAjusteSql = "SELECT c.codigo AS codigo_cuenta, cc.nombrecuenta AS nombre_cuenta, abc.saldodeudor AS saldo_deudor_ajuste,\n"
                    + "    abc.saldoacredor AS saldo_acredor_ajuste FROM cuenta c LEFT JOIN catalogocuenta cc ON c.codigo = cc.codigo\n"
                    + "LEFT JOIN ajustebalancecomprobacion abc ON cc.codigo = abc.codigocuenta;\n";
            Statement statement = conexion.conectar().createStatement();

            ResultSet resultado1 = statement.executeQuery(setenciaAjusteSql);

            while (resultado1.next()) {

                AjusteBalanceComprobacion ajuste = new AjusteBalanceComprobacion();

                ajuste.codigo = resultado1.getInt("codigo_cuenta");
                ajuste.nombreCuenta = resultado1.getString("nombre_cuenta");
                ajuste.saldodeudor = resultado1.getDouble("saldo_deudor_ajuste");
                ajuste.saldoacredor = resultado1.getDouble("saldo_acredor_ajuste");
                this.balanceAjTModel.balancesAjus.add(ajuste);

            }
            tableBalanceAjustado.repaint();

        } catch (SQLException e) {

            System.out.println("Error al extrar los ajustes" + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tableBalanceCAjustado = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        BtnInicioBalanceC = new javax.swing.JButton();
        BtnInventarioBalanceC = new javax.swing.JButton();
        BtnTransaBalanceC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBcomprobacion = new javax.swing.JTable();
        btnAjuste = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableBalanceAjustado = new javax.swing.JTable();
        labelTotalDebe = new javax.swing.JLabel();
        labelTotalHaber = new javax.swing.JLabel();
        labelAjusteD = new javax.swing.JLabel();
        labelAjusteH = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        tableBalanceCAjustado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableBalanceCAjustado);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Balance de Comprobación");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnInicioBalanceC.setText("Inicio");
        BtnInicioBalanceC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioBalanceCActionPerformed(evt);
            }
        });
        jPanel1.add(BtnInicioBalanceC, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 100, 30));

        BtnInventarioBalanceC.setText("Inventario");
        BtnInventarioBalanceC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInventarioBalanceCActionPerformed(evt);
            }
        });
        jPanel1.add(BtnInventarioBalanceC, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 110, 30));

        BtnTransaBalanceC.setText("Transacciones");
        BtnTransaBalanceC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransaBalanceCActionPerformed(evt);
            }
        });
        jPanel1.add(BtnTransaBalanceC, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Balance de Comprobación");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 51, 152, -1));

        tableBcomprobacion.setModel(balanceTModel);
        tableBcomprobacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(tableBcomprobacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 85, 593, 146));

        btnAjuste.setText("Agregar ajuste");
        btnAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjusteActionPerformed(evt);
            }
        });
        jPanel1.add(btnAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 581, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Balance de comprobación ajustado");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 293, 196, 24));

        tableBalanceAjustado.setModel(balanceAjTModel);
        jScrollPane4.setViewportView(tableBalanceAjustado);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 335, 593, 210));

        labelTotalDebe.setText("Total Debe.");
        jPanel1.add(labelTotalDebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 237, 140, 22));

        labelTotalHaber.setText("Total Haber: ");
        jPanel1.add(labelTotalHaber, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 271, 140, 20));

        labelAjusteD.setText("Total Debe: ");
        jPanel1.add(labelAjusteD, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 553, 154, -1));

        labelAjusteH.setText("Total Haber: ");
        jPanel1.add(labelAjusteH, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 584, 154, -1));

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
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 140, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ajusteBalance() {

        //Conexion conexion = new Conexion();
        try {
            String setenciaSql = "SELECT c.codigo AS codigo_cuenta, cc.nombrecuenta AS nombre_cuenta, COALESCE(bc.saldoAcredor, 0) + COALESCE(ab.saldoacredor, 0) AS saldo_total_acredor,\n"
                    + "COALESCE(bc.saldoDeudor, 0) + COALESCE(ab.saldodeudor, 0) AS saldo_total_deudor FROM cuenta c LEFT JOIN catalogocuenta cc ON c.codigo = cc.codigo\n"
                    + "LEFT JOIN ( SELECT idcuenta, SUM(saldoAcredor) AS saldoAcredor, SUM(saldoDeudor) AS saldoDeudor FROM balanceComprobacion GROUP BY idcuenta\n"
                    + ") bc ON c.idcuenta = bc.idcuenta LEFT JOIN ( SELECT codigo, SUM(saldoacredor) AS saldoacredor, SUM(saldodeudor) AS saldodeudor FROM ajuste\n"
                    + "    GROUP BY codigo) ab ON c.codigo = ab.codigo;";

            Statement statement = conexion.conectar().createStatement();
            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {
                AjusteBalanceComprobacion ajustesBalance = new AjusteBalanceComprobacion();

                ajustesBalance.codigo = resultado.getInt("codigo_cuenta");
                ajustesBalance.nombreCuenta = resultado.getString("nombre_cuenta");
                if (resultado.getDouble("saldo_total_acredor") >= resultado.getDouble("saldo_total_deudor")) {

                    ajustesBalance.saldoacredor = resultado.getDouble("saldo_total_acredor") - resultado.getDouble("saldo_total_deudor");
                    ajustesBalance.deudor = false;
                } else {
                    ajustesBalance.saldodeudor = resultado.getDouble("saldo_total_deudor") - resultado.getDouble("saldo_total_acredor");
                    ajustesBalance.deudor = true;
                }

                listaAjustes.add(ajustesBalance);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Error al extraer los datos de la tabla cuenta " + e);
        }

        insertandoAjustes(listaAjustes);
        /* for (AjusteBalanceComprobacion ajuste1 : listaAjustes) {

            System.out.println("Codigo: " + ajuste1.codigo + " Saldo deudor: " + ajuste1.saldodeudor + " Saldo acredor: " + ajuste1.saldoacredor);

        }*/

    }

    private void cerrarConexion() {
        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
    }

    private void insertandoAjustes(ArrayList<AjusteBalanceComprobacion> listBalanceAjus) {

        double tDebe = 0, tHaber = 0;
        double costoV = 0;
        double gastoSobreC = 0;

        Conexion conexion = new Conexion();

        try {
            PreparedStatement statement = null;

            for (AjusteBalanceComprobacion ajuste : listBalanceAjus) {

                tDebe += ajuste.saldodeudor;
                tHaber += ajuste.saldoacredor;

                String sentenciaSql = "select * from ajustebalancecomprobacion where codigocuenta=?";
                statement = conexion.conectar().prepareStatement(sentenciaSql);
                statement.setInt(1, ajuste.codigo);
                ResultSet resultado = statement.executeQuery();

                if (resultado.next()) {

                    try {

                        String sentencia = "UPDATE ajustebalancecomprobacion SET saldoacredor= ?, saldodeudor=? WHERE codigocuenta=? ";

                        PreparedStatement preparedStatement = conexion.conectar().prepareCall(sentencia);

                        preparedStatement.setDouble(1, ajuste.saldoacredor);
                        preparedStatement.setDouble(2, ajuste.saldodeudor);
                        preparedStatement.setInt(3, ajuste.codigo);

                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {

                        JOptionPane.showMessageDialog(this, "Error al actualizar " + e);
                    }

                } else {

                    try {
                        String sentenciaIn = "INSERT INTO ajustebalancecomprobacion(saldodeudor, saldoacredor, codigocuenta) VALUES ( ?, ?, ?)";

                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaIn);

                        preparedStatement.setDouble(1, ajuste.saldodeudor);
                        preparedStatement.setDouble(2, ajuste.saldoacredor);
                        preparedStatement.setInt(3, ajuste.codigo);

                        preparedStatement.execute();

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error insertar los ajustes " + e);
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);

        }

        labelAjusteD.setText("Total Debe: $ " + tDebe);
        labelAjusteH.setText("Total Haber: $" + tHaber);

    }

    private void BtnInicioBalanceCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioBalanceCActionPerformed
        // TODO add your handling code here:
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
        cerrarConexion();
    }//GEN-LAST:event_BtnInicioBalanceCActionPerformed

    private void BtnInventarioBalanceCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInventarioBalanceCActionPerformed
        // TODO add your handling code here:
        InventarioCRUD inventario = new InventarioCRUD();
        inventario.setVisible(true);
        this.dispose();
        cerrarConexion();
    }//GEN-LAST:event_BtnInventarioBalanceCActionPerformed

    private void BtnTransaBalanceCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransaBalanceCActionPerformed
        // TODO add your handling code here:
        Transacciones transaccion = new Transacciones();
        transaccion.setVisible(true);
        this.dispose();
        cerrarConexion();
    }//GEN-LAST:event_BtnTransaBalanceCActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
        JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");


    }//GEN-LAST:event_formWindowClosing

    private void btnAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjusteActionPerformed
        // TODO add your handling code here:
        AjustesP pAjuste = new AjustesP();

        pAjuste.setVisible(true);
        this.dispose();
        cerrarConexion();
    }//GEN-LAST:event_btnAjusteActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        // Crea una nueva instancia de NuevoVentana
        Login login = new Login();
        login.setVisible(true);
        // Cierra la ventana actual
        this.dispose();
        /* try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }*/
        // JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");

    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(255, 102, 102));
        btnCerrar.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        // Regresa al color original fuera
        btnCerrar.setBackground(new Color(255, 0, 0));
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(BalanceComprobacionP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BalanceComprobacionP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BalanceComprobacionP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceComprobacionP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BalanceComprobacionP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicioBalanceC;
    private javax.swing.JButton BtnInventarioBalanceC;
    private javax.swing.JButton BtnTransaBalanceC;
    private javax.swing.JButton btnAjuste;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelAjusteD;
    private javax.swing.JLabel labelAjusteH;
    private javax.swing.JLabel labelTotalDebe;
    private javax.swing.JLabel labelTotalHaber;
    private javax.swing.JTable tableBalanceAjustado;
    private javax.swing.JTable tableBalanceCAjustado;
    private javax.swing.JTable tableBcomprobacion;
    // End of variables declaration//GEN-END:variables
}
