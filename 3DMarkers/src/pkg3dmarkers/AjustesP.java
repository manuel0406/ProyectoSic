/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3dmarkers;

import clases.Ajuste;
import clases.AjusteTableModel;
import clases.CatalogoCuenta;
import clases.Transaccion;
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
public class AjustesP extends javax.swing.JFrame {

    /**
     * Creates new form AjustesP
     */
    public AjusteTableModel ajusteTModel = new AjusteTableModel();
    Ajuste ajusteActual = new Ajuste();

    public AjustesP() {
        initComponents();
        habilitarControles(false);
        cbo();
        inicilizarColumnas();
        consultaIncial();
    }
    Conexion conexion = new Conexion();

    private void cbo() {
        try {
            Statement statement = conexion.conectar().createStatement();
            String setenciaCBO = "SELECT * FROM catalogocuenta";
            ResultSet resultadoCbo = statement.executeQuery(setenciaCBO);

            while (resultadoCbo.next()) {
                CatalogoCuenta cuenta = new CatalogoCuenta();

                cuenta.nombre = resultadoCbo.getString("nombrecuenta");
                cboCuentaAfectada.addItem(cuenta.nombre);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar las cuentas de la BD");

            ex.printStackTrace();
        }

    }

    private void inicilizarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();

        for (int i = 0; i < 5; i++) {

            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("ID");
                    break;

                case 1:
                    col.setHeaderValue("Codigo");

                    break;
                case 2:
                    col.setHeaderValue("Cuenta");
                    break;
                case 3:
                    col.setHeaderValue("Debe");
                    break;
                case 4:
                    col.setHeaderValue("Haber");
                    break;

            }
            tColumnModel.addColumn(col);

        }

