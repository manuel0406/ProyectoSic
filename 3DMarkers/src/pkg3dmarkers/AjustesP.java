/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3dmarkers;

import clases.Ajuste;
import clases.AjusteTableModel;
import clases.BalanceComprobacion;
import clases.CatalogoCuenta;
import clases.Transaccion;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
    double costV = 0, compras = 0, gastoCompras = 0, devCompras = 0, rcompras = 0, inventarioI = 0, inventarioF = 0;

    public AjustesP() {

        initComponents();
        habilitarControles(false);
        cbo();
        inicilizarColumnas();
        extrayendoBC();
        consultaIncial();
        txtidAjuste.setVisible(false);
        centrarVentanaEnPantalla();

    }
    Conexion conexion = new Conexion();

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
                if (resultado.getInt("codigo") == 113) {
                    inventarioF = resultado.getDouble("saldodeudor");
                }

                ajuste.idAjuste = resultado.getInt("idajuste");
                ajuste.codigo = resultado.getInt("codigo");
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
        TableColumn columna3 = tablaAjuste.getColumnModel().getColumn(2);
        columna3.setPreferredWidth(250);

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

    private void extrayendoBC() {
        ArrayList<BalanceComprobacion> listBalanceC = new ArrayList<BalanceComprobacion>();

        Conexion conexion = new Conexion();
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

                listBalanceC.add(balance);

            }

            for (BalanceComprobacion balanceComprobacion : listBalanceC) {
                System.out.println(balanceComprobacion.codigo + " " + balanceComprobacion.nombreCuenta);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los datos de la base comprobacion");

            ex.printStackTrace();
        }
        try {
            PreparedStatement statement = null;

            for (BalanceComprobacion balanceComprobacion : listBalanceC) {

                String setenciaSql = "Select * from ajuste where codigo=?";
                statement = this.conexion.conectar().prepareStatement(setenciaSql);
                statement.setInt(1, balanceComprobacion.codigo);
                ResultSet resultado = statement.executeQuery();

                if (resultado.next()) {
                    try {
                        String setencia = "UPDATE ajuste SET saldoacredor=? where codigo=?";
                        PreparedStatement preparedStatement = conexion.conectar().prepareCall(setencia);

                        preparedStatement.setDouble(1, balanceComprobacion.saldodeudor);
                        preparedStatement.setInt(2, balanceComprobacion.codigo);
                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e);

                    }
                } else {
                    try {
                        String sentenciaIn = " INSERT INTO ajuste(codigo,  saldoacredor) Values(?,  ?)";

                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaIn);
                        if (balanceComprobacion.codigo == 415
                                || balanceComprobacion.codigo == 412 || balanceComprobacion.codigo == 413 || balanceComprobacion.codigo == 414 || balanceComprobacion.codigo == 113) {
                            preparedStatement.setInt(1, balanceComprobacion.codigo);

                            preparedStatement.setDouble(2, balanceComprobacion.saldodeudor);
                            preparedStatement.execute();
                        }

                    } catch (SQLException e) {
                        System.out.println("Error al insertar: " + e);
                    }

                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        calcularCostoVenta();
    }

    // private int invetarioFinal(){}
    private void calcularCostoVenta() {
        ArrayList<Ajuste> listAjust = new ArrayList<Ajuste>();
        try {

            String setenciaSql = "SELECT s.idajuste, s.codigo, p.nombreCuenta, s.saldodeudor, s.saldoacredor FROM ajuste s JOIN catalogocuenta p ON s.codigo=p.codigo  ";

            Statement statement = conexion.conectar().createStatement();

            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {

                Ajuste ajuste = new Ajuste();

                ajuste.idAjuste = resultado.getInt("idajuste");
                ajuste.codigo = resultado.getInt("codigo");
                ajuste.cuenta = resultado.getString("nombrecuenta");
                ajuste.debe = resultado.getDouble("saldodeudor");
                ajuste.haber = resultado.getDouble("saldoacredor");
                listAjust.add(ajuste);
            }

            // tablaAjuste.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los ajustes de la base");

            ex.printStackTrace();
        }
        for (Ajuste ajuste : listAjust) {

            switch (ajuste.codigo) {
                case 412:
                    gastoCompras = ajuste.haber;
                    break;
                case 413:
                    devCompras = ajuste.haber;
                    break;
                case 414:
                    rcompras = ajuste.haber;
                    break;
                case 415:
                    compras = ajuste.haber;
                    break;
                case 113:
                    inventarioI = ajuste.haber;
                    inventarioF = ajuste.debe;
                    break;
            }

        }

        costV = compras + gastoCompras - (devCompras + rcompras) + inventarioI - inventarioF;
        System.out.println(costV);
        try {
            String setenciaSql = "Select * from ajuste where codigo=612  ";

            Statement statement = conexion.conectar().createStatement();
            ResultSet resultado = statement.executeQuery(setenciaSql);
            if (resultado.next()) {

                try {
                    String setencia = "UPDATE ajuste SET saldodeudor=? where codigo=612";
                    PreparedStatement preparedStatement = conexion.conectar().prepareCall(setencia);

                    preparedStatement.setDouble(1, costV);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("Error: " + e);

                }

            } else {
                try {
                    String setencia = "INSERT INTO ajuste(codigo, saldodeudor, saldoacredor) VALUES(612,?,?)";
                    PreparedStatement preparedStatement = conexion.conectar().prepareCall(setencia);

                    preparedStatement.setDouble(2, costV);
                    preparedStatement.setDouble(3, 0);
                    preparedStatement.execute();

                } catch (SQLException e) {
                    System.out.println("Error: " + e);

                }
            }

        } catch (SQLException e) {
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
        btnRegresar = new javax.swing.JButton();
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
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cboCuentaAjuste.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnInicioAjustes.setText("Inicio");
        BtnInicioAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioAjustesActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(BtnInicioAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 110, -1));

        BtnTransaccionAjustes.setText("Transacciones");
        BtnTransaccionAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransaccionAjustesActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(BtnTransaccionAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, -1, -1));

        BtnInventarioAjustes.setText("Inventario");
        BtnInventarioAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInventarioAjustesActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(BtnInventarioAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 110, -1));

        jLabel1.setText("Registros de los Ajustes");
        cboCuentaAjuste.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 78, 172, -1));

        tablaAjuste.setModel(ajusteTModel);
        tablaAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAjusteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAjuste);

        cboCuentaAjuste.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 107, 503, 257));

        btnNuevoAjuste.setBackground(new java.awt.Color(0, 102, 0));
        btnNuevoAjuste.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevoAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoAjuste.setText("Nuevo ajuste");
        btnNuevoAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAjusteActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnNuevoAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 110, -1));

        btnEliminarAjuste.setBackground(new java.awt.Color(170, 0, 0));
        btnEliminarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarAjuste.setText("Eliminar ajuste");
        btnEliminarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAjusteActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnEliminarAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, -1));

        btnRegresar.setBackground(new java.awt.Color(0, 89, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });
        cboCuentaAjuste.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ingresar nuevos ajuste");
        cboCuentaAjuste.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 179, -1));

        jLabel3.setText("Monto: ");
        cboCuentaAjuste.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 125, 104, -1));

        txtMontoAjuste.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoAjusteKeyTyped(evt);
            }
        });
        cboCuentaAjuste.add(txtMontoAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 122, 165, -1));

        jLabel5.setText("Cuenta afecta:");
        cboCuentaAjuste.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 187, 104, -1));

        cboCuentaAfectada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cboCuentaAjuste.add(cboCuentaAfectada, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 184, 165, -1));

        jLabel6.setText("Saldo:");
        cboCuentaAjuste.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 249, 104, -1));

        cboSaldoAjuste.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Debe", "Haber" }));
        cboCuentaAjuste.add(cboSaldoAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 246, 163, -1));

        btnGuardarAjuste.setBackground(new java.awt.Color(0, 102, 0));
        btnGuardarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarAjuste.setText("Guardar");
        btnGuardarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAjusteActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnGuardarAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 308, -1, -1));

        btnCancelarAjuste.setBackground(new java.awt.Color(170, 0, 0));
        btnCancelarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarAjuste.setText("Cancelar");
        btnCancelarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAjusteActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnCancelarAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(788, 308, -1, -1));

        jLabel8.setText("Hacer doble clic en un registros para editar");
        cboCuentaAjuste.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 382, -1, -1));

        btnEditarAjuste.setBackground(new java.awt.Color(51, 153, 255));
        btnEditarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarAjuste.setText("Actualizar registro");
        btnEditarAjuste.setBorderPainted(false);
        btnEditarAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAjusteActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnEditarAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 342, 166, -1));

        txtidAjuste.setEnabled(false);
        cboCuentaAjuste.add(txtidAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 52, 71, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cboCuentaAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cboCuentaAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioAjustesActionPerformed
        // TODO add your handling code here:

        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnInicioAjustesActionPerformed

    private void BtnTransaccionAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransaccionAjustesActionPerformed
        // TODO add your handling code here:
        Transacciones transaccion = new Transacciones();
        transaccion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnTransaccionAjustesActionPerformed

    private void BtnInventarioAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInventarioAjustesActionPerformed
        // TODO add your handling code here:
        InventarioCRUD inventario = new InventarioCRUD();
        inventario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnInventarioAjustesActionPerformed

    private void btnGuardarAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAjusteActionPerformed

        try {
            Ajuste ajuste = new Ajuste();
            String seleccion;

            seleccion = (String) cboCuentaAfectada.getSelectedItem();

            ajuste.codigo = buscandoSeleccion(seleccion);

            if (ajuste.codigo == 113) {
                try {
                    String setencia = "UPDATE ajuste SET saldodeudor=? where codigo=113";
                    PreparedStatement preparedStatement = conexion.conectar().prepareCall(setencia);

                    preparedStatement.setDouble(1, Double.valueOf(txtMontoAjuste.getText()));

                    preparedStatement.executeUpdate();
                    calcularCostoVenta();
                } catch (SQLException e) {
                    System.out.println("Error: " + e);

                }

            } else {
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
            }
            //transaccion.concepto = txtConceptoTransaccion.getText();

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
        codigo = buscandoSeleccion(seleccion);
        try {

            if (codigo == 113) {
                try {
                    String setencia = "UPDATE ajuste SET saldodeudor=? where codigo=113";
                    PreparedStatement preparedStatement = conexion.conectar().prepareCall(setencia);

                    preparedStatement.setDouble(1, Double.valueOf(txtMontoAjuste.getText()));

                    preparedStatement.executeUpdate();
                    calcularCostoVenta();
                    UpdateJTable();

                } catch (SQLException e) {
                    System.out.println("Error: " + e);

                }

            } else {
                String sentenciaSql = "UPDATE ajuste SET codigo = ?,  saldodeudor=?, saldoacredor=? WHERE idajuste= ?";

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

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar ajuste");
            e.printStackTrace();
        }
        // UpdateJTable();
        limpiar();
        habilitarControles(false);


    }//GEN-LAST:event_btnEditarAjusteActionPerformed

    private void txtMontoAjusteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAjusteKeyTyped

        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String text = txtMontoAjuste.getText();

        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.') || (text.length() >= 5 && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE)) {
            evt.consume();
        }

    }//GEN-LAST:event_txtMontoAjusteKeyTyped

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        // TODO add your handling code here:
        BalanceComprobacionP balance = new BalanceComprobacionP();
        balance.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarMouseClicked

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
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboCuentaAfectada;
    private javax.swing.JPanel cboCuentaAjuste;
    private javax.swing.JComboBox<String> cboSaldoAjuste;
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