        tablaAjuste.setColumnModel(tColumnModel);
    }

    private int buscandoSeleccion(String seleccion) {

        int codigo = 0;

        try {

            Statement statement = this.conexion.conectar().createStatement();

            String setenciaCBO = "SELECT * FROM catalogocuenta Where nombrecuenta = " + "'" + seleccion + "'";

            //  System.out.println(seleccion + " ya en el metodo");
            ResultSet resultadoCbo = statement.executeQuery(setenciaCBO);

            if (resultadoCbo.next()) {
                codigo = resultadoCbo.getInt("codigo");
                System.out.println(codigo);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Error en la seleccion");
        }
        return codigo;

    }

    private void consultaIncial() {

        Conexion conexion = new Conexion();
        try {

            String setenciaSql = "SELECT s.idajuste, s.codigo, p.nombreCuenta, s.saldodeudor, s.saldoacredor FROM ajuste s JOIN catalogocuenta p ON s.codigo=p.codigo  ";

            Statement statement = conexion.conectar().createStatement();

            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {

                Ajuste ajuste = new Ajuste();

                ajuste.idAjuste = resultado.getInt("idajuste");
                ajuste.codigo = resultado.getInt("codigo");
                // aj.concepto = resultado.getString("concepto");
                ajuste.cuenta = resultado.getString("nombrecuenta");
                ajuste.debe = resultado.getDouble("saldodeudor");
                ajuste.haber = resultado.getDouble("saldoacredor");

                this.ajusteTModel.ajustes.add(ajuste);

            }

            tablaAjuste.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los ajustes de la base");

            ex.printStackTrace();
        }

        cbo();

    }

    private void UpdateJTable() {

        ajusteTModel.ajustes.clear();

        try {
            PreparedStatement statement = null;
            String setenciaSql = "SELECT s.idajuste, s.codigo, p.nombreCuenta, s.saldodeudor, s.saldoacredor FROM ajuste s JOIN catalogocuenta p ON s.codigo=p.codigo   ";
            statement = this.conexion.conectar().prepareStatement(setenciaSql);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Ajuste ajuste = new Ajuste();

                ajuste.idAjuste = resultado.getInt("idajuste");
                ajuste.codigo = resultado.getInt("codigo");
                ajuste.cuenta = resultado.getString("nombrecuenta");
                ajuste.debe = resultado.getDouble("saldodeudor");
                ajuste.haber = resultado.getDouble("saldoacredor");

                this.ajusteTModel.ajustes.add(ajuste);

            }

            tablaAjuste.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar las transaciones de la base de datos");

        }

    }

    private void limpiar() {

        //  txtConceptoTransaccion.setText(" ");
        txtMontoAjuste.setText(" ");
        txtidAjuste.setText(" ");
        cboCuentaAfectada.setSelectedIndex(0);
        cboSaldoAjuste.setSelectedIndex(0);

    }

    public void habilitarControles(boolean f) {

        // txtConceptoTransaccion.setEnabled(f);
        // txtidTransaccionTra.setEnabled(f);
        txtMontoAjuste.setEnabled(f);
        cboCuentaAfectada.setEnabled(f);
        cboSaldoAjuste.setEnabled(f);
        btnEditarAjuste.setEnabled(f);
        btnCancelarAjuste.setEnabled(f);
        btnEditarAjuste.setEnabled(f);
        btnGuardarAjuste.setEnabled(f);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboCuentaAjuste = new javax.swing.JPanel();
        BtnInicioAjustes = new javax.swing.JButton();
        BtnTransaccionAjustes = new javax.swing.JButton();
        BtnInventarioAjustes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAjuste = new javax.swing.JTable();
        btnNuevoAjuste = new javax.swing.JButton();
        btnEliminarAjuste = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMontoAjuste = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboCuentaAfectada = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboSaldoAjuste = new javax.swing.JComboBox<>();
        btnGuardarAjuste = new javax.swing.JButton();
        btnCancelarAjuste = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnEditarAjuste = new javax.swing.JButton();
        txtidAjuste = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajustes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        BtnInicioAjustes.setText("Inicio");
        BtnInicioAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioAjustesActionPerformed(evt);
            }
        });

        BtnTransaccionAjustes.setText("Transacciones");
        BtnTransaccionAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransaccionAjustesActionPerformed(evt);
            }
        });

        BtnInventarioAjustes.setText("Inventario");
        BtnInventarioAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInventarioAjustesActionPerformed(evt);
            }
        });

        jLabel1.setText("Registros de los Ajustes");

        tablaAjuste.setModel(ajusteTModel);
        tablaAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAjusteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAjuste);

        btnNuevoAjuste.setText("Nuevo ajuste");
        btnNuevoAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAjusteActionPerformed(evt);
            }
        });

        btnEliminarAjuste.setText("Eliminar ajuste");
        btnEliminarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAjusteActionPerformed(evt);
            }
        });

        jButton3.setText("Regresar");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ingresar nuevos ajuste");

        jLabel3.setText("Monto: ");

        jLabel5.setText("Cuenta afecta:");

        cboCuentaAfectada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        jLabel6.setText("Saldo:");

        cboSaldoAjuste.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Debe", "Haber" }));

        btnGuardarAjuste.setText("Guardar");
        btnGuardarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAjusteActionPerformed(evt);
            }
        });

        btnCancelarAjuste.setText("Cancelar");
        btnCancelarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAjusteActionPerformed(evt);
            }
        });

        jLabel8.setText("Hacer doble clic en un registros para editar");

        btnEditarAjuste.setText("Actualizar registro");
        btnEditarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAjusteActionPerformed(evt);
            }
        });

        txtidAjuste.setEnabled(false);

        javax.swing.GroupLayout cboCuentaAjusteLayout = new javax.swing.GroupLayout(cboCuentaAjuste);
        cboCuentaAjuste.setLayout(cboCuentaAjusteLayout);
        cboCuentaAjusteLayout.setHorizontalGroup(
            cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                .addComponent(btnNuevoAjuste)
                                .addGap(33, 33, 33)
                                .addComponent(btnEliminarAjuste)
                                .addGap(33, 33, 33)
                                .addComponent(jButton3)))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboCuentaAfectada, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(16, 16, 16)
                                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMontoAjuste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboSaldoAjuste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtidAjuste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditarAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                        .addComponent(btnGuardarAjuste)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancelarAjuste))))))
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(BtnInicioAjustes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnTransaccionAjustes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnInventarioAjustes)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        cboCuentaAjusteLayout.setVerticalGroup(
            cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnInicioAjustes)
                    .addComponent(BtnTransaccionAjustes)
                    .addComponent(BtnInventarioAjustes))
                .addGap(30, 30, 30)
                .addComponent(txtidAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMontoAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboCuentaAfectada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboSaldoAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarAjuste)
                            .addComponent(btnCancelarAjuste))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarAjuste)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarAjuste)
                    .addComponent(jButton3)
                    .addComponent(btnNuevoAjuste))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cboCuentaAjuste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboCuentaAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioAjustesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnInicioAjustesActionPerformed

    private void BtnTransaccionAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransaccionAjustesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnTransaccionAjustesActionPerformed

    private void BtnInventarioAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInventarioAjustesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnInventarioAjustesActionPerformed

    private void btnGuardarAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAjusteActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {

            Transaccion transaccion = new Transaccion();
            Ajuste ajuste = new Ajuste();
            String seleccion;

            seleccion = (String) cboCuentaAfectada.getSelectedItem();

            System.out.println(seleccion);

            ajuste.codigo = buscandoSeleccion(seleccion);

            //transaccion.concepto = txtConceptoTransaccion.getText();
            if (cboSaldoAjuste.getSelectedItem() == "Debe") {
                ajuste.debe = Double.parseDouble(txtMontoAjuste.getText());
                ajuste.haber = 0.00;
            } else {
                ajuste.haber = Double.parseDouble(txtMontoAjuste.getText());
                ajuste.debe = 0.00;
            }

            String setenciaSql = "INSERT INTO ajuste(codigo, saldodeudor, saldoacredor) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
            preparedStatement.setInt(1, ajuste.codigo);
            preparedStatement.setDouble(2, ajuste.debe);
            preparedStatement.setDouble(3, ajuste.haber);
            preparedStatement.execute();

            ajusteTModel.ajustes.add(ajuste);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos");
            e.printStackTrace();
        }
        UpdateJTable();

        limpiar();
        habilitarControles(false);
        //totalizacion();
        AjusteTableModel model = (AjusteTableModel) tablaAjuste.getModel();
        model.fireTableDataChanged();

    }//GEN-LAST:event_btnGuardarAjusteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
        JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");

    }//GEN-LAST:event_formWindowClosing

    private void btnEliminarAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAjusteActionPerformed
        // TODO add your handling code here:
        // habilitarControles(false);
        // indices de las filas seleccionadas
        int[] indices = tablaAjuste.getSelectedRows();
        ArrayList<Ajuste> aEliminar = new ArrayList<Ajuste>();

        for (int i : indices) {
            Ajuste ajuste = ajusteTModel.ajustes.get(i);
            // Transaccion transaccion = ajusteTModel.ajustes.get(i);
            String setenciaSql = "DELETE FROM ajuste Where idajuste= ?";
            aEliminar.add(ajuste);
            try {
                PreparedStatement prepStat = conexion.conectar().prepareStatement(setenciaSql);
                prepStat.setInt(1, ajuste.idAjuste);
                prepStat.executeUpdate();
                JOptionPane.showMessageDialog(this, "Elimino correctamente " + ajuste.idAjuste);
                UpdateJTable();

            } catch (SQLException ex) {
                Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        tablaAjuste.repaint();
    }//GEN-LAST:event_btnEliminarAjusteActionPerformed

    private void btnNuevoAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAjusteActionPerformed
        // TODO add your handling code here:

        habilitarControles(true);
    }//GEN-LAST:event_btnNuevoAjusteActionPerformed

    private void btnCancelarAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAjusteActionPerformed
        // TODO add your handling code here:
        limpiar();
        habilitarControles(false);
    }//GEN-LAST:event_btnCancelarAjusteActionPerformed

    private void tablaAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAjusteMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        habilitarControles(false);
        int clics = evt.getClickCount();
        int row = tablaAjuste.rowAtPoint(evt.getPoint());

        if (clics == 2) {
            Ajuste t = ajusteTModel.ajustes.get(row);
            ajusteActual = t;

            txtidAjuste.setText(String.valueOf(t.idAjuste));
            //  txtConceptoTransaccion.setText(t.concepto);
            habilitarControles(true);

            if (t.debe > 0) {

                txtMontoAjuste.setText(String.valueOf(t.debe));
            } else {
                txtMontoAjuste.setText(String.valueOf(t.haber));

            }

        }

        btnGuardarAjuste.setEnabled(false);
    }//GEN-LAST:event_tablaAjusteMouseClicked

    private void btnEditarAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAjusteActionPerformed
        // TODO add your handling code here:
        String seleccion = (String) cboCuentaAfectada.getSelectedItem();
        int codigo = 0;

        try {
            String sentenciaSql = "UPDATE ajuste SET codigo = ?,  saldodeudor=?, saldoacredor=? WHERE idajuste= ?";

            codigo = buscandoSeleccion(seleccion);
            System.out.println("probando codigo desde btn +" + codigo);
            PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, codigo);
           

            if (cboSaldoAjuste.getSelectedItem() == "Debe") {

                preparedStatement.setDouble(2, Double.parseDouble(txtMontoAjuste.getText()));
                preparedStatement.setDouble(3, 0.00);
            } else {
                preparedStatement.setDouble(2, 0.00);
                preparedStatement.setDouble(3, Double.parseDouble(txtMontoAjuste.getText()));
            }

            preparedStatement.setInt(4, Integer.valueOf(txtidAjuste.getText()));
            preparedStatement.executeUpdate();

            UpdateJTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar ajuste");
            e.printStackTrace();
        }
        // UpdateJTable();
        limpiar();
        habilitarControles(false);


    }//GEN-LAST:event_btnEditarAjusteActionPerformed

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
            java.util.logging.Logger.getLogger(AjustesP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjustesP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjustesP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjustesP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjustesP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicioAjustes;
    private javax.swing.JButton BtnInventarioAjustes;
    private javax.swing.JButton BtnTransaccionAjustes;
    private javax.swing.JButton btnCancelarAjuste;
    private javax.swing.JButton btnEditarAjuste;
    private javax.swing.JButton btnEliminarAjuste;
    private javax.swing.JButton btnGuardarAjuste;
    private javax.swing.JButton btnNuevoAjuste;
    private javax.swing.JComboBox<String> cboCuentaAfectada;
    private javax.swing.JPanel cboCuentaAjuste;
    private javax.swing.JComboBox<String> cboSaldoAjuste;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAjuste;
    private javax.swing.JTextField txtMontoAjuste;
    private javax.swing.JTextField txtidAjuste;
    // End of variables declaration//GEN-END:variables
}
